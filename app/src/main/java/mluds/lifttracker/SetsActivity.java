package mluds.lifttracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SetsActivity extends AppCompatActivity {

    private String mTitle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("onCreate");

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
        System.out.println("onSaveInstanceState");
        outState.putString("name", mTitle);
        System.out.println(outState.getString("name"));
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("onRestoreInstanceState");
        System.out.println(savedInstanceState);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onStop() {
        System.out.println("onStop");
        super.onStop();
    }

    public void addSet(View view) {
        Intent intent = new Intent(this, AddSetActivity.class);
        startActivity(intent);
    }
}
