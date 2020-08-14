package com.example.three.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.three.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShapFragment extends Fragment {

    @BindView(R.id.tool_bar_category)
    Toolbar toolBarCategory;
    @BindView(R.id.rv_category)
    RecyclerView rvCategory;
    @BindView(R.id.rv_goods)
    RecyclerView rvGoods;
    private View inflate;

    public ShapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_shap, container, false);
        ButterKnife.bind(this,inflate);
        return inflate;
    }
}
