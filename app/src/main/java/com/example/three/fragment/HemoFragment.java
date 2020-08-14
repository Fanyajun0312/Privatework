package com.example.three.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.three.R;
import com.example.three.adapter.RavAdapter;
import com.example.three.adapter.Vpadapter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.crosswall.lib.coverflow.core.PagerContainer;

/**
 * A simple {@link Fragment} subclass.
 */
public class HemoFragment extends Fragment {

    LinearLayout ll;
    @BindView(R.id.hemo_banner)
    Banner hemoBanner;
    @BindView(R.id.iv_huo)
    ImageView ivHuo;
    @BindView(R.id.ll_o)
    LinearLayout llO;
    @BindView(R.id.rlv_hemo)
    RecyclerView rlvHemo;
    @BindView(R.id.ll_two)
    LinearLayout llTwo;
    @BindView(R.id.mTopicPager)
    ViewPager mTopicPager;
    @BindView(R.id.mTopicContainer)
    PagerContainer mTopicContainer;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.viewFen)
    View viewFen;
    @BindView(R.id.filpper)
    ViewFlipper filpper;
    @BindView(R.id.ll)
    LinearLayout llTree;
    private View inflate;
    private Unbinder bind;
    private static final String HOME_BANNER_ONE = "https://gitee.com/ccyy2019/server/raw/master/img05.jpg";
    private static final String HOME_BANNER_TWO = "https://gitee.com/ccyy2019/server/raw/master/img06.jpg";
    private static final String HOME_BANNER_THREE = "https://gitee.com/ccyy2019/server/raw/master/img07.jpg";
    private static final String HOME_BANNER_FOUR = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503471047&di=679d7a6c499f59d1b0dcd56b62a9aa6c&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.90sheji.com%2Fdianpu_cover%2F11%2F14%2F64%2F55%2F94ibannercsn_1200.jpg";
    /*
       首页折扣图片
     */
    private static final String HOME_DISCOUNT_ONE = "https://img14.360buyimg.com/n0/jfs/t3157/231/5756125504/98807/97ab361d/588084a1N06efb01d.jpg";
    private static final String HOME_DISCOUNT_TWO = "https://img10.360buyimg.com/n7/jfs/t5905/106/1120548052/61075/6eafa3a5/592f8f7bN763e3d30.jpg";
    private static final String HOME_DISCOUNT_THREE = "https://img10.360buyimg.com/n7/jfs/t5584/99/6135095454/371625/423b9ba5/59681d91N915995a7.jpg";
    private static final String HOME_DISCOUNT_FOUR = "https://img11.360buyimg.com/n7/jfs/t4447/301/1238553109/193354/13c7e995/58db19a7N25101fe4.jpg";
    private static final String HOME_DISCOUNT_FIVE = "https://img14.360buyimg.com/n1/s190x190_jfs/t7525/189/155179632/395056/e200017f/598fb8a4N7800dee6.jpg";

    /*
        首页话题图片
     */
    private static final String HOME_TOPIC_ONE = "https://gitee.com/ccyy2019/server/raw/master/img05.jpg";
    private static final String HOME_TOPIC_TWO = "https://gitee.com/ccyy2019/server/raw/master/img06.jpg";
    private static final String HOME_TOPIC_THREE = "https://gitee.com/ccyy2019/server/raw/master/img07.jpg";
    private static final String HOME_TOPIC_FOUR = "http://img.zcool.cn/community/01796c58772f66a801219c77e4fc04.png@1280w_1l_2o_100sh.png";
    private static final String HOME_TOPIC_FIVE = "http://img.zcool.cn/community/0154805791ffeb0000012e7edba495.jpg@900w_1l_2o_100sh.jpg";
    private RavAdapter adapter;
    private ArrayList<String> imgList;
    private ArrayList<String> titles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_hemo, container, false);
        bind = ButterKnife.bind(this, inflate);
        initData();
        initBanner();
        initRlv();

        initPagerContainer();//画廊
        return inflate;
    }

    private void initData() {
        titles = new ArrayList();
        titles.add("没有什么退路，只有咬牙坚持走下去的路！");
        titles.add("今天的你依旧帅气如初 ");
        titles.add("愿十年之后的自己会感谢当初努力奋斗的你");
        titles.add("日子再甜,也没有你甜");
        titles.add("喜欢阿羡也喜欢李现但更喜欢你现（出现）");

        for (int i = 0; i < titles.size(); i++) {
            View inflate = getActivity().getLayoutInflater().inflate(R.layout.title_view, null);

            TextView tvTitle = inflate.findViewById(R.id.tvItem);
            //赋值
            tvTitle.setText(titles.get(i));
            //动态添加视图
            filpper.addView(inflate);
        }

        //设置的时间间隔来开始切换所有的View,切换会循环进行
        filpper.startFlipping();
        //视图进入动画
        filpper.setInAnimation(getActivity(), R.anim.news_in);
        //视图退出动画
        filpper.setOutAnimation(getActivity(), R.anim.news_out);
        //自动开始滚动
        filpper.setAutoStart(true);
        //视图的切换间隔
        filpper.setFlipInterval(3000);
    }

    private void initPagerContainer() {

        ArrayList<String> listimg = new ArrayList<>();

        listimg.add(HOME_TOPIC_ONE);
        listimg.add(HOME_TOPIC_TWO);
        listimg.add(HOME_TOPIC_THREE);
        listimg.add(HOME_TOPIC_FOUR);
        listimg.add(HOME_TOPIC_FIVE);

        mTopicPager.setPageTransformer(true, new GallyPageTransformer());
        Vpadapter vpadapter = new Vpadapter(getActivity(), listimg);
        mTopicPager.setAdapter(vpadapter);
    }



    private void initRlv() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rlvHemo.setLayoutManager(linearLayoutManager);
        ArrayList<String> list = new ArrayList<>();
        list.add(HOME_DISCOUNT_ONE);
        list.add(HOME_DISCOUNT_TWO);
        list.add(HOME_DISCOUNT_THREE);
        list.add(HOME_DISCOUNT_FOUR);
        list.add(HOME_DISCOUNT_FIVE);

        adapter = new RavAdapter(getActivity(), list);
        rlvHemo.setAdapter(adapter);
    }

    private void initBanner() {
        ArrayList<String> img = new ArrayList<>();
        img.add(HOME_BANNER_ONE);
        img.add(HOME_BANNER_TWO);
        img.add(HOME_BANNER_THREE);
        img.add(HOME_BANNER_FOUR);

        hemoBanner.setImages(img).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }
    }

    private class GallyPageTransformer implements ViewPager.PageTransformer {
        @Override
        public void transformPage(@NonNull View page, float position) {
            page.setPivotX(0);//X轴
            if (position < -1 || position > 1) {
                page.setRotationY(0);
            } else {
                if (position < 0) {
                    page.setPivotX(page.getWidth());
                    page.setRotationY((position) * 30);
                    page.setScaleX(1 + (position / 2));
                } else {
                    page.setPivotX(0);
                    page.setRotationY(position * 30);
                    page.setScaleX(1 - (position / 2));
                }
            }
        }
    }
}
