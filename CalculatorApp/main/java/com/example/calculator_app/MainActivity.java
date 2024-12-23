package com.example.calculator_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText eText1;
    EditText eText2;
    TextView Textview1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button bPlus = (Button) findViewById(R.id.button);
        eText1 = (EditText) findViewById(R.id.edit1);
        eText2 = (EditText) findViewById(R.id.edit2);
        Textview1 = (TextView) findViewById(R.id.textView5);
    }

    public void cal_plus(View e){
        String s1 = eText1.getText().toString();
        String s2 = eText2.getText().toString();

        int result = Integer.parseInt(s1) + Integer.parseInt(s2);
        Textview1.setText("" + result);
    }

    public void cal_minus(View e){
        String s1 = eText1.getText().toString();
        String s2 = eText2.getText().toString();

        int result = Integer.parseInt(s1) - Integer.parseInt(s2);
        Textview1.setText("" + result);
    }

    public void cal_mul(View e){
        String s1 = eText1.getText().toString();
        String s2 = eText2.getText().toString();

        int result = Integer.parseInt(s1) * Integer.parseInt(s2);
        Textview1.setText("" + result);
    }

    public void cal_div(View e){
        String s1 = eText1.getText().toString();
        String s2 = eText2.getText().toString();

        Double result = Double.parseDouble(s1) / Double.parseDouble(s2);
        Textview1.setText("" + String.format("%.3f", result));
    }

    public void cal_mod(View e){
        String s1 = eText1.getText().toString();
        String s2 = eText2.getText().toString();

        int result = Integer.parseInt(s1) % Integer.parseInt(s2);
        Textview1.setText("" + result);
    }
}