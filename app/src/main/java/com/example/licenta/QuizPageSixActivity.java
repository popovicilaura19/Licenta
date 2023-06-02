package com.example.licenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

public class QuizPageSixActivity extends AppCompatActivity {

    private CheckBox checkedYes;
    private CheckBox checkedNo;
    private Button btnGetResults;

    private Intent intent;

    private ActivityResultLauncher<Intent> launcher;

    public static final String QUIZ_KEY = "quizKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page_six);

        intent = getIntent();
        initComponents();
    }

    private void initComponents() {
        btnGetResults = findViewById(R.id.id_btn_getResults);
        btnGetResults.setOnClickListener(getResultsOfQuizEventListener());
    }

    private View.OnClickListener getResultsOfQuizEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuizResultsActivity.class);
//                intent.putExtra(QUIZ_KEY, client);
                setResult(RESULT_OK, intent);
                finish();
                startActivity(intent);
            }
        };
    }
}