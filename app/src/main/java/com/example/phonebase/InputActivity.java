package com.example.phonebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InputActivity extends AppCompatActivity {

    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener((View v) -> {
            finish();
        });
    }
}