package com.example.root.databaselogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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

public class MainActivity extends AppCompatActivity {
    private EditText email,password;
    private Button btnlogin;
    private TextView info;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private int counter=5;
    private TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUIview();
        info.setText("No of remaining attempts : "+String.valueOf(counter));
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);


        FirebaseUser user = firebaseAuth.getCurrentUser(); // check if user already loged in

        if (user != null){
            finish();
            startActivity(new Intent(MainActivity.this,SecondActivity.class));
//
        }


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Please wait a moment");
                progressDialog.show();
                if (!validate()){
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this,"Please enter all the details",Toast.LENGTH_SHORT).show();
                }
                else {
                    ValidateLogin(email.getText().toString(),password.getText().toString());
                }
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,PasswordActivity.class));
            }
        });


    }
    public void registerClicked(View view){
        Intent intent = new Intent(MainActivity.this,  RegistrationActivity.class);
        startActivity(intent);
    }
    private void setUIview(){
        email=findViewById(R.id.edt1);
        password=findViewById(R.id.edt2);
        btnlogin=findViewById(R.id.btnLogin);
        info=findViewById(R.id.tv2);
        forgotPassword=findViewById(R.id.forgotPassword);
    }
    private Boolean validate(){
        String Emial=email.getText().toString();
        String passwd=password.getText().toString();
        if (TextUtils.isEmpty(Emial) || TextUtils.isEmpty(passwd)){
            return false;
        }
        else {
            return true;
        }

    }
    //  signing in to your account
    private void ValidateLogin(String userEmail,String userPassword){

        firebaseAuth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    //Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    //finish();
                    // /startActivity(new Intent(MainActivity.this, SecondActivity.class));
                    CheckEmailVarification();
                }
                else {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    counter--;
                    if (counter==0){
                        info.setText("No of remaining attempts : "+String.valueOf(counter));
                        btnlogin.setEnabled(false);
                    }else {
                        info.setText("No of remaining attempts : "+String.valueOf(counter));
                    }
                }
            }
        });
    }

    //  Email verification
    private void CheckEmailVarification(){
        FirebaseUser firebaseUser=firebaseAuth.getInstance().getCurrentUser();


        Boolean emailFlag=firebaseUser.isEmailVerified();

        startActivity(new Intent(MainActivity.this,SecondActivity.class));


//        if (emailFlag){
//            finish();
//            Toast.makeText(this,"Successfully Login",Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(MainActivity.this,SecondActivity.class));
//        }
//        else{
//            Toast.makeText(this,"Verify your email",Toast.LENGTH_LONG);
//            firebaseAuth.signOut();
//        }
    }

}

