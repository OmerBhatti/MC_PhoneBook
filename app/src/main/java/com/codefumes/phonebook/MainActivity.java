package com.codefumes.phonebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Contact> contacts = new ArrayList<>();
    private RecyclerView recycleView;
    private RecyclerView.LayoutManager layoutManager;
    private ContactsAdapter adapter;
    DBHelper _DBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycleView = findViewById(R.id.contactsList);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recycleView.setLayoutManager(layoutManager);
        _DBHelper = new DBHelper(MainActivity.this);
        contacts = _DBHelper.getAllContacts();

        adapter = new ContactsAdapter(MainActivity.this,contacts);
        recycleView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        contacts = _DBHelper.getAllContacts();
        adapter = new ContactsAdapter(MainActivity.this,contacts);
        recycleView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void AddContact(View view){
        Intent intent = new Intent(MainActivity.this,AddContact.class);
        startActivity(intent);
    }

}