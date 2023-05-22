package com.example.testapp.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapp.data.model.Accounts;
import com.example.testapp.ui.Common.Common;
import com.example.testapp.JsonPlaceHolderApi;
import com.example.testapp.Learn;
import com.example.testapp.Post;
import com.example.testapp.R;
import com.example.testapp.User;
import com.example.testapp.api.ApiService;
import com.example.testapp.databinding.ActivityLoginBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private TextView textViewResult;
    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;
    private User user = new User();
    private Accounts account;

    //SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.1.6:3000/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
        Retrofit retrofit = ApiService.retrofit;

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.login;
        final ProgressBar loadingProgressBar = binding.loading;

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Accounts accounts = new Accounts(usernameEditText.getText().toString(), passwordEditText.getText().toString(), "","","","");

                Call<Accounts> call = jsonPlaceHolderApi.createPost(accounts);
                call.enqueue(new Callback<Accounts>() {

                    @Override
                    public void onResponse(Call<Accounts> call, Response<Accounts> response) {
                        if (!response.isSuccessful()) {
                            //textViewResult.setText("code: " + response.code());
                            Toast.makeText(getApplicationContext(), "login sucsses code", Toast.LENGTH_LONG).show();
                            return;
                        }
                        Accounts postResponse = response.body();
                        Common.currentUser = postResponse.getEmail();
                        Common.currentAccount = postResponse;
                        Common.token = postResponse.getToken();

                        Intent learn = new Intent(LoginActivity.this, Learn.class);
                        Toast.makeText(LoginActivity.this, "ddddd" + postResponse.getToken(), Toast.LENGTH_SHORT).show();

                        startActivity(learn);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Accounts> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"loi dang nhap: "+ t.toString(), Toast.LENGTH_LONG).show();
                        System.out.println(t.toString());
                    }
                });
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });
        //createPost();
    }

    private void updateUiWithUser(LoggedInUserView model) {

        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    private void createPost() {


    }
}