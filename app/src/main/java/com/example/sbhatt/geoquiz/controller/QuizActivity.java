package com.example.sbhatt.geoquiz.controller;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sbhatt.geoquiz.R;
import com.example.sbhatt.geoquiz.model.TrueFalse;

import java.util.ArrayList;

public class QuizActivity extends ActionBarActivity {

    private Button mTrueBtn;
    private Button mFalseBtn;
    private ImageButton mNextQtnBtn;
    private ImageButton mPrevQtnBtn;
    private Button mCheatBtn;
    private TextView mQuestionView;
    private TrueFalse trueFalseObj;
    private ArrayList<TrueFalse> mQuestionBankList;
    private int mCurrentQuestionIndex = -1;
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Logging that onCreate was clalled
        Log.d(TAG,"onCreate(Bundle) was called");

        // Getting View Objects for buttons
        mTrueBtn = (Button)findViewById(R.id.trueBtn);
        mFalseBtn = (Button)findViewById(R.id.falseBtn);
        mNextQtnBtn = (ImageButton)findViewById(R.id.nextQuestnBtn);
        mQuestionView = (TextView)findViewById(R.id.questionView);
        mPrevQtnBtn = (ImageButton)findViewById(R.id.previousQuestnBtn);
        mCheatBtn = (Button) findViewById(R.id.cheat_btn);

        // Create Questions and add them to TrueFalse QuestionBank
        trueFalseObj = new TrueFalse();
        mQuestionBankList = new ArrayList<TrueFalse>();
        mQuestionBankList = trueFalseObj.createQuestionBank(mQuestionBankList);

        // Fetching the current index for question from bundle if exists
        if(savedInstanceState!= null)
            mCurrentQuestionIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        // Set first question on the screen
        if(mCurrentQuestionIndex == -1)
            mCurrentQuestionIndex++;
        fetchNewQuestion(mCurrentQuestionIndex);

        // Set OnClickListener for True button
        mTrueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isRight = checkAnswer(true);
                if(isRight)
                    Toast.makeText(QuizActivity.this,R.string.true_pressed_toast,Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(QuizActivity.this,R.string.false_pressed_toast,Toast.LENGTH_SHORT).show();
            }
        });

        // Set OnClickListener for False Button
        mFalseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isRight = checkAnswer(false);
                if(isRight)
                    Toast.makeText(QuizActivity.this,R.string.true_pressed_toast,Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(QuizActivity.this,R.string.false_pressed_toast,Toast.LENGTH_SHORT).show();
            }
        });

        // Set OnClickListener for Next Question Button
        mNextQtnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Increasing the index to point to next question
                mCurrentQuestionIndex++;
                fetchNewQuestion(mCurrentQuestionIndex);

            }
        });

        // Set Listener on TextView to get new question
        mQuestionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchNewQuestion(mCurrentQuestionIndex);
            }
        });

        // Set On click listener on the previous question
        mPrevQtnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Decreasing the global question index
                mCurrentQuestionIndex--;
                fetchLastQuestion(mCurrentQuestionIndex);
            }
        });

        // Set on click listener for the cheat button
        mCheatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(QuizActivity.this,CheatActivity.class);
                startActivity(newIntent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"onPause() called");
    }

    @Override
    public void onSaveInstanceState (Bundle savedState) {
        super.onSaveInstanceState(savedState);
        Log.d(TAG, "OnSavedInstance()");
        savedState.putInt(KEY_INDEX,mCurrentQuestionIndex);
    }

    private void fetchLastQuestion(int mCurrentQuestionIndex) {
        if(mCurrentQuestionIndex > 0)
        {
            int questionId = mQuestionBankList.get(mCurrentQuestionIndex-1).getQuestion();
            // Setting the previous question on Question View text box
            mQuestionView.setText(questionId);
        }
        else{
            Toast.makeText(this,R.string.first_questn,Toast.LENGTH_SHORT).show();
        }

    }

    private void fetchNewQuestion(int mCurrentQuestionIndex) {
        if(mCurrentQuestionIndex <= mQuestionBankList.size()-1)
        {
            int questionId = mQuestionBankList.get(this.mCurrentQuestionIndex).getQuestion();
            // Setting the TextView with updated question
            mQuestionView.setText(questionId);
        }else{
            Toast.makeText(this,R.string.last_questn,Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkAnswer(boolean a) {

        boolean answer = mQuestionBankList.get(this.mCurrentQuestionIndex).isQuestionAnswer();
        if(a == answer)
            return true;
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
