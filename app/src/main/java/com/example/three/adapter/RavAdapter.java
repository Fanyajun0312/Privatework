package com.example.three.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
public class RavAdapter extends RecyclerView.Adapter<RavAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> list;

    public RavAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.rlv_frag_hemo, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position)).into(holder.ivPhon);
        holder.moenryTwo.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//删除线
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mGoodsIconIv)
        ImageView ivPhon;
        @BindView(R.id.moenry)
        TextView moenry;
        @BindView(R.id.moenry_two)
        TextView moenryTwo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
