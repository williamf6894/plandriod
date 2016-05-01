package com.williamf6894.plandroid;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class NewPlanActivity extends AppCompatActivity {
    
    private DBHandler dbHandler;
    EditText titleText, tagText, descText, locText, repText;
    String idString, title, tag, desc, loc, rep; //New to the database
    String titleCurrent, tagCurrent, descCurrent, locCurrent;
    int repCurrent, spinCurrent; //Gets the current data in the db
    int IdNumber;
    Button btnDelete, btnTime, btnDate;
    CheckBox checkBox, chkRepeat;
    TextView viewTime, viewDate;
    Spinner spinner;
    int spin_val;
    String[] times = { "Hourly", "Daily", "Weekly", "Bimonthly" ,"Monthly","Yearly"}; //Might be needed
    Calendar timeCurrent = new GregorianCalendar();


    int notif_id; //Will be same as id.
    NotificationManager notificationManager;

    int pickHour, pickMinute, pickYear, pickMonth, pickDay;


    TextView viewText, viewText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_plan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle idData = getIntent().getExtras();
        dbHandler = new DBHandler(this);
        if (idData == null)
            return;
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        createTimeSpinner();

        IdNumber = idData.getInt("IdNumber");

        btnDelete = (Button) findViewById(R.id.btnDelete);
        titleText = (EditText) findViewById(R.id.editTitle);
        tagText = (EditText) findViewById(R.id.editTag);
        descText = (EditText) findViewById(R.id.editDescription);
        locText = (EditText) findViewById(R.id.editLocation);
        openOptions();
    }
