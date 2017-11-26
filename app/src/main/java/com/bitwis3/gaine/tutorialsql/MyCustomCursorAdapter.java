package com.bitwis3.gaine.tutorialsql;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by gaine on 11/24/2017.
 */

public class MyCustomCursorAdapter extends CursorAdapter {
    public MyCustomCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }
// this is used to inflate new view and return it
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.listview_item,viewGroup,false);
    }


    //where you bind data, aka initialize views and attach data

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView tvname = (TextView)view.findViewById(R.id.lv_tv1);
        TextView tvage = (TextView)view.findViewById(R.id.lv_tv2);

        String name = cursor.getString(1);
        String age = cursor.getString(2);

        tvname.setText(name);
        tvage.setText(age);



    }
}
