package com.williamf6894.plandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class NewPlanActivity extends AppCompatActivity {
    private DBHandler dbHandler;
    EditText titleText, tagText, descText, locText;
    String title, tag, desc, loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_plan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle idData = getIntent().getExtras();
        dbHandler = new DBHandler(this);
        if (idData == null)
            return;

        titleText = (EditText) findViewById(R.id.editTitle);
        tagText = (EditText) findViewById(R.id.editTag);
        descText = (EditText) findViewById(R.id.editDescription);
        locText = (EditText) findViewById(R.id.editLocation);

    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent parentIntent = new Intent(this, MainActivity.class);

            /*
            Make an if to find out is we are making a Note, Plan or Event.
            Maybe different views for each
            */
            title = titleText.getText().toString();
            tag = tagText.getText().toString();
            desc = descText.getText().toString();
            loc = locText.getText().toString();

            if(title.equals("") && desc.equals("")){
                startActivity(parentIntent);
                return true;
            }
            else{
                dbHandler.insertData(
                    title,
                    tag,
                    desc,
                    loc,
                    12
                );
                Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();
                startActivity(parentIntent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }


}
