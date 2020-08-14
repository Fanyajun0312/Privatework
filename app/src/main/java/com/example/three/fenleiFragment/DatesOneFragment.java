package com.example.three.fenleiFragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.httplibary.clicent.HttpClient;
import com.example.httplibary.utils.JsonUtils;
import com.example.three.R;
import com.example.three.app.HttpCallBack;
import com.example.three.bean.DataDetailedean;
import com.google.gson.JsonElement;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatesOneFragment extends Fragment {

    private View inflate;
    private Banner mGoodsDetailBanner;
    private TextView mGoodsDescTv;
    private TextView mGoodsPriceTv;
    private TextView mSkuLabelTv;
    private TextView mSkuSelectedTv;
    private ImageView mMoreIv;
    private RelativeLayout mSkuView;

    public DatesOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_dates_one, container, false);

        initView();
        initbanner();
        return inflate;
    }

    private void initbanner() {
        int posiId = getActivity().getIntent().getExtras().getInt("posiId");//传过来的id；
        new HttpClient.Builder()
                .setApiUrl("kotlin/goods/getGoodsList")
                .setJsonbody("{\"categoryId\":" + posiId + ",\"pageNo\":1}", true)
                .post()
                .build()
                .requset(new HttpCallBack<List<DataDetailedean>>() {
                    @Override
                    public void onError(String err, int onError) {
                        Log.i("TAG", "onError: " + err + onError);
                    }
                    @Override
                    public void cancle() {

                    }
                    @Override
                    protected void onSuccess(List<DataDetailedean> pare) {


                        ArrayList<String> img = new ArrayList<>();
                        for (int i = 0; i < pare.size(); i++) {
                            img.add(pare.get(i).getGoodsDefaultIcon());
                        }
                        mGoodsDetailBanner.setImages(img).setBannerStyle(BannerConfig.RIGHT)
                                .setImageLoader(new ImageLoader() {
                                    @Override
                                    public void displayImage(Context context, Object path, ImageView imageView) {
                                        Glide.with(context).load(path).into(imageView);
                                    }
                                }).start();
                    }

                    @Override
                    public List<DataDetailedean> convert(JsonElement result) {
                        List<DataDetailedean> dataDetailedeans = JsonUtils.jsonToClassList(result, DataDetailedean.class);
                        return dataDetailedeans;
                    }
                });
    }

    private void initView() {
        mGoodsDetailBanner = inflate.findViewById(R.id.mGoodsDetailBanner);//banner
        mGoodsDescTv = inflate.findViewById(R.id.mGoodsDescTv);//desc详细信息
        mGoodsPriceTv = inflate.findViewById(R.id.mGoodsPriceTv);//价格
        mSkuLabelTv = inflate.findViewById(R.id.mSkuLabelTv);//已选
        mSkuSelectedTv = inflate.findViewById(R.id.mSkuSelectedTv);//选择条件
        mMoreIv = inflate.findViewById(R.id.mMoreIv);//...三个点
        mSkuView = inflate.findViewById(R.id.mSkuView);
    }


}
