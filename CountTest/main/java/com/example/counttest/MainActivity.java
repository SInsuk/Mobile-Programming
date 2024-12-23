package com.example.counttest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView textview;
    int num=0;
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

        Button bplus = (Button) findViewById(R.id.button3);
        Button bminus = (Button) findViewById(R.id.button4);
        textview = (TextView) findViewById(R.id.textView2);

    }

    public void  count_plus(View e){
        num = num + 1;
        textview.setText("카운트: " + num);
    }

    public void  count_minus(View e){
        num = num - 1;
        textview.setText("카운트: " + num);
    }

    public void count_reset(View e){
        num=0;
        textview.setText("카운트: " + num);
    }
}