package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ResetEmail extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference reference;

    EditText write_mail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_email);
        init();
    }


    private void init(){
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        reference = db.getReference();

        write_mail = (EditText) findViewById(R.id.mail_resest);
    }

    public void Resest_cont(View v){
     if(write_mail.getText().toString().isEmpty()){
         Toast.makeText(ResetEmail.this, getText(R.string.empty), Toast.LENGTH_SHORT).show();
     } else{
          auth.sendPasswordResetEmail(write_mail.getText().toString().trim())
                  .addOnCompleteListener(new OnCompleteListener<Void>() {
              @Override
              public void onComplete(@NonNull Task<Void> task) {
                    startActivity(new Intent(ResetEmail.this, EnterResestPassword.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
              }
          });
     }
    }
}