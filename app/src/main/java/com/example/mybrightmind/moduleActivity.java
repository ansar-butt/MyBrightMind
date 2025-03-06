package com.example.mybrightmind;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class moduleActivity extends BottomNav {
    private ArrayList<String> moduleList;
    private ArrayList<Integer> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_module);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.module), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        moduleList = new ArrayList<>();
        imageList = new ArrayList<>();

        TextView heading = findViewById(R.id.page_type);
        heading.setText(type);

        assert type != null;
        if (type.equals("Courses")) {
            moduleList.add("Course 1");
            moduleList.add("Course 2");
            moduleList.add("Course 3");
            moduleList.add("Course 4");
            imageList.add(R.drawable.home1);
            imageList.add(R.drawable.home1);
            imageList.add(R.drawable.home1);
            imageList.add(R.drawable.home1);
        } else {
            moduleList.add("Story 1");
            moduleList.add("Story 2");
            moduleList.add("Story 3");
            moduleList.add("Story 4");
            imageList.add(R.drawable.home2);
            imageList.add(R.drawable.home2);
            imageList.add(R.drawable.home2);
            imageList.add(R.drawable.home2);
        }

        setRecyclerView();

    }

    private void setRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.module_list);

        CustomAdapterModule mAdapterModule = new CustomAdapterModule(moduleList, imageList);
        recyclerView.setAdapter(mAdapterModule);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
