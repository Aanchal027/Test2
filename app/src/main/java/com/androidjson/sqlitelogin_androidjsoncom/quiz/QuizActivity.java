package com.androidjson.sqlitelogin_androidjsoncom.quiz;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.androidjson.sqlitelogin_androidjsoncom.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {

    // used to store score that is to be returned
    public static final String extra_score = "extrascore";

    // will give max 30s for each question
    private static final long countdown_in_millis = 30000;

    private static final String Key_score = "keyscore";
    private static final String Key_Question_Count = "KeyQuestionCount";
    private static final String Key_Millis_Left = "keyMillisLeft";
    private static final String Key_Answered = "keyAnswered";
    private static final String Key_Question_List = "keyQuestionList";

    private TextView textviewQuestion;
    private TextView textviewScore;
    private TextView textviewQuestionCount;
    private TextView textviewCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button buttonConfirmNext;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColourDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private ArrayList<Question> questionList ;

    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;
    private int score;
    private boolean answered;


    private long backPressedTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        textviewQuestion = findViewById(R.id.textViewQuestion);
        textviewScore = findViewById(R.id.textViewScore);
        textviewQuestionCount = findViewById(R.id.textViewQuestionCount);
        textviewCountDown = findViewById(R.id.countdown);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio1);
        rb2 = findViewById(R.id.radio2);
        rb3 = findViewById(R.id.radio3);
        buttonConfirmNext = findViewById(R.id.confirm);

        textColorDefaultRb = rb1.getTextColors();

        textColourDefaultCd = textviewCountDown.getTextColors();

        Bundle extras = getIntent().getExtras();
        final String typeOfQuestion = extras.getString("type of question");

        if (savedInstanceState == null){
            // this condition will load saved state
            QuizDbHelper dbHelper = new QuizDbHelper(this);
            // when getAllQuestion will be called it will create database too
            questionList = dbHelper.getAllQuestions(typeOfQuestion);

            questionCountTotal = questionList.size();
            // shuffle all the questions in random order
            Collections.shuffle(questionList);

            // shows question when activity is started
            showNextQuestion();
        } else {
            questionList = savedInstanceState.getParcelableArrayList(Key_Question_List);
            questionCountTotal = questionList.size();
            questionCounter = savedInstanceState.getInt(Key_Question_Count);
            currentQuestion = questionList.get(questionCounter-1);
            score = savedInstanceState.getInt(Key_score);
            timeLeftInMillis = savedInstanceState.getLong(Key_Millis_Left);
            answered = savedInstanceState.getBoolean(Key_Answered);

            if (!answered){
                startCountDown();
            }else{
                updateCountDownText();
                showSolution();
            }
        }

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!answered){
                    if (rb1.isChecked()  || rb2.isChecked() || rb3.isChecked()){
                        checkAnswer();
                    }else{
                        Toast.makeText(QuizActivity.this, "PLease Select an Answer !", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    showNextQuestion();
                }
            }
        });

    }

    private void showNextQuestion(){
        // setting color of options as default for every new question
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);

        rbGroup.clearCheck();//unselects all buttons

        if (questionCounter < questionCountTotal){

            // if questions are left we will come in this loop

            currentQuestion = questionList.get(questionCounter);
            textviewQuestion.setText(currentQuestion.getQuestion());

            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());

            //because we increase the counter here we start at one
            questionCounter++;
            //displays the question count in screen
            textviewQuestionCount.setText("Question: "+ questionCounter + "/" + questionCountTotal);

            answered = false;

            buttonConfirmNext.setText("Confirm");

            timeLeftInMillis = countdown_in_millis;
            startCountDown();
        }else{
            finishQuiz();
        }
    }

    private void startCountDown(){
        countDownTimer = new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    private void updateCountDownText(){
        int minutes =(int) (timeLeftInMillis / 1000)/60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted  = String.format(Locale.getDefault(),"%02d:%02d", minutes,seconds);

        textviewCountDown.setText(timeFormatted);

        if (timeLeftInMillis < 10000){
            textviewCountDown.setTextColor(Color.RED);
        }else {
            textviewCountDown.setTextColor(textColourDefaultCd);
        }

    }

    private void checkAnswer(){
        answered = true;

        countDownTimer.cancel();

        //selects the option chosen by user
        RadioButton rbselected = findViewById(rbGroup.getCheckedRadioButtonId());

        // we used +1 as our index of answers starts with 1
        int answerNumber = rbGroup.indexOfChild(rbselected) + 1;

        if (answerNumber == currentQuestion.getAnswerNr()){
            score++;
            textviewScore.setText("Score: " + score);
        }
        // it will show solution anyways
        showSolution();
    }

    private void showSolution(){
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);

        //changes the color of right answer to green
        switch (currentQuestion.getAnswerNr()) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                textviewQuestion.setText("Answer 1 is correct");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textviewQuestion.setText("Answer 2 is correct");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                textviewQuestion.setText("Answer 3 is correct");
                break;
        }

        //if question remains the button will display accordingly
        if (questionCounter < questionCountTotal) {
            buttonConfirmNext.setText("Next");
        } else {
            buttonConfirmNext.setText("Finish");
        }
    }

    private void finishQuiz(){
        Intent resultIntent = new Intent();

        //extra score will have value of score
        resultIntent.putExtra(extra_score,score);

        setResult(RESULT_OK, resultIntent);
        finish();//closes activity
    }

    @Override
    public void onBackPressed() {
        //if user quits the game in between this will hanlde it
        // and upadted rsult will be shown
        if (backPressedTime + 2000 >  System.currentTimeMillis()) {
            //if user press back button in between 2 seconds we will return to main activity
            finishQuiz();
        }
        else {
            // will be displayed when late
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }
        //sets current time
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (countDownTimer != null){
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Key_score,score);
        outState.putInt(Key_Question_Count, questionCounter);
        outState.putLong(Key_Millis_Left, timeLeftInMillis);
        outState.putBoolean(Key_Answered, answered);
        outState.putParcelableArrayList(Key_Question_List, questionList);
    }
}
