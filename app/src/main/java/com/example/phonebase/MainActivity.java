package com.example.phonebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PhoneViewModel phoneViewModel;
    private PhoneListAdapter phoneListAdapter;
    private RecyclerView recyclerView;

    private FloatingActionButton insertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set RecyclerView, its adapter and ViewModel for display of phone collection

        recyclerView = findViewById(R.id.recyclerView);
        phoneListAdapter = new PhoneListAdapter(this);

        recyclerView.setAdapter(phoneListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        phoneViewModel = new ViewModelProvider(this).get(PhoneViewModel.class);

        phoneViewModel.getAllPhones().observe(this,
                phones -> phoneListAdapter.setPhoneList(phones));

        setInsertionButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.clearDataOption) {

            phoneViewModel.deleteAll();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setInsertionButton() {
        insertButton = findViewById(R.id.fabMain);
        insertButton.setOnClickListener((View v) -> {
            Intent intent = new Intent(MainActivity.this, InputActivity.class);
            startActivity(intent);
        });
    }
}