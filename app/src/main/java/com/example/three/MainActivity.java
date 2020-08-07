package com.example.three;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.three.fragment.FenLFragment;
import com.example.three.fragment.HemoFragment;
import com.example.three.fragment.MsangFragment;
import com.example.three.fragment.ShapFragment;
import com.example.three.fragment.WodeFragment;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    //    @BindView(R.id.iv_img)
//    ImageView ivImg;
//    @BindView(R.id.re)
//    RelativeLayout re;
    @BindView(R.id.main_fl)
    FrameLayout mainFl;
    @BindView(R.id.bom_bar)
    BottomNavigationBar bomBar;
    private FragmentManager fragmentManager;
    private Fragment mfragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        BommBar();//BottomNavigationBar设置

        //fl
        cutFragment();//BottomNavigationBar+Fragnebt借切换

    }

    private void BommBar() {
        BottomNavigationItem shoWye = new BottomNavigationItem(R.drawable.d, "首页").setInactiveIconResource(R.drawable.b);
        BottomNavigationItem xiangMu = new BottomNavigationItem(R.drawable.t, "分类").setInactiveIconResource(R.drawable.i);
        BottomNavigationItem gouWu = new BottomNavigationItem(R.drawable.g, "购物车").setInactiveIconResource(R.drawable.h);
        BottomNavigationItem huiHua = new BottomNavigationItem(R.drawable.c, "消息").setInactiveIconResource(R.drawable.a);
        BottomNavigationItem lianXi = new BottomNavigationItem(R.drawable.f, "我的").setInactiveIconResource(R.drawable.e);
        bomBar
                .addItem(shoWye)
                .addItem(xiangMu)
                .addItem(gouWu)
                .addItem(huiHua)
                .addItem(lianXi)
//                .setFirstSelectedPosition(0)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setActiveColor(R.color.bule)
                .initialise();
    }

    private void cutFragment() {
        HemoFragment hemoFragment = new HemoFragment();
        FenLFragment fenLFragment = new FenLFragment();
        MsangFragment msangFragment = new MsangFragment();
        WodeFragment wodeFragment = new WodeFragment();
        ShapFragment shapFragment = new ShapFragment();


        fragmentManager = getSupportFragmentManager();//从新导入fragmentManager
        fragmentManager.beginTransaction()
                .add(R.id.main_fl, hemoFragment)
                .commit();
         mfragment=hemoFragment;


        bomBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position) {
                    case 0:
                     switchFragment(hemoFragment);
                        break;
                    case 1:
                        switchFragment(fenLFragment);
                        break;
                    case 2:
                        switchFragment(shapFragment);
                        break;
                    case 3:
                        switchFragment(msangFragment);
                        break;
                    case 4:
                        switchFragment(wodeFragment);
                        break;

                }

            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

    }
    private void switchFragment(Fragment fragment) {
        //判断当前显示的Fragment是不是切换的Fragment
        if (mfragment != fragment) {
            //判断切换的Fragment是否已经添加过
            if (!fragment.isAdded()) {
                //如果没有，则先把当前的Fragment隐藏，把切换的Fragment添加上
                getSupportFragmentManager().beginTransaction().hide(mfragment)
                        .add(R.id.main_fl, fragment).commit();
            } else {
                //如果已经添加过，则先把当前的Fragment隐藏，把切换的Fragment显示出来
                getSupportFragmentManager().beginTransaction().hide(mfragment).show(fragment).commit();
            }
            mfragment = fragment;
        }
    }
}
