package com.example.three.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.httplibary.utils.JsonUtils;
import com.example.three.R;
import com.example.three.bean.Responses;
import com.example.three.bean.logbena;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ReguserActivity extends AppCompatActivity {

    @BindView(R.id.et_phonenum_registe)
    EditText etPhonenumRegiste;//手机号
    @BindView(R.id.tv_get_verify)
    TextView tvGetVerify;
    @BindView(R.id.et_verify_code)
    EditText et_verify_code;//验证码
    @BindView(R.id.et_pass_registe)
    EditText etPassRegiste;//密码
    @BindView(R.id.et_pass_registe_two)
    EditText etPassRegisteTwo;
    @BindView(R.id.reg_btn)
    Button regBtn;
    @BindView(R.id.ll_login_info)
    LinearLayout llLoginInfo;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reguser);
        ButterKnife.bind(this);
        initToolbar();
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etPhonenumRegiste.getText().toString();
                String verify = et_verify_code.getText().toString();
                String pass = etPassRegiste.getText().toString();
                String passtw = etPassRegisteTwo.getText().toString();
                if (user.length() == 11 && !user.isEmpty()) {
                    if (pass.equals(passtw)) {
                        initUpLoad(user, pass, verify);
                    } else {
                        Toast.makeText(ReguserActivity.this, "密码输入不一致", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(ReguserActivity.this, "手机号输入错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initUpLoad(String user, String pass, String verify) {

        logbena registerParmesan = new logbena();
        registerParmesan.setMobile(user);
        registerParmesan.setPwd(pass);
        registerParmesan.setVerifyCode(verify);
        String toJson = JsonUtils.classToJson(registerParmesan);
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , toJson);
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://169.254.189.205:8080/kotlin/userCenter/register")
                .post(requestBody)
                .build();
        okHttpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.i("TAG", "onFailure: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        final Responses bean = JsonUtils.jsonToClass1(string, Responses.class);
                        int status = bean.getStatus();
                        if (status == 0) {
                            Log.i("TAG", "onResponse: " + "成功");
                            Intent intent = new Intent(ReguserActivity.this,UserloginActivity.class);
                            intent.putExtra("user", user);
                            startActivity(intent);
                            finish();
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(ReguserActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }
                });
    }
}
