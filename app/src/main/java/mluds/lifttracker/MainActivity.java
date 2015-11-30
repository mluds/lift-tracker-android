package mluds.lifttracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get database cursor
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

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
        startActivity(new Intent(this, AddExerciseActivity.class));
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
}
