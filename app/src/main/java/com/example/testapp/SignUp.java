package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapp.api.ApiService;
import com.example.testapp.data.model.Users;
import com.example.testapp.ui.Common.Common;
import com.example.testapp.ui.login.LoginActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUp extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword, editFirstName, editLastName;

    private Button buttonSignUp;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    Retrofit retrofit = ApiService.retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editFirstName = findViewById(R.id.editFirstName);
        editLastName = findViewById(R.id.editLastName);

        buttonSignUp = findViewById(R.id.buttonSignUp);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    private void login() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String fName = editFirstName.getText().toString();
        String lName = editLastName.getText().toString();

        Users user = new Users(email, fName, lName);
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        //Post post = new Post(usernameEditText.getText().toString(), passwordEditText.getText().toString());

        Call<Users> call = jsonPlaceHolderApi.signUp(user, password);
        call.enqueue(new Callback<Users>() {

            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (!response.isSuccessful()) {
                    //textViewResult.setText("code: " + response.code());
                    Toast.makeText(getApplicationContext(), "login sucsses code", Toast.LENGTH_LONG).show();
                    return;
                }
                Common.currentUser = user.getEmail();
                Intent learn = new Intent(SignUp.this, Learn.class);
                startActivity(learn);
                finish();
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                System.out.println(t.toString());
            }
        });
        // Replace the following validation logic with your own user authentication code
        if (email.equals("user@example.com") && password.equals("password123")) {
            //Intent intent = new Intent(SignIn.this, HomeActivity.class);
            //startActivity(intent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_SHORT).show();
        }
    }
}