package com.example.phonebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InputActivity extends AppCompatActivity {

    Button cancelButton;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        setButtons();
    }

    void setButtons() {

        cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener((View v) -> {

            Intent intent = new Intent();
            setResult(RESULT_CANCELED, intent);
            finish();
        });

        saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener((View v) -> {

            Intent intent = new Intent();
            // intent.putExtra()    // put data to return
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}