package com.example.chatbot;

import java.util.ArrayList;
import java.util.List;

public class Response {
    private String currentQuestion;
    private String currentResponse;
    private List<Response> nextQuestions;

    public Response(String question, String response) {
        this.currentQuestion = question;
        this.currentResponse = response;
        this.nextQuestions = new ArrayList<>();
    }

    public String getQuestion() {
        return currentQuestion;
    }

    public String getResponse() {
        return currentResponse;
    }

    public List<Response> getNextQuestions() {
        return nextQuestions;
    }

    public void addNextQuestion(String question, String response) {
        nextQuestions.add(new Response(question, response));
    }
}
