package com.example.licenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.example.licenta.utils.QuizResponse;

public class QuizPageFiveActivity extends AppCompatActivity {

    private CheckBox checkedYes;
    private CheckBox checkedNo;
    private Button btnNext;

    private Intent intent;
    private QuizResponse quizResponse;

    private ActivityResultLauncher<Intent> launcher;

    public static final String QUIZ_KEY = "quizKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page_five);

        intent = getIntent();
        initComponents();
        quizResponse = (QuizResponse) getIntent().getSerializableExtra(QUIZ_KEY);
    }

    private void initComponents() {
        checkedYes = findViewById(R.id.id_checkBox_yes_bigMoney);
        checkedNo = findViewById(R.id.id_checkBox_no_bigMoney);
        btnNext = findViewById(R.id.id_btn_quizFiveNext);
        btnNext.setOnClickListener(goToPageSixOfQuizEventListener());
    }

    private View.OnClickListener goToPageSixOfQuizEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkedYes.isChecked()) {
                    quizResponse.setWantBigLoan(true);
                } else {
                    if (checkedNo.isChecked()) {
                        quizResponse.setWantBigLoan(false);
                    }
                }
                Intent intent = new Intent(getApplicationContext(), QuizPageSixActivity.class);
                intent.putExtra(QUIZ_KEY, quizResponse);
                setResult(RESULT_OK, intent);
                finish();
                startActivity(intent);
            }
        };
    }
}