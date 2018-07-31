package com.example.root.bmiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
    }
    public void calcClicked(View view){
        EditText et1=(EditText)findViewById(R.id.et1);
        EditText et2=(EditText)findViewById(R.id.et2);
        Button btn1=(Button)findViewById(R.id.btn1);
        TextView text1=(TextView)findViewById(R.id.tv1);
        TextView text2=(TextView)findViewById(R.id.tv2);

        double weight =0;
        double height=0;
        double bmi=0;
        String msg="";

        weight= Double.parseDouble(et1.getText().toString());
        height=Double.parseDouble(et2.getText().toString());

        bmi=height * height;
        bmi=weight/bmi;
        String bmiRes="BMI = "+String.valueOf(bmi);
        text1.setText(bmiRes);
        if(bmi<10.5){
            msg="Underweight";
        }
        else if(bmi>10.5 && bmi<15){
            msg="Normal";
        }
        else if (bmi>15){
            msg="Overweight";
        }
        text2.setText(msg);
    }

}

