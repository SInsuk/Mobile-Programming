package com.example.wordchaingame;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WordActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private WordAdapter wordAdapter;
    private RecyclerView recyclerViewWords;
    private EditText etWord;
    private Button btnAddWord, btndelWord, btnUser, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        db = new DatabaseHelper(this);
        recyclerViewWords = findViewById(R.id.recyclerViewWords);
        etWord = findViewById(R.id.etWord);
        btnAddWord = findViewById(R.id.btnAddWord);
        btndelWord = findViewById(R.id.btndelWord);
        btnUser = findViewById(R.id.btnUser);
        btnLogout = findViewById(R.id.btnLogout);

        recyclerViewWords.setLayoutManager(new LinearLayoutManager(this));
        wordAdapter = new WordAdapter(db.getAllWordsCursor());
        recyclerViewWords.setAdapter(wordAdapter);

        btnAddWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = etWord.getText().toString().trim();
                if (word.isEmpty()) {
                    Toast.makeText(WordActivity.this, "단어를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (db.checkWord(word)) {
                    Toast.makeText(WordActivity.this, "이미 존재하는 단어입니다.", Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues values = new ContentValues();
                    values.put(DatabaseHelper.COL_WORD, word);
                    db.getWritableDatabase().insert(DatabaseHelper.TABLE_WORDS, null, values);
                    Toast.makeText(WordActivity.this, "단어가 추가되었습니다.", Toast.LENGTH_SHORT).show();

                    // RecyclerView 업데이트
                    wordAdapter.swapCursor(db.getAllWordsCursor());
                    etWord.setText("");
                }
            }
        });

        btndelWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = etWord.getText().toString().trim();
                if (word.isEmpty()) {
                    Toast.makeText(WordActivity.this, "삭제할 단어를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!db.checkWord(word)) {
                    Toast.makeText(WordActivity.this, "존재하지 않는 단어입니다.", Toast.LENGTH_SHORT).show();
                } else {
                    db.deleteWord(word);
                    Toast.makeText(WordActivity.this, "단어가 삭제되었습니다.", Toast.LENGTH_SHORT).show();

                    // RecyclerView 업데이트
                    wordAdapter.swapCursor(db.getAllWordsCursor());
                    etWord.setText("");
                }
            }
        });

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WordActivity.this, MemberListActivity.class);
                // 관리자인지 여부를 인텐트로 전달
                intent.putExtra("isAdmin", true);
                startActivity(intent);
            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    private void logout() {
        // Clear session and navigate to login screen
        // For simplicity, show a Toast and navigate to LoginActivity
        Toast.makeText(WordActivity.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(WordActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
