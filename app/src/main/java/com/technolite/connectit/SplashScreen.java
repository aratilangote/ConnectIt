package com.technolite.connectit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        Thread background = new Thread(){
            public void run(){
                try{
                    sleep(4*1000);
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    finish();
                }
                catch (Exception e){}
            }
        };
        background.start();

    }
}