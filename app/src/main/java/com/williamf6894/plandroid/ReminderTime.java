package com.williamf6894.plandroid;

/**
 * Created by will on 10/04/16.
 */
public class ReminderTime {

    private final int day = 1000 * 3600 * 24;



    ReminderTime(){
        //This will be the default time for Study
    }

    ReminderTime(int time1){
        time1 = time1 * day;

    }

    // CreateStudy(){
    //     long time1 = day;  /* One Day */
    //     long time2 = 7 * day;  /* One Week */
    //     long time3 = 30 * day;  /* One Month */
    //     long time4 = 90 * day;  /* Three Months */
    //     long time5 = 180 * day;  /* Six Months */
    // }
    //
    //
    //
    //
    //
    // public void to_reminder(View view)
    // {
    //     Intent intent=new Intent(this,Notification_morning.class);
    //     AlarmManager manager=(AlarmManager)getSystemService(Activity.ALARM_SERVICE);
    //     PendingIntent pendingIntent=PendingIntent.getService(this,
    //             0,intent, 0);
    //     Calendar cal=Calendar.getInstance();
    //     cal.set(Calendar.HOUR_OF_DAY, timepicker.getCurrentHour());
    //     cal.set(Calendar.MINUTE,timepicker.getCurrentMinute());
    //     cal.set(Calendar.SECOND, 0);
    //     cal.set(Calendar.MILLISECOND, 0);
    //     manager.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),24*60*60*1000,pendingIntent);
    //
    // }

}
