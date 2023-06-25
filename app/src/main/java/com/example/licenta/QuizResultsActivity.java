package com.example.licenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.example.licenta.services.MailService;
import com.example.licenta.utils.CreditType;
import com.example.licenta.utils.QuizResponse;

public class QuizResultsActivity extends AppCompatActivity {

    private Button btnReturnHome;
    private TextView tvResults;
    private ImageView imgQuizResult;
    private CheckBox yesSendMail;

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
        setImageBasedOnResult();
    }

    private void initComponents() {
        tvResults = findViewById(R.id.id_tv_hereAreResults);
        imgQuizResult = findViewById(R.id.id_img_quizResult);
        yesSendMail = findViewById(R.id.id_checkBox_yesSendMail_quizResults);
        btnReturnHome = findViewById(R.id.id_btn_returnHome);
        btnReturnHome.setOnClickListener(returnHomeEventListener());
    }

    public void setImageBasedOnResult() {
        if (creditType.equals(CreditType.STUDENT_LOAN)) {
            imgQuizResult.setImageResource(R.drawable.student);
        } else {
            if (creditType.equals(CreditType.TRAVEL_LOAN)) {
                imgQuizResult.setImageResource(R.drawable.travel);
            } else {
                if (creditType.equals(CreditType.HOUSE_LOAN)) {
                    imgQuizResult.setImageResource(R.drawable.house);
                }
            }
        }
    }

    private View.OnClickListener returnHomeEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesSendMail.isChecked()) {
                    sendEmail();
                }
                setResult(RESULT_OK, intent);
                finish();
            }
        };
    }

    protected void sendEmail() {
        String[] TO = new String[]{"laurica.popovici@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Results For DigitalBank Credit Loan Quiz");
        emailIntent.putExtra(Intent.EXTRA_TEXT, MailService.setTextForEmailQuizResults(creditType));
        emailIntent.setType("message/rfc822");
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ignored) {
        }
    }

    public void analyzeAnswers() {
        creditType = CreditType.PERSONAL_LOAN;
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
            }
        }
    }

}