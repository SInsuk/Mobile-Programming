package com.example.final_term_project;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ThirdActivity extends AppCompatActivity {
    EditText edit;
    TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);

        edit = (EditText) findViewById(R.id.editTextText);
        text = (TextView) findViewById(R.id.textView5);
        Button readbtn = findViewById(R.id.button7);
        readbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    FileInputStream fis = openFileInput(String.valueOf(edit.getText()));
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    text.setText(new String(buffer));
                    fis.close();
                }catch (IOException e){
                }
            }
        });

        Button writebtn = findViewById(R.id.button8);
        writebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    FileOutputStream fos = openFileOutput(String.valueOf(edit.getText()), Context.MODE_PRIVATE);
                    fos.write(text.getText().toString().getBytes());
                    fos.close();
                }catch (IOException e){
                }
            }
        });
    }
}