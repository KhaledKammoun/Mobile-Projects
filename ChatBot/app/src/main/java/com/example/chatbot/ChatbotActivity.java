package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View ;
import android.widget.Button ;
import android.widget.TextView;
import org.w3c.dom.Text;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
public class ChatbotActivity extends AppCompatActivity {
    private RecyclerView messageRecyclerView;
    private MessageAdapter messageAdapter;
    private List<Message> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_inteface);

        messageRecyclerView = findViewById(R.id.messageRecyclerView);
        messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(messageList);
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageRecyclerView.setAdapter(messageAdapter);


        Button askButton = findViewById(R.id.selectQuestionButton);
        askButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMessage("Hi !!", false); // Add the "Hi !!" message as if it's from the user

            }
        });

        private void addMessage(String text, boolean isSentByUser) {
            Message message = new Message(text, isSentByUser);
            messageList.add(message);
            messageAdapter.notifyItemInserted(messageList.size() - 1);
            messageRecyclerView.scrollToPosition(messageList.size() - 1);
        }
        
        Button returnButton = findViewById(R.id.returnButton) ;
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatbotActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }) ;
    }
}
