package com.example.root.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int count=5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvinfo);
        Login = (Button) findViewById(R.id.btn1);

        Login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Validate(Name.getText().toString(), Password.getText().toString());
            }
        });
    }

    private void Validate(String userName, String userPassword){
        if((userName.equals("admin@test.com")) && (userPassword.equals("1234"))){
            Intent intent = new Intent(MainActivity.this,  SecondActivity.class);
            startActivity(intent);
        }
        else{
            count --;
            Info.setText("No. of attempts remaining : "+String.valueOf(count));
            if(count==0){
                Login.setEnabled(false);
            }
        }
    }
}

