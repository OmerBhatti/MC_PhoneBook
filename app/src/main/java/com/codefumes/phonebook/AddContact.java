package com.codefumes.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddContact extends AppCompatActivity {
    EditText nameTxt;
    EditText emailTxt;
    EditText phoneNumberTxt;
    DBHelper _DBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        _DBHelper = new DBHelper(AddContact.this);

        nameTxt = findViewById(R.id.nameTxt);
        emailTxt = findViewById(R.id.emailTxt);
        phoneNumberTxt = findViewById(R.id.phoneTxt);
    }

    public void save(View view){
        Contact contact = new Contact(nameTxt.getText().toString(),phoneNumberTxt.getText().toString(),emailTxt.getText().toString());
        _DBHelper.addContact(contact);
        Toast.makeText(this,"Contact Saved!",Toast.LENGTH_SHORT).show();
        finish();
    }

    public void cancel(View view){
        finish();
    }

}