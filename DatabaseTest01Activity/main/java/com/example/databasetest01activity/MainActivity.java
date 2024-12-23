package com.example.databasetest01activity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

class DBHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "mycontacts.db";
    private static final int DATABASE_VERSION =1;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE contacts ( _id INTEGER PRIMARY KEY" + " AUTOINCREMENT, name Text, tel TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }
}
public class MainActivity extends AppCompatActivity {

    DBHelper helper;
    SQLiteDatabase db;
    EditText edit_name, edit_tel;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        helper = new DBHelper(this);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException ex){
            db = helper.getReadableDatabase();
        }

        edit_name = (EditText) findViewById(R.id.editTextText);
        edit_tel = (EditText) findViewById(R.id.editTextText2);
        result = (TextView) findViewById(R.id.textView3);
    }

    public void insert(View target){
        String name = edit_name.getText().toString();
        String tel = edit_tel.getText().toString();
        db.execSQL("INSERT INTO contacts VALUES (null, '" + name + "', '" + tel + "');");

        Toast.makeText(getApplicationContext(), "성공적으로 추가되었음", Toast.LENGTH_SHORT).show();
        edit_name.setText("");
        edit_tel.setText("");
    }

    public void search(View target){
        String name = edit_name.getText().toString();
        Cursor cursor;
        cursor = db.rawQuery("SELECT name, tel FROM contacts WHERE name='" + name + "';", null);

        while (cursor.moveToNext()){
            String tel = cursor.getString(1);
            edit_tel.setText(tel);
        }
    }

    public void select_all(View target) {
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM contacts", null);

        String s="ID    Name    Tel \r\n";
        while (cursor.moveToNext()){
            s += cursor.getString(0) + "    ";
            s += cursor.getString(1) + "    ";
            s += cursor.getString(2) + "    \r\n";
        }
        result.setText(s);
    }

    public void modify(View target){
        String name = edit_name.getText().toString();
        String tel = edit_tel.getText().toString();
        db.execSQL("UPDATE contacts SET tel='" + tel + "' WHERE name='" + name + "';");
        Toast.makeText(getApplicationContext(), "성공적으로 수정되었습니다", Toast.LENGTH_SHORT).show();
    }

    public void delete(View target){
        String name = edit_name.getText().toString();
        db.execSQL("DELETE FROM contacts WHERE name='" + name + "';");
        Toast.makeText(getApplicationContext(), "성공적으로 삭제되었습니다", Toast.LENGTH_SHORT).show();
        edit_name.setText("");
        edit_tel.setText("");

    }

}