package com.example.three.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.three.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WodeFragment extends Fragment {

    private View inflate;

    public WodeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_wode, container, false);
        return inflate;
    }
}
