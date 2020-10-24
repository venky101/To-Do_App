package com.venkat.todoapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.venkat.todoapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listData.add("Data 1");
        listData.add("Data 2");
        listData.add("Data 3");
        listData.add("Data 4");
        listData.add("Data 5");
        listData.add("Data 6");
        listData.add("Data 7");
        listData.add("Data 8");
        listData.add("Data 9");
        listData.add("Data 10");
        listData.add("Data 11");
        listData.add("Data 12");
        listData.add("Data 13");
        listData.add("Data 14");
        listData.add("Data 15");
        listData.add("Data 16");
        listData.add("Data 17");
        listData.add("Data 18");
        listData.add("Data 19");
        listData.add("Data 20");


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        RecyclerAdapter adapter = new RecyclerAdapter(listData,this);
        recyclerView.setAdapter(adapter);


    }
}