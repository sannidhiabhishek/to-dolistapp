package com.example.remainder;


import android.app.DatePickerDialog;

import android.app.TimePickerDialog;

import android.content.Intent;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class DateAndTime extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    Button date,time,set;
    public EditText dateText,timeText,task;
    MyDatabase myDatabase;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_and_time);
        date = findViewById(R.id.date);
        dateText = findViewById(R.id.dateText);
        time = findViewById(R.id.time);
        timeText = findViewById(R.id.timeText);
        set = findViewById(R.id.Set);
        task = findViewById(R.id.task);
        myDatabase = new MyDatabase(this,MyDatabase.DATABASE_NAME,null,1);
        intent = new Intent(this,MainActivity.class);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new com.example.remainder.DatePicker();
                datePicker.show(getSupportFragmentManager(),"Date Picker");

            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePicker();
                timePicker.show(getSupportFragmentManager(),"Time Picker");
            }
        });
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertMethod(v);
            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String selectedData = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        dateText.setText(selectedData);
    }

    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        timeText.setText(hourOfDay+":"+minute);
    }
    public void insertMethod(View view){
        if(isEmpty(task) || isEmpty(dateText) || isEmpty(timeText)){
            Toast.makeText(getApplicationContext(),"Please fill all fields",Toast.LENGTH_SHORT).show();
        }
        else {
            boolean status = myDatabase.insertData(task.getText().toString().trim(),dateText.getText().toString().trim(),timeText.getText().toString().trim());
            if(status){
                Toast.makeText(getApplicationContext(),"Data entered",Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(),"Data entry failed..:(",Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        startActivity(intent);
        finish();
    }
    private boolean isEmpty(EditText editText)
    {
        return editText.getText().toString().trim().length()==0;
    }

}
