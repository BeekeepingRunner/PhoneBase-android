package com.example.phonebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        setCancelButton();
        setSaveButton();
    }

    void setEditTextsReferences() {
        manufacturerEditText = findViewById(R.id.editTextManufacturer);
        modelEditText = findViewById(R.id.editTextModel);
        androidVersionEditText = findViewById(R.id.editTextAndroid);
        websiteEditText = findViewById(R.id.editTextWebsite);
    }

    // Cancel button returns to the main activity
    void setCancelButton() {
        cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener((View v) -> {

            Intent intent = new Intent();
            setResult(RESULT_CANCELED, intent);
            finish();
        });
    }

    // After clicking save button, entered data is passed to the main activity
    void setSaveButton() {
        saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener((View v) -> {

            if (isInputComplete()) {
                Intent intent = new Intent();
                intent.putExtra(MainActivity.PHONE_MANUFACTURER_INPUT, manufacturerEditText.getText().toString());
                intent.putExtra(MainActivity.PHONE_MODEL_INPUT, modelEditText.getText().toString());
                intent.putExtra(MainActivity.PHONE_ANDROID_VERSION_INPUT, androidVersionEditText.getText().toString());
                intent.putExtra(MainActivity.PHONE_WEBSITE_INPUT, websiteEditText.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    boolean isInputComplete() {

        String manufacturer = manufacturerEditText.getText().toString().trim();
        String model = modelEditText.getText().toString().trim();
        String androidVersion = androidVersionEditText.getText().toString().trim();
        String website = websiteEditText.getText().toString().trim();

        if (TextUtils.isEmpty(manufacturer)) {
            manufacturerEditText.setError("Manufacturer name cannot be empty");
            return false;
        }
        if (TextUtils.isEmpty(model)) {
            modelEditText.setError("Model name cannot be empty");
            return false;
        }
        if (TextUtils.isEmpty(androidVersion)) {
            androidVersionEditText.setError("Android version cannot be empty");
            return false;
        }
        if (TextUtils.isEmpty(website)) {
            websiteEditText.setError("Website field cannot be empty");
            return false;
        }

        return true;
    }
}