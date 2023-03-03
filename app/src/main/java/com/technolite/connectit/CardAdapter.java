package com.technolite.connectit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.technolite.connectit.module.demo;

import java.util.ArrayList;

public class CardAdapter extends ArrayAdapter<demo> {


    public CardAdapter(@NonNull Context context, ArrayList<demo> demoArrayList) {
        super(context, 0, demoArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.card_item, parent, false);
        }

        demo demo = getItem(position);
        TextView courseTV = listitemView.findViewById(R.id.idTVCourse);
        ImageView courseIV = listitemView.findViewById(R.id.idIVcourse);

        courseTV.setText(demo.getCard_name());
        courseIV.setImageResource(demo.getImgid());
        return listitemView;
    }

}
