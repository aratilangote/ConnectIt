package com.technolite.connectit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;

import android.content.pm.PackageManager;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    MaterialTextView signup_activity;

    MaterialButton signBtn;
    TextInputEditText email, pass;

    private FirebaseAuth auth;

    AlertDialog.Builder builder;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.user_email);
        pass = findViewById(R.id.user_pass);
        signBtn = findViewById(R.id.signin);

        auth = FirebaseAuth.getInstance();


//        startActivity(new Intent(MainActivity.this, NetworkSMSOptions.class));

        signup_activity = findViewById(R.id.signup_activity);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (checkSelfPermission(android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {

            }

            else {
                requestPermissions(new String[] {Manifest.permission.SEND_SMS}, 1);
            }
        }

        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email.getText().toString().isEmpty()){
                    email.setError("Enter email");
                } else if (pass.getText().toString().isEmpty()) {
                    pass.setError("Enter password");
                } else if (!email.getText().toString().isEmpty() && !pass.getText().toString().isEmpty()) {
                    String txt_email = email.getText().toString();
                    String txt_pass = pass.getText().toString();


                    //firebase login method
                    loginuser(txt_email, txt_pass);
                }else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signup_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

    }




    //Firebase login

    private void loginuser(String email, String password){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    finish();
                }else {
                    Toast.makeText(MainActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {

        super.onStart();

        if (auth!=null && auth.getCurrentUser()!=null) {
            startActivity( new Intent(MainActivity.this, HomeActivity.class));
            finish();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}