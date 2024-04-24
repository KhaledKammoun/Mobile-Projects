
package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View ;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button ;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import android.widget.Toast;
import android.content.DialogInterface;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Text;

public class QuestionListPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.question_list_style);
        ArrayList<String> nextQuestions = getIntent().getStringArrayListExtra("nextQuestions");
        ListView questionsListView = findViewById(R.id.questionsListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nextQuestions);
        questionsListView.setAdapter(adapter);

        // Set a click listener for the items in the ListView (optional)
        questionsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedQuestion = nextQuestions.get(position);
                // Handle the selected question as needed
            }
        });

        Toast.makeText(QuestionListPage.this, "Welcome!", Toast.LENGTH_SHORT).show();
    }
}