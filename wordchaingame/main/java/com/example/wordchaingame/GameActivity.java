package com.example.wordchaingame;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView tvWord, tvTimer;
    private EditText etInput;
    private Button btnNext;
    private CountDownTimer timer;
    private DatabaseHelper db;
    private RecyclerView recyclerView;
    private int wordCount = 0;  // Initialize word counter
    private List<String> usedWords;
    private HashSet<String> wordSet;
    private RecyclerViewAdapter recyclerViewAdapter;

    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tvWord = findViewById(R.id.tvWord);
        tvTimer = findViewById(R.id.tvTimer);
        etInput = findViewById(R.id.etInput);
        btnNext = findViewById(R.id.btnNext);
        // 이전 액티비티로부터 userId 받기
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");

        db = new DatabaseHelper(this);

        recyclerView = findViewById(R.id.wordview);
        usedWords = new ArrayList<>();
        wordSet = new HashSet<>();

        recyclerViewAdapter = new RecyclerViewAdapter(usedWords); // RecyclerViewAdapter 초기화
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

        // 임의의 단어를 데이터베이스에서 가져오기
        String[] words = db.getAllWords();
        if (words.length > 0) {
            String randomWord = words[new Random().nextInt(words.length)];
            tvWord.setText(randomWord);
        } else {
            Toast.makeText(this, "단어를 가져올 수 없습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }

        // 타이머 설정
        timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText(millisUntilFinished / 1000 + "초");
            }

            @Override
            public void onFinish() {
                endGame();
            }
        };

        timer.start();

        btnNext.setOnClickListener(view -> {
            String inputWord = etInput.getText().toString().trim();
            String currentWord = tvWord.getText().toString().trim();

            if (inputWord.isEmpty()) {
                Toast.makeText(GameActivity.this, "단어를 입력하세요.", Toast.LENGTH_SHORT).show();
                return;
            } else if (wordSet.contains(inputWord)) {
                Toast.makeText(GameActivity.this, "이미 사용된 단어입니다", Toast.LENGTH_SHORT).show();
                return;
            }

            // 입력 단어의 첫 글자와 현재 단어의 마지막 글자가 일치하는지 확인
            if (inputWord.charAt(0) == currentWord.charAt(currentWord.length() - 1)) {
                if (db.checkWord(inputWord)) {
                    tvWord.setText(inputWord);
                    etInput.setText("");
                    wordCount++;  // Increment word counter
                    usedWords.add(inputWord);
                    wordSet.add(inputWord);
                    recyclerViewAdapter.notifyItemInserted(usedWords.size() - 1); // RecyclerViewAdapter 사용
                } else {
                    Toast.makeText(GameActivity.this, "유효하지 않은 단어입니다.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(GameActivity.this, "올바른 단어가 아닙니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void endGame() {
        Intent intent = new Intent(GameActivity.this, GameOverActivity.class);
        intent.putExtra("userId", userId);
        intent.putExtra("score", wordCount);
        startActivity(intent);
        finish(); // End the GameActivity
    }
}
