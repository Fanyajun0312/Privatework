package com.example.three.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.three.R;
import com.example.three.bean.CategoryTab;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @date：2020/8/11
 * @describe：
 * @author：FanYaJun
 */
public class FenLeAdapter extends RecyclerView.Adapter<FenLeAdapter.ViewHodelr> {
    private Context context;
    private ArrayList<CategoryTab> list;
    private onClickListener onClickListener;

    public void setOnClickListener(FenLeAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public FenLeAdapter(Context context, ArrayList<CategoryTab> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHodelr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.fenlei_tem, parent, false);
        return new ViewHodelr(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodelr holder, int position) {

        holder.tvFenlei.setText(list.get(position).getCategoryName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.itemItemClickListener(position);
                    notifyDataSetChanged();
                }
            }
        });
        if (position == getmPosition()) {//构造方法获取他的position
            holder.tvFenlei.setBackgroundColor(Color.WHITE);
        } else {
            holder.tvFenlei.setBackgroundColor(Color.LTGRAY);

        }
    }

    private int mPosition;

    public int getmPosition() {
        return mPosition;
    }

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface onClickListener {
        void itemItemClickListener(int cid);
    }

    public class ViewHodelr extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_fenlei)
        TextView tvFenlei;

        public ViewHodelr(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
