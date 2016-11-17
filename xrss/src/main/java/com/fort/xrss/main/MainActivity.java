package com.fort.xrss.main;

import android.content.Intent;
import android.os.Bundle;

import com.fort.xrss.R;
import com.fort.xrss.base.BaseActivity;
import com.fort.xrss.web.WebActivity;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        for(int index=0;index<10;index++){
            goWeb();
//        }
    }

    private void goWeb() {
        Intent intent = new Intent(this, WebActivity.class);
        startActivity(intent);
    }
}
