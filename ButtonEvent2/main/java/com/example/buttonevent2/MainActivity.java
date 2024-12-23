package com.example.buttonevent2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    int i;
    Button[] c_buttons = new Button[4];
//    Integer[] btnIds = {R.id.c_button1,R.id.c_button2,R.id.c_button3,R.id.c_button4};
//
//    Integer[] colorIds = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};

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



        c_buttons[0] = findViewById(R.id.c_button1);
        c_buttons[1] = findViewById(R.id.c_button2);
        c_buttons[2] = findViewById(R.id.c_button3);
        c_buttons[3] = findViewById(R.id.c_button4);

//        for(i=0; i<c_buttons.length; i++){
//            c_buttons[i] = (Button) findViewById(btnIds[i]);
//        }
//
//        for(i=0; i<c_buttons.length; i++){
//            final int index;
//            index=1;
//            c_buttons[i].setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    changeClothingColor(colorIds[i]);
//                }
//            });
//        }

        for (int i = 0; i < c_buttons.length; i++) {
            int finalI = i;
            c_buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int color;
                    switch (finalI) {
                        case 0:
                            color = Color.RED;
                            break;
                        case 1:
                            color = Color.YELLOW;
                            break;
                        case 2:
                            color = Color.GREEN;
                            break;
                        case 3:
                            color = Color.BLUE;
                            break;
                        default:
                            color = Color.WHITE;
                            break;
                    }
                    changeClothingColor(color);
                }
            });
        }

    }

        private void changeClothingColor(int color){
        imageView.setBackgroundColor(color);

    }
}