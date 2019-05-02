package com.example.remainder;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    ListView listView;
    MyAdapter myAdapter;
    MyDatabase myDatabase;
    ArrayList<MyData> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDatabase = new MyDatabase(this,MyDatabase.DATABASE_NAME,null,1);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,DateAndTime.class));
            }
        });

        listView = findViewById(R.id.list);
        myAdapter = new MyAdapter(this,arrayList);
        listView.setAdapter(myAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                Object getSelectedItem = arrayList.get(pos);

               // myDatabase.deleteTask((String) getSelectedItem);
                myAdapter.deleteItem(getSelectedItem);

                return true;
            }
        });


    }
    public void showAll(View view){
            MyData myData;
            Cursor data = myDatabase.getAllData();
            if(data !=null) {
                while (data.moveToNext()) {
                    String task = data.getString(1);
                    String date = data.getString(2);
                    String time = data.getString(3);
                    myData = new MyData(task,date,time);
                    arrayList.add(myData);
                }
            }
            myAdapter.notifyDataSetChanged();


    }
    @Override
    public void onBackPressed() {
        // super.onBackPressed();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit Dialogue");
        builder.setMessage("Do you want to exit ?");
        builder.setIcon(R.drawable.ic_alarm_add_black_24dp);
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.create();
        builder.show();

    }
}
