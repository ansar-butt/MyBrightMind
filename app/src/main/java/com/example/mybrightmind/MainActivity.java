package com.example.mybrightmind;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class MainActivity extends AppCompatActivity {
    private TypedArray homeImages;
    private TypedArray fillImages;
    private String[] headingList;
    private String[] subHeadingList;
    private ImageSwitcher imageSwitcher;
    private ImageSwitcher fillSwitcher;
    private int imageIndex = 0;
    private float x1, x2;
    static final int MIN_DISTANCE = 30;
    TextView heading;
    TextView subHeading;

    @SuppressLint("ClickableViewAccessibility")
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

        imageSwitcher = findViewById(R.id.HomeImage);
        fillSwitcher = findViewById(R.id.fillImage);
        imageSwitcher.setFactory(() -> {
            ImageView imgView = new ImageView(getApplicationContext());
            imgView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imgView;
        });
        fillSwitcher.setFactory(() -> {
            ImageView imgView = new ImageView(getApplicationContext());
            imgView.setScaleType(ImageView.ScaleType.CENTER);
            return imgView;
        });

        imageSwitcher.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    x1 = event.getX();
                    return true;
                case MotionEvent.ACTION_UP:
                    x2 = event.getX();
                    float deltaX = x2 - x1;
                    if (deltaX > MIN_DISTANCE && imageIndex > 0) {
                        imageIndex--;
                        setCardValues();
                    } else if (deltaX < 0 && imageIndex < homeImages.length() - 1) {
                        imageIndex++;
                        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
                        setCardValues();
                    }
                    return true;
                default:
                    return false;
            }
        });

        headingList = getResources().getStringArray(R.array.headings);
        subHeadingList = getResources().getStringArray(R.array.sub_headings);
        homeImages = getResources().obtainTypedArray(R.array.homeImages);
        fillImages = getResources().obtainTypedArray(R.array.fills);
        heading = findViewById(R.id.Heading);
        subHeading = findViewById(R.id.SubHeading);

        Button nextButton = findViewById(R.id.Next);

        setCardValues();

        nextButton.setOnClickListener(v -> {
            if (imageIndex < homeImages.length() - 1) {
                imageIndex++;
                setCardValues();
            } else {
                goToSignUpActivity();
            }
        });

        TextView skipText = findViewById(R.id.Skip);
        skipText.setOnClickListener(view -> goToSignUpActivity());
        try {
            FileInputStream fis = this.openFileInput("userData");
            InputStreamReader inputStreamReader =
                    new InputStreamReader(fis, StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            if (!line.isEmpty())
                goToHomeActivity();
        } catch (IOException e) {
            // Error occurred when opening raw file for reading.
        } finally {
            String contents = "stringBuilder.toString();";
        }
    }

    private void goToHomeActivity() {
        Intent intent = new Intent(this, homeActivity.class);
        startActivity(intent);
        setResult(Activity.RESULT_OK);
    }

    private void goToSignUpActivity() {
        Intent intent = new Intent(this, signUpActivity.class);
        startActivity(intent);
        setResult(Activity.RESULT_OK);
    }

    private void setCardValues() {
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));
        imageSwitcher.setImageResource(homeImages.getResourceId(imageIndex, -1));
        fillSwitcher.setImageResource(fillImages.getResourceId(imageIndex, -1));
        heading.setText(headingList[imageIndex]);
        subHeading.setText(subHeadingList[imageIndex]);
    }
}