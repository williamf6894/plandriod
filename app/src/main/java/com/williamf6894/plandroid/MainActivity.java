package com.williamf6894.plandroid;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public List<PlanItem> listOfAllPlans = new ArrayList<>();
    public DBHandler dbHandler;
    TextView title ;
    TextView tag;
    TextView description;
    TextView location;
    ArrayList<Integer> idNumbers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHandler = new DBHandler(this);

        // PlanItem test3 = new PlanItem(3, 1, "TitleThing", "Tag", "Example of a real Description", "");
        // PlanItem test4 = new PlanItem(4, 1, "Title", "Tag34", "Description", "");
        // dbHandler.addPlan(test3.getTitle(), test3.getTag(), test3.getDescription(), test3.getLocation(), test3.getSched_ID());
        // dbHandler.addPlan(test4.getTitle(), test4.getTag(), test4.getDescription(), test4.getLocation(), test4.getSched_ID());
        // listOfAllPlans = dbHandler.getTablePlansinfo();
        // Replace the String with a plan objects in the future

        idNumbers = new ArrayList<>();
        Cursor res = dbHandler.getAllData();
        if (res.getCount() == 0){
            return;
        }
        if (idNumbers.isEmpty())
            while(res.moveToNext()){
                listOfAllPlans.add(new PlanItem(res.getInt(0), res.getInt(1), res.getString(2),
                        res.getString(3), res.getString(4), res.getString(5)));
                idNumbers.add(res.getInt(0));
            }
        else
            while(res.moveToNext()){
                listOfAllPlans.add(new PlanItem(res.getInt(0), res.getInt(1), res.getString(2),
                        res.getString(3), res.getString(4), res.getString(5)));
            }

        ListAdapter mainListAdapter = new CustomAdapter(this, listOfAllPlans);

        ListView thePlanView = (ListView) findViewById(R.id.mainView);
        thePlanView.setItemsCanFocus(false);
        assert thePlanView != null;
        thePlanView.setAdapter(mainListAdapter);

            //What happens when item is clicked on screen
        thePlanView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Bundle dataBundle = new Bundle();
                        dataBundle.putInt("IdNumber", idNumbers.get(position));

                        Intent intent = new Intent(getApplicationContext(),OpenPlanActivity.class);

                        intent.putExtras(dataBundle);
                        startActivity(intent);
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

        switch(item.getItemId()){
            case R.id.action_add:Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", 0);

                Intent intent = new Intent(getApplicationContext(), NewPlanActivity.class);
                intent.putExtras(dataBundle);

                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

        //noinspection SimplifiableIfStatement
        // if (id == R.id.action_add){
        //     Intent i = new Intent(this,OpenPlanActivity.class);
        //     startActivity(i);
        //     return true;
        // }

        //return super.onOptionsItemSelected(item);
    }
}
