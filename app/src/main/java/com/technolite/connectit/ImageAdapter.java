package com.technolite.connectit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    ArrayList<Image> arrayList;
    Context context;
    OnItemClickListener onItemClickListener;

    public ImageAdapter(Context context, ArrayList<Image> arrayList) {
        this.arrayList = arrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Image image = arrayList.get(position);
        final Uri videoUri = Uri.parse(image.getPath());
        holder.title.setText(arrayList.get(position).getTitle());
        //  holder.size.setText(getSize(arrayList.get(position).getSize()));


        if (image.getPath().endsWith(".mp4")) {
            // If the current item is a video, show the VideoView and hide the ImageView
            holder.imageView.setVisibility(View.GONE);
            holder.videoView.setVisibility(View.VISIBLE);

            Uri videouri = Uri.parse(image.getPath());
            holder.videoView.setVideoURI(videouri);
            holder.videoView.setOnPreparedListener(mp -> {
                // Set the video to loop indefinitely
                mp.setLooping(true);
                // Start the video playback
                mp.start();
            });
        } else {
            // If the current item is an image, show the ImageView and hide the VideoView
            holder.imageView.setVisibility(View.VISIBLE);
            holder.videoView.setVisibility(View.GONE);

            Glide.with(context).load(image.getPath()).placeholder(R.drawable.ic_baseline_broken_image_24).into(holder.imageView);
        }

        //Glide.with(context).load(arrayList.get(position).getPath()).placeholder(R.drawable.ic_baseline_broken_image_24).into(holder.imageView);
        holder.itemView.setOnClickListener(v -> onItemClickListener.onClick(v, arrayList.get(position).getPath()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, videoUri);
                    holder.itemView.getContext().startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(context.getApplicationContext(), "text"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("Tejas",e.getMessage());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static String getSize(long size) {


        if (size <= 0) {
            return "0";
        }

        double d = (double) size;
        int log10 = (int) (Math.log10(d) / Math.log10(1024.0d));
        StringBuilder stringBuilder = new StringBuilder();
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.#");
        double power = Math.pow(1024.0d, log10);
        stringBuilder.append(decimalFormat.format(d/power));
        stringBuilder.append(" ");
        stringBuilder.append(new String[] {"B", "KB", "MB", "GB", "TB"}[log10]);
        return stringBuilder.toString();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title, size;
        VideoView videoView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.list_item_image);
            title = itemView.findViewById(R.id.list_item_title);
            videoView = itemView.findViewById(R.id.list_item_video);

            // size = itemView.findViewById(R.id.list_item_size);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {



        void onClick(View view, String path);


    }
}
