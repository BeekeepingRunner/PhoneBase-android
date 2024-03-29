package com.example.phonebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.TreeMap;

// Allows to add new phone or edit an existing one. User can go to the specified phone website too.
public class InputActivity extends AppCompatActivity {

    EditText manufacturerEditText;
    EditText modelEditText;
    EditText androidVersionEditText;
    EditText websiteEditText;

    Button cancelButton;
    Button saveButton;
    Button websiteButton;

    long phoneId;
    public static final long DEFAULT_PHONE_ID = -1;   // default value in case when it isn't needed for phone edit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        phoneId = DEFAULT_PHONE_ID;

        setEditTextsReferences();
        setCancelButton();

        // User came here, because he wanted either to add or to edit the phone
        Intent intent = getIntent();
        if (intent.hasExtra(MainActivity.PHONE_MANUFACTURER_INPUT)) {
            restorePhoneData(intent);
            setActionButton(MainActivity.ActivityRequest.EDIT_PHONE);
        }
        else {
            setActionButton(MainActivity.ActivityRequest.ADD_PHONE);
        }

        setWebsiteButton();
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

    void restorePhoneData(Intent intent) {
        phoneId = intent.getLongExtra(MainActivity.PHONE_ID, DEFAULT_PHONE_ID);
        manufacturerEditText.setText(intent.getStringExtra(MainActivity.PHONE_MANUFACTURER_INPUT));
        modelEditText.setText(intent.getStringExtra(MainActivity.PHONE_MODEL_INPUT));
        androidVersionEditText.setText(intent.getStringExtra(MainActivity.PHONE_ANDROID_VERSION_INPUT));
        websiteEditText.setText(intent.getStringExtra(MainActivity.PHONE_WEBSITE_INPUT));
    }

    // After clicking save button, entered data is passed to the main activity
    void setActionButton(int actionType) {
        saveButton = findViewById(R.id.save_button);
        if (actionType == MainActivity.ActivityRequest.EDIT_PHONE) {
            saveButton.setText(R.string.edit_button);
        }

        saveButton.setOnClickListener((View v) -> {

            if (isInputComplete()) {
                Intent intent = new Intent();
                intent.putExtra(MainActivity.PHONE_ID, phoneId); // -1 if not needed to edit
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
            manufacturerEditText.setError(getString(R.string.manufacturer_not_empty));
            return false;
        }
        if (TextUtils.isEmpty(model)) {
            modelEditText.setError(getString(R.string.model_not_empty));
            return false;
        }
        if (TextUtils.isEmpty(androidVersion)) {
            androidVersionEditText.setError(getString(R.string.android_not_empty));
            return false;
        }
        if (TextUtils.isEmpty(website)) {
            websiteEditText.setError(getString(R.string.website_not_empty));
            return false;
        }

        return true;
    }

    void setWebsiteButton()
    {
        websiteButton = findViewById(R.id.website_button);

        websiteButton.setOnClickListener((View v) -> {

            String uri = websiteEditText.getText().toString();

            if (uri.startsWith("http://") || uri.startsWith("https://"))
            {
                Intent browserIntent = new Intent("android.intent.action.VIEW",
                        Uri.parse(websiteEditText.getText().toString()));
                startActivity(browserIntent);
            }
            else {
                Toast.makeText(this, "Website address is incorrect", Toast.LENGTH_LONG).show();
            }
        });
    }
}