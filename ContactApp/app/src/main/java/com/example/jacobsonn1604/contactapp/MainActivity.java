package com.example.jacobsonn1604.contactapp;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity{

    DataBaseHelper myDb;
    EditText editName;
    EditText editAddress;
    EditText editAge;
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DataBaseHelper(this);
        editName = (EditText) findViewById(R.id.editText_name);
        editAddress = (EditText) findViewById(R.id.editText_address);
        editAge = (EditText) findViewById(R.id.editText_age);

    }

    public void addData(View v){
        boolean isInserted = myDb.insertData(editName.getText().toString(), editAddress.getText().toString(), editAge.getText().toString());
        if(isInserted == true){
            //Create toast message to user indicating data inserted correctly
            Log.d("MyContact", "Data insertion successful");
        }
        else{
            //Create toast message to user indicating data inserted incorrectly
            Log.d("MyContact", "Data insertion NOT successful");
        }
    }

    public void viewData(View v){
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0){
            showMessage("Error", "No data found in database");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append(res.getString(res.getPosition()));
        }
        //Setup loop with Cursor moveToNext method
        //  append each column to the buffer
        //  use the getString method
        showMessage("Data", buffer.toString());
    }

    private void showMessage(String title, String message) {
        Log.d(title,message);
    }
}
