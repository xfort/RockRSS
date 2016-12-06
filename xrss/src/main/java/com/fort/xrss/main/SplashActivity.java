package com.fort.xrss.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by zhxcxy on 16-11-11.
 */

public class SplashActivity extends Activity {
    final String TAG = "SpalshAct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppInstance.getInstance().getThreadPool();
        goMainAct();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void goMainAct() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
