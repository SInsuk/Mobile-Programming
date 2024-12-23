package com.example.test3_10;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView image_answer;
    private EditText editText;
    private Button button_start;
    private Button button_answer;
    private int answer;
    private TextView textView;
    Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        image_answer = findViewById(R.id.imageView2);
        editText = findViewById(R.id.edit1);
        textView = findViewById(R.id.textView2);
    }

    public void  start (View view){
        answer = random.nextInt(100) + 1;
        textView.setText("정수를 입력하세요");
    }

    public void  answer (View e) {
        String input = editText.getText().toString();
        if (!input.isEmpty()) {

            int guess = Integer.parseInt(input);
            if (guess == answer) {
                image_answer.setVisibility(View.VISIBLE);
                textView.setText("정답입니다");
            } else if (guess < answer) {
                textView.setText("업");
            } else {
                textView.setText("다운");
            }

        }
    }
}