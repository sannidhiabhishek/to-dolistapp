package com.example.remainder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MyData {
    String task;String date;String time;
    public MyData(String task, String date, String time) {
        this.task = task;
        this.date = date;
        this.time = time;
    }

    public String getTask() {return task;}

    public String getDate() { return date;    }

    public String getTime() { return time;    }

}
