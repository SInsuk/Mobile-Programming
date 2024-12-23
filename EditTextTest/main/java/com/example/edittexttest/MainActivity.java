package com.example.edittexttest;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUserName;
    private EditText editTextPassword;
    private EditText editTextPhoneNumber;
    private TextView textViewUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        textViewUserInfo = findViewById(R.id.textViewUserInfo);
        }
        public void onSignupButtonClick(View view){
        String username = editTextUserName.getText().toString();
        String password = editTextPassword.getText().toString();
        String phoneNumber = editTextPhoneNumber.getText().toString();

        //입력된 정보를 화면 하단에 출력
        String userInfo = "아이디: " + username + "\n패스워드: " + password + "\n전화번호: " + phoneNumber;
        textViewUserInfo.setText(userInfo);
    }
}