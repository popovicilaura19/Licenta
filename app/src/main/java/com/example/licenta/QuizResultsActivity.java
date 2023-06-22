package com.example.licenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.example.licenta.utils.CreditType;
import com.example.licenta.utils.QuizResponse;

public class QuizResultsActivity extends AppCompatActivity {

    private Button btnReturnHome;
    private TextView tvResults;

    private Intent intent;
    private QuizResponse quizResponse;
    private CreditType creditType;

    private ActivityResultLauncher<Intent> launcher;

    public static final String QUIZ_KEY = "quizKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        intent = getIntent();
        initComponents();
        quizResponse = (QuizResponse) getIntent().getSerializableExtra(QUIZ_KEY);
        analyzeAnswers();
        tvResults.append(" " + creditType.toString() + "!");
    }

    private void initComponents() {
        tvResults = findViewById(R.id.id_tv_hereAreResults);
        btnReturnHome = findViewById(R.id.id_btn_returnHome);
        btnReturnHome.setOnClickListener(returnHomeEventListener());
    }

    private View.OnClickListener returnHomeEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK, intent);
                finish();
            }
        };
    }

    public void analyzeAnswers() {
        if (quizResponse.isStudent()) {
            if (!quizResponse.isWantBigLoan()) {
                creditType = CreditType.STUDENT_LOAN;
            } else {
                creditType = CreditType.PERSONAL_LOAN;
            }
        } else {
            if (quizResponse.isLikeToTravel()) {
                if (!quizResponse.isWantBigLoan()) {
                    creditType = CreditType.TRAVEL_LOAN;
                }
            } else {
                if (quizResponse.isLikeLuxury()) {
                    if (quizResponse.isMultipleInvestments()) {
                        creditType = CreditType.PERSONAL_LOAN;
                    } else {
                        creditType = CreditType.HOUSE_LOAN;
                        if (!quizResponse.isHaveEconomies()) {
                            creditType = CreditType.PERSONAL_LOAN;
                        }
                    }
                }
                {
                    if (quizResponse.isMultipleInvestments()) {
                        creditType = CreditType.PERSONAL_LOAN;
                    } else {
                        creditType = CreditType.TRAVEL_LOAN;
                    }
                }
            }
        }
    }

}