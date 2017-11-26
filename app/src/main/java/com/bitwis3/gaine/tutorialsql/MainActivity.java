package com.bitwis3.gaine.tutorialsql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etAge;
    Button addButton;
    Button show30Button;
    ListView listView;

    SQLiteDatabase db;
    OurDBHelper helper;
    ContentValues values;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText)findViewById(R.id.et_name);
        etAge = (EditText)findViewById(R.id.et_age);
        addButton = (Button)findViewById(R.id.add_button);
        show30Button = (Button)findViewById(R.id.show30);
        listView = (ListView)findViewById(R.id.listview);
        Log.i("TUTORIAL_INFO", "BUILDING DB");
        helper = new OurDBHelper(MainActivity.this, "_tutorial_db_name", null, 3);
        db = helper.getWritableDatabase();
        values = new ContentValues();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEntry();
            }
        });

        show30Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelected();
            }
        });

        updateListView();

    }

    public void addEntry(){
        String name = etName.getText().toString().trim();
        String age = etAge.getText().toString().trim();

        values.put("_name", name);
        values.put("_age", age);

        /*
        //insert data
INSERT INTO _tutorial_table (_name, _age)
VALUES ('insertedNameJoe', '100');

        */

        db.insert("_tutorial_table", null, values);
        values.clear();
        etName.setText("");
        etAge.setText("");

        updateListView();
    }
    public void showSelected(){
        ArrayList<String> arrayList = new ArrayList<>();

        String age = etAge.getText().toString().trim();
        cursor = db.rawQuery("SELECT  _name FROM _tutorial_table WHERE _age = '" + age + "';", null);
        if (cursor != null && cursor.moveToFirst()){
            do{
               arrayList.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        ArrayAdapter<String> adapterS = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,
                arrayList);
        listView.setAdapter(adapterS);

    }

    public void updateListView(){
        cursor = db.rawQuery("SELECT * FROM _tutorial_table;", null);
        MyCustomCursorAdapter customCursorAdapter = new MyCustomCursorAdapter(MainActivity.this, cursor);
        listView.setAdapter(customCursorAdapter);



    }
}


