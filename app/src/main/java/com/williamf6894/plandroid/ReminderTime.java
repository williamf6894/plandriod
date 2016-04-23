package com.williamf6894.plandroid;

/**
 * Created by will on 10/04/16.
 */
public class ReminderTime {

    private final int day = 1000 * 3600 * 24;
    private int time1;
    private int time2;
    private int time3;
    private int time4;
    private int time5;
    private int time6;


    ReminderTime(){
        //This will be the default time for Study


        this.time1 = 2 * day;  /* One Day */
        this.time2 = 2 * day;  /* One Week */
        this.time3 = 2 * day;  /* One Month */
        this.time4 = 2 * day;  /* Three Months */
        this.time5 = 2 * day;  /* Six Months */
    }

    ReminderTime(int time1){
        this.time1 = time1 * day;

    }


    /*
    public void to_reminder(View view)
    {
        Intent intent=new Intent(this,Notification_morning.class);
        AlarmManager manager=(AlarmManager)getSystemService(Activity.ALARM_SERVICE);
        PendingIntent pendingIntent=PendingIntent.getService(this,
                0,intent, 0);
        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, timepicker.getCurrentHour());
        cal.set(Calendar.MINUTE,timepicker.getCurrentMinute());
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        manager.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),24*60*60*1000,pendingIntent);

    }
    */
}
