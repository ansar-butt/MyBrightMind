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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class moduleActivity extends BottomNav {
    private ArrayList<String> moduleList;
    private ArrayList<Integer> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_module);
        defineNavBar(R.id.explore_button);
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
        getData(type.equals("Courses")?"courses.json":"stories.json");

        setRecyclerView();

    }

    private void setRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.module_list);

        CustomAdapterModule mAdapterModule = new CustomAdapterModule(moduleList, imageList);
        recyclerView.setAdapter(mAdapterModule);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    void getData(String fileName){
        String json;
        try{
            InputStream is = this.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(json);
            JSONArray arr = jsonObject.getJSONArray("data");
            for (int i = 0; i < arr.length(); i++){
                JSONObject jsnObj = arr.getJSONObject(i);
                moduleList.add((String) jsnObj.get("name"));
                imageList.add(this.getResources().getIdentifier((String) jsnObj.get("drawable"), "drawable", this.getPackageName()));
            }

        }  catch (JSONException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
