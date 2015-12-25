package mluds.lifttracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SetsActivity extends AppCompatActivity {

    private String mTitle = null;
    final static int ADD_SET_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sets);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            mTitle = extras.getString("name");
        }
        if (savedInstanceState != null) {
            mTitle = savedInstanceState.getString("name");
        }

        if (mTitle != null) {
            setTitle(mTitle);
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("name", mTitle);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println(savedInstanceState);
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void addSet(View view) {
        Intent intent = new Intent(this, AddSetActivity.class);
        startActivityForResult(intent, ADD_SET_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_SET_REQUEST) {
            if (resultCode == RESULT_OK) {
                
            }
        }
    }
}
