package com.example.three.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.httplibary.clicent.HttpClient;
import com.example.httplibary.utils.JsonUtils;
import com.example.three.R;
import com.example.three.adapter.RlvDataAdapter;
import com.example.three.app.HttpCallBack;
import com.example.three.bean.DataDetailedean;
import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.JsonElement;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

public class DetailedActivity extends AppCompatActivity implements OnRefreshLoadmoreListener {

    private RecyclerView rlv_detailed;
    private ArrayList<DataDetailedean> datalist;
    private RlvDataAdapter adapter;
    private Toolbar toolbar;
    private TextView tv_toolbar_title;
    private AppBarLayout appBarLayout;
    private TextView textView2;
    private SmartRefreshLayout sm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        initView();
        initData();
        initToolbar();
        initListener();
    }

    private void initListener() {
        adapter.setOndateItemListener(new RlvDataAdapter.ondateItemListener() {
            @Override
            public void onItemListener(int positon) {
                DataDetailedean dataDetailedean = datalist.get(positon);
                int categoryId = dataDetailedean.getCategoryId();
                Intent intent = new Intent(DetailedActivity.this, DetailsActivity.class);
                intent.putExtra("posiId",categoryId);
                startActivity(intent);
            }
        });
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

    private int page = 1;

    private void initData() {

        int id = getIntent().getExtras().getInt("id");
        new HttpClient.Builder()
                .setApiUrl("kotlin/goods/getGoodsList")
                .setJsonbody("{\"categoryId\":"+id+",\"pageNo\":"+page+"}", true)
                .post()
                .build()
                .requset(new HttpCallBack<List<DataDetailedean>>() {
                    @Override
                    public void onError(String err, int onError) {
                        Log.i("TAG", "onError: +" + err + onError);
                    }

                    @Override
                    public void cancle() {
                    }

                    @Override
                    protected void onSuccess(List<DataDetailedean> pare) {
                        datalist.addAll(pare);
                        adapter.notifyDataSetChanged();
                        sm.finishRefresh();
                        sm.finishLoadmore();
                    }

                    @Override
                    public List<DataDetailedean> convert(JsonElement result) {
                        List<DataDetailedean> dataDetailedeans = JsonUtils.jsonToClassList(result, DataDetailedean.class);
                        return dataDetailedeans;
                    }
                });
    }

    private void initView() {
        rlv_detailed = (RecyclerView) findViewById(R.id.rlv_detailed);
        tv_toolbar_title = (TextView) findViewById(R.id.tv_toolbar_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);

        //网格布局
        rlv_detailed.setLayoutManager(new GridLayoutManager(this, 2));
        datalist = new ArrayList<>();
        adapter = new RlvDataAdapter(this, datalist);
        rlv_detailed.setAdapter(adapter);


        sm = (SmartRefreshLayout) findViewById(R.id.sm);
        sm.setOnRefreshLoadmoreListener(this);
    }


    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
            page++;
            initData();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        if(datalist!=null&&datalist.size()>0){
            datalist.clear();
            initData();
        }
    }
}
