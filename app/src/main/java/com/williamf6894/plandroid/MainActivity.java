package com.williamf6894.plandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PlanItem test = new PlanItem("Title", "Tag", "Description");
        List<PlanItem> listOfAllPlans = new ArrayList<>();
        listOfAllPlans.add(test);

        // Replace the String with a plan objects in the future

        ListAdapter mainListAdapter = new CustomAdapter(this, listOfAllPlans);

        ListView thePlanView = (ListView) findViewById(R.id.mainView);
        thePlanView.setAdapter(mainListAdapter);

            //What happens when item is clicked on screen
        thePlanView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String thisIsJustSoemthingTempToHoldIt = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(MainActivity.this, thisIsJustSoemthingTempToHoldIt, Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