/*
    Notifications will be created here along with storage and many other things, this is where almost everything is made.

    Its important to remember to create, update and delete, not just create or delete.

    Each Notification will have the same notifitcation ID as the plans with their IDs.

    There may be customs timed notifications, depending on time, which are notifs that
    don't have to happen by set loop, like weekly or daily, but instead can happen at customized times.



     */
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // Originally had the contents of OnBackPressed in here, now calling OnBackPressed
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    //Upstairs does the Up button in the App Bar, with is the back button on the bottom
    public void onBackPressed() {
        Intent parentIntent = new Intent(this, MainActivity.class);
        /*

        SAVE DATA ON BACK OR EXIT.LEAVE A TOAST
        */
        title = titleText.getText().toString();
        tag = tagText.getText().toString();
        desc = descText.getText().toString();
        loc = locText.getText().toString();
        rep = repText.getText().toString();
        checkBox = (CheckBox) findViewById(R.id.checkReminder);
        chkRepeat = (CheckBox) findViewById(R.id.checkRepeat);
        spin_val = spinner.getSelectedItemPosition();
        int repNum = 1;
        if(!rep.equals(""))
            repNum = Integer.parseInt(rep);
        String time = viewTime.getText().toString();
        String date = viewTime.getText().toString();


        if (title.equals("") && desc.equals("")) {
            startActivity(parentIntent);
        }
        else {
            dbHandler.insertData(title,
                    tag,desc,loc,12);
        }
        if(checkBox.isChecked()) {

            if (chkRepeat.isChecked()){
                if (date.equals("") || time.equals("") || rep.equals("")) {
                    startActivity(parentIntent);
                }
                else{
                    dbHandler.insertReminder(idString, getAlarmTime(), repNum, spin_val);
                    createAllNotifications(repNum, spin_val);
                    startActivity(parentIntent);
                    //Update the reminder, but not the data
                }
            }
            else {
                if (time.equals("") || date.equals("") || timeCurrent.getTimeInMillis() == getAlarmTime()) {
                    startActivity(parentIntent);
                }
                else{
                    dbHandler.insertReminder(idString, getAlarmTime(), repNum, spin_val);
                    createAllNotifications(repNum, spin_val);
                    startActivity(parentIntent);
                    //Update the reminder, but not the data
                }
                //Update both data and reminder
            }
        }
        startActivity(parentIntent);

    }

    public void openOptions(View view) {

        checkBox = (CheckBox) findViewById(R.id.checkReminder);
        viewTime = (TextView) findViewById(R.id.viewTime);
        viewDate = (TextView) findViewById(R.id.viewDate);
        btnTime = (Button) findViewById(R.id.btnTime);
        btnDate = (Button) findViewById(R.id.btnDate);
        chkRepeat = (CheckBox) findViewById(R.id.checkRepeat);


        if (checkBox.isChecked()) {

            btnTime.setVisibility(View.VISIBLE);
            btnDate.setVisibility(View.VISIBLE);
            viewTime.setVisibility(View.VISIBLE);
            viewDate.setVisibility(View.VISIBLE);
            chkRepeat.setVisibility(View.VISIBLE);
            //plansName.setHeight(60);
        } else {
            btnTime.setVisibility(View.GONE);
            btnDate.setVisibility(View.GONE);
            viewTime.setVisibility(View.GONE);
            viewDate.setVisibility(View.GONE);
            chkRepeat.setVisibility(View.GONE);
            //plansName.setHeight(0);
        }
    }

    public void openOptions() {

        checkBox = (CheckBox) findViewById(R.id.checkReminder);
        viewTime = (TextView) findViewById(R.id.viewTime);
        viewDate = (TextView) findViewById(R.id.viewDate);
        btnTime = (Button) findViewById(R.id.btnTime);
        btnDate = (Button) findViewById(R.id.btnDate);
        chkRepeat = (CheckBox) findViewById(R.id.checkRepeat);
        repText = (EditText) findViewById(R.id.editRepeat);
        viewText = (TextView) findViewById(R.id.textView);
        viewText2 = (TextView) findViewById(R.id.textView2);
        spinner = (Spinner) findViewById(R.id.repTime);

        if (checkBox.isChecked()) {

            btnTime.setVisibility(View.VISIBLE);
            btnDate.setVisibility(View.VISIBLE);
            viewTime.setVisibility(View.VISIBLE);
            viewDate.setVisibility(View.VISIBLE);
            chkRepeat.setVisibility(View.VISIBLE);
        } else {
            btnTime.setVisibility(View.GONE);
            btnDate.setVisibility(View.GONE);
            viewTime.setVisibility(View.GONE);
            viewDate.setVisibility(View.GONE);
            chkRepeat.setVisibility(View.GONE);
            viewText.setVisibility(View.GONE);
            viewText2.setVisibility(View.GONE);
            repText.setVisibility(View.GONE);
            spinner.setVisibility(View.GONE);
        }
    }

    public void openReminderOptions(View view) {


        repText = (EditText) findViewById(R.id.editRepeat);
        spinner = (Spinner) findViewById(R.id.repTime);
        viewText = (TextView) findViewById(R.id.textView);
        viewText2 = (TextView) findViewById(R.id.textView2);

        chkRepeat = (CheckBox) findViewById(R.id.checkRepeat);
        if (chkRepeat.isChecked()) {
            viewText.setVisibility(View.VISIBLE);
            viewText2.setVisibility(View.VISIBLE);
            repText.setVisibility(View.VISIBLE);
            spinner.setVisibility(View.VISIBLE);
        } else {
            viewText.setVisibility(View.GONE);
            viewText2.setVisibility(View.GONE);
            repText.setVisibility(View.GONE);
            spinner.setVisibility(View.GONE);
        }
    }

    public void createTimeSpinner() {

        setContentView(R.layout.activity_new_plan);
        spinner = (Spinner) findViewById(R.id.repTime);
        ArrayAdapter<CharSequence> spin_adapter = ArrayAdapter.createFromResource(this, R.array.repeat_array, android.R.layout.simple_spinner_item);

        spin_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(spin_adapter);

    }


    public void showTimePickerDialog(View view){
        CreateDialog(1).show();
    }

    public void showDatePickerDialog(View view){
        CreateDialog(2).show();
    }


    protected Dialog CreateDialog(int id){
        final Calendar c = Calendar.getInstance();
        int pickYear = c.get(Calendar.YEAR);
        int pickMonth = c.get(Calendar.MONTH);
        int pickDay = c.get(Calendar.DAY_OF_MONTH);
        int pickHour = c.get(Calendar.HOUR_OF_DAY);
        int pickMinute = c.get(Calendar.MINUTE);
        c.add(Calendar.DATE, 1);

        if(id == 1){
            return new TimePickerDialog(NewPlanActivity.this, tpListener, pickHour, pickMinute, true);
        }
        else if (id == 2){
            DatePickerDialog datePickerDialog = new DatePickerDialog(NewPlanActivity.this, dpListener, pickYear, pickMonth, pickDay);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            return datePickerDialog;
        }
        return null;


    }

    protected TimePickerDialog.OnTimeSetListener tpListener =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    pickHour = hourOfDay;
                    pickMinute = minute;

                    viewTime.setText(String.format("%02d", pickHour) + " : " + String.format("%02d", pickMinute));


                }
            };

    protected DatePickerDialog.OnDateSetListener dpListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    pickYear = year;
                    pickMonth = monthOfYear;
                    pickDay = dayOfMonth;
                    viewDate.setText(pickDay + " / " + pickMonth + " / " + pickYear);

                }
            };


    public long getAlarmTime(){
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, pickYear);
        c.set(Calendar.MONTH, pickMonth);
        c.set(Calendar.DAY_OF_MONTH, pickDay);
        c.set(Calendar.HOUR_OF_DAY, pickHour);
        c.set(Calendar.MINUTE, pickMinute);

        return c.getTimeInMillis();

    }

    public void createAllNotifications(int reps, int timegap){

        int loop=0;
        long increase = 3600000; //One hour
        long pickedTime = getAlarmTime();
        switch (timegap){
            case 0:
                break;
            case 1:
                increase *= 24;//day
                break;
            case 2:
                increase *= 168;//week
                break;
            case 3:
                increase *= 336;//month
                break;
            case 4:
                increase *=672;//month
                break;
            case 5:
                increase *=8064;//year
                break;

        }
        while(loop < reps){
            setAlarm(pickedTime+increase);
            increase += increase;
            reps++;
        }


    }


    public void setAlarm(long alertTime){

        Bundle alertBundle = new Bundle();
        alertBundle.putString("id", idString);
        alertBundle.putString("title", titleText.getText().toString());
        alertBundle.putString("description", descText.getText().toString());
        Intent alertIntent = new Intent(this, AlertReciever.class);
        alertIntent.putExtras(alertBundle);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, alertTime, PendingIntent.getBroadcast(this, 1, alertIntent, PendingIntent.FLAG_UPDATE_CURRENT));
    }

    public void studyPlan(){
        long increase = 60000; //One min
        increase *= 60;

        setAlarm((getAlarmTime()+increase));
        increase *= 24;//One day
        setAlarm((getAlarmTime()+increase));
        increase *= 7;
        setAlarm((getAlarmTime()+increase));
        increase *= 4;//One Month
        setAlarm((getAlarmTime()+increase));
        increase *= 3;
        setAlarm((getAlarmTime()+increase));
        increase *= 2;//6 months
        setAlarm((getAlarmTime()+increase));

    }

}
