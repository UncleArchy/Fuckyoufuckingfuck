package com.example.aleks.fuckyoufuckingfuck;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;



public class MainActivity extends AppCompatActivity {

    private static final int SIGN_IN_REQUEST_CODE = 1;
    private FirebaseListAdapter<Message> adapter;
    RelativeLayout activity_main;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity_main = (RelativeLayout)findViewById(activity_main.getId());
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = (EditText)findViewById(R.id.editText);
                FirebaseDatabase.getInstance().getReference().push()
                        .setValue(new Message(input.getText().toString(),
                                FirebaseAuth.getInstance().getCurrentUser().getEmail()));
                input.setText("");
            }
        });

        if(FirebaseAuth.getInstance().getCurrentUser() == null){
        startActivityForResult(AuthUI.getInstance()
        .createSignInIntentBuilder().build(), SIGN_IN_REQUEST_CODE);}
        else{
            displayChat();
        }
    }

    private void displayChat(){
        ListView listMessage = (ListView)findViewById(R.id.messlist);
        adapter = new FirebaseListAdapter<Message>(this, Message.class, R.layout.item,
                FirebaseDatabase.getInstance().getReference()) {
            @Override
            protected void populateView(View v, Message model, int position) {
                TextView text, author, time;

                text = (TextView)findViewById(R.id.tvMessage);
                author = (TextView)findViewById(R.id.tvUser);
                time = (TextView)findViewById(R.id.tvTime);

                text.setText(model.getTextMessage());
                author.setText(model.getAuthorMessage());
                time.setText(DateFormat.format("dd-MM-yyyy (MM:mm:ss)", model.getTimeMessage()));

            }
        };
        listMessage.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SIGN_IN_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                Snackbar.make(activity_main, "Вход успешен", Snackbar.LENGTH_SHORT).show();
                displayChat();
            }
            else{
                Snackbar.make(activity_main, "Вход неуспешен", Snackbar.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}