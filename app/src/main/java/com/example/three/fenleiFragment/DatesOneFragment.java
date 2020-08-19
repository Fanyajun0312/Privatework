package com.example.three.fenleiFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.httplibary.clicent.HttpClient;
import com.example.httplibary.utils.JsonUtils;
import com.example.three.R;
import com.example.three.app.HttpCallBack;
import com.example.three.bean.DataDetailedean;
import com.example.three.bean.ShopDemo;
import com.example.three.flow.FlowLayout;
import com.example.three.flow.FlowLayoutAdapter;
import com.example.three.ui.DetailsActivity;
import com.google.gson.JsonElement;
import com.youth.banner.Banner;
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
    private LinearLayout llw;
    private DataDetailedean data1;
    private TextView tv_pop_manary;
    private TextView tv_pop_manarybian;
    private ImageView iv_pop_img;
    private String s;
    private DataDetailedean detailedean;
    private boolean indexx = false;
    private ArrayList<ShopDemo.GoodsSkuBean> list;
    private FlowLayout fl;
    private List<ShopDemo.GoodsSkuBean> goodsSku;
    private int posi;
    private FlowLayout fl_pop_two;

    private int num = 1;//用作于pop加减乘除  选种商品

    public DatesOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_dates_one, container, false);
        DetailsActivity activity = (DetailsActivity) getActivity();
        data1 = activity.getData();
        initView();
        initbanner();
        list = new ArrayList<>();
        initData();
        initPop();
        return inflate;
    }
    private void initData() {
        int id = data1.getId();
        new HttpClient.Builder()
                .setApiUrl("kotlin/goods/getGoodsDetail")
                .setJsonbody("{\"goodsId\":" + id + "}", true)
                .build()
                .requset(new HttpCallBack<ShopDemo>() {
                    @Override
                    public void onError(String err, int onError) {

                    }

                    @Override
                    public void cancle() {

                    }

                    @Override
                    protected void onSuccess(ShopDemo pare) {
                        goodsSku = pare.getGoodsSku();
                        list.addAll(goodsSku);
                        String goodsBanner = pare.getGoodsBanner();
                        ArrayList<String> imgs = new ArrayList<>();
                        imgs.add(goodsBanner);
                        mGoodsDetailBanner.setImages(list).setImageLoader(new ImageLoader() {
                            @Override
                            public void displayImage(Context context, Object path, ImageView imageView) {
                                Glide.with(context).load(path).into(imageView);
                            }
                        }).start();
                        Log.i("TAG", "onSuccess: " + pare);
                    }

                    @Override
                    public ShopDemo convert(JsonElement result) {
                        return JsonUtils.jsonToClass(result, ShopDemo.class);
                    }
                });
    }

    private void initPop() {
        mSkuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
    }

    private void pop() {
        //点击已选弹出pop
        mSkuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View inflate = LayoutInflater.from(getContext()).inflate(R.layout.pop, null);
                fl = inflate.findViewById(R.id.fl);
                fl_pop_two = inflate.findViewById(R.id.fl_pop_two);
                TextView tv_price = inflate.findViewById(R.id.tv_pop_manary);
                TextView tv_bian = inflate.findViewById(R.id.tv_pop_manarybian);
                ImageView iv_img = inflate.findViewById(R.id.iv_pop_img);

                Button jia = inflate.findViewById(R.id.btn_pop_jia);
                Button jian = inflate.findViewById(R.id.btn_pop_jian);
                TextView tv_pop_one = inflate.findViewById(R.id.tv_pop_one);
                TextView pei = inflate.findViewById(R.id.pei);

                Glide.with(getContext()).load(data1.getGoodsDefaultIcon()).into(iv_img);
                tv_price.setText(data1.getGoodsDefaultPrice() + ".00");
                tv_bian.setText("商品编号：" + data1.getGoodsCode());
                PopupWindow pop = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, 1600);
                //点击外围关闭
                pop.setBackgroundDrawable(new BitmapDrawable());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                    pop.setOutsideTouchable(true);
                }
                fl.setAdapter(new flowAdapter(list));
                fl_pop_two.setAdapter(new flowAdapter(list));

                //透明度
                setBackGroup(0.5f);
                //透明度监听
                pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        setBackGroup(1f);
                    }
                });

                jia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        num++;
                        tv_pop_one.setText(num + "");
                    }
                });
                jian.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (num > 1) {
                            num--;
                            tv_pop_one.setText(num + "");
                        } else {
                            tv_pop_one.setText("1");
                        }
                    }
                });
                //给布局设置监听
                pop.setFocusable(true);
                pop.showAtLocation(inflate, Gravity.BOTTOM, 0, 0);
            }
        });
    }

    class flowAdapter extends FlowLayoutAdapter<ShopDemo.GoodsSkuBean> {
        private SparseArray<TextView> sparseArray = new SparseArray<>();

        public flowAdapter(List<ShopDemo.GoodsSkuBean> list_bean) {
            super(list_bean);
        }

        @Override
        public void bindDataToView(ViewHolder holder, int position, ShopDemo.GoodsSkuBean bean) {
            List<String> skuContent = bean.getSkuContent();
            TextView textView = holder.getView(R.id.tv_item);
            if (skuContent.size() > 1) {
                fl.setVisibility(View.VISIBLE);
                fl_pop_two.setVisibility(View.INVISIBLE);
                sparseArray.put(position, textView);
                if (position == 0) {
                    textView.setSelected(true);
                }
                for (int i = 0; i < skuContent.size(); i++) {
                    String s1 = skuContent.get(i);
                    textView.setText(s1);
                }
                mSkuSelectedTv.setText(textView.getText());
            } else {
                fl.setVisibility(View.INVISIBLE);
                fl_pop_two.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onItemClick(int position, ShopDemo.GoodsSkuBean bean, View view) {
            posi = position;
            for (int i = 0; i < sparseArray.size(); i++) {
                sparseArray.get(i).setSelected(false);
            }
            sparseArray.get(position).setSelected(true);

        }

        @Override
        public int getItemLayoutID(int position, ShopDemo.GoodsSkuBean bean) {
            return R.layout.fl_item;
        }
    }

    private void setBackGroup(float alpha) {
        Window window = getActivity().getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha = alpha;
        window.setAttributes(attributes);
    }

    private void initbanner() {
        mGoodsDescTv.setText(data1.getGoodsDesc());
        mGoodsPriceTv.setText(data1.getGoodsDefaultPrice());
        mSkuSelectedTv.setText(data1.getGoodsDefaultSku());
    }

    private void initView() {
        mGoodsDetailBanner = inflate.findViewById(R.id.mGoodsDetailBanner);//banner
        mGoodsDescTv = inflate.findViewById(R.id.mGoodsDescTv);//desc详细信息
        mGoodsPriceTv = inflate.findViewById(R.id.mGoodsPriceTv);//价格
        mSkuLabelTv = inflate.findViewById(R.id.mSkuLabelTv);//已选
        mSkuSelectedTv = inflate.findViewById(R.id.mSkuSelectedTv);//选择条件
        mMoreIv = inflate.findViewById(R.id.mMoreIv);//...三个点
        mSkuView = inflate.findViewById(R.id.mSkuView);
        llw = inflate.findViewById(R.id.llw);
    }


}
