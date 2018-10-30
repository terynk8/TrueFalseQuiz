package com.example.truefalsequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import dalvik.annotation.TestTarget;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private Quiz quiz;
    private TextView textViewQuestion;
    private Button buttonTrue;
    private Button buttonFalse;

    public static final String EXTRA_SENT_MESSAGE = "the message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        setListeners();
        initializeQuiz();
        displayNextQuestion();

    }

    private void initializeQuiz() {
        //call readFile method on the resource you get
        String jsonString = readTextFile(getResources().openRawResource(R.raw.questions));
        //create a gson object
        Gson gson = new Gson();
        //read your json file into an array of questions
        Question[] questions = gson.fromJson(jsonString, Question[].class);
        //convert your array to a list using the Arrays utility class
        List<Question> questionList = Arrays.asList(questions);
        //verify that it read everything properly
        Log.d(TAG, "onCreate: " + questionList.toString());

        quiz = new Quiz(questionList);
    }

    private void setListeners() {
        buttonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quiz.checkAnswer(false);
                displayNextQuestion();
            }
        });
        buttonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quiz.checkAnswer(true);
                displayNextQuestion();
            }
        });
    }

    private void displayNextQuestion() {
        Toast.makeText(this, String.valueOf(quiz.getScore()), Toast.LENGTH_SHORT).show();
        if(quiz.hasMoreQuestions()) {
            textViewQuestion.setText(quiz.getMoreQuestions().getQuestion());
        } else {
            //create intent
            //the arguments are where you are coming from (this) and where you are going (TargetActivity)
            Intent intentSendScore = new Intent(MainActivity.this, ScoreActivity.class);

            //package the text into the intent
            intentSendScore.putExtra(EXTRA_SENT_MESSAGE, String.valueOf(quiz.getScore()));

            //start the new activity
            startActivity(intentSendScore);

        }

    }



    private void wireWidgets() {
        buttonTrue = findViewById(R.id.button_main_true);
        buttonFalse = findViewById(R.id.button_main_false);
        textViewQuestion = findViewById(R.id.textView_main_question);
    }

    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }
}
