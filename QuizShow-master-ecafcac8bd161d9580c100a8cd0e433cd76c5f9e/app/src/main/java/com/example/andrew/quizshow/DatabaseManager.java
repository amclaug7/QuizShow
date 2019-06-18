package com.example.andrew.quizshow;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "quizDB";
    private static final String TABLE_QUIZ = "quiz";
    private static final String ID = "id";
    private static final String QUESTION = "question";
    private static final String ANSWER = "answer";
    private SQLiteDatabase db;

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase newDB) {
        db = newDB;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUIZ + " ( "
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + QUESTION
                + " TEXT, " + ANSWER + ")";
        newDB.execSQL(sql);
        addQuestions();
    }

    private void addQuestions() {
        Quiz questionOne = new Quiz("When was the Peace of Westphalia","1648");
        this.addQuestion(questionOne);
        Quiz questionTwo = new Quiz("Who invented the barometer","Evangelista Torricella");
        this.addQuestion(questionTwo);
        Quiz questionThree = new Quiz("Who was the first Lord Protector of England","Oliver Cromwell");
        this.addQuestion(questionThree);
        Quiz questionFour = new Quiz("Who is called the Father of Empiricism","Francis Bacon");
        this.addQuestion(questionFour);
        Quiz questionFive = new Quiz("Who wrote The Pilgrim's Progress","John Bunyan");
        this.addQuestion(questionFive);
        Quiz questionSix = new Quiz("What was the Nine Years War called in America","King William's War");
        this.addQuestion(questionSix);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIZ);
        onCreate(db);
    }

    public void addQuestion(Quiz quiz) {
        ContentValues values = new ContentValues();
        values.put(QUESTION, quiz.getQuestion());
        values.put(ANSWER, quiz.getAnswer());
        db.insert(TABLE_QUIZ, null, values);
    }

    public ArrayList<Quiz> getAllQuestions() {
        ArrayList<Quiz> questionList = new ArrayList<Quiz>();
        String selectQuery = "SELECT  * FROM " + TABLE_QUIZ;
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Quiz questions = new Quiz();
                questions.setId(cursor.getInt(0));
                questions.setQuestion(cursor.getString(1));
                questions.setAnswer(cursor.getString(2));
                questionList.add(questions);
            } while (cursor.moveToNext());
        }
        return questionList;
    }
}

