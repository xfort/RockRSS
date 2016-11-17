package com.fort.xrss.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.fort.xrock.android.weak.WeakHandler;

/**
 * Created by zhxcxy on 16-11-11.
 */

public class SplashActivity extends Activity {
    final String TAG = "SpalshAct";
    WeakHandler<SplashActivity> weakHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                goMainAct();
            }
        }, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        weakHandler.destroy();
    }

    private void goMainAct() {
        weakHandler = new WeakHandler<>();
        weakHandler.setWeak(this);
        weakHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "goMain");
            }
        }, 1000 * 60 * 1000);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); 
    }


}
