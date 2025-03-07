package com.example.mybrightmind;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class rewardActivityFirst extends BottomNav{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reward_first);
        defineNavBar(R.id.reward_button);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.reward_first), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn = findViewById(R.id.explore_courses_button);
        btn.setOnClickListener(v->{
            Intent intent = new Intent(this, moduleActivity.class);
            intent.putExtra("type", "Courses");
            startActivity(intent);
            setResult(Activity.RESULT_OK);
        });
    }

}
