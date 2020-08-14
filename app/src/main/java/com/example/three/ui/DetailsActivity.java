package com.example.three.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.three.R;
import com.example.three.adapter.VpDatasAdapter;
import com.example.three.fenleiFragment.DatesOneFragment;
import com.example.three.fenleiFragment.DatesTwoFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    private TabLayout dates_tab;
    private Toolbar toolbar;
    private ViewPager dates_vp;
    private TextView mShareTv;
    private TextView mEnterCartTv;
    private Button mAddCartBtn;
    private LinearLayout ll_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initView();
//        int posiId = getIntent().getExtras().getInt("posiId");
        initToolbar();
        initBnner();
    }

    private void initBnner() {

    }

    private void initToolbar() {
        //设置支持toolbar
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // 设置主标题或者子标题是否应该被显示，隐藏掉toolbar自带的主标题和子标题
        actionBar.setDisplayShowTitleEnabled(false);
        //返回按钮点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        dates_tab = (TabLayout) findViewById(R.id.dates_tab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        dates_vp = (ViewPager) findViewById(R.id.dates_vp);
        mShareTv = (TextView) findViewById(R.id.mShareTv);
        mEnterCartTv = (TextView) findViewById(R.id.mEnterCartTv);
        mAddCartBtn = (Button) findViewById(R.id.mAddCartBtn);
        ll_bottom = (LinearLayout) findViewById(R.id.ll_bottom);


        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new DatesOneFragment());
        fragments.add(new DatesTwoFragment());
        ArrayList<String> titles = new ArrayList<>();
        titles.add("商品");
        titles.add("详情");
        VpDatasAdapter adapter = new VpDatasAdapter(getSupportFragmentManager(), fragments, titles);
        dates_vp.setAdapter(adapter);
        dates_tab.setupWithViewPager(dates_vp);


    }


}
