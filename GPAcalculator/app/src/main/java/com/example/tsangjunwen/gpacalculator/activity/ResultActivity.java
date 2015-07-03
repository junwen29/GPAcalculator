package com.example.tsangjunwen.gpacalculator.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.tsangjunwen.gpacalculator.helper.PreferenceStore;
import com.example.tsangjunwen.gpacalculator.R;

public class ResultActivity extends ActionBarActivity {

    private TextView mCurrentGPA;

    private double mScore;
    private int mCredits;
    private double mOldGPA;
    private int mOldCredits;

    private PreferenceStore mStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mStore = new PreferenceStore(this);
        mOldCredits = mStore.getModuleCredits();
        mOldGPA = mStore.getGPA();

        initCurrentGPA();

        Intent i = getIntent();
        mScore = i.getDoubleExtra("score", 0);
        mCredits = i.getIntExtra("credits", 0);

        double cap = calculateCAP();
        storeNewValues(cap, mOldCredits + mCredits);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
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

    private void initCurrentGPA(){
        mCurrentGPA = (TextView) findViewById(R.id.currentGPA);
    }

    private double calculateCAP(){
        return ((mOldGPA * mOldCredits) + (mScore * mCredits)) / (mOldCredits + mCredits);
    }

    private void storeNewValues(double cap, int credits){
        mStore.storeGPA(cap);
        mStore.storeModuleCredits(credits);
        mCurrentGPA.setText("Your current CAP: " + Double.toString(cap));
    }
}
