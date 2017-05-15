package com.example.jacobsonn1604.contactapp;

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
        boolean isInserted = myDb.insertData(editName.getText().toString(),editAddress.getText().toString(),editAge.getText().toString());
        if(isInserted == true){
            //Create toast message to user indicating data inserted correctly
            Log.d("MyContact","Data insertion successful");
        }
        else{
            //Create toast message to user indicating data inserted incorrectly
            Log.d("MyContact", "Data insertion NOT successful");
        }
    }
}
