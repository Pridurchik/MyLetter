package com.example.myapplication;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapplication.databinding.ActivityListChatBinding;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Objects;


public class List_Chat extends AppCompatActivity {


    ListView ListOfMessage;

    FloatingActionButton send_mes;
    EditText write_text;

    FirebaseAuth  mAuth;
    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;

    private FirebaseListAdapter<Message> adapter;
    Query query;

    ActivityListChatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
        displayAllMessages();

    }


    private void init(){
        ListOfMessage = (ListView) findViewById(R.id.list_of_message);
        mAuth =  FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("ONE");

        send_mes = (FloatingActionButton) findViewById(R.id.Btn_Send);
        write_text = (EditText) findViewById(R.id.List_Message);
        query = FirebaseDatabase.getInstance().getReference().child("chats");
    }

    public void LogOut(View v){
        mAuth.signOut();
        startActivity(new Intent(List_Chat.this, MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    private void displayAllMessages() {
            FirebaseListOptions<Message> options = new FirebaseListOptions.Builder<Message>()
                    .setQuery(query, Message.class)
                    .setLayout(R.layout.item).build();

        adapter = new FirebaseListAdapter<Message>(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Message model, int position) {
                TextView text_user = (TextView) v.findViewById(R.id.message_user);
                TextView text_mess = (TextView) v.findViewById(R.id.message_text);

                text_user.setText(mAuth.getCurrentUser().getEmail().toString());
                text_mess.setText(write_text.getText().toString());
            }
        };
        ListOfMessage.setAdapter(adapter);
    }


    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public void Send_message(View v){
        firebaseDatabase.getReference().push().setValue(new Message(
                mAuth.getCurrentUser().getEmail().toString(),
                write_text.getText().toString()
        ));

        write_text.setText("");
    }

}
