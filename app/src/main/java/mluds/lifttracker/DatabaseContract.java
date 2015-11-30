package mluds.lifttracker;

import android.provider.BaseColumns;

public final class DatabaseContract {
    public DatabaseContract() {}

    public static abstract class Exercises implements BaseColumns {
        public static final String TABLE_NAME = "exercises";
        public static final String COLUMN_NAME_NAME = "name";
    }

    public static abstract class Sets implements BaseColumns {
        public static final String TABLE_NAME = "sets";
        public static final String COLUMN_NAME_EXERCISE_ID = "exercise_id";
        public static final String COLUMN_NAME_REPS = "reps";
    }
}
