package com.example.sbhatt.geoquiz.controller;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sbhatt.geoquiz.R;
import com.example.sbhatt.geoquiz.model.TrueFalse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;


public class QuizActivity extends ActionBarActivity {

    private Button mTrueBtn;
    private Button mFalseBtn;
    private Button mNextQtnBtn;
    private TextView mQuestionView;
    private TrueFalse trueFalseObj;
    private ArrayList<TrueFalse> mQuestionBankList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Getting View Objects for buttons
        mTrueBtn = (Button)findViewById(R.id.trueBtn);
        mFalseBtn = (Button)findViewById(R.id.falseBtn);
        mNextQtnBtn = (Button)findViewById(R.id.nextQuestnBtn);
        mQuestionView = (Button)findViewById(R.id.questionView);

        // Set OnClickListener for True button
        mTrueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(QuizActivity.this,R.string.true_pressed_toast,Toast.LENGTH_SHORT).show();
            }
        });

        // Set OnClickListener for False Button
        mFalseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(QuizActivity.this,R.string.false_pressed_toast,Toast.LENGTH_SHORT).show();
            }
        });

        // Set OnClickListener for Next Question Button
        mNextQtnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // Get Questions from QuestionBank
        trueFalseObj = new TrueFalse();
        mQuestionBankList = trueFalseObj.createQuestionBank(mQuestionBankList);

        // Create a ListIterator to move over the questions array list
        ListIterator questionIterator = mQuestionBankList.listIterator();
        if(questionIterator.hasNext())
        {
            int questionId = questionIterator.next().getQuestion();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
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
}
