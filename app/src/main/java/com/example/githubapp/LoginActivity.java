package com.example.githubapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

public class LoginActivity extends AppCompatActivity {
    private Button logIn;
    private Button signIn;
    private EditText inputUserName;

    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logIn = (Button) findViewById(R.id.btn_login);
        inputUserName = (EditText) findViewById(R.id.input_username);
        signIn = (Button) findViewById(R.id.btn_google);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUser(v); // Call the getUser method when the button is clicked
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail() // Request user's email
                .build();

// Create a GoogleApiClient object
        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

// Set an OnClickListener for your sign-in button
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the Google Sign-In process
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, 9001);
            }
        });

    }

    public void getUser(View view){
//
//        i = new Intent(LoginActivity.this, UserActivity.class);
//        i.putExtra("username", inputUserName.getText().toString());
//        startActivity(i);
        String username = inputUserName.getText().toString();
        Log.d("LoginActivity", "Username: " + username); // Log the username
        i = new Intent(LoginActivity.this, UserActivity.class);
        i.putExtra("username", username);
        startActivity(i);

//        logIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("LoginActivity", "Button clicked"); // Log button click event
//                getUser(v);
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 9001) {
            // Handle the result from Google Sign-In
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            // Sign-in was successful, you can get user information like this:
            GoogleSignInAccount account = result.getSignInAccount();
            String displayName = account.getDisplayName();
            String email = account.getEmail();

            // TODO: Proceed with your app logic after successful sign-in
        } else {
            // Sign-in failed, handle the error
            Status status = result.getStatus();
            String errorMessage = status.getStatusMessage();

            // TODO: Handle sign-in failure, display an error message, etc.
        }
    }




}