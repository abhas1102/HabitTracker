package com.example.yes.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yes.habittracker.HabitContract;
import com.example.yes.habittracker.HabitContract.HabitEntry;
import com.example.yes.habittracker.HabitDbHelper;



public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    //Database helper private variable
    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Instance of Database Helper created in current acitivity

        mDbHelper = new HabitDbHelper(this);

        //operations on Database

        insertHabit();
        readDatabase();
        displayDatabaseInfo();
    }

    //return the Cursor Object,which contains all data of database in row and columns which has been selected in projection

    private Cursor readDatabase() {
        //Database created and reading info from it

        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        // Select column for displaying
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_ACTIVITY_NAME,
                HabitEntry.COLUMN_ACTIVITY_TYPE,
                HabitEntry.COLUMN_ACTIVITY_DATE,
                HabitEntry.COLUMN_ACTIVITY_DURATION,
                HabitEntry.COLUMN_ACTIVITY_STATUS
        };

        // quereing SQL info
        return database.query(
                HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
    }

    private void insertHabit() {
        // Now take Database into write mode
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and Yoga attributes are the values.
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_ACTIVITY_NAME, getString(R.string.dummy_data_activity_name));
        values.put(HabitEntry.COLUMN_ACTIVITY_TYPE, HabitEntry.TYPE_LEARN);
        values.put(HabitEntry.COLUMN_ACTIVITY_DATE, 1499731200);
        values.put(HabitEntry.COLUMN_ACTIVITY_DURATION, 7);
        values.put(HabitEntry.COLUMN_ACTIVITY_STATUS, HabitEntry.STATUS_DONE);

        long newRowId = database.insert(HabitContract.HabitEntry.TABLE_NAME, null, values);

        // Displaying proper message depends on result of saving data to DB
        if (newRowId == -1) {
            Log.i(LOG_TAG, getString(R.string.database_saving_error_log_msg) + newRowId);
            Toast.makeText(this, getString(R.string.database_saving_error_toast_msg) + newRowId, Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, getString(R.string.database_success_saving_toast_msng) + newRowId, Toast.LENGTH_SHORT).show();
            Log.i(LOG_TAG, getString(R.string.database_saving_success_log_msng) + newRowId);
        }


    }

    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        // using rawQuery to get Cursor
        Cursor cursor = database.rawQuery("SELECT * FROM " + HabitContract.HabitEntry.TABLE_NAME, null);
        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // habit table in the database).
            TextView displayView = (TextView) findViewById(R.id.text_view);
            displayView.setText("Number of rows in habits table " + cursor.getCount());
            Log.i(LOG_TAG, getString(R.string.database_rows_amount_log_msng) + cursor.getCount());
        } finally {
            //clsoing cursor
            cursor.close();
        }
    }
}
