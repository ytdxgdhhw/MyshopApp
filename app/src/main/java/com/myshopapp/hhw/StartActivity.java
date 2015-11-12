package com.myshopapp.hhw;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.myshopapp.R;
import com.myshopapp.lxc.*;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengRegistrar;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
//        PushAgent mPushAgent = PushAgent.getInstance(this);
//        mPushAgent.enable();
//        PushAgent.getInstance(this).onAppStart();
//        String device_token = UmengRegistrar.getRegistrationId(this);
//        Toast.makeText(this,device_token,Toast.LENGTH_LONG).show();
        initView();
    }

    private void initView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(StartActivity.this, com.myshopapp.lxc.MainActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);
    }
    private void initView2() {

    }




}
