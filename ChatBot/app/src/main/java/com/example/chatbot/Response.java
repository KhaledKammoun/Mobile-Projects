package com.example.chatbot;

import java.util.ArrayList;
import java.util.List;

public class Response {
    private String currentQuestion;
    private String currentResponse;
    private List<Response> nextQuestions;
    private static boolean callRootNode ;
    private static Response currentQuestionNode ;

    public static Response getCurrentNode() {
        return Response.currentQuestionNode ;
    }

    public static void setCurrentNode(Response node) {
        Response.currentQuestionNode = node ;
    }
    public Response(String question, String response) {
        this.currentQuestion = question;
        this.currentResponse = response;
        this.nextQuestions = new ArrayList<>();
        if (!Response.callRootNode) {
            Response.callRootNode = true ;
        }
    }

    public static boolean getCallRootNode () {
        return Response.callRootNode ;
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

    public void addNextQuestion(Response node) {
        nextQuestions.add(node);
    }

    public static Response createGraph() {
        // Racine du graphe
        Response root = new Response("Quelle est la langue la plus populaire au monde ?", "La langue la plus populaire au monde est l'anglais.");

        // Langue et pays
        Response languageQuestion = new Response("Quelle est la langue la plus parlée en France ?", "La langue la plus parlée en France est le français.");
        root.addNextQuestion(languageQuestion);

        Response capitalQuestion = new Response("Quelle est la capitale des États-Unis ?", "La capitale des États-Unis est Washington D.C.");
        languageQuestion.addNextQuestion(capitalQuestion);

        Response continentQuestion = new Response("Quel est le plus grand continent du monde ?", "Le plus grand continent du monde est l'Asie.");
        capitalQuestion.addNextQuestion(continentQuestion);

        Response populationQuestion = new Response("Quel est le pays le plus peuplé au monde ?", "Le pays le plus peuplé au monde est la Chine.");
        capitalQuestion.addNextQuestion(populationQuestion);

        // Géographie et nature
        Response oceanQuestion = new Response("Quel est le plus grand océan du monde ?", "Le plus grand océan du monde est l'océan Pacifique.");
        root.addNextQuestion(oceanQuestion);

        Response mountainQuestion = new Response("Quel est le plus haut sommet du monde ?", "Le plus haut sommet du monde est l'Everest.");
        root.addNextQuestion(mountainQuestion);

        Response desertQuestion = new Response("Quel est le plus grand désert du monde ?", "Le plus grand désert du monde est le désert de l'Antarctique.");
        root.addNextQuestion(desertQuestion);

        Response riverQuestion = new Response("Quel est le fleuve le plus long d'Europe ?", "Le fleuve le plus long d'Europe est la Volga.");
        root.addNextQuestion(riverQuestion);

        Response lakeQuestion = new Response("Quel est le plus grand lac du monde ?", "Le plus grand lac du monde est la mer Caspienne.");
        root.addNextQuestion(lakeQuestion);

        Response animalQuestion = new Response("Quel est le plus grand animal terrestre ?", "Le plus grand animal terrestre est l'éléphant.");
        root.addNextQuestion(animalQuestion);

        // Économie et culture
        Response currencyQuestion = new Response("Quelle est la monnaie utilisée en Russie ?", "La monnaie utilisée en Russie est le rouble.");
        root.addNextQuestion(currencyQuestion);

        Response monumentQuestion = new Response("Quels sont les monuments célèbres de Paris ?", "Certains des monuments célèbres de Paris sont la Tour Eiffel et le Louvre.");
        root.addNextQuestion(monumentQuestion);

        Response populationParisQuestion = new Response("Quelle est la population de Paris ?", "La population de Paris est d'environ 2.2 millions d'habitants.");
        root.addNextQuestion(populationParisQuestion);

        // Autres langues et pays
        Response languageSpainQuestion = new Response("Quelle est la langue officielle de l'Espagne ?", "La langue officielle de l'Espagne est l'espagnol.");
        root.addNextQuestion(languageSpainQuestion);

        Response capitalItalyQuestion = new Response("Quelle est la capitale de l'Italie ?", "La capitale de l'Italie est Rome.");
        root.addNextQuestion(capitalItalyQuestion);

        Response continentSmallestQuestion = new Response("Quel est le plus petit continent du monde ?", "Le plus petit continent du monde est l'Océanie.");
        root.addNextQuestion(continentSmallestQuestion);

        Response populationVaticanQuestion = new Response("Quel est le pays le moins peuplé au monde ?", "Le pays le moins peuplé au monde est le Vatican.");
        root.addNextQuestion(populationVaticanQuestion);

        Response languageGermanyQuestion = new Response("Quelle est la langue officielle de l'Allemagne ?", "La langue officielle de l'Allemagne est l'allemand.");
        root.addNextQuestion(languageGermanyQuestion);

        Response capitalIndiaQuestion = new Response("Quelle est la capitale de l'Inde ?", "La capitale de l'Inde est New Delhi.");
        root.addNextQuestion(capitalIndiaQuestion);

        Response continentMostPopulatedQuestion = new Response("Quel est le continent le plus peuplé du monde ?", "Le continent le plus peuplé du monde est l'Asie.");
        root.addNextQuestion(continentMostPopulatedQuestion);

        Response animalLargest2Question = new Response("Quel est le plus grand animal terrestre ?", "Le plus grand animal terrestre est l'éléphant.");
        root.addNextQuestion(animalLargest2Question);

        // Définition de la racine
        setCurrentNode(root);
        return root;
    }


}
