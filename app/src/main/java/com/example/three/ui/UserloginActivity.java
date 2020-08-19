package com.example.three.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.httplibary.clicent.HttpClient;
import com.example.httplibary.utils.JsonUtils;
import com.example.mymvplibrary.base.BaseActivity;
import com.example.three.R;
import com.example.three.app.HttpCallBack;
import com.example.three.bean.RegBean;
import com.example.three.bean.RegBeanTwo;
import com.google.gson.JsonElement;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class UserloginActivity extends BaseActivity {

    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tool_bar_login)
    Toolbar toolBarLogin;
    @BindView(R.id.et_phonenum)
    EditText etPhonenum;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.ll_login_info)
    LinearLayout llLoginInfo;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    @BindView(R.id.tv_forgrt_pass)
    TextView tvForgrtPass;
    private String user;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_userlogin);
//        ButterKnife.bind(this);
//    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {
        initToolbar();
    }

    private void initToolbar() {
        //设置支持toolbar
        setSupportActionBar(toolBarLogin);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // 设置主标题或者子标题是否应该被显示，隐藏掉toolbar自带的主标题和子标题
        actionBar.setDisplayShowTitleEnabled(false);
        //返回按钮点击事件
        toolBarLogin.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        user = getIntent().getStringExtra("user");
        etPhonenum.setText(user);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDatapass();
            }
        });
    }

    private void initDatapass() {
        String pwd = etPwd.getText().toString();
        RegBeanTwo regBean = new RegBeanTwo();
        regBean.setUserName(user);
        regBean.setUserMobile(pwd);
        regBean.setPushId("1111");
        String s = JsonUtils.classToJson(regBean);
        new HttpClient.Builder()
                .setJsonbody(s, true)
                .post()
                .build()
                .requset(new HttpCallBack<RegBeanTwo>() {
                    @Override
                    public void onError(String err, int onError) {
                    }
                    @Override
                    public void cancle() {
                    }
                    @Override
                    protected void onSuccess(RegBeanTwo pare) {
                        if (user.equals(pare.getUserName())) {
                            Toast.makeText(UserloginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(UserloginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public RegBeanTwo convert(JsonElement result) {
                        return JsonUtils.jsonToClass(result, RegBeanTwo.class);
                    }
                });
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_userlogin;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutoolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                startActivity(new Intent(this, ReguserActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
