package com.example.wordchaingame;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MemberListActivity extends AppCompatActivity {
    private Button btnWord, btnLogout;
    private DatabaseHelper dbHelper;
    private SimpleCursorAdapter adapter;
    private boolean isAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);

        // 인텐트에서 관리자 여부 받기
        Intent intent = getIntent();
        isAdmin = intent.getBooleanExtra("isAdmin", false);

        btnWord = findViewById(R.id.btnWord);
        btnLogout = findViewById(R.id.btnLogout);

        dbHelper = new DatabaseHelper(this);
        ListView listView = findViewById(R.id.listView);

        Cursor cursor = dbHelper.getAllUsers();
        String[] from = new String[]{DatabaseHelper.COL_NAME, DatabaseHelper.COL_ID, DatabaseHelper.COL_PASSWORD, DatabaseHelper.COL_H_SCORE};
        int[] to = new int[]{R.id.textView, R.id.textView2, R.id.textView3, R.id.textView4};

        adapter = new SimpleCursorAdapter(this, R.layout.activity_member, cursor, from, to, 0);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) adapter.getItem(position);
                String selectedUserId = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_ID)); // 선택한 사용자의 ID

                // 관리자인 경우에만 삭제 기능 활성화
                if (isAdmin) {
                    dbHelper.deleteUser(selectedUserId);
                    updateListView();
                    Toast.makeText(MemberListActivity.this, "사용자 정보가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MemberListActivity.this, "삭제 권한이 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MemberListActivity.this, WordActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MemberListActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateListView() {
        Cursor cursor = dbHelper.getAllUsers();
        adapter.changeCursor(cursor);
    }
}
