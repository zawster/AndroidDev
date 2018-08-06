package com.example.root.databaselogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private EditText userName, userPassword, userEmail, userAge;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    private String name,password,email,age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        setUIViews();
        firebaseAuth=FirebaseAuth.getInstance();


        progressDialog=new ProgressDialog(this);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){
//
                    // Upload data to the database

                    progressDialog.setMessage("Please wait a moment");
                    progressDialog.show();
                    String user_email=userEmail.getText().toString().trim();
                    String user_password=userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()) {

                                sendUserData();
                                firebaseAuth.signOut();
                                Toast.makeText(RegistrationActivity.this, "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
                                finish();
                                progressDialog.dismiss();
                                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                            }
                            else{
                                Toast.makeText(RegistrationActivity.this, "Registration Failed",Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                    });

                }

            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
            }
        });

    }


    protected void setUIViews(){
        userName=(EditText)findViewById(R.id.etName);
        userPassword=(EditText)findViewById(R.id.etPassword);
        userEmail=(EditText)findViewById(R.id.etEmail);
        regButton=(Button)findViewById(R.id.regBtn);
        userLogin=(TextView)findViewById(R.id.tv3);
        userAge=(EditText)findViewById(R.id.edtAge);
    }

    private Boolean validate(){
        Boolean res=false ;

        name=userName.getText().toString();
        password = userPassword.getText().toString();
        email = userEmail.getText().toString();
        age = userAge.getText().toString();

        if (name.isEmpty() || password.isEmpty() || email.isEmpty() || age.isEmpty()){
            res=false;
            Toast.makeText(this,"Please enter all the details",Toast.LENGTH_SHORT).show();
        }
        else {
            res = true;
        }
        return res;
    }

    private void sendEmailVerification(){
        FirebaseUser firebaseUser=firebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        //sendUserData();
                        Toast.makeText(RegistrationActivity.this, "Successfully Registered, Verification mail sent!", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                    }
                    else {
                        Toast.makeText(RegistrationActivity.this,"Verification mail not sent",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        else {
            Toast.makeText(RegistrationActivity.this,"There is no current user",Toast.LENGTH_SHORT).show();
        }
    }

    private void sendUserData(){
        Toast.makeText(RegistrationActivity.this,"Successfully Uploaded Data",Toast.LENGTH_LONG).show();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef=firebaseDatabase.getReference(firebaseAuth.getUid());
        UserProfile userProfile = new UserProfile(name, age, email);
        myRef.setValue(userProfile);

    }


}

