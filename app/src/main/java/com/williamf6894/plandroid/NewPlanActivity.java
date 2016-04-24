package com.williamf6894.plandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class NewPlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_plan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*Bundle descriptionData = getIntent().getExtras();
        if (descriptionData == null)
            return;

        String DescriptionMessage = descriptionData.getString("DescriptionMessage");
        final EditText descText = (EditText) findViewById(R.id.editDescription);
        descText.setText(DescriptionMessage);*/
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent parentIntent = new Intent(this, MainActivity.class);
            startActivity(parentIntent);

            /*

            SAVE DATA ON BACK OR EXIT.LEAVE A TOAST
             */
            return true;

        }
        return super.onOptionsItemSelected(item);
    }
}