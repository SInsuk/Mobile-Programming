package com.example.p_test01;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class activity_game extends AppCompatActivity {

    private static final String TAG = "activity_game";
    private TextView tvTimer, tvWord;
    private EditText etInput;
    private Button btnNext;
    private DBUser dbHelper;
    private String currentWord;
    private int score = 0;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tvTimer = findViewById(R.id.tvTimer);
        tvWord = findViewById(R.id.tvWord);
        etInput = findViewById(R.id.etInput);
        btnNext = findViewById(R.id.btnNext);

        dbHelper = new DBUser(this);

        startGame();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputWord = etInput.getText().toString().trim();

                if (isValidWord(inputWord)) {
                    currentWord = inputWord;
                    tvWord.setText(currentWord);
                    etInput.setText("");
                    score++;
                } else {
                    Toast.makeText(activity_game.this, "유효하지 않은 단어입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void startGame() {
        currentWord = getRandomWord();
        if (currentWord == null) {
            Toast.makeText(this, "단어를 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        tvWord.setText(currentWord);

        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText(String.format(Locale.getDefault(), "%d초", millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                gameOver();
            }
        }.start();
    }

    private void gameOver() {
        Intent intent = new Intent(activity_game.this, activity_game_over.class);
        intent.putExtra("score", score);
        startActivity(intent);
        finish();
    }

    private String getRandomWord() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT word FROM words ORDER BY RANDOM() LIMIT 1", null);
        if (cursor != null && cursor.moveToFirst()) {
            String word = cursor.getString(0);
            cursor.close();
            return word;
        }
        return null;
    }

    private boolean isValidWord(String word) {
        if (word.isEmpty()) return false;

        // 끝말잇기 규칙 확인: 현재 단어의 마지막 글자와 입력 단어의 첫 글자가 일치하는지 확인
        char lastCharOfCurrentWord = currentWord.charAt(currentWord.length() -1);
        char firstCharOfInputWord = word.charAt(0);
        Log.d(TAG, "현재 단어의 마지막 글자: " + lastCharOfCurrentWord);
        Log.d(TAG, "입력된 단어의 첫 글자: " + firstCharOfInputWord);

        if (lastCharOfCurrentWord != firstCharOfInputWord) {
            Log.d(TAG, "끝말잇기 규칙에 맞지 않습니다.");
            return false;
        }

        // 데이터베이스에 단어가 존재하는지 확인
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                "words",
                new String[]{"id"},
                "word=?",
                new String[]{word},
                null, null, null
        );

        boolean exists = cursor != null && cursor.moveToFirst();
        if (cursor != null) {
            cursor.close();
        }

        Log.d(TAG, "단어 존재 여부: " + exists);
        return exists;
    }
}
