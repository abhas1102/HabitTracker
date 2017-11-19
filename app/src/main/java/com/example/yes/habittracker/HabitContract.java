package com.example.yes.habittracker;

import android.provider.BaseColumns;

/**
 * Created by yes on 11/19/2017.
 */

public final class HabitContract {
    //preventing accidentally instantiated HabitContract class
    private HabitContract(){

    }

    public static abstract class HabitEntry implements BaseColumns {
        public static final String TABLE_NAME = "habits";

        // (INTEGER) Unique ID number for the habit
        public final static String _ID = BaseColumns._ID;

        public static final String COLUMN_ACTIVITY_NAME = "name";
        public static final String COLUMN_ACTIVITY_TYPE = "type";
        public static final String COLUMN_ACTIVITY_DATE = "date";
        public static final String COLUMN_ACTIVITY_DURATION = "duration";
        public static final String COLUMN_ACTIVITY_STATUS = "status";


         // Possible values for the STATUS of habits of type Integer.

        public static final int STATUS_PENDING = 0;
        public static final int STATUS_DONE = 1;
        public static final int STATUS_VOID = 2;


           //Possible values for the TYPE of habits.

        public static final int OTHER = 0;
        public static final int TYPE_TALK = 1;
        public static final int TYPE_SPORT = 2;
        public static final int TYPE_HOBBY = 3;
        public static final int TYPE_MOVIES = 4;
        public static final int TYPE_WORK = 5;

    }


}
