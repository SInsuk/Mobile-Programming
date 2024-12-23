package com.example.wordchaingame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity {
    private String userId;
    private int score;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        // 이전 액티비티로부터 userId와 score 받기
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        score = intent.getIntExtra("score", 0);

        // 데이터베이스 초기화
        databaseHelper = new DatabaseHelper(this);

        // 최고 점수 업데이트
        databaseHelper.updateHighScore(userId, score);

        // 현재 점수와 최고 점수를 화면에 표시
        TextView scoreTextView = findViewById(R.id.tvScore);
        TextView highScoreTextView = findViewById(R.id.tvHighScore);
        scoreTextView.setText("점수: " + score);

        int highScore = databaseHelper.getHighScore(userId);
        highScoreTextView.setText("최고 점수: " + highScore);

        // 버튼 초기화
        Button btnRetry = findViewById(R.id.btnRetry);
        Button btnMain = findViewById(R.id.btnMain);

        // 다시 하기 버튼 클릭 리스너
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 다시 하기 버튼 클릭 시 게임 액티비티로 이동
                Intent retryIntent = new Intent(GameOverActivity.this, GameActivity.class);
                retryIntent.putExtra("userId", userId);
                startActivity(retryIntent);
                finish();
            }
        });

        // 메인으로 버튼 클릭 리스너
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 메인으로 버튼 클릭 시 메인 액티비티로 이동
                Intent mainIntent = new Intent(GameOverActivity.this, MainActivity.class);
                mainIntent.putExtra("userId", userId); // 메인 액티비티로 userId를 전달
                startActivity(mainIntent);
                finish();
            }
        });
    }
}
