package mluds.lifttracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper mDbHelper;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    final static int ADD_EXERCISE_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get database cursor
        mDbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                DatabaseContract.Exercises._ID,
                DatabaseContract.Exercises.COLUMN_NAME_NAME
        };
        Cursor cursor = db.query(
                DatabaseContract.Exercises.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                DatabaseContract.Exercises.COLUMN_NAME_NAME + " DESC"
        );

        // Set up recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.exercises_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ExercisesAdapter(this, cursor);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void addExercise(View view) {
        Intent intent = new Intent(this, AddExerciseActivity.class);
        startActivityForResult(intent, ADD_EXERCISE_REQUEST);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_exercises, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_EXERCISE_REQUEST) {
            if (resultCode == RESULT_OK) {
                String name = data.getExtras().getString("name");
                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(DatabaseContract.Exercises.COLUMN_NAME_NAME, name);
                db.insert(DatabaseContract.Exercises.TABLE_NAME, null, values);
                Snackbar.make(
                        findViewById(R.id.exercises_view),
                        "Added " + name,
                        Snackbar.LENGTH_LONG
                ).show();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
