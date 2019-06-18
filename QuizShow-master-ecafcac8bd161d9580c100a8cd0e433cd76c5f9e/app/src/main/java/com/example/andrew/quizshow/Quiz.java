package com.example.andrew.quizshow;

public class Quiz {
    private int id;
    private String question;
    private String answer;

    public Quiz() {
        id = 0;
        question = "";
        answer = "";
    }

    public Quiz(String newQuestion, String newAnswer){
        setQuestion(newQuestion);
        setAnswer(newAnswer);
    }

    public int getId(){
        return id;
    }

    public String getQuestion(){
        return question;
    }

    public String getAnswer(){
        return answer;
    }

    public void setId(int newId){
        id = newId;
    }

    public void setQuestion(String newQuestion){
        question = newQuestion;
    }

    public void setAnswer(String newAnswer){
        answer = newAnswer;
    }

}
