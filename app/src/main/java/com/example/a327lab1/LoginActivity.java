package com.example.a327lab1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a327lab1.models.User;

public class LoginActivity extends AppCompatActivity {

    UserJSONProcessor userJSONProcessor;

    private EditText userName, userPassword;
    private Button loginButton;
    private TextView navToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupUIViews();

        userJSONProcessor = new UserJSONProcessor(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            //Get user input
            String name = userName.getText().toString();
            String password = userPassword.getText().toString();

            if (validateLoginCredentials(name, password)){
                User user = userJSONProcessor.getUser(name);

                Intent i = new Intent(LoginActivity.this, PlaylistActivity.class);
                i.putExtra("name", name);
                i.putExtra("listOfPlaylists", user.getListOfPlaylists());
                startActivity(i);

                finish();
            }
            }
        });

        navToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivityForResult(i, 1);
            }
        });

    }

    private void setupUIViews() {
        userName = (EditText)findViewById(R.id.etUserName);
        userPassword = (EditText)findViewById(R.id.etUserPassword);
        loginButton = (Button)findViewById(R.id.btnLogin);
        navToRegister = (TextView)findViewById((R.id.tvNavToRegister));
    }

    private boolean validateLoginCredentials(String name, String password){
        boolean result = false;

        if (name.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "There are missing fields. Please enter all details.", Toast.LENGTH_SHORT).show();
        } else if (userJSONProcessor.hasUserName(name)){
            result = true;
        } else {
            Toast.makeText(this, "Username or password is invalid. Please try again.", Toast.LENGTH_SHORT).show();
        }

        return result;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Intent refresh = new Intent(this, LoginActivity.class);
            startActivity(refresh);
            this.finish();
        }
    }
}
