package com.example.phonebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PhoneViewModel phoneViewModel;
    private PhoneListAdapter phoneListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        phoneListAdapter = new PhoneListAdapter(this);

        recyclerView.setAdapter(phoneListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        phoneViewModel = new ViewModelProvider(this).get(PhoneViewModel.class);

        phoneViewModel.getAllPhones().observe(this, new Observer<List<Phone>>() {
            @Override
            public void onChanged(List<Phone> phones) {
                phoneListAdapter.setPhoneList(phones);
            }
        });
    }
}