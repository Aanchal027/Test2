package com.androidjson.sqlitelogin_androidjsoncom.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidjson.sqlitelogin_androidjsoncom.R;
import com.androidjson.sqlitelogin_androidjsoncom.ui.home.CprogTopic.*;
import com.androidjson.sqlitelogin_androidjsoncom.ui.home.JavaTopic.*;
import com.androidjson.sqlitelogin_androidjsoncom.ui.home.CppTopic.*;
import com.androidjson.sqlitelogin_androidjsoncom.ui.home.HtmlTopic.*;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Holder> {

    Context c;
    ArrayList<Model> models;
    public Adapter(Context c,ArrayList<Model> models){
        this.c =c;
        this.models = models;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {
        holder.title.setText(models.get(position).getTitle());
        holder.iconImage.setImageResource(models.get(position).getImg());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(c, "Clicked", Toast.LENGTH_SHORT).show();
                if (models.get(position).getTitle().equals("Java")){
                    Intent intent = new Intent(c, Java_Home.class);
                    c.startActivity(intent);
                }
                else  if (models.get(position).getTitle().equals("HTML")){
                    Intent intent = new Intent(c, html.class);
                    c.startActivity(intent);
                }
                else  if (models.get(position).getTitle().equals("C Prog")){
                    Intent intent = new Intent(c, cprog.class);
                    c.startActivity(intent);
                }
                else  if (models.get(position).getTitle().equals("C++")){
                    Intent intent = new Intent(c, cpp.class);
                    c.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
