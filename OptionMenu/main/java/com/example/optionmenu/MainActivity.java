package com.example.optionmenu;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        layout = (LinearLayout) findViewById(R.id.main);
    }

    //xml로부터 메뉴 받아오기
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.mymenu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if(id == R.id.blue){
//            layout.setBackgroundColor(Color.BLUE);
//            return true;
//        } else if (id == R.id.green) {
//            layout.setBackgroundColor(Color.GREEN);
//            return true;
//        } else if (id == R.id.red) {
//            layout.setBackgroundColor(Color.RED);
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    //xml이용하지 않고 메뉴 추가
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "배경색: RED");
        menu.add(0, 2, 0, "배경색: GREEN");
        menu.add(0, 3, 0, "배경색: BLUE");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == 1){
            layout.setBackgroundColor(Color.RED);
            return true;
        } else if (id == 2) {
            layout.setBackgroundColor(Color.GREEN);
            return true;
        } else if (id == 3) {
            layout.setBackgroundColor(Color.BLUE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}