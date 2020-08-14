package com.example.three.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.three.R;
import com.example.three.bean.DataDetailedean;

import java.util.ArrayList;

/**
 * @date：2020/8/12
 * @describe：
 * @author：FanYaJun
 */
public class RlvDataAdapter extends RecyclerView.Adapter<RlvDataAdapter.ViewHodelr> {
    private Context context;
    private ArrayList<DataDetailedean> list;

    public RlvDataAdapter(Context context, ArrayList<DataDetailedean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHodelr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.rlv_dataitem, parent, false);
        return new ViewHodelr(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodelr holder, int position) {
        DataDetailedean dataDetailedean = list.get(position);
        Glide.with(context).load(dataDetailedean.getGoodsDefaultIcon()).into(holder.iv_data_img);
        holder.tv_xinghao.setText(dataDetailedean.getGoodsDesc());
        holder.tv_manary.setText("¥"+dataDetailedean.getGoodsDefaultPrice()+".00");
        holder.tv_xiaoliang.setText("销量:"+dataDetailedean.getGoodsSalesCount()+"件");
        holder.tv_kucun.setText("库存"+dataDetailedean.getGoodsStockCount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ondateItemListener.onItemListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    private ondateItemListener ondateItemListener;

    public void setOndateItemListener(RlvDataAdapter.ondateItemListener ondateItemListener) {
        this.ondateItemListener = ondateItemListener;
    }

    public interface ondateItemListener{
        void onItemListener(int positon);
    }
    public class ViewHodelr extends RecyclerView.ViewHolder {
        public ImageView iv_data_img;
        public TextView tv_xinghao;
        public TextView tv_manary;
        public TextView tv_xiaoliang;
        public TextView tv_kucun;
        public ViewHodelr(@NonNull View itemView) {
            super(itemView);
            this.iv_data_img = (ImageView) itemView.findViewById(R.id.iv_data_img);
            this.tv_xinghao = (TextView) itemView.findViewById(R.id.tv_xinghao);
            this.tv_manary = (TextView) itemView.findViewById(R.id.tv_manary);
            this.tv_xiaoliang = (TextView) itemView.findViewById(R.id.tv_xiaoliang);
            this.tv_kucun = (TextView) itemView.findViewById(R.id.tv_kucun);
        }
    }
}
