package com.example.imageviewtest;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private int scaleTypeIndex = 0;

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

        imageView = findViewById(R.id.imageView);

    }

    //버튼 클릭 이벤트 처리: Scale Type 변경
    public void changeScaleType(View view){
        ImageView.ScaleType[] scaleTypes = {
                ImageView.ScaleType.CENTER,
                ImageView.ScaleType.CENTER_CROP,
                ImageView.ScaleType.CENTER_INSIDE,
                ImageView.ScaleType.FIT_CENTER,
                ImageView.ScaleType.FIT_XY
        };

        imageView.setScaleType(scaleTypes[scaleTypeIndex]);
        scaleTypeIndex = (scaleTypeIndex + 1) % scaleTypes.length;
    }

    //버튼 클릭 이벤트 처리: 회전 변경
    public void changeRotation(View view){
        imageView.setRotation(imageView.getRotation() + 45);
    }

    //버튼 클릭 이벤트 처리: Alpha 변경
    public void changeAlpha(View view){
        float alpha = imageView.getAlpha();
        alpha = (alpha == 1.0f) ? 0.5f : 1.0f;
        imageView.setAlpha(alpha);
    }
}