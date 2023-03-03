package com.technolite.connectit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.technolite.connectit.module.UDer;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    MaterialTextView signin_activity;
    TextInputLayout verify_phoneno;

    TextInputEditText name, address, mail, mobile, pass, cnfPass, referalId;
    MaterialButton signupBtn;




    //Firebase database
    FirebaseAuth fAuth;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("Users");




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Authentication
        fAuth = FirebaseAuth.getInstance();

        verify_phoneno = findViewById(R.id.phone_no);
        signin_activity = findViewById(R.id.signin_activity);
        signupBtn = findViewById(R.id.signup_btn);

        name = findViewById(R.id.user_name);
        address = findViewById(R.id.user_address);
        mail = findViewById(R.id.user_email);
        mobile = findViewById(R.id.user_mobile);
        pass = findViewById(R.id.user_pass);
        cnfPass = findViewById(R.id.user_cnfpass);
        referalId = findViewById(R.id.user_refId);




        //Firebase

        String userName = name.getText().toString();
        String userAddress = address.getText().toString();
        String userEmail = mail.getText().toString();
        String userMobileNo = mobile.getText().toString();
        String userPass = pass.getText().toString();
        String userConPass = cnfPass.getText().toString();
        String userReferalId = referalId.getText().toString();


        Map<String, Object> user = new HashMap<>();
        user.put("Username", userName);
        user.put("Address", userAddress);
        user.put("Email", userEmail);
        user.put("Mobile No", userMobileNo);
        user.put("Password", userPass);
        user.put("Confirm Password", userConPass);
        user.put("Referal Id", userReferalId);





        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(SignupActivity.this, HomeActivity.class));

                if(TextUtils.isEmpty(name.getText().toString()) && TextUtils.isEmpty(address.getText().toString())
                        && TextUtils.isEmpty(mail.getText().toString()) && TextUtils.isEmpty(mobile.getText().toString())
                        && TextUtils.isEmpty(pass.getText().toString()) && TextUtils.isEmpty(cnfPass.getText().toString())
                        && TextUtils.isEmpty(referalId.getText().toString())){  //TO CHECK IF BOTH EDITTEXT ARE EMPTY

                    name.setError("Please enter fullname");
                    address.setError("Please enter address");
                    mail.setError("Please enter your E-mail");
                    mobile.setError("Please enter mobile number");
                    pass.setError("Please enter password");
                    cnfPass.setError("Please enter your password");
                    referalId.setError("Please enter referral id");

                } else if(pass.getText().toString().length() < 6 && !isValidPassword(pass.getText().toString())){
                    pass.setError("Password not valid");
                } else if (!(mail.getText().toString().contains("@")&& mail.getText().toString().contains("."))) {
                    mail.setError("Please enter valid mail");
                } else if(pass.getText().toString().equals(cnfPass.getText().toString())){



                    //Firebase database
//
//                    fAuth.signInWithEmailAndPassword(mail.getText().toString(), pass.getText().toString())
//                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                @Override
//                                public void onComplete(@NonNull Task<AuthResult> task) {
//                                    if (task.isSuccessful()){
//                                        // User already exists, show error message or take desired action
//                                        AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
//                                        builder.setMessage("Your account is already Signed In\n Thank you!")
//                                                .setCancelable(false)
//                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                    public void onClick(DialogInterface dialog, int id) {
//                                                        dialog.dismiss();
//                                                        startActivity(new Intent(SignupActivity.this,  MainActivity.class));
//                                                    }
//                                                });
//                                        AlertDialog alert = builder.create();
//                                        alert.show();
//                                    }else{
//                                        // User doesn't exist, create a new user
//                                        fAuth.createUserWithEmailAndPassword(mail.getText().toString(), pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                            @Override
//                                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                                if (task.isSuccessful()) {
//                                                    UDer ud=new UDer(mail.getText().toString(),pass.getText().toString());
//                                                    String id=task.getResult().getUser().getUid();
//
//                                                    reference.child(id).setValue(ud);
////                                        Toast.makeText(SignupActivity4.this, "signup successful", Toast.LENGTH_SHORT).show();
//
//                                                    startActivity(new Intent(SignupActivity.this, HomeActivity.class));
//                                                    finish();
//                                                } else {
//                                                    // Toast.makeText(SignupActivity4.this, "Please check E-mail & Password enter valid details", Toast.LENGTH_SHORT).show();
//                                                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
//                                                    builder.setMessage("Please check your entered E-mail is valid or Password has minimum length of 6 digit\n Thank you!")
//                                                            .setCancelable(false)
//                                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                                public void onClick(DialogInterface dialog, int id) {
//                                                                    dialog.dismiss();
//                                                                }
//                                                            });
//                                                    AlertDialog alert = builder.create();
//                                                    alert.show();
//
//                                                }
//
//                                            }
//                                        });
//                                    }
//                                }
//                            });


                }
                else{
                    //Toast.makeText(SignupActivity4.this, "Please fill with proper deatials", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                    builder.setMessage("Both passwords doesn't match \n Thank you!")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }




            }
        });
        verify_phoneno.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(SignupActivity.this, OTPVerificationActivity.class);
//                startActivity(intent);
            }
        });

        signin_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public static boolean isValidPassword(final String password) {  //DEFINATION OF METHOD FOR  VALIDATION OF THE PASSWORD
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";  //VALIDATION STRING
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

}