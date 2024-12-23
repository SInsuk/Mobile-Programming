package com.example.p_test01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button startGameButton;
    private Button checkRankButton;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGameButton = findViewById(R.id.btnStartGame);
        checkRankButton = findViewById(R.id.btnCheckRank);
        loginButton = findViewById(R.id.btnLogin);

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_game.class);
                startActivity(intent);
            }
        });

        checkRankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add intent to check rank activity if exists
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, loginActivity.class);
                startActivity(intent);
            }
        });
    }
}