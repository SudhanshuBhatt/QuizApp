package com.example.sbhatt.geoquiz.controller;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sbhatt.geoquiz.R;

public class CheatActivity extends ActionBarActivity {

    private boolean mCorrectAnswer;
    private TextView mShowAnswerView;
    private Button mShowAnswerBtn;

    public static final String CORRECT_ANSWER = "correct_answer";
    public static final String RESULT_SHOWN_USER_CHEATED = "result_shown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        // Get resource id for xml elements
        mShowAnswerView = (TextView) findViewById(R.id.answer_text_view);
        mShowAnswerBtn = (Button) findViewById(R.id.show_answer_btn);

        // If Show button was not called then setResult as false
        checkIfAnswerShown(false);

        // Setting an On Click listener to Show Answer button
        mShowAnswerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfAnswerShown(true);
                if(mCorrectAnswer) {
                    mShowAnswerView.setText(R.string.true_button);
                }
                else
                    mShowAnswerView.setText(R.string.false_button);
            }
        });

        // Get the results of the current answer from QuizActivity
        mCorrectAnswer = getIntent().getBooleanExtra(CORRECT_ANSWER,false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cheat, menu);
        return true;
    }

    private void checkIfAnswerShown(boolean wasAnswerShown) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(RESULT_SHOWN_USER_CHEATED,wasAnswerShown);
        setResult(RESULT_OK,resultIntent);
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
}
