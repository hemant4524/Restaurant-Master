package com.htech.restaurant.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.htech.restaurant.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryDetailFragment extends Fragment {


    private String TAG = CategoryDetailFragment.class.getSimpleName();

    public CategoryDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_detail, container, false);
        Log.d(TAG, "detail fragment");

        return view;
    }


}
