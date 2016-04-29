package com.williamf6894.plandroid;

import android.content.Intent;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OpenPlanActivity extends AppCompatActivity {
    private DBHandler dbHandler;
    EditText titleText, tagText, descText, locText;
    String idString, title, tag, desc, loc; //New to the database
    String titleCurrent, tagCurrent, descCurrent, locCurrent; //Gets the current data in the db
    int IdNumber;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_plan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle idData = getIntent().getExtras();
        dbHandler = new DBHandler(this);
        if (idData == null)
            return;

        IdNumber = idData.getInt("IdNumber");

        btnDelete = (Button)findViewById(R.id.btnDelete) ;
        titleText = (EditText) findViewById(R.id.editTitle);
        tagText = (EditText) findViewById(R.id.editTag);
        descText = (EditText) findViewById(R.id.editDescription);
        locText = (EditText) findViewById(R.id.editLocation);

        fillEditView(IdNumber);

        DeleteData();
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent parentIntent = new Intent(this, MainActivity.class);
            /*

            SAVE DATA ON BACK OR EXIT.LEAVE A TOAST
            */
            title = titleText.getText().toString();
            tag = tagText.getText().toString();
            desc = descText.getText().toString();
            loc = locText.getText().toString();

            if(title.equals("") && desc.equals("")){
                startActivity(parentIntent);
                return true;
            }
            else if(title.equals(titleCurrent) &&
                    tag.equals(tagCurrent) &&
                    desc.equals(descCurrent) &&
                    loc.equals(locCurrent) ){
                startActivity(parentIntent);
                return true;
            }
            else
                dbHandler.updateData(
                        idString,
                        title,
                        tag,
                        desc,
                        loc,
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
            idString = res.getString(0);
            titleText.setText(res.getString(2));
            tagText.setText(res.getString(3));
            descText.setText(res.getString(4));
            locText.setText(res.getString(5));
            //res.getString(5)));
        }

        titleCurrent = titleText.getText().toString();
        tagCurrent = tagText.getText().toString();
        descCurrent = descText.getText().toString();
        locCurrent = locText.getText().toString();
    }

    public void DeleteData(){
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = dbHandler.deleteData(idString);

                        if(deletedRows > 0)
                            Toast.makeText(OpenPlanActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(OpenPlanActivity.this, "Data Not Deleted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}
