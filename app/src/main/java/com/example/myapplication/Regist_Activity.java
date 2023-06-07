package com.example.myapplication;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import  com.example.myapplication.RegistFirst_Activity.*;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Regist_Activity extends AppCompatActivity {

    Message mes;
    private EditText mail;
    private EditText pass;
    private EditText enter_pass;

    private Button send_button;

    private String text_mail;
    private String text_name;
    private String  text_surname;


    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    SharedPreferences SP;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);


        init();
    }

    private void init(){
        mail = (EditText) findViewById(R.id.mail_regist);
        pass = (EditText) findViewById(R.id.pass_regist);
        enter_pass = (EditText) findViewById(R.id.enter_pass);

        send_button = (Button) findViewById(R.id.Btn_Regist);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Mes");

        SP = getSharedPreferences("Test", MODE_PRIVATE);

        text_mail = getIntent().getStringExtra("mail_user");
        text_name = getIntent().getStringExtra("name_user");
        text_surname = getIntent().getStringExtra("surname_user");

    }

    public void Regist(View v){
        if (!pass.getText().toString().trim().isEmpty() && !enter_pass.getText().toString().trim().isEmpty()){
            if (pass.getText().toString().equals(enter_pass.getText().toString())){
                firebaseAuth.createUserWithEmailAndPassword(text_mail.trim(), pass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){

                                    firebaseDatabase.getReference().push().setValue(new User(
                                            text_name,
                                            text_surname,
                                            text_mail
                                    ));

                                    startActivity(new Intent(Regist_Activity.this, List_Chat.class)
                                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

                                } else {
                                    Toast.makeText(Regist_Activity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } else {
                Toast.makeText(Regist_Activity.this, getString(R.string.pass_to_pass), Toast.LENGTH_SHORT).show();
            }
        } else{
            Toast.makeText(Regist_Activity.this, getString(R.string.empty_line), Toast.LENGTH_SHORT).show();
        }
    }
}