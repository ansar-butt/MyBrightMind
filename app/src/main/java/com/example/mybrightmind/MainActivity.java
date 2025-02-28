package com.example.mybrightmind;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {
    private final int[] homeImages = {R.drawable.home1, R.drawable.home2, R.drawable.home3, R.drawable.home4};
    private int imageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageSwitcher imageSwitcher = findViewById(R.id.HomeImage);
        imageSwitcher.setFactory(() -> {
            ImageView imgView = new ImageView(getApplicationContext());
            imgView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imgView.setPadding(8, 8, 8, 8);
            return imgView;
        });

        imageSwitcher.setImageResource(homeImages[imageIndex]);
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));

        Button nextButton = findViewById(R.id.Next);
        nextButton.setOnClickListener(v -> {
            if (imageIndex < homeImages.length - 1) {
                imageIndex++;
                imageSwitcher.setImageResource(homeImages[imageIndex]);
            } else {
                goToSignUpActivity();
            }

        });


        TextView skipText = findViewById(R.id.Skip);
        skipText.setOnClickListener(view -> {
            goToSignUpActivity();
        });
    }

    private void goToSignUpActivity() {
        Intent intent = new Intent(this, signUpActivity.class);
        startActivity(intent);
        setResult(Activity.RESULT_OK);
    }

}