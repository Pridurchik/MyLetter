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

public class EnterResestPassword extends AppCompatActivity {

    EditText pass;
    EditText conf_pass;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_resest_password);
        init();
    }

    public void init(){
        pass = (EditText) findViewById(R.id.pass);
        conf_pass = (EditText) findViewById(R.id.confirm_pass);
     }


    public void resest_pass(View view) {
        if(pass.getText().toString().isEmpty() && conf_pass.getText().toString().isEmpty()){
            Toast.makeText(this, "Убедитесь что вы ввели все данные", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Убедитесь что вы ввели все данные", Toast.LENGTH_SHORT).show();
            auth.confirmPasswordReset(pass.getText().toString().toString(), conf_pass.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Intent i = new Intent(EnterResestPassword.this, MainActivity.class);
                    startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }

            });
        }
    }

    public void Back_resest(View view) {
        startActivity(new Intent(EnterResestPassword.this, SendEnterMail.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}