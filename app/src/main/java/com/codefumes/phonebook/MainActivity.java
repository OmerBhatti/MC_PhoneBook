package com.codefumes.phonebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Contact> contacts = new ArrayList<>();
    private RecyclerView recycleView;
    private RecyclerView.LayoutManager layoutManager;
    private ContactsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycleView = findViewById(R.id.contactsList);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recycleView.setLayoutManager(layoutManager);

        contacts.add(new Contact("M Omer Sharif","+923364135517","omerbhatti34@gmail.com"));
        contacts.add(new Contact("Aazam Jutt","+923123456788","bcsf18m036@pucit.edu.pk"));

        adapter = new ContactsAdapter(MainActivity.this,contacts);
        recycleView.setAdapter(adapter);
    }
}