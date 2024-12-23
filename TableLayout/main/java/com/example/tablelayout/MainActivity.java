package com.example.tablelayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button[] button = new Button[10];
    Integer[] btnIds = {R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button10};
    int i;
    String num1, num2;
    private EditText editText1;
    private EditText editText2;
    TextView Textview1;

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

        editText1 = findViewById(R.id.editTextText);
        editText2 = findViewById(R.id.editTextText2);

        Textview1 = (TextView) findViewById(R.id.textView);

        for (i = 0; i < btnIds.length; i++) {
            button[i] = (Button) findViewById(btnIds[i]);
        }

        for (i = 0; i < btnIds.length; i++) {
            final int index;
            index = i;

            button[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editText1.isFocused() == true) {
                        num1 = editText1.getText().toString() + button[index].getText().toString();
                        editText1.setText(num1);
                    } else if (editText2.isFocused() == true) {
                        num2 = editText2.getText().toString() + button[index].getText().toString();
                        editText2.setText(num2);
                    } else {
                        Toast.makeText(getApplicationContext(), "먼저 에디트텍스트를 선택하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

        public void cal_plus(View e){
            String s1 = editText1.getText().toString();
            String s2 = editText2.getText().toString();

            int result = Integer.parseInt(s1) + Integer.parseInt(s2);
            Textview1.setText("" + result);
        }

        public void cal_minus(View e){
            String s1 = editText1.getText().toString();
            String s2 = editText2.getText().toString();

            int result = Integer.parseInt(s1) - Integer.parseInt(s2);
            Textview1.setText("" + result);
        }

        public void cal_mul(View e){
            String s1 = editText1.getText().toString();
            String s2 = editText2.getText().toString();

            int result = Integer.parseInt(s1) * Integer.parseInt(s2);
            Textview1.setText("" + result);
        }

        public void cal_div(View e){
            String s1 = editText1.getText().toString();
            String s2 = editText2.getText().toString();

            Double result = Double.parseDouble(s1) / Double.parseDouble(s2);
            Textview1.setText("" + String.format("%.2f", result));
        }
}