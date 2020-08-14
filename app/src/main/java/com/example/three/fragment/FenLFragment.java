package com.example.three.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.httplibary.clicent.HttpClient;
import com.example.httplibary.utils.JsonUtils;
import com.example.three.R;
import com.example.three.adapter.FenLeAdapter;
import com.example.three.adapter.RlvFenListAdapter;
import com.example.three.app.HttpCallBack;
import com.example.three.bean.CategoryList;
import com.example.three.bean.CategoryTab;
import com.example.three.bean.Idbean;
import com.example.three.reqstbean.CategoryPrams;
import com.example.three.ui.DetailedActivity;
import com.google.gson.JsonElement;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FenLFragment extends Fragment {


    @BindView(R.id.tool_bar_category)
    Toolbar toolBarCategory;
    @BindView(R.id.rv_category)
    RecyclerView rvCategory;
    @BindView(R.id.fl_fenl)
    RecyclerView flFen;
    @BindView(R.id.iv_jia)
    ImageView ivJia;
    @BindView(R.id.jia_vis)
    TextView jiaVis;
    @BindView(R.id.jia_tv)
    TextView jiaTv;
    private View inflate;
    private FenLeAdapter adapter;
    private ArrayList<CategoryTab> list;
    private ArrayList<CategoryList> categoryLists;
    private RlvFenListAdapter adapterlist;
    private String page = "1";
    private CategoryPrams categoryPrams;
    private CategoryPrams categoryPramstwo;
//    private MyListener listenerid;
//    public interface MyListener{
//        public void sendValue(int positionid);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_fen_l, container, false);
        ButterKnife.bind(this, inflate);
        categoryPramstwo = new CategoryPrams();
        categoryPramstwo.setParentId(1 + "");
        initView();
        initData();
        initFlLei();
        initDataLsit(categoryPramstwo);
        initListener();
        initadapterlistListener();
        return inflate;
    }

    private void initadapterlistListener() {
        adapterlist.setOnClickitem(new RlvFenListAdapter.onClickitem() {
            @Override
            public void onclicklistener(int position) {
                CategoryList categoryList = categoryLists.get(position);
                if(categoryList!=null&&categoryList!=null){
                    int id = categoryList.getId();
                    Intent intent = new Intent(getActivity(), DetailedActivity.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }
            }
        });
    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        listenerid= (MyListener) getActivity();
//    }

    private void initDataLsit(CategoryPrams categoryPrams) {
        Map<String, Object> map = new HashMap<>();
        map.put("Content-Type", "application/json");
        new HttpClient.Builder()
                .setApiUrl("kotlin/category/getCategory")
                .setJsonbody(JsonUtils.classToJson(categoryPrams), true)
                .setHeadres(map)
                .post()
                .build().requset(new HttpCallBack<List<CategoryList>>() {
            @Override
            public void onError(String err, int onError) {
                Log.i("TAG", "onError: " + err + onError);
            }

            @Override
            public void cancle() {

            }

            @Override
            protected void onSuccess(List<CategoryList> pare) {
                categoryLists.clear();
                categoryLists.addAll(pare);
                adapterlist.notifyDataSetChanged();
                Log.i("TAG", "111onSuccess: " + page);
            }

            @Override
            public List<CategoryList> convert(JsonElement result) {
                List<CategoryList> categoryLists = JsonUtils.jsonToClassList(result, CategoryList.class);
                return categoryLists;
            }
        });
    }

    private void initFlLei() {
        flFen.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        categoryLists = new ArrayList<>();
        adapterlist = new RlvFenListAdapter(getActivity(), categoryLists);
        flFen.setAdapter(adapterlist);
    }

    private void initListener() {
        adapter.setOnClickListener(new FenLeAdapter.onClickListener() {
            @Override
            public void itemItemClickListener(int cid) {
                if (cid == 0) {
                    ivJia.setVisibility(View.VISIBLE);
                    jiaTv.setVisibility(View.VISIBLE);
                    jiaVis.setVisibility(View.INVISIBLE);
                    categoryPramstwo.setParentId((cid + 1) + "");
                    initDataLsit(categoryPramstwo);
                    adapterlist.notifyDataSetChanged();

                } else if (cid == 1) {
                    ivJia.setVisibility(View.VISIBLE);
                    jiaTv.setVisibility(View.VISIBLE);
                    jiaVis.setVisibility(View.INVISIBLE);
                    categoryPramstwo.setParentId((cid + 1) + "");
                    initDataLsit(categoryPramstwo);
                    adapterlist.notifyDataSetChanged();
                } else {
                    ivJia.setVisibility(View.INVISIBLE);
                    jiaTv.setVisibility(View.INVISIBLE);
                    jiaVis.setVisibility(View.VISIBLE);
                    categoryLists.clear();
                    adapterlist.notifyDataSetChanged();
                }
                adapter.setmPosition(cid);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initData() {
        categoryPrams = new CategoryPrams();
        categoryPrams.setParentId("0");
        Map<String, Object> map = new HashMap<>();
        map.put("Content-Type", "application/json");
        new HttpClient.Builder()
                .setApiUrl("kotlin/category/getCategory")
                .setJsonbody(JsonUtils.classToJson(categoryPrams), true)
                .setHeadres(map)
                .post()
                .build().requset(new HttpCallBack<List<CategoryTab>>() {
            @Override
            public void onError(String err, int onError) {
                Log.i("TAG", "onError: " + err + onError);
            }

            @Override
            public void cancle() {

            }

            @Override
            protected void onSuccess(List<CategoryTab> pare) {
                list.addAll(pare);
                adapter.notifyDataSetChanged();
            }

            @Override
            public List<CategoryTab> convert(JsonElement result) {
                List<CategoryTab> categoryTabs = JsonUtils.jsonToClassList(result, CategoryTab.class);
                return categoryTabs;
            }
        });
    }

    private void initView() {
        rvCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        adapter = new FenLeAdapter(getActivity(), list);
        rvCategory.setAdapter(adapter);
        Log.i("TAG", "convert: " + list.toString());
    }

}
