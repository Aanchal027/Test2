package com.androidjson.sqlitelogin_androidjsoncom.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidjson.sqlitelogin_androidjsoncom.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    Adapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new Adapter(getActivity(),getMyList());
        recyclerView.setAdapter(adapter);

        return root;
    }
   public ArrayList<Model> getMyList(){

        ArrayList<Model> models = new ArrayList<Model>();
        Model m;
        m= new Model();
        m.setTitle("Java");
        m.setImg(R.drawable.icons8_java_100);
        models.add(m);

        m= new Model();
        m.setTitle("HTML");
        m.setImg(R.drawable.icons8_html5_70);
        models.add(m);

        m= new Model();
        m.setTitle("C Prog");
        m.setImg(R.drawable.icons8_c_programming_70);
        models.add(m);

        m= new Model();
        m.setTitle("C++");
        m.setImg(R.drawable.icons8_cpp_100);
        models.add(m);

       return models;
    }
}
