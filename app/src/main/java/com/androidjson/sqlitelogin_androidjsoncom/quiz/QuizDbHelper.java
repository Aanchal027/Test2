package com.androidjson.sqlitelogin_androidjsoncom.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuizContract.QuestionsTable.COLUMN_CATEGORY + " TEXT " +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {

        //Java Questions
        Question q1 = new Question(" Which one of the following is true ?", "Java has a huge developer community",
                "Java is only used in web and moblie applications",
                "Java is used only in NASA's space related applications", 1,"java");
        addQuestion(q1);
        Question q2 = new Question(" Which method prints a text in a Java program ?", "System.out.println()",
                "out.writeText()", "System.printText()", 1,"java");
        addQuestion(q2);
        Question q3 = new Question(" Which variable would you use for city name ?", "int", "String",
                "double", 2,"java");
        addQuestion(q3);
        Question q4 = new Question(" An if statement can contain how many else if statements ?", "None",
                "Only two", "As many as you want", 3,"java");
        addQuestion(q4);
        Question q5 = new Question(" How is a do while loop different from a while loop ?", "A while loop runs the code before testing the condition.",
                "A do while loop runs your code at least one time",
                "A do while loop tests the condition before running the code", 2,"java");
        addQuestion(q5);

        //html question
        Question q6 = new Question(" What is HTML ?", "Programming Language",
                "Markup Language",
                "Hypertext Library", 2,"html");
        addQuestion(q6);
        Question q7 = new Question(" Of which main parts does the HTML file consist of ?", "head and body",
                "hands and feet",
                "header and footer", 1,"html");
        addQuestion(q7);
        Question q8 = new Question(" Which tag contains the visual part of the web page ?", "<head>",
                "<title>",
                "<body>", 3,"html");
        addQuestion(q8);
        Question q9 = new Question("What does a markup language use to identify content ?", "tags",
                "scripts",
                "functions", 1,"html");
        addQuestion(q9);

        //cprog question
        Question q10 = new Question(" Which is the starting point of a C program ?",
                "The main() function",
                "First line",
                "The <stdio.h> header", 1,"cprog");
        addQuestion(q10);
        Question q11 = new Question(" Which choice indicates a single line comment ?",
                "*/ single line comment",
                "// single line comment",
                "/* single line comment", 2,"cprog");
        addQuestion(q11);
        Question q12 = new Question(" Which of the following is a valid data type in C ?",
                "int, double, char, boolean",
                "int, float, string, char",
                "int, float double, char", 3,"cprog");
        addQuestion(q12);
        Question q13 = new Question(" C is a :",
                "Photo editting program",
                "Client-side scripting language",
                "General purpose programming language", 3,"cprog");
        addQuestion(q13);
        Question q14 = new Question("The _______ function is used to assign input to variable.",
                "scnaf()",
                "printf()",
                "putChar()", 1,"cprog");
        addQuestion(q14);

        // cpp question
        Question q15 = new Question("Each instruction must end with a  :",
                "colon ( ; ) ",
                "dot ( . )",
                "comma ( , )", 1,"cpp");
        addQuestion(q15);
        Question q16 = new Question("Which option do you need to make a C++ program ?",
                "Web Browser",
                "Framework",
                "Compiler", 3,"cpp");
        addQuestion(q16);
        Question q17 = new Question("Which of the following is a C++ compiler ?",
                "GNU GCC",
                "Console",
                "GAC", 1,"cpp");
        addQuestion(q17);
        Question q18 = new Question("What should be used to move to a new line?",
                "startl",
                "endl",
                "return", 2,"cpp");
        addQuestion(q18);
        Question q19 = new Question("Which statement is true ?",
                "Variables must be declared before their use",
                "Variables are pre-processor directives",
                "Variables do not have names", 1,"cpp");
        addQuestion(q19);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuizContract.QuestionsTable.COLUMN_CATEGORY, question.getCategory());
        db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, cv);
    }

    public ArrayList<Question> getAllQuestions(String type) {

        //Log.i("from quizActivity",type);
        String[] selectionArgs = new String[]{type};
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME +
                " WHERE " + QuizContract.QuestionsTable.COLUMN_CATEGORY + " = ?" , selectionArgs);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                question.setCategory((c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_CATEGORY))));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }


}
