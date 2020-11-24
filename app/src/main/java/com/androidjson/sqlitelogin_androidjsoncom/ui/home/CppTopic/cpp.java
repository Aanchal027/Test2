package com.androidjson.sqlitelogin_androidjsoncom.ui.home.CppTopic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.androidjson.sqlitelogin_androidjsoncom.R;
import com.androidjson.sqlitelogin_androidjsoncom.ui.home.Model;
import com.androidjson.sqlitelogin_androidjsoncom.ui.home.MyAdapter;

import java.util.ArrayList;

public class cpp extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpp);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        myAdapter = new MyAdapter(this,getList(), MyAdapter.AdapterType.cpp);
        recyclerView.setAdapter(myAdapter);
    }
    public ArrayList<Model> getList(){

        ArrayList<Model> models = new ArrayList<>();
        Model m;
        m= new Model();
        m.setTitle("Introduction");
        m.setImg(R.raw.intro);
        models.add(m);

        m= new Model();
        m.setTitle("Hello World");
        m.setImg(R.raw.code);
        models.add(m);

        m= new Model();
        m.setTitle("Data Types");
        m.setImg(R.raw.objects);
        models.add(m);

        m= new Model();
        m.setTitle("C++ Loops");
        m.setImg(R.raw.loop);
        models.add(m);

        m= new Model();
        m.setTitle("Exception");
        m.setImg(R.raw.exception);
        models.add(m);

        m= new Model();
        m.setTitle("Quiz");
        m.setImg(R.raw.quiz);
        models.add(m);

        return models;
    }
}
