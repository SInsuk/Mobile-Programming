package com.example.p_test01;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activity_game_over extends AppCompatActivity {

    private TextView tvScore, tvHighScore;
    private Button btnRetry;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        tvScore = findViewById(R.id.tvScore);
        tvHighScore = findViewById(R.id.tvHighScore);
        btnRetry = findViewById(R.id.btnRetry);

        score = getIntent().getIntExtra("score", 0);
        tvScore.setText("점수: " + score);

        // 높은 점수 저장 및 표시 (이 기능은 추후에 구현 가능)
        tvHighScore.setText("최고 점수: " + getHighScore());

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_game_over.this, activity_game.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private int getHighScore() {
        // 높은 점수 가져오는 로직 (추후 구현 가능)
        return score;
    }
}