package com.androidjson.sqlitelogin_androidjsoncom.ui.home.CprogTopic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.androidjson.sqlitelogin_androidjsoncom.R;
import com.androidjson.sqlitelogin_androidjsoncom.ui.home.Model;
import com.androidjson.sqlitelogin_androidjsoncom.ui.home.MyAdapter;

import java.util.ArrayList;

public class cprog extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cprog);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        myAdapter = new MyAdapter(this,getList(), MyAdapter.AdapterType.c_prog);
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
        m.setTitle("Hello World!");
        m.setImg(R.raw.code);
        models.add(m);

        m= new Model();
        m.setTitle("Data Types");
        m.setImg(R.raw.objects);
        models.add(m);

        m= new Model();
        m.setTitle("Input");
        m.setImg(R.raw.input);
        models.add(m);

        m= new Model();
        m.setTitle("Output");
        m.setImg(R.raw.output);
        models.add(m);

        m= new Model();
        m.setTitle("Comments");
        m.setImg(R.raw.comments);
        models.add(m);

        m= new Model();
        m.setTitle("Quiz");
        m.setImg(R.raw.quiz);
        models.add(m);

        return models;
    }
}
