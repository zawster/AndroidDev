package com.example.root.databaselogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity {
    private Button profile;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        profile=(Button)findViewById(R.id.btnprofile);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
//        final MediaPlayer mediaPlayer=MediaPlayer.create(this, R.raw.sam);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(SecondActivity.this,"Profile is checked",Toast.LENGTH_LONG).show();
                startActivity(new Intent(SecondActivity.this,ProfileActivity.class));
            }
        });
    }
    public void Logout(){
        progressDialog.setMessage("Please wait a moment");
        progressDialog.show();
        firebaseAuth.signOut();
        Toast.makeText(SecondActivity.this,"Successfully Logout",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(SecondActivity.this,MainActivity.class));
        finish();
        progressDialog.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.logoutMenu:{
                Logout();
               /* progressDialog.setMessage("Please wait a moment");
                progressDialog.show();
                firebaseAuth.signOut();
                Toast.makeText(SecondActivity.this,"Successfully Logout",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SecondActivity.this,MainActivity.class));
                finish();
                progressDialog.dismiss();*/
            }

//            case R.id.refreshMenu:{
//                Intent intent = getIntent();
//                Toast.makeText(this,"Activity is refreshed",Toast.LENGTH_SHORT).show();
//
//                finish();
//                startActivity(intent);
//            }


        }

        return super.onOptionsItemSelected(item);
    }
}

