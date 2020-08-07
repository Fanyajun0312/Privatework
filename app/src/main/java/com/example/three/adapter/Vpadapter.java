package com.example.three.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.three.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @date：2020/8/7
 * @describe：
 * @author：FanYaJun
 */
public class Vpadapter extends PagerAdapter {
    private Context context;
    private ArrayList<String> imglist;

    public Vpadapter(Context context, ArrayList<String> imglist) {
        this.context = context;
        this.imglist = imglist;
    }

    @Override
    public int getCount() {
        return imglist.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.iv_vp, container, false);
        String s = imglist.get(position);
        ImageView imageView=inflate.findViewById(R.id.iv_img_vp);
        Glide.with(context).load(s).into(imageView);
        container.addView(inflate);
        return inflate;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
       return view==object;
    }


}
