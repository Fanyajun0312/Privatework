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
import com.example.three.bean.CategoryList;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @date：2020/8/11
 * @describe：
 * @author：FanYaJun
 */
public class RlvFenListAdapter extends RecyclerView.Adapter<RlvFenListAdapter.ViewHodelr> {
    private Context context;
    private ArrayList<CategoryList> lists;
    private onClickitem onClickitem;

    public void setOnClickitem(RlvFenListAdapter.onClickitem onClickitem) {
        this.onClickitem = onClickitem;
    }

    public RlvFenListAdapter(Context context, ArrayList<CategoryList> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public ViewHodelr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.fenlei_list, parent, false);
        return new ViewHodelr(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodelr holder, int position) {
        CategoryList categoryList = lists.get(position);
        Glide.with(context).load(categoryList.getCategoryIcon()).into(holder.ivDiannao);
        holder.tvDian.setText(categoryList.getCategoryName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickitem.onclicklistener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ViewHodelr extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_diannao)
        ImageView ivDiannao;
        @BindView(R.id.tv_dian)
        TextView tvDian;

        public ViewHodelr(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public interface onClickitem{
        void onclicklistener(int position);
    }
}
