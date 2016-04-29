package com.williamf6894.plandroid;

import java.sql.Time;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by will on 08/04/16.
 */
public class PlanItem {
    /* Might need to add an ID */
    private int ID;
    private int sched_ID;
    private String title;
    private String tag;
    private String description;
    private Date triggerDate;
    private Time triggerTime;
    private boolean repeating;
    private boolean alarm;
    private String location;
    private int customTimeRepeat;
    private int numberOfRepetitions;

    public int getID(){
        return ID;
    }

    public int getNumberOfRepetitions() {
        return numberOfRepetitions;
    }

    public int getCustomTimeRepeat() {
        return customTimeRepeat;
    }

    public String getLocation() {
        return location;
    }

    public boolean isAlarm() {
        return alarm;
    }

    public boolean isRepeating() {
        return repeating;
    }

    public Time getTriggerTime() {
        return triggerTime;
    }

    public Date getTriggerDate() {
        return triggerDate;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getTag() {
        return tag;
    }

    public int getSched_ID() {
        return sched_ID;
    }


    /**
    * Default empty PlanItem constructor
    */
    public PlanItem() {
        super();
    }

    /**
    * Default PlanItem constructor
    */

    //This is a Plan
    public PlanItem(int ID, int sched_ID, String title, String tag, String description, Date triggerDate, Time triggerTime, boolean repeating, boolean alarm, String location, int customTimeRepeat, int numberOfRepetitions) {
        super();
        this.ID = ID;
        this.sched_ID = sched_ID;
        this.title = title;
        this.tag = tag;
        this.description = description;
        this.triggerDate = triggerDate;
        this.triggerTime = triggerTime;
        this.repeating = repeating;
        this.alarm = alarm;
        this.location = location;
        this.customTimeRepeat = customTimeRepeat;
        this.numberOfRepetitions = numberOfRepetitions;

    }

    // This is another type of plan

    public PlanItem(int ID, int sched_ID, String title, String tag, String description, String location) {
        super();
        this.ID = ID;
        this.sched_ID = sched_ID;
        this.title = title;
        this.tag = tag;
        this.description = description;
        this.location = location;


    }

    //This is a note.
    public PlanItem(int ID, int sched_ID, String title, String tag, String description){
        super();
        this.ID = ID;
        this.sched_ID = sched_ID;
        this.title = title;
        this.tag = tag;
        this.description = description;

    }

    /*
    //This is a study note
    public PlanItem(int ID, String title, String tag, String description, String help){
        super();
        this.ID = ID;
        this.title = title;
        this.tag = tag;
        this.description = description;

        //this kind of note will remind you, just have to set when
    }

    //This is a default study note
    public PlanItem(int ID, String title, String tag, String description){
        super();
        this.ID = ID;
        this.title = title;
        this.tag = tag;
        this.description = description;

    }*/


}
