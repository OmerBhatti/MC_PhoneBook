package com.codefumes.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class AddContact extends AppCompatActivity {
    EditText nameTxt;
    EditText emailTxt;
    EditText phoneNumberTxt;
    DBHelper _DBHelper;

    //edit contact Case
    Intent intent;
    boolean EditMode = false;
    Contact contactToEdit;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        _DBHelper = new DBHelper(AddContact.this);

        nameTxt = findViewById(R.id.nameTxt);
        emailTxt = findViewById(R.id.emailTxt);
        phoneNumberTxt = findViewById(R.id.phoneTxt);

        intent = getIntent();
        id = intent.getIntExtra("ID",-1);
        if(id == -1){
            //add Contact Mode
            EditMode = false;
        }
        else{
            //Edit Contact Mode
            EditMode = true;
            contactToEdit = _DBHelper.getContact(id);
            TextView mode = findViewById(R.id.mode);
            mode.setText("Edit Contact");

            nameTxt.setText(contactToEdit.getName());
            emailTxt.setText(contactToEdit.getEmail());
            phoneNumberTxt.setText(contactToEdit.getPhoneNumber());

            findViewById(R.id.saveBtn).setOnClickListener(view -> {
                Contact contact = new Contact(nameTxt.getText().toString(),phoneNumberTxt.getText().toString(),emailTxt.getText().toString());
                contact.setId(contactToEdit.id);
                _DBHelper.editContact(contact);
                Toast.makeText(this,"Contact Updated!",Toast.LENGTH_SHORT).show();
                finish();
            });
        }
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