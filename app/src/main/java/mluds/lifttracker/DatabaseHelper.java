package mluds.lifttracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import mluds.lifttracker.DatabaseContract.Exercises;
import mluds.lifttracker.DatabaseContract.Sets;
import mluds.lifttracker.DatabaseContract.SetsExercisesIndex;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String NAME = "LiftTracker.db";
    public static final int VERSION = 1;

    public static final String SQL_CREATE_EXERCISES =
            "CREATE TABLE " + Exercises.TABLE_NAME + "(" +
            Exercises._ID + " INTEGER PRIMARY KEY," +
            Exercises.COLUMN_NAME_NAME + " TEXT)";

    public static final String SQL_CREATE_SETS =
            "CREATE TABLE " + Sets.TABLE_NAME + "(" +
            Sets._ID + " INTEGER PRIMARY KEY," +
            Sets.COLUMN_NAME_EXERCISE_ID + " INTEGER REFERENCES " + Exercises.TABLE_NAME + "," +
            Sets.COLUMN_NAME_TIMESTAMP + " INTEGER," +
            Sets.COLUMN_NAME_WEIGHT + " TEXT," +
            Sets.COLUMN_NAME_UNIT + " TEXT," +
            Sets.COLUMN_NAME_REPS + " TEXT)";

    public static final String SQL_CREATE_SETS_EXERCISES_INDEX =
            "CREATE INDEX " + SetsExercisesIndex.INDEX_NAME + " ON " +
            SetsExercisesIndex.TARGET_TABLE + "(" + SetsExercisesIndex.TARGET_COLUMN + ")";

    public DatabaseHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_EXERCISES);
        db.execSQL(SQL_CREATE_SETS);
        db.execSQL(SQL_CREATE_SETS_EXERCISES_INDEX);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
