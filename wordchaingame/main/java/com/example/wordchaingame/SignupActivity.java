package com.example.wordchaingame;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText etName, etId, etPw, etPwConfirm;
    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        db = new DatabaseHelper(this);
        etName = findViewById(R.id.etName);
        etId = findViewById(R.id.etId);
        etPw = findViewById(R.id.etPw);
        etPwConfirm = findViewById(R.id.etPwConfirm);
        btnSignup = findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String id = etId.getText().toString();
                String password = etPw.getText().toString();
                String passwordConfirm = etPwConfirm.getText().toString();

                if (name.isEmpty() || id.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "모든 필드를 채워주세요.", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(passwordConfirm)) {
                    Toast.makeText(SignupActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    if (db.checkUserExists(id)) {
                        Toast.makeText(SignupActivity.this, "이미 존재하는 ID입니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        ContentValues values = new ContentValues();
                        values.put(DatabaseHelper.COL_NAME, name);
                        values.put(DatabaseHelper.COL_ID, id);
                        values.put(DatabaseHelper.COL_PASSWORD, password);
                        db.getWritableDatabase().insert(DatabaseHelper.TABLE_USERS, null, values);
                        Toast.makeText(SignupActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                        finish();
                    }
                }
            }
        });
    }
}
