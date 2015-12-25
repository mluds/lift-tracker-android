package mluds.lifttracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import org.w3c.dom.Text;

public class AddSetActivity extends AppCompatActivity {

    EditText inputWeight;
    EditText inputReps;
    TextInputLayout inputLayoutWeight;
    TextInputLayout inputLayoutReps;
    DatabaseHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_set);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inputWeight = (EditText) findViewById(R.id.input_weight);
        inputReps = (EditText) findViewById(R.id.input_reps);
        inputLayoutWeight = (TextInputLayout) findViewById(R.id.input_layout_weight);
        inputLayoutReps = (TextInputLayout) findViewById(R.id.input_layout_reps);

        mDbHelper = new DatabaseHelper(this);
        refreshDatabase();
    }

    private void refreshDatabase() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_set, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_submit:
                String weight = inputWeight.getText().toString();
                String reps = inputReps.getText().toString();
                if (weight.length() == 0) {
                    inputLayoutWeight.setError(getString(R.string.input_error_message));
                } else if (reps.length() == 0 ) {
                    inputLayoutReps.setError(getString(R.string.input_error_message));
                } else {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("weight", weight);
                    intent.putExtra("reps", reps);
                    setResult(RESULT_OK, intent);
                    NavUtils.navigateUpFromSameTask(this);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
