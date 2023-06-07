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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class InAcc_Activity extends AppCompatActivity {

    EditText mail;
    EditText pass;


    FirebaseAuth mAuth;

    FirebaseDatabase db;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_acc);
        init();
    }

    private void init() {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        reference = db.getReference();

        mail = (EditText) findViewById(R.id.mail);
        pass = (EditText) findViewById(R.id.pass);
    }


    public void In_account(View v) {
        if (mail.getText().toString().isEmpty() || pass.getText().toString().isEmpty()) {
            Toast.makeText(InAcc_Activity.this, getString(R.string.empty), Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(mail.getText().toString().trim(), pass.getText().toString().trim())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(InAcc_Activity.this, List_Chat.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

                            } else {
                                Toast.makeText(InAcc_Activity.this, "No", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    public void In_create_account(View view) {
        startActivity(new Intent(InAcc_Activity.this, RegistFirst_Activity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
