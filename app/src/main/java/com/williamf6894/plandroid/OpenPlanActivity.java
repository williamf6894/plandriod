package com.williamf6894.plandroid;

import android.content.Intent;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OpenPlanActivity extends AppCompatActivity {
    private DBHandler dbHandler;

    EditText titleText, tagText, descText;
    Button savebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_plan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle idData = getIntent().getExtras();
        dbHandler = new DBHandler(this);
        if (idData == null)
            return;

        int IdNumber = idData.getInt("IdNumber");

        titleText = (EditText) findViewById(R.id.editTitle);
        tagText = (EditText) findViewById(R.id.editTag);
        descText = (EditText) findViewById(R.id.editDescription);
        fillEditView(IdNumber);

    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent parentIntent = new Intent(this, MainActivity.class);
            /*

            SAVE DATA ON BACK OR EXIT.LEAVE A TOAST
            */
            if(titleText.getText().toString() == null && descText.getText().toString() == null){

            }
            else
                dbHandler.insertData(
                    titleText.getText().toString(),
                    tagText.getText().toString(),
                    descText.getText().toString(),
                    "locationsplace",
                    12
                );
            startActivity(parentIntent);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void fillEditView(int id){
        Cursor res = dbHandler.getIdData(id);
        if (res.getCount() == 0)
            return;
        while(res.moveToNext()){
            titleText.setText(res.getString(2));
            tagText.setText(res.getString(3));
            descText.setText(res.getString(4));
            //res.getString(5)));
        }
    }

}
