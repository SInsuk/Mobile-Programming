package com.example.wordchaingame;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class RankingList extends AppCompatActivity {
    private Button btnWord;
    private DatabaseHelper dbHelper;
    private SimpleCursorAdapter adapter;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking_list);

        btnWord = findViewById(R.id.btnBack);

        dbHelper = new DatabaseHelper(this);
        ListView listView = findViewById(R.id.listView);

        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");

        Cursor cursor = dbHelper.getAllUsersScore();
        String[] from = new String[]{DatabaseHelper.COL_NAME, DatabaseHelper.COL_ID, DatabaseHelper.COL_H_SCORE};
        int[] to = new int[]{R.id.textView, R.id.textView2, R.id.textView4};

        adapter = new SimpleCursorAdapter(this, R.layout.activity_ranking, cursor, from, to, 0);
        listView.setAdapter(adapter);

        btnWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RankingList.this, MainActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });
    }
}
