package com.example.framelayout_test;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView tv1, tv2, tv3;
    LinearLayout linearLayout;

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
        tv1 = (TextView) findViewById(R.id.view1);
        tv2 = (TextView) findViewById(R.id.view2);
        tv3 = (TextView) findViewById(R.id.view3);

        linearLayout=(LinearLayout) findViewById(R.id.main);

    }

    public void onClick(View view){
        tv1.setVisibility(View.INVISIBLE);
        tv2.setVisibility(View.INVISIBLE);
        tv3.setVisibility(View.INVISIBLE);

        switch (view.getId()){
            case R.id.button:
                tv1.setVisibility(View.VISIBLE);
                break;
            case R.id.button2:
                tv2.setVisibility(View.VISIBLE);
                break;
            case R.id.button3:
                tv3.setVisibility(View.VISIBLE);
                break;
        }
    }
}