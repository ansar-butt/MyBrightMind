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

public class lessonActivity extends BottomNav {
    private ArrayList<String> lessonTitles;
    private ArrayList<String> lessonDescriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_module_list);
        defineNavBar(R.id.explore_button);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.module_list), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String module = intent.getStringExtra("topicName");
        assert module != null;

        lessonTitles = new ArrayList<>();
        lessonDescriptions = new ArrayList<>();

        TextView heading = findViewById(R.id.topic_name);
        heading.setText(module);

        getData(module);

        setRecyclerView();

    }

    private void setRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.lesson_list);

        CustomAdapterLesson mAdapterModule = new CustomAdapterLesson(lessonTitles, lessonDescriptions);
        recyclerView.setAdapter(mAdapterModule);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    void getData(String moduleName) {
        try {
            InputStream is = this.getAssets().open("courses.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(json);
            JSONArray arr = jsonObject.getJSONArray("data");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject jsnObj = arr.getJSONObject(i);
                if (jsnObj.get("name").toString().equals(moduleName)) {
                    TextView description = findViewById(R.id.topic_description);
                    description.setText(jsnObj.get("description").toString());

                    JSONArray arr2 = jsnObj.getJSONArray("lessons");
                    for (int j = 0; j < arr2.length(); j++) {
                        JSONObject jsnObj2 = arr2.getJSONObject(j);
                        lessonTitles.add((String) jsnObj2.get("title"));
                        lessonDescriptions.add((String) jsnObj2.get("description"));
                    }
                    i = 1000;
                }
            }

        } catch (JSONException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
