package com.androidjson.sqlitelogin_androidjsoncom.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.androidjson.sqlitelogin_androidjsoncom.R;

public class QuizMainActivity extends AppCompatActivity {
    // used to get result back
    private static final int REQUEST_CODE_QUIZ = 1;

    public static final String SHARED_PREFS = "sharedPrefs";
    // will save highscore in this
    public static final String KEY_HIGHSCORE = "keyHighscore";

    private TextView textViewHighscore;

    private int highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quiz);
        textViewHighscore = findViewById(R.id.highscore);

        loadHighscore();

        Bundle extras = getIntent().getExtras();
        final String msg = extras.getString("Linear Card");

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        Button buttonStartQuiz = findViewById(R.id.buttonStart);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuiz(msg);
            }
        });
    }
    private void startQuiz(String type){

        if (type.equals("JavaQuiz")){
            String questionType = "java";
            // this is way to open a new activity
            Intent intent = new Intent(QuizMainActivity.this,QuizActivity.class);
            intent.putExtra("type of question", questionType);
            //gets results back when the activity starts
            startActivityForResult(intent,REQUEST_CODE_QUIZ);
        }
        else if (type.equals("HtmlQuiz")){
            String questionType = "html";
            // this is way to open a new activity
            Intent intent = new Intent(QuizMainActivity.this,QuizActivity.class);
            intent.putExtra("type of question", questionType);
            //gets results back when the activity starts
            startActivityForResult(intent,REQUEST_CODE_QUIZ);
        }
        else if (type.equals("CprogQuiz")){
            String questionType = "cprog";
            // this is way to open a new activity
            Intent intent = new Intent(QuizMainActivity.this,QuizActivity.class);
            intent.putExtra("type of question", questionType);
            //gets results back when the activity starts
            startActivityForResult(intent,REQUEST_CODE_QUIZ);
        }
        else if (type.equals("CppQuiz")){
            String questionType = "cpp";
            // this is way to open a new activity
            Intent intent = new Intent(QuizMainActivity.this,QuizActivity.class);
            intent.putExtra("type of question", questionType);
            //gets results back when the activity starts
            startActivityForResult(intent,REQUEST_CODE_QUIZ);
        }
        else{
            Toast.makeText(this, "The selected course Does not have  quiz", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ) {
            // will enter the loop if request code is from same activity
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(QuizActivity.extra_score, 0);
                if (score > highscore) {
                    updateHighscore(score);
                }
            }
        }
    }

    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        textViewHighscore.setText("Highscore: " + highscore);
    }

    private void updateHighscore(int highscoreNew) {
        // put new high score
        highscore = highscoreNew;
        textViewHighscore.setText("Highscore: " + highscore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        // using editor to save data
        editor.putInt(KEY_HIGHSCORE, highscore);
        editor.apply();
    }
}
