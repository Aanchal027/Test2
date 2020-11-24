package com.androidjson.sqlitelogin_androidjsoncom.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidjson.sqlitelogin_androidjsoncom.R;
import com.androidjson.sqlitelogin_androidjsoncom.quiz.QuizMainActivity;
import com.androidjson.sqlitelogin_androidjsoncom.ui.home.JavaTopic.*;
import com.androidjson.sqlitelogin_androidjsoncom.ui.home.CppTopic.*;
import com.androidjson.sqlitelogin_androidjsoncom.ui.home.CprogTopic.*;
import com.androidjson.sqlitelogin_androidjsoncom.ui.home.HtmlTopic.*;

import java.util.ArrayList;

import static android.content.Intent.getIntent;

public class MyAdapter extends RecyclerView.Adapter<Holder> {

    Context c;
    ArrayList<Model> models;
    AdapterType type;

    public MyAdapter(Context c, ArrayList<Model> models, AdapterType type) {
        this.c = c;
        this.models = models;
        this.type = type;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contentcard,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        holder.infoTitle.setText(models.get(position).getTitle());
        holder.infoIconImage.setImageResource(models.get(position).getImg());

        holder.infoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                switch (type) {
                    case java_prog:
                        if (models.get(position).getTitle().equals("Introduction")) {
                            intent = new Intent(c, javaIntroduction.class);
                            c.startActivity(intent);
                        }
                        else if(models.get(position).getTitle().equals("Classes")){
                            intent = new Intent(c, java_classes.class);
                            c.startActivity(intent);
                        }
                        else if(models.get(position).getTitle().equals("Arrays")){
                            intent = new Intent(c, arrays.class);
                            c.startActivity(intent);
                        }
                        else if(models.get(position).getTitle().equals("Loops")){
                            intent = new Intent(c, loops.class);
                            c.startActivity(intent);
                        }
                        else if(models.get(position).getTitle().equals("Exception")){
                            intent = new Intent(c, exception.class);
                            c.startActivity(intent);
                        }
                        else if(models.get(position).getTitle().equals("Quiz")){
                            String baseTitle = "JavaQuiz";
                            intent = new Intent(c, QuizMainActivity.class);
                            intent.putExtra("Linear Card", baseTitle);
                            c.startActivity(intent);
                        }
                        break;
                    case c_prog:
                        if(models.get(position).getTitle().equals("Introduction")) {
                           intent = new Intent(c, introduction.class );
                           c.startActivity(intent);
                        }
                        else if(models.get(position).getTitle().equals("Hello World!")) {
                            intent = new Intent(c, cprog_helloWorld.class );
                            c.startActivity(intent);
                        }
                        else if(models.get(position).getTitle().equals("Data Types")) {
                            intent = new Intent(c, types_in_cprog.class );
                            c.startActivity(intent);
                        }
                        else if(models.get(position).getTitle().equals("Input")) {
                            intent = new Intent(c, input_in_cprog.class );
                            c.startActivity(intent);
                        }
                        else if(models.get(position).getTitle().equals("Output")) {
                            intent = new Intent(c, output_in_cprog.class );
                            c.startActivity(intent);
                        }
                        else if(models.get(position).getTitle().equals("Comments")) {
                            intent = new Intent(c, comments_in_cprog.class );
                            c.startActivity(intent);
                        }
                        else if(models.get(position).getTitle().equals("Quiz")) {
                            String baseTitle = "CprogQuiz";
                            intent = new Intent(c, QuizMainActivity.class);
                            intent.putExtra("Linear Card", baseTitle);
                            c.startActivity(intent);
                        }
                        break;
                    case cpp:
                        if (models.get(position).getTitle().equals("Introduction")) {
                            intent = new Intent(c, cpp_introduction.class);
                            c.startActivity(intent);
                        }
                        else if (models.get(position).getTitle().equals("Hello World")){
                            intent = new Intent(c, hello_world.class);
                            c.startActivity(intent);
                        }
                        else if (models.get(position).getTitle().equals("Data Types")){
                            intent = new Intent(c, data_types.class);
                            c.startActivity(intent);
                        }
                        else if (models.get(position).getTitle().equals("C++ Loops")){
                            intent = new Intent(c, cpp_loops.class);
                            c.startActivity(intent);
                        }
                        else if(models.get(position).getTitle().equals("Quiz")){
                            String baseTitle = "CppQuiz";
                            intent = new Intent(c, QuizMainActivity.class);
                            intent.putExtra("Linear Card", baseTitle);
                            c.startActivity(intent);
                        }
                        break;
                    case html_prog:
                        if (models.get(position).getTitle().equals("Introduction")) {
                            intent = new Intent(c, htmlIntroduction.class);
                            c.startActivity(intent);
                        }
                        else if (models.get(position).getTitle().equals("First Web Page")) {
                            intent = new Intent(c, first_webpage.class);
                            c.startActivity(intent);
                        }
                        else if (models.get(position).getTitle().equals("Document Structure")) {
                            intent = new Intent(c, structure.class);
                            c.startActivity(intent);
                        }
                        else if(models.get(position).getTitle().equals("Quiz")){
                            String baseTitle = "HtmlQuiz";
                            intent = new Intent(c, QuizMainActivity.class);
                            intent.putExtra("Linear Card", baseTitle);
                            c.startActivity(intent);
                        }
                    default: break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
    public enum AdapterType{
        java_prog,html_prog,cpp,c_prog
    };
}
