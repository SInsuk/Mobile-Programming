package com.example.wordchaingame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etId;
    private EditText etPw;
    private Button btnLogin;
    private Button btnSignup;  // 회원가입 버튼
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etId = findViewById(R.id.etId);
        etPw = findViewById(R.id.etPw);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);  // 회원가입 버튼 초기화
        db = new DatabaseHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString();
                String password = etPw.getText().toString();

                if (id.equals("1111") && password.equals("1111")) {
                    // 관리자 모드로 이동
                    Intent intent = new Intent(LoginActivity.this, WordActivity.class);
                    startActivity(intent);
                } else if (db.checkUser(id, password)) {
                    String userId = db.getUserId(id);

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("userId", userId);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {  // 회원가입 버튼 클릭 리스너 추가
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
