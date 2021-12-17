package com.codefumes.phonebook;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URI;
import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    ArrayList<Contact> contacts;
    Activity context;
    DBHelper _DBHelper;
    public ContactsAdapter(Activity context,ArrayList<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
        _DBHelper = new DBHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_contact,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.contact = contact;
        holder.nameTxt.setText(contact.name);
        holder.phoneTxt.setText(contact.phoneNumber);
        holder.emailTxt.setText(contact.email);
        holder.callButton.setOnClickListener(view -> {
            Intent dial_intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contact.phoneNumber));
            context.startActivity(dial_intent);
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameTxt;
        TextView phoneTxt;
        TextView emailTxt;
        Contact contact;
        Button callButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.name);
            phoneTxt = itemView.findViewById(R.id.phoneNumber);
            emailTxt = itemView.findViewById(R.id.email);
            callButton = itemView.findViewById(R.id.call);
            itemView.setOnClickListener(view->{
                int id = contact.getId();
                Toast.makeText(context,"Edit",Toast.LENGTH_SHORT).show();
            });
            itemView.setOnLongClickListener(view -> {
                new AlertDialog.Builder(context)
                        .setTitle("Delete!")
                        .setMessage("Do you really want to Delete?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                int id = contact.getId();
                                _DBHelper.removeContact(id);
                                Contact.Relaunch(context);
                                Toast.makeText(context, contact.name+" Deleted", Toast.LENGTH_SHORT).show();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
                return false;
            });
        }
    }

}
