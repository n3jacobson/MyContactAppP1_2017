package com.example.jacobsonn1604.contactapp;

import android.app.AlertDialog;
import android.content.Intent;
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
import android.widget.Toast;


public class MainActivity extends AppCompatActivity{
    public static final String EXTRA_MESSAGE = "com.example.search.MESSAGE";
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
            Toast toast = Toast.makeText(getApplicationContext(),"Data insertion successful", Toast.LENGTH_SHORT);
            toast.show();
            Log.d("MyContact", "Data insertion successful");
        }
        else{
            //Create toast message to user indicating data inserted incorrectly
            Log.d("MyContact", "Data insertion NOT successful");
            Toast toast = Toast.makeText(getApplicationContext(),"Data insertion NOT successful", Toast.LENGTH_SHORT);
            toast.show();
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
            for(int col = 0; col <4;col++) {
                buffer.append(res.getString(col));
                buffer.append("\n");
            }
        }
        showMessage("Data", buffer.toString());
        //Setup loop with Cursor moveToNext method
        //  append each column to the buffer
        //  use the getString method
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true); // Cancel using back button
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void search(View view) {
        Intent intent = new Intent(this, findData.class);
        EditText editText = (EditText) findViewById(R.id.editText_search);
        String name = editText.getText().toString();
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0){
            showMessage("Error", "No data found in database");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        int discoveries = 0;
        while (res.moveToNext()) {
            if (name.compareTo(res.getString(1)) == 0) {
                for (int col = 0; col < 4; col++) {
                    buffer.append(res.getString(col));
                    buffer.append("\n");
                }
                buffer.append("\n\n\n");
                discoveries++;
            }
        }
        String message = discoveries + " discoveries found: \n\n\n" + buffer.toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }



}
