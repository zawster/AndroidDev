package com.example.root.databaselogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordActivity extends AppCompatActivity {
    private EditText userEmail;
    private Button resetButton;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        userEmail=(EditText)findViewById(R.id.edtmail);
        resetButton=(Button)findViewById(R.id.btnreset);
        firebaseAuth=FirebaseAuth.getInstance();


        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String resetEmail=userEmail.getText().toString().trim();

                if (resetEmail.equals("")){
                    Toast.makeText(PasswordActivity.this,"Please enter the email",Toast.LENGTH_SHORT).show();
                }
                else{
                    firebaseAuth.sendPasswordResetEmail(resetEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(PasswordActivity.this,"Password reset email has been sent",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(PasswordActivity.this,MainActivity.class));
                            }
                            else{
                                Toast.makeText(PasswordActivity.this,"Password reset failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}

