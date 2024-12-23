package com.example.mid_202095049;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    Integer[] chbIds = {R.id.chb1, R.id.chb2, R.id.chb3, R.id.chb4, R.id.chb5, R.id.chb6};
    String[] food = {"김밥", "라면", "떡볶이", "피자", "햄버거", "파스타"};
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

    }

    public void reset(View view){
        for(i=0; i<chbIds.length; i++){

        }

    }
}