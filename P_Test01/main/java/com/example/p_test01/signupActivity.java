package com.example.p_test01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class signupActivity extends AppCompatActivity {
    private EditText etName, etId, etPw, etPwConfirm;
    private Button btnSignup;
    private DBUser dbUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        dbUser = new DBUser(this);

        etName = findViewById(R.id.etName);
        etId = findViewById(R.id.etId);
        etPw = findViewById(R.id.etPw);
        etPwConfirm = findViewById(R.id.etPwConfirm);
        btnSignup = findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String id = etId.getText().toString().trim();
                String password = etPw.getText().toString().trim();
                String passwordConfirm = etPwConfirm.getText().toString().trim();

                if (!password.equals(passwordConfirm)) {
                    Toast.makeText(signupActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (name.isEmpty() || id.isEmpty() || password.isEmpty()) {
                    Toast.makeText(signupActivity.this, "모든 필드를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbUser.addUser(id, name, password);
                Toast.makeText(signupActivity.this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}