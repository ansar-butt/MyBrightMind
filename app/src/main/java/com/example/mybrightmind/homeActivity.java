package com.example.mybrightmind;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.navigation.NavigationBarView;

public class homeActivity extends BottomNav {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        defineNavBar();
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.home), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView allCourses = findViewById(R.id.all_courses);
        allCourses.setOnClickListener(v->goToAllModules("Courses"));
        
        TextView allStories = findViewById(R.id.all_books);
        allStories.setOnClickListener(v->goToAllModules("Story World"));

    }

    private void goToAllModules(String type) {
        Intent intent = new Intent(this, moduleActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
        setResult(Activity.RESULT_OK);
    }

}
