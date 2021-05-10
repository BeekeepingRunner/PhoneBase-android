package com.example.phonebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.TreeMap;

public class InputActivity extends AppCompatActivity {

    EditText manufacturerEditText;
    EditText modelEditText;
    EditText androidVersionEditText;
    EditText websiteEditText;

    Button cancelButton;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        setEditTextsReferences();
        setButtons();
    }

    void setEditTextsReferences() {
        manufacturerEditText = findViewById(R.id.editTextManufacturer);
        modelEditText = findViewById(R.id.editTextModel);
        androidVersionEditText = findViewById(R.id.editTextAndroid);
        websiteEditText = findViewById(R.id.editTextWebsite);
    }

    void setButtons() {
        // return to the main activity
        cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener((View v) -> {

            Intent intent = new Intent();
            setResult(RESULT_CANCELED, intent);
            finish();
        });

        // pass entered data to the main activity
        saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener((View v) -> {

            Intent intent = new Intent();
            intent.putExtra(MainActivity.PHONE_MANUFACTURER_INPUT, manufacturerEditText.getText().toString());
            intent.putExtra(MainActivity.PHONE_MODEL_INPUT, modelEditText.getText().toString());
            intent.putExtra(MainActivity.PHONE_ANDROID_VERSION_INPUT, androidVersionEditText.getText().toString());
            intent.putExtra(MainActivity.PHONE_WEBSITE_INPUT, websiteEditText.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}