package com.example.wordchaingame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnStartGame, btnCheckRank, btnLogin;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 이전 액티비티로부터 userId 받기
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");

        btnStartGame = findViewById(R.id.btnStartGame);
        btnCheckRank = findViewById(R.id.btnCheckRank);
        btnLogin = findViewById(R.id.btnLogin);

        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

        btnCheckRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RankingList.class);
                intent.putExtra("userId", userId);  // 로그인한 사용자 ID를 전달
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
