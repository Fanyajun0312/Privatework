package com.example.three.fenleiFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.httplibary.clicent.HttpClient;
import com.example.httplibary.utils.JsonUtils;
import com.example.three.R;
import com.example.three.app.HttpCallBack;
import com.example.three.bean.DataDetailedean;
import com.google.gson.JsonElement;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class DatesTwoFragment extends Fragment {
    private View inflate;
    private ImageView iv_dates_img;
    private ImageView iv_dates_two;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_dates__two, container, false);
        initView();
        return inflate;
    }

    private void initView() {
        iv_dates_img = inflate.findViewById(R.id.iv_dates_img);
        iv_dates_two = inflate.findViewById(R.id.iv_dates_two);

        int posiId = getActivity().getIntent().getExtras().getInt("posiId");//传过来的id；
        new HttpClient.Builder()
                .setApiUrl("kotlin/goods/getGoodsList")
                .setJsonbody("{\"categoryId\":" + posiId + ",\"pageNo\":1}", true)
                .post()
                .build()
                .requset(new HttpCallBack<List<DataDetailedean>>() {

                    private String goodsDetailTwo;
                    private String goodsDetailOne;

                    @Override
                    public void onError(String err, int onError) {
                        Log.i("TAG", "onError: " + err + onError);
                    }

                    @Override
                    public void cancle() {

                    }
                    @Override
                    protected void onSuccess(List<DataDetailedean> pare) {

                        for(int i=0;i<pare.size()  ;i++){
                            goodsDetailOne = pare.get(i).getGoodsDetailOne();
                            goodsDetailTwo = pare.get(i).getGoodsDetailTwo();

                        }

                        Glide.with(getActivity()).load(goodsDetailOne).into(iv_dates_img);
                        Glide.with(getActivity()).load(goodsDetailTwo).into(iv_dates_two);


                    }

                    @Override
                    public List<DataDetailedean> convert(JsonElement result) {
                        List<DataDetailedean> dataDetailedeans = JsonUtils.jsonToClassList(result, DataDetailedean.class);
                        return dataDetailedeans;

                    }
                });
    }
}
