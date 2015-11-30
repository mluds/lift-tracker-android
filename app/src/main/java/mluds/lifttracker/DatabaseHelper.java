package mluds.lifttracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import mluds.lifttracker.DatabaseContract.Exercises;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String NAME = "LiftTracker.db";
    public static final int VERSION = 1;

    public static final String SQL_CREATE_EXERCISES =
            "CREATE TABLE " + Exercises.TABLE_NAME + "(" +
            Exercises._ID + " INTEGER PRIMARY KEY," +
            Exercises.COLUMN_NAME_NAME + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_EXERCISES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
