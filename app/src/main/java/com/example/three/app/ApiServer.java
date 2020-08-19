package com.example.three.app;

import com.example.three.bean.LoginBean;
import com.google.gson.JsonElement;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 项目名：Shopping
 * 包名：  com.example.httplibrary.server
 * 文件名：ApiServer
 * 创建者：liangxq
 * 创建时间：2020/8/1  10:23
 * 描述：TODO
 */
public interface ApiServer {
    String uri = "http://169.254.189.205:8080/";

    @POST("kotlin/userCenter/register")
    @FormUrlEncoded
    Observable<LoginBean> getpost(@FieldMap Map<String, Object> params, @HeaderMap Map<String,Object> type);

}
