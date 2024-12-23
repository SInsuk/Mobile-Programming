package com.example.final_term_project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        textView3 = findViewById(R.id.textView3);

        Button selectDate = findViewById(R.id.button3);
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView3.setVisibility(View.VISIBLE);
            }
        });

        Button selectTime = findViewById(R.id.button4);
        selectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

//        private void showDatePickerDialog() {
//            Calendar calendar = Calendar.getInstance();
//            int year = calendar.get(Calendar.YEAR);
//            int month = calendar.get(Calendar.MONTH);
//            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
//
//            DatePickerDialog datePickerDialog = new DatePickerDialog(
//                    this,
//                    new DatePickerDialog.OnDateSetListener() {
//                        @Override
//                        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
//                            String selectedDate = selectedYear + "년" + (selectedMonth + 1) + "월" + selectedDayOfMonth + "일";
//                            textView3.setText(selectedDate);
//
//                        }
//                    },
//                    year, month, dayOfMonth
//            );
//            datePickerDialog.show();
//        }

    }
}