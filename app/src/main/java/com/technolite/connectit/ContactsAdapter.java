package com.technolite.connectit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactsAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Contact> mContacts;

    ContactsAdapter(Context context, ArrayList<Contact> contacts) {
        mContext = context;
        mContacts = contacts;
    }

    @Override
    public int getCount() {
        return mContacts.size();
    }

    @Override
    public Object getItem(int i) {
        return mContacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item, viewGroup, false);
        }

        TextView nameTextView = view.findViewById(R.id.name_text_view);
        TextView numberTextView = view.findViewById(R.id.number_text_view);

        Contact contact = mContacts.get(i);
        nameTextView.setText(contact.getName());
        numberTextView.setText(contact.getNumber());

        return view;
    }

}
