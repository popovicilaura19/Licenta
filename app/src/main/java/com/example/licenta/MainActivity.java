package com.example.licenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.licenta.async.Callback;
import com.example.licenta.dto.User;
import com.example.licenta.services.UserService;
import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String UPDATED_POSITION = "updatedPosition";
    public static final String ACTION = "action";
    public static final String UPDATE_ACTION = "update";
    public static final String ADD_ACTION = "add";
    private TextInputEditText tietEmail;
    private TextInputEditText tietPassword;
    private Button btnSignIn;

    private List<User> users = new ArrayList<>();
    private ActivityResultLauncher<Intent> launcher;

    private UserService userService;

    public static final String USER_KEY = "userKey";

    private Intent intent;
    private User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();

        launcher=getLauncher();
        userService = new UserService(getApplicationContext());
        userService.getAll(getAllCallback());
    }

    private ActivityResultLauncher<Intent> getLauncher() {
        ActivityResultCallback<ActivityResult> callback = getHomeActivityResultCallback();
        return registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), callback);
    }

    private ActivityResultCallback<ActivityResult> getHomeActivityResultCallback() {
        return new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

            }
        };
    }

    private void initComponents() {
        tietEmail = findViewById(R.id.id_tiet_email);
        tietPassword = findViewById(R.id.id_tiet_password);
        btnSignIn = findViewById(R.id.id_btn_signIn);
        btnSignIn.setOnClickListener(signInEventListener());
    }

    private View.OnClickListener signInEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()) {
                    User signInUser=createFromViews();
                    authenticateUser(signInUser);
                    if(user == null){
                        Toast.makeText(getApplicationContext(), R.string.invalid_credentials,Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
                        intent.putExtra(USER_KEY, (Serializable) user);
                        setResult(RESULT_OK, intent);
                        finish();
                        launcher.launch(intent);
                    }
                }
            }
        };
    }

    private void authenticateUser(User signInUser){
        for(int i=0;i<users.size();i++){
            if(users.get(i).getEmail().equals(signInUser.getEmail())){
                if(users.get(i).getPassword().equals(signInUser.getPassword())){
                    user=users.get(i);
                    break;
                }else {
                    Toast.makeText(getApplicationContext(),
                            R.string.invalid_passsword, Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }
    }

    private boolean isValid() {
        if (tietEmail.getText() == null || tietPassword.getText() == null) {
            Toast.makeText(getApplicationContext(),
                            R.string.sign_in_form_error,
                            Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        return true;
    }

    private User createFromViews() {
        String email = tietEmail.getText().toString();
        String password = tietPassword.getText().toString();
        if (user == null) {
            user = new User(email, password);
        } else {
            user.setEmail(email);
            user.setPassword(password);
        }
        return user;
    }

    private Callback<List<User>> getAllCallback() {
        return new Callback<List<User>>() {
            @Override
            public void runResultOnUiThread(List<User> result) {
                users.addAll(result);
            }
        };
    }
}