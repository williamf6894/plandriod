package com.williamf6894.plandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class OpenPlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_plan);

        Bundle descriptionData = getIntent().getExtras();
        if (descriptionData == null)
            return;

        String DescriptionMessage = descriptionData.getString("DescriptionMessage");
        final EditText descText = (EditText) findViewById(R.id.editDescription);
        descText.setText(DescriptionMessage);

    }
}
