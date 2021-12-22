package com.codefumes.phonebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Contact> contacts = new ArrayList<>();
    private RecyclerView recycleView;
    private RecyclerView.LayoutManager layoutManager;
    private ContactsAdapter adapter;
    DBHelper _DBHelper;

    //side drawer
    DrawerLayout myDrawer;
    ActionBarDrawerToggle NavigationBar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //side drawer
        myDrawer = findViewById(R.id.drawer);
        NavigationBar = new ActionBarDrawerToggle(this,myDrawer,R.string.OPEN,R.string.CLOSE);
        myDrawer.addDrawerListener(NavigationBar);
        NavigationBar.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.call:
                    Intent call_intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"));
                    startActivity(call_intent);
                    return true;
                case R.id.repo:
                    Intent repo_intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/OmerBhatti/MC_PhoneBook/commits/master"));
                    startActivity(repo_intent);
                    return true;
                case R.id.exit:
                    finishAffinity();
                    System.exit(0);
                    return true;
                case R.id.add:
                    Intent addContact = new Intent(MainActivity.this,AddContact.class);
                    startActivity(addContact);
                    return true;
                default:
                    return true;
              }
        });

        //app start
        recycleView = findViewById(R.id.contactsList);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recycleView.setLayoutManager(layoutManager);
        _DBHelper = new DBHelper(MainActivity.this);
        contacts = _DBHelper.getAllContacts();

        adapter = new ContactsAdapter(MainActivity.this,contacts);
        recycleView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //side drawer
        if(NavigationBar.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
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

    public static void Relaunch(Activity context){
        context.finish();
        context.overridePendingTransition(0, 0);
        context.startActivity(context.getIntent());
        context.overridePendingTransition(0, 0);
    }

}