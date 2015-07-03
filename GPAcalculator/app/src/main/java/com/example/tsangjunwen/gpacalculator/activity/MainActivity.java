package com.example.tsangjunwen.gpacalculator.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tsangjunwen.gpacalculator.helper.PreferenceStore;
import com.example.tsangjunwen.gpacalculator.R;

// JK: My first comment for git!
public class MainActivity extends ActionBarActivity {

    private PreferenceStore mStore;

    private Toolbar mAppBar;
    private TextView mCurrentGPA;

    private Button mButton;

    private EditText mScoreField;
    private EditText mCreditsField;

    private int mCredits;
    private double mScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStore = new PreferenceStore(this);

        //initate stuff
//        initAppBar();
//        setSupportActionBar(mAppBar);
        initCurrentGPA();
        initEditText();
        initButton();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(storeFields()){
                    Intent i = new Intent(MainActivity.this, ResultActivity.class);
                    i.putExtra("score", mScore);
                    i.putExtra("credits", mCredits);
                    startActivity(i);
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateGPA();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    private void initAppBar(){
//        mAppBar = (Toolbar)findViewById(R.id.bar);
//    }


    private void initCurrentGPA(){
        mCurrentGPA = (TextView)findViewById(R.id.currentGPA);
        double gpa = mStore.getGPA();
        mCurrentGPA.setText("Current GPA: " + Double.toString(gpa));
    }

    private void initButton(){
        mButton = (Button) findViewById(R.id.button);
    }

    private void initEditText(){
        mScoreField = (EditText) findViewById(R.id.module_score);
        mCreditsField = (EditText) findViewById(R.id.module_credits);
    }

    private boolean storeFields(){
        String score = mScoreField.getText().toString().trim();
        String credits = mCreditsField.getText().toString().trim();


        if (TextUtils.isEmpty(score)) {
            Toast.makeText(this,"Please Enter A Score", Toast.LENGTH_SHORT);
            return false;
        }
        else {
            mScore = Double.parseDouble(score);
        }

        if (TextUtils.isEmpty(credits)) {
            Toast.makeText(this, "Please Enter The credits", Toast.LENGTH_SHORT);
            return false;
        }
        else {
            mCredits = Integer.parseInt(credits);
        }

        return true;
    }

    private void updateGPA(){
        double gpa = mStore.getGPA();
        mCurrentGPA.setText("Current GPA: " + Double.toString(gpa));
    }
}
