package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View ;
import android.widget.Button ;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ChatbotActivity extends AppCompatActivity {

    private RecyclerView messageRecyclerView ;
    private MessageAdapter messageAdapter;
    private List<Message> messageList;
    private Response rootQuestion ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_inteface);

        Response rootQuestion = new Response("What is your favorite color?", "My favorite color is blue.");

        rootQuestion.addNextQuestion("Do you like red?", "Yes, I like red.");
        rootQuestion.addNextQuestion("Do you like green?", "No, I don't like green.");

        System.out.println("Root Question: " + rootQuestion.getQuestion());

        System.out.println("Related Questions:");
        for (Response nextQuestion : rootQuestion.getNextQuestions()) {
            System.out.println(nextQuestion.getQuestion());
        }

        messageRecyclerView = findViewById(R.id.messageRecyclerView);
        messageList = new ArrayList<>();

        // put responseList Empty

        messageAdapter = new MessageAdapter(messageList);
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageRecyclerView.setAdapter(messageAdapter);


        // Set initial message for the user
        addMessage("Welcome, i'm your chatbot, ask me some Questions !", false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true); // Start from the bottom of the list
        layoutManager.setReverseLayout(false); // Display items in reverse order
        messageRecyclerView.setLayoutManager(layoutManager);

        Button selectQuestionButton = findViewById(R.id.selectQuestionButton) ;
        selectQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Response> nextQuestionsList = rootQuestion.getNextQuestions();
                ArrayList<String> questionStrings = new ArrayList<>();
                for (Response response : nextQuestionsList) {
                    questionStrings.add(response.getQuestion());
                }

                Intent intent = new Intent(ChatbotActivity.this, QuestionListPage.class);

                intent.putStringArrayListExtra("nextQuestions", questionStrings);

                startActivity(intent);
            }
        });

        Button returnButton = findViewById(R.id.returnButton) ;
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatbotActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }) ;
    }

    private void addMessage(String text, boolean isSentByUser) {
        Message message = new Message(text, isSentByUser);
        messageList.add(message);
        messageAdapter.notifyItemInserted(messageList.size() - 1);
        messageRecyclerView.scrollToPosition(messageList.size() - 1);
    }

    private boolean checkInputValid(String inputString) {
        // Remove spaces and parentheses for validation
        String cleanString = inputString.replaceAll("\\s+", "").replaceAll("[()]", "");

        // Check for invalid characters (anything other than digits and operators)
        for (char c : cleanString.toCharArray()) {
            if (!Character.isDigit(c) && c != '+' && c != '-' && c != '*' && c != '/') {
                return false; // Invalid character found
            }
        }

        // Check for consecutive operators or trailing operators
        if (cleanString.matches(".*[+\\-*/]{2,}.*") || cleanString.matches(".*[+\\-*/]$")) {
            return false; // Consecutive or trailing operators are not valid
        }

        return true; // Input is valid
    }

    private float calculateResult(String inputString) {
        float result = 0;
        inputString = inputString.replace("(", "").replace(")", ""); // Remove parentheses

        // Split the string by spaces to separate numbers and operators
        String[] tokens = inputString.split("\\s+");
        String sign = "+";

        for (String token : tokens) {
            if (token.equals("+") || token.equals("-")) {
                sign = token;
            } else {
                if (sign.equals("+")) {
                    result += Float.parseFloat(token); // Parse token as float and add
                } else {
                    result -= Float.parseFloat(token); // Parse token as float and subtract
                }
            }
        }

        return result;
    }

}
