package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class RegistFirst_Activity extends AppCompatActivity {

    EditText name;
    EditText surname;
     EditText mail;


    String text_mail;
    String text_name;
    String text_surname;

    FirebaseDatabase firebaseDatabase;

    SharedPreferences SP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_first);
        init();
    }

    private void init(){
        name = (EditText) findViewById(R.id.name_regist);
        surname = (EditText) findViewById(R.id.surname_regist);
        mail = (EditText) findViewById(R.id.mail_regist);
        firebaseDatabase = FirebaseDatabase.getInstance();

    }

    public void Back(View view) {
        startActivity(new Intent(this, MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    public void next(View view){
            if (name.getText().toString().isEmpty() || surname.getText().toString().isEmpty() || mail.getText().toString().isEmpty())  {
                Toast.makeText(RegistFirst_Activity.this, getText(R.string.empty), Toast.LENGTH_SHORT).show();

            } else {

                text_mail = mail.getText().toString();
                text_name = name.getText().toString();
                text_surname = surname.getText().toString();

                Intent i = new Intent(this, Regist_Activity.class);

                i.putExtra("name_user", text_name);
                i.putExtra("surname_user", text_surname);
                i.putExtra("mail_user", text_mail);

                startActivity(i);
            }
    }
}