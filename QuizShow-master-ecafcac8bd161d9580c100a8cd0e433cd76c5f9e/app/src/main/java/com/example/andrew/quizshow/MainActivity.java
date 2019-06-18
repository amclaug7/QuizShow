package com.example.andrew.quizshow;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    ArrayList<Quiz> quizzes;
    int questionID = 0;
    Quiz currentQuestion;
    TextView question;
    Button button_answer;
    TextView label_answer;
    TextView answer;
    Button next_question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseManager db = new DatabaseManager(this);
        quizzes = db.getAllQuestions();
        currentQuestion = quizzes.get(questionID);
        question = findViewById(R.id.question);
        button_answer = findViewById(R.id.button_answer);
        label_answer = findViewById(R.id.label_answer);
        answer = findViewById(R.id.answer);
        next_question = findViewById(R.id.next_question);
        ButtonHandler1 bh1 = new ButtonHandler1();
        ButtonHandler2 bh2 = new ButtonHandler2();
        button_answer.setOnClickListener(bh1);
        next_question.setOnClickListener(bh2);
        setQuestionView();
    }

    public void showAnswer(){
        if(questionID < 6) {
            answer.setText(currentQuestion.getAnswer());
            label_answer.setVisibility(View.VISIBLE);
            answer.setVisibility(View.VISIBLE);
            next_question.setVisibility(View.VISIBLE);
        }
        else{
            answer.setText(currentQuestion.getAnswer());
            next_question.setText("End Quiz");
            label_answer.setVisibility(View.VISIBLE);
            answer.setVisibility(View.VISIBLE);
            next_question.setVisibility(View.VISIBLE);

        }
    }

    private void setQuestionView() {
        question.setText(currentQuestion.getQuestion());
        label_answer.setVisibility(View.INVISIBLE);
        answer.setVisibility(View.INVISIBLE);
        next_question.setVisibility(View.INVISIBLE);
        questionID++;
    }

    private class ButtonHandler1 implements View.OnClickListener{
        public void onClick(View v){
            showAnswer();
        }
    }

    private class ButtonHandler2 implements View.OnClickListener{
        public void onClick(View v){
            if(questionID < 6){
            currentQuestion=quizzes.get(questionID);
            setQuestionView();
            }
            else {
            setContentView(R.layout.activity_final);
            }
        }
    }
}

