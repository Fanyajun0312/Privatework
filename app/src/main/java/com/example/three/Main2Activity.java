package com.example.three;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import jy.com.libumengsharelogin.LoginActivity;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private Button ww;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();


    }

    private void initView() {
        ww = (Button) findViewById(R.id.ww);

        ww.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ww:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}
