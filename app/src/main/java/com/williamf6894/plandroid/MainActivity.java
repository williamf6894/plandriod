package com.williamf6894.plandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public List<PlanItem> listOfAllPlans = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PlanItem test = new PlanItem("Title", "Tag", "Description");
        PlanItem test2 = new PlanItem("Title2", "Tags", "Remember to buy eggs, milk and button for the cake.");
        PlanItem test3 = new PlanItem("TitleThing", "Tag", "Example of a real Description");
        PlanItem test4 = new PlanItem("Title", "Tag", "Description");
        PlanItem test5 = new PlanItem("Title2", "Tags", "Remember to buy eggs, milk and button for the cake.");
        PlanItem test6 = new PlanItem("TitleThing", "Tag", "Example of a real Description");
        listOfAllPlans.add(test);
        listOfAllPlans.add(test2);
        listOfAllPlans.add(test3);
        listOfAllPlans.add(test4);
        listOfAllPlans.add(test5);
        listOfAllPlans.add(test6);

        // Replace the String with a plan objects in the future

        ListAdapter mainListAdapter = new CustomAdapter(this, listOfAllPlans);

        ListView thePlanView = (ListView) findViewById(R.id.mainView);
        assert thePlanView != null;
        thePlanView.setAdapter(mainListAdapter);

            //What happens when item is clicked on screen
        thePlanView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        /*Intent i = new Intent(this,OpenPlanActivity.class);
                        String userMessage = "Example of the description";
                        i.putExtra("DescriptionMessage", userMessage);
                        startActivity(i);*/
                    }
                }
        );

    }

    public void openEdit(View view){
        Intent i = new Intent(this,OpenPlanActivity.class);
        i.putExtra("DescriptionMessage", "Sample description");
        startActivity(i);
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
        if (id == R.id.action_add){
            Intent i = new Intent(this,OpenPlanActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
