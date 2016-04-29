package com.williamf6894.plandroid;

/**
 * Created by will on 10/04/16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


class CustomAdapter extends ArrayAdapter<PlanItem> {

    View divisor;

    // This is just the constructor
    public CustomAdapter(Context context, List<PlanItem> PlanList) {
        super(context, R.layout.custom_row, PlanList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater starter = LayoutInflater.from(getContext());
        View customView = starter.inflate(R.layout.custom_row, parent, false);

        String titleOfPlan = getItem(position).getTitle();
        String tagOfPlan = getItem(position).getTag();
        String descriptionOfPlan = getItem(position).getDescription();
        String locationOfPlan = getItem(position).getLocation();


        //Get Something in the array.

        divisor = customView.findViewById(R.id.primerdivisor);
        TextView plansTitle = (TextView) customView.findViewById(R.id.planTitle);
        TextView plansTag = (TextView) customView.findViewById(R.id.planTag);
        TextView plansDescription = (TextView) customView.findViewById(R.id.planDescription);
        TextView plansLocation = (TextView) customView.findViewById(R.id.planLocation);

        plansTitle.setText(titleOfPlan);
        plansDescription.setText(descriptionOfPlan);
        plansLocation.setText(locationOfPlan);
        plansTag.setText("#"+tagOfPlan);

        if (titleOfPlan.equals(""))
        changeVisibility(plansTitle, "");

        divisor.setVisibility(View.GONE); //For anything below the divisor

        changeVisibility(plansLocation, locationOfPlan);
        changeVisibility(plansTag, tagOfPlan);

        return customView;
    }

    public void changeVisibility(TextView plansName, String plansNameString){
        if (plansNameString.equals("")){
            plansName.setVisibility(View.INVISIBLE);
            plansName.setHeight(0);
        }
        else{
            plansName.setVisibility(View.VISIBLE);
            plansName.setHeight(60);
            if (divisor.getVisibility() == View.GONE)
                divisor.setVisibility(View.VISIBLE);
        }
    }
}
