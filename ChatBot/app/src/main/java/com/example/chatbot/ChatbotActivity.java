package com.example.chatbot;

import static com.example.chatbot.Response.createGraph;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View ;
import android.widget.Button ;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChatbotActivity extends AppCompatActivity {

    private RecyclerView messageRecyclerView ;
    private MessageAdapter messageAdapter;
    private List<Message> messageList;

    private Response rootQuestion ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_inteface);


        rootQuestion = Response.createGraph();

        // get next questions
        for (Response nextQuestion : rootQuestion.getNextQuestions()) {
            System.out.println(nextQuestion.getQuestion());
        }


        /***  RecyclerView element Style*/
        messageRecyclerView = findViewById(R.id.messageRecyclerView);
        messageList = new ArrayList<>();

        messageAdapter = new MessageAdapter(messageList);
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageRecyclerView.setAdapter(messageAdapter);


        // Set initial message for the user
        addMessage("Bienvenue, je suis votre chatbot. Posez-moi des questions !", false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true); // Start from the bottom of the list
        layoutManager.setReverseLayout(false); // Display items in reverse order
        messageRecyclerView.setLayoutManager(layoutManager);

        Button selectQuestionButton = findViewById(R.id.questionButton) ;
        selectQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Response> nextQuestionsList = rootQuestion.getNextQuestions();
                ArrayList<String> questionStrings = new ArrayList<>();
                for (Response response : nextQuestionsList) {
                    questionStrings.add(response.getQuestion());
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("messageList", (Serializable) messageList);

                Intent intent = new Intent(ChatbotActivity.this, QuestionListPage.class);
                intent.putExtras(bundle);

                intent.putStringArrayListExtra("nextQuestions", questionStrings);

                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Serializable serializedList = bundle.getSerializable("messageList");
            if (serializedList instanceof List<?>) {
                List<?> receivedList = (List<?>) serializedList;
                if (!receivedList.isEmpty() && receivedList.get(0) instanceof Message) {
                    messageList.clear(); // Clear existing list
                    for (Object obj : receivedList) {
                        messageList.add((Message) obj);
                    }
                    messageAdapter.notifyDataSetChanged(); // Notify adapter of data change
                }
            }
        }

        List<Response> nextQuestionsList = rootQuestion.getNextQuestions();
        ArrayList<String> questionStrings = new ArrayList<>();
        ArrayList<String> responseStrings = new ArrayList<>();

        for (Response response : nextQuestionsList) {
            questionStrings.add(response.getQuestion());
            responseStrings.add(response.getResponse());
        }

        int selectedQuestion = getIntent().getIntExtra("selectedQuestion", -1);

        // Check if the selected question index is valid
        if (selectedQuestion >= 0 && selectedQuestion < questionStrings.size()) {

            // Add messages to the interface
            addMessage(questionStrings.get(selectedQuestion), true);
            addMessage(responseStrings.get(selectedQuestion), false);
            /*for (Response response : nextQuestionsList) {
                if (response.getQuestion().equals(questionStrings.get(selectedQuestion))) {
                    updateRootQuestion(response);
                    break;

                }

            }*/
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

    private void addMessage(String text, boolean isSentByUser) {
        Message message = new Message(text, isSentByUser);
        messageList.add(message);
        messageAdapter.notifyItemInserted(messageList.size() - 1);
        messageRecyclerView.scrollToPosition(messageList.size() - 1);
    }

    private void updateRootQuestion(Response newRoot) {
        this.rootQuestion = newRoot;
    }

}


