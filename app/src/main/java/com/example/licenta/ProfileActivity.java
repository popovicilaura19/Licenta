package com.example.licenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.licenta.dto.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    private Button btnChangeEmail;
    private Button btnChangePassword;
    private Button btnHome;
    private TextInputEditText tietEmail;
    private TextInputLayout tilEmail;
    private TextInputEditText tilCurrentPassword;
    private TextInputEditText tilNewPassword;
    private TextInputEditText tilConfirm;

    private Intent intent;
    private User user;

    public static final String USER_KEY = "userKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        intent = getIntent();
        user = (User) getIntent().getSerializableExtra(USER_KEY);
        initComponents();
    }

    private void initComponents() {
        btnChangeEmail = findViewById(R.id.id_btn_profile_changeEmail);
        btnChangeEmail.setOnClickListener(getChangeEmailEventListener());
        btnChangePassword = findViewById(R.id.id_btn_profile_changePassword);
        btnChangePassword.setOnClickListener(getChangePasswordEventListener());
        btnHome = findViewById(R.id.id_btn_profile_home);
        btnHome.setOnClickListener(getHomeEventListener());
        tietEmail = findViewById(R.id.id_tiet_profile_email);
        tilEmail = findViewById(R.id.id_til_profile_email);
        tilEmail.setHint(user.getEmail());
        tilCurrentPassword = findViewById(R.id.id_tiet_profile_current);
        tilNewPassword = findViewById(R.id.id_tiet_profile_new);
        tilConfirm = findViewById(R.id.id_tiet_profile_confirm);
    }

    private View.OnClickListener getHomeEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK, intent);
                finish();
            }
        };
    }

    private View.OnClickListener getChangePasswordEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Objects.equals(user.getPassword(), Objects.requireNonNull(tilCurrentPassword.getText()).toString())) {
                    Toast.makeText(getApplicationContext(), R.string.password_incorrect_not_match, Toast.LENGTH_SHORT).show();
                } else {
                    if (!Objects.requireNonNull(tilNewPassword.getText()).toString().equals(tilConfirm.getText().toString())) {
                        Toast.makeText(getApplicationContext(), R.string.not_match, Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), R.string.password_updated, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
    }

    private View.OnClickListener getChangeEmailEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.email_sent_for_new_email_address, Toast.LENGTH_SHORT).show();
            }
        };
    }
}