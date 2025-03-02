package com.example.mybrightmind;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileOutputStream;

public class signUpActivity extends AppCompatActivity {
    private String selected = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signup), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView student = findViewById(R.id.student);
        TextView teacher = findViewById(R.id.teacher);
        TextView parent = findViewById(R.id.parent);


        student.setOnClickListener(v -> {
            v.setBackground(AppCompatResources.getDrawable(this, R.drawable.bg_rounded_selected));
            parent.setBackground(AppCompatResources.getDrawable(this, R.drawable.bg_rounded));
            teacher.setBackground(AppCompatResources.getDrawable(this, R.drawable.bg_rounded));
            selected = "student";
        });
        parent.setOnClickListener(v -> {
            v.setBackground(AppCompatResources.getDrawable(this, R.drawable.bg_rounded_selected));
            teacher.setBackground(AppCompatResources.getDrawable(this, R.drawable.bg_rounded));
            student.setBackground(AppCompatResources.getDrawable(this, R.drawable.bg_rounded));
            selected = "parent";
        });
        teacher.setOnClickListener(v -> {
            v.setBackground(AppCompatResources.getDrawable(this, R.drawable.bg_rounded_selected));
            parent.setBackground(AppCompatResources.getDrawable(this, R.drawable.bg_rounded));
            student.setBackground(AppCompatResources.getDrawable(this, R.drawable.bg_rounded));
            selected = "teacher";
        });

        Button signupButton = findViewById(R.id.signup_button);
        signupButton.setOnClickListener(v -> {
            String firstName = String.valueOf(((EditText) findViewById(R.id.first_name)).getText());
            String lastName = String.valueOf(((EditText) findViewById(R.id.last_name)).getText());
            if (!firstName.isEmpty() && !lastName.isEmpty() && !selected.isEmpty()) {
                String filename = "userData";
                String fileContents = String.format("%s;%s;%s;", firstName, lastName, selected);
                try (FileOutputStream fos = this.openFileOutput(filename, Context.MODE_PRIVATE)) {
                    fos.write(fileContents.getBytes());
                    goToHomeActivity();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                Toast.makeText(this, "Please Input Missing Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void goToHomeActivity() {
        Intent intent = new Intent(this, homeActivity.class);
        startActivity(intent);
        setResult(Activity.RESULT_OK);
    }
}

