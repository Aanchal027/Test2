package com.androidjson.sqlitelogin_androidjsoncom.ui.home.JavaTopic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.androidjson.sqlitelogin_androidjsoncom.R;
import com.androidjson.sqlitelogin_androidjsoncom.ui.home.*;

import java.util.ArrayList;

public class Java_Home extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java__home);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        myAdapter = new MyAdapter(this,getList(), MyAdapter.AdapterType.java_prog);
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
        m.setTitle("Classes");
        m.setImg(R.raw.objects);
        models.add(m);

        m= new Model();
        m.setTitle("Arrays");
        m.setImg(R.raw.array);
        models.add(m);

        m= new Model();
        m.setTitle("Loops");
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
