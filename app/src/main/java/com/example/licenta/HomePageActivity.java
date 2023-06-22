package com.example.licenta;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.licenta.async.Callback;
import com.example.licenta.dto.Client;
import com.example.licenta.dto.LoanRequest;
import com.example.licenta.dto.User;
import com.example.licenta.dto.adapter.LoanRequestAdapter;
import com.example.licenta.dto.adapter.PendingLoanRequestAdapter;
import com.example.licenta.services.ClientService;
import com.example.licenta.services.LoanRequestService;
import com.example.licenta.utils.RequestStatus;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    private TextView tvHello;
    private Button btnNewLoan;
    private Button btnLoanQuiz;
    private FloatingActionButton fabCalculator;
    private ListView lvActiveLoans;
    private ListView lvPendingLoans;
    private User user;
    private Client client = new Client();
    private List<LoanRequest> loanRequestList = new ArrayList<>();
    private List<LoanRequest> activeLoans = new ArrayList<>();
    private List<LoanRequest> pendingLoans = new ArrayList<>();
    private ClientService clientService;
    private LoanRequestService loanRequestService;
    private ActivityResultLauncher<Intent> launcher;
    public static final String USER_KEY = "userKey";
    public static final String CLIENT_KEY = "clientKey";
    public static final String PENDING_REQUEST_KEY = "pendingRequestKey";
    public static final String QUIZ_KEY = "quizKey";
    private LoanRequest selectedPendingLoanRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = (User) getIntent().getSerializableExtra(USER_KEY);
        clientService = new ClientService(getApplicationContext());
        clientService.getClientByUserId(user.getUserId(), getClientCallback());
        loanRequestService = new LoanRequestService(getApplicationContext());
        loanRequestService.getListOfLoansByClientId(user.getUserId(), getLoanListCallback());
        setContentView(R.layout.activity_home_page);
        initComponents();
        launcher = getLauncher();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(
                R.menu.main_menu,
                menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.id_item_requestHistory) {
            Toast.makeText(getApplicationContext(),
                            R.string.invalid_credentials,
                            Toast.LENGTH_LONG)
                    .show();
        } else {
            if (item.getItemId() == R.id.id_item_appointmentHistory) {
                Intent intent = new Intent(getApplicationContext(), AppointmentHistoryActivity.class);
                intent.putExtra(CLIENT_KEY, client);
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private Callback<List<LoanRequest>> getLoanListCallback() {
        return new Callback<List<LoanRequest>>() {
            @Override
            public void runResultOnUiThread(List<LoanRequest> result) {
                loanRequestList.addAll(result);
                for (LoanRequest loan : loanRequestList
                ) {
                    if (loan.getStatus() == RequestStatus.APPROVED)
                        activeLoans.add(loan);
                    else {
                        if (loan.getStatus() == RequestStatus.REQUIRES_AGENT_REVIEW) {
                            pendingLoans.add(loan);
                        }
                    }
                }
                lvActiveLoans = findViewById(R.id.id_lv_activeLoans);
                LoanRequestAdapter adapter = new LoanRequestAdapter(getApplicationContext(), R.layout.lv_active_loan_item, activeLoans, getLayoutInflater());
                lvActiveLoans.setAdapter(adapter);
                lvActiveLoans.setOnItemClickListener(getClickedActiveLoan());
                lvPendingLoans = findViewById(R.id.id_lv_pendingLoans);
                PendingLoanRequestAdapter adapterPending = new PendingLoanRequestAdapter(getApplicationContext(), R.layout.lv_pending_loan_item, pendingLoans, getLayoutInflater());
                lvPendingLoans.setAdapter(adapterPending);
                lvPendingLoans.setOnItemClickListener(getClickedPendingLoan());
            }
        };
    }

    private AdapterView.OnItemClickListener getClickedPendingLoan() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPendingLoanRequest = pendingLoans.get(position);
                AlertDialog dialog = new AlertDialog.Builder(HomePageActivity.this)
                        .setTitle(R.string.dialog_pending_title)
                        .setMessage(R.string.dialog_pending_message)
                        .setPositiveButton(R.string.dialog_yes_label, getPositivePendingDialogEvent())
                        .setNegativeButton(R.string.dialog_no_label, getNegativeDialogEvent())
                        .create();
                dialog.show();
            }
        };
    }

    private DialogInterface.OnClickListener getNegativeDialogEvent() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        };
    }


    private DialogInterface.OnClickListener getPositivePendingDialogEvent() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getApplicationContext(), AgentAppointmentActivity.class);
                intent.putExtra(PENDING_REQUEST_KEY, selectedPendingLoanRequest);
                startActivity(intent);
            }
        };
    }

    private AdapterView.OnItemClickListener getClickedActiveLoan() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        };
    }

    private Callback<Client> getClientCallback() {
        return new Callback<Client>() {
            @Override
            public void runResultOnUiThread(Client result) {
                client.setId(result.getId());
                client.setName(result.getName());
                client.setSurname(result.getSurname());
                client.setCnp(result.getCnp());
                client.setAddress(result.getAddress());
                client.setCity(result.getCity());
                client.setCountry(result.getCountry());
                client.setNationality(result.getNationality());
                client.setEmail(result.getEmail());
                client.setPhoneNumber(result.getPhoneNumber());
                tvHello = findViewById(R.id.id_tv_homePage);
                tvHello.append(" " + client.getSurname() + "!");
            }
        };
    }

    private ActivityResultLauncher<Intent> getLauncher() {
        ActivityResultCallback<ActivityResult> callback = getLoanRequestActivityResultCallback();
        return registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), callback);
    }

    private ActivityResultCallback<ActivityResult> getLoanRequestActivityResultCallback() {
        return new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_CANCELED) {
                } else {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        HomePageActivity.super.recreate();
                    }
                }
            }
        };
    }

    private void initComponents() {
        btnNewLoan = findViewById(R.id.id_btn_newLoanRequest);
        btnNewLoan.setOnClickListener(newLoanRequestEventListener());
        btnLoanQuiz = findViewById(R.id.id_btn_helper);
        btnLoanQuiz.setOnClickListener(loanQuizEventListener());
        fabCalculator = findViewById(R.id.id_fab_calculator);
        fabCalculator.setOnClickListener(calculatorOnClickListener());
    }

    private View.OnClickListener calculatorOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CalculatorActivity.class);
                setResult(RESULT_OK, intent);
                launcher.launch(intent);
            }
        };
    }

    private View.OnClickListener loanQuizEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog quizDialog = new AlertDialog.Builder(HomePageActivity.this)
                        .setTitle(R.string.dialog_quiz_title)
                        .setMessage(R.string.dialog_quiz_message)
                        .setPositiveButton(R.string.dialog_quiz_yes, getPositiveQuizDialogEvent())
                        .setNegativeButton(R.string.dialog_quiz_no, getNegativeQuizDialogEvent())
                        .create();
                quizDialog.show();
            }
        };
    }

    private DialogInterface.OnClickListener getNegativeQuizDialogEvent() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        };
    }

    private DialogInterface.OnClickListener getPositiveQuizDialogEvent() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), QuizPageOneActivity.class);
                intent.putExtra(QUIZ_KEY, client);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener newLoanRequestEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoanRequestFormActivity.class);
                intent.putExtra(CLIENT_KEY, (Serializable) client);
                setResult(RESULT_OK, intent);
//                finish();
                launcher.launch(intent);
            }
        };
    }
}