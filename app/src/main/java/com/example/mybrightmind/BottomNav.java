package com.example.mybrightmind;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationBarView;

public class BottomNav extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void defineNavBar(Integer selected) {
        NavigationBarView navBar = findViewById(R.id.bottom_navigation);
        navBar.setSelectedItemId(selected);

        try{
            navBar.setOnItemSelectedListener(item -> {
                int exploreButton = R.id.explore_button;
                int learnButton = R.id.learn_button;
                int rewardButton = R.id.reward_button;
                int searchButton = R.id.search_button;

                int id = item.getItemId();
                if (id == searchButton) {
                    goToSearchActivity();
                    return true;
                } else if (id == exploreButton) {
                    goToExploreActivity();
                    return true;
                } else if (id == learnButton) {
                    goToLearnActivity();
                    return true;
                } else if (id == rewardButton) {
                    goToRewardActivity();
                    return true;
                } else {
                    goToHomeActivity();
                    return true;
                }
            });

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private void goToSearchActivity() {
        Intent intent = new Intent(this, searchActivity.class);
        startActivity(intent);
        setResult(Activity.RESULT_OK);
    }

    private void goToRewardActivity() {
        Intent intent = new Intent(this, rewardActivityFirst.class);//Add check and redirect to reward proper
        startActivity(intent);
        setResult(Activity.RESULT_OK);
    }

    private void goToLearnActivity() {
        Intent intent = new Intent(this, learnActivityFirst.class); //Add check and redirect to learn proper
        startActivity(intent);
        setResult(Activity.RESULT_OK);
    }

    private void goToExploreActivity() {
        Intent intent = new Intent(this, moduleActivity.class);
        intent.putExtra("type", "Courses");
        startActivity(intent);
        setResult(Activity.RESULT_OK);
    }

    private void goToHomeActivity() {
        Intent intent = new Intent(this, homeActivity.class);
        startActivity(intent);
        setResult(Activity.RESULT_OK);
    }
}
