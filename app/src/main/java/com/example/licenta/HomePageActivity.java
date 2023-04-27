package com.example.licenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.licenta.async.Callback;
import com.example.licenta.dto.Client;
import com.example.licenta.dto.User;
import com.example.licenta.services.ClientService;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    private TextView tvHello;
    private Button btnNewLoan;
    private User user;
    private Client client = new Client();
    private ClientService clientService;
    private ActivityResultLauncher<Intent> launcher;
    //    private Intent intent;
    public static final String USER_KEY = "userKey";
    private List<Client> clientList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = (User) getIntent().getSerializableExtra(USER_KEY);
        clientList.add(new Client(1, "Popescu", "Ion", "1950302450012", "Str Toamnei nr 23", "Bucuresti", "Romania", "RO", "popescuion@gmail.com", "+40711123456"));
        clientList.add(new Client(2, "Ionescu", "Maria", "2970504450012", "Str Doamnei nr 23", "Bucuresti", "Romania", "RO", "ionescumaria@gmail.com", "+40722123456"));
        clientService = new ClientService(getApplicationContext());
//        clientService.getClientByUserId(user.getUserId(), getClientCallback());
        client = getCurrentClient(user.getUserId());
        launcher = getLauncher();
        setContentView(R.layout.activity_home_page);
        initComponents();
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
            }
        };
    }

    private Client getCurrentClient(long id) {
        if (id < 0) {
            return null;
        } else {
            for (int i = 0; i < clientList.size(); i++) {
                if (clientList.get(i).getId() == id) {
                    return clientList.get(i);
                }
            }
        }
        return null;
    }

    private ActivityResultLauncher<Intent> getLauncher() {
        ActivityResultCallback<ActivityResult> callback = getLoanRequestActivityResultCallback();
        return registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), callback);
    }

    private ActivityResultCallback<ActivityResult> getLoanRequestActivityResultCallback() {
        return new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

            }
        };
    }

    private void initComponents() {
        tvHello = findViewById(R.id.id_tv_homePage);
        tvHello.append(client.getSurname() + "!");
        btnNewLoan = findViewById(R.id.id_btn_newLoanRequest);
        btnNewLoan.setOnClickListener(newLoanRequestEventListener());
    }

    private View.OnClickListener newLoanRequestEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoanRequestFormActivity.class);
//                intent.putExtra(USER_KEY, (Serializable) user);
                setResult(RESULT_OK, intent);
                finish();
                launcher.launch(intent);
            }
        };
    }
}