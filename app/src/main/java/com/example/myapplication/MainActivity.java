package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    TextView text_wel;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        if(mAuth.getCurrentUser() != null){
            Intent i = new Intent(MainActivity.this, List_Chat.class);

            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(i
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        }
        Thread thread = new Printer();
        thread.start();
    }



    @Override
    protected void onStart() {
        super.onStart();
    }

    public void InAccount(View v){
        startActivity(new Intent(MainActivity.this, InAcc_Activity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }


    public void RegistAcc(View view){
        startActivity(new Intent(MainActivity.this, RegistFirst_Activity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    public void InResPass(View v){
        startActivity(new Intent(MainActivity.this, ResetEmail.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    private void init(){
        text_wel = findViewById(R.id.text_welcome);
        mAuth = FirebaseAuth.getInstance();
    }

    // Обратный вывод текста машинки

    public static String removeLastChar(String s) {
        return (s == null || s.length() == 0)
                ? null
                : (s.substring(0, s.length() - 1));
    }

    // Ввод текста машинки

    class Printer extends Thread {

        String text_str_wel_one = getString(R.string.welcome);

        String[] text_str_wel_two = text_str_wel_one.split("");

        String word = "";


        @Override
        public void run() {
            while (true){

                word = "";

                for (int i = 0; text_str_wel_one.length() > i; i++) {
                    word += text_str_wel_two[i];

                    text_wel.setText(word);

                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                for (int z = text_str_wel_one.length(); z > 0; z -= 1){
                    word = removeLastChar(word);

                    text_wel.setText(word);

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}