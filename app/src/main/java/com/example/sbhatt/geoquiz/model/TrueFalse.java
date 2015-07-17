package com.example.sbhatt.geoquiz.model;

import com.example.sbhatt.geoquiz.R;

import java.util.ArrayList;

/**
 * Created by sbhatt on 7/17/2015.
 */
public class TrueFalse {

    private int mQuestion;
    private boolean mQuestionAnswer;

    public TrueFalse() {}

    private TrueFalse(int mQuestion, boolean mQuestionAnswer) {
        this.mQuestion = mQuestion;
        this.mQuestionAnswer = mQuestionAnswer;
    }

    public ArrayList<TrueFalse> createQuestionBank(ArrayList<TrueFalse> questionsList) {
        // Instantiating ArrayList
        if(questionsList!= null)
            questionsList = new ArrayList<TrueFalse>();

        questionsList.add(new TrueFalse(R.string.question_ocean, true));
        questionsList.add(new TrueFalse(R.string.question_africa, true));
        questionsList.add(new TrueFalse(R.string.question_americas, true));
        questionsList.add(new TrueFalse(R.string.question_asia, true));
        questionsList.add(new TrueFalse(R.string.question_mideast, true));

        return questionsList;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public TrueFalse setQuestion(int mQuestion) {
        this.mQuestion = mQuestion;
        return this;
    }

    public boolean isQuestionAnswer() {
        return mQuestionAnswer;
    }

    public TrueFalse setQuestionAnswer(boolean mQuestionAnswer) {
        this.mQuestionAnswer = mQuestionAnswer;
        return this;
    }
}
