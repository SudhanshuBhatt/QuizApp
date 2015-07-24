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
    private int mCurrentQuestionIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Getting View Objects for buttons
        mTrueBtn = (Button)findViewById(R.id.trueBtn);
        mFalseBtn = (Button)findViewById(R.id.falseBtn);
        mNextQtnBtn = (Button)findViewById(R.id.nextQuestnBtn);
        mQuestionView = (TextView)findViewById(R.id.questionView);

        // Create Questions and add them to TrueFalse QuestionBank
        trueFalseObj = new TrueFalse();
        mQuestionBankList = new ArrayList<TrueFalse>();
        mQuestionBankList = trueFalseObj.createQuestionBank(mQuestionBankList);

        // Set first question on the screen
        fetchNewQuestion(mCurrentQuestionIndex);

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
                fetchNewQuestion(mCurrentQuestionIndex);

            }
        });
    }

    private void fetchNewQuestion(int mCurrentQuestionIndex) {
        if(mCurrentQuestionIndex < mQuestionBankList.size()-1)
        {
            // Increasing the index to point to next question
            this.mCurrentQuestionIndex = mCurrentQuestionIndex+1;
            int questionId = mQuestionBankList.get(this.mCurrentQuestionIndex).getQuestion();
            // Setting the TextView with updated question
            mQuestionView.setText(questionId);
        }
    }

    private boolean checkAnswer(boolean a) {

        return false;
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
