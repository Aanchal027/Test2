package com.androidjson.sqlitelogin_androidjsoncom.ui.home.HtmlTopic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.androidjson.sqlitelogin_androidjsoncom.R;
import com.androidjson.sqlitelogin_androidjsoncom.ui.home.Model;
import com.androidjson.sqlitelogin_androidjsoncom.ui.home.MyAdapter;

import java.util.ArrayList;

public class html extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        myAdapter = new MyAdapter(this,getList(), MyAdapter.AdapterType.html_prog);
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
        m.setTitle("First Web Page");
        m.setImg(R.raw.code);
        models.add(m);

        m= new Model();
        m.setTitle("Document Structure");
        m.setImg(R.raw.array);
        models.add(m);

        m= new Model();
        m.setTitle("Quiz");
        m.setImg(R.raw.quiz);
        models.add(m);

        return models;
    }
}
