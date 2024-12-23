package com.example.mid_202095049;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdMainActivity extends AppCompatActivity {
    private  ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third_main);

        imageView = (ImageView) findViewById(R.id.imageView2);
    }

    public void onRadio(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.radioButton:
                if(checked)
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageResource(R.drawable.dog);
                break;
            case R.id.radioButton2:
                if(checked)
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageResource(R.drawable.cat);
                break;
            case R.id.radioButton3:
                if(checked)
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageResource(R.drawable.rabbit);
                break;
        }
    }
}