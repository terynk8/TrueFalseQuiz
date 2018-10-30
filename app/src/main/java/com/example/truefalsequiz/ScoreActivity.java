package com.example.truefalsequiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    private TextView textViewDisplayScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        wireWidgets();
    }

    private void wireWidgets() {
        textViewDisplayScore = findViewById(R.id.textView_score_score);
        textViewDisplayScore.setText(getIntent().getStringExtra(MainActivity.EXTRA_SENT_MESSAGE));
    }
}
