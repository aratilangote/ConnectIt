package com.technolite.connectit;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.io.File;
import java.util.ArrayList;

public class DownloadsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
//    ArrayList<Image> arrayList = new ArrayList<>();

    ArrayList<Image> arrayList = new ArrayList<>();

    private final ActivityResultLauncher<String> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(),
            result -> {
                if (result) {
                    getImages();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloads);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(DownloadsActivity.this));
        recyclerView.setHasFixedSize(true);
        getImages();
    }


    @Override
    protected void onResume() {
        super.onResume();
        getImages();
    }

    private void getImages(){
        arrayList.clear();
        String filePath = "/storage/emulated/0/Download/eazypost";
        File file = new File(filePath);
        File[] files = file.listFiles();
        if (files != null) {
            for (File file1 : files) {
                if (file1.getPath().endsWith(".png") || file1.getPath().endsWith(".jpg") || file1.getPath().endsWith(".jepg")||file1.getPath().endsWith(".mp4")) {
                    arrayList.add(new Image(file1.getName(), file1.getPath(), file1.length()));

                }
            }
        }
        ImageAdapter adapter = new ImageAdapter(DownloadsActivity.this, arrayList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((view, path) -> startActivity(new Intent(DownloadsActivity.this, DownloadedImageViewerActivity.class).putExtra("image", path)));
    }
}