package com.example.remainder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    public MyAdapter(Context context , ArrayList<MyData> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        layoutInflater = LayoutInflater.from(context);
    }
    private class MyViewHolder{
        TextView taskName,dateName,timeName;
        public MyViewHolder(View v) {

            taskName = v.findViewById(R.id.taskName);
            dateName = v.findViewById(R.id.dateName);
            timeName = v.findViewById(R.id.timeName);
        }
    }
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<MyData> arrayList;

    @Override
    public int getCount() { return arrayList.size();    }

    @Override
    public Object getItem(int position) { return arrayList.get(position);    }

    @Override
    public long getItemId(int position) { return position;    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder;
        if (convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.custom_layout,parent,false);
            myViewHolder = new MyViewHolder(convertView);
            convertView.setTag(myViewHolder);
        }
        else
        {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        MyData myData = arrayList.get(position);
        myViewHolder.taskName.setText(myData.getTask());
        myViewHolder.dateName.setText(myData.getDate());
        myViewHolder.timeName.setText(myData.getTime());
        return convertView;
    }
    public void deleteItem(Object item) {
        arrayList.remove(item);
        notifyDataSetChanged();
    }
}
