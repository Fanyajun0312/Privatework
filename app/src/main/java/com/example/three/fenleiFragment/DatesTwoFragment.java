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
import com.example.three.ui.DetailsActivity;
import com.google.gson.JsonElement;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class DatesTwoFragment extends Fragment {
    private View inflate;
    private ImageView iv_dates_img;
    private ImageView iv_dates_two;
    private DataDetailedean data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_dates__two, container, false);
        DetailsActivity activity= (DetailsActivity) getActivity();
        data = activity.getData();
                initView();
        return inflate;
    }

    private void initView() {
        iv_dates_img = inflate.findViewById(R.id.iv_dates_img);
        iv_dates_two = inflate.findViewById(R.id.iv_dates_two);
        Glide.with(getActivity()).load(data.getGoodsDetailOne()).into(iv_dates_img);
//        Glide.with(getActivity()).load(data.getGoodsDetailTwo()).into(iv_dates_two);
    }
}
