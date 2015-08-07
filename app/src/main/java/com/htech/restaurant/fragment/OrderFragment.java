package com.htech.restaurant.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.htech.restaurant.MyApplication;
import com.htech.restaurant.R;
import com.htech.restaurant.adapter.OrderAdapter;
import com.htech.restaurant.db.DatabaseService;
import com.htech.restaurant.vos.Order;

import java.io.IOException;
import java.util.ArrayList;

public class OrderFragment extends Fragment {


    private OrderAdapter mAdapter;

    private RecyclerView mRecyclerView;

    private String TAG = OrderFragment.class.getSimpleName();
    private DatabaseService mDatabaseService;

    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_order, container, false);
            mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_category_recycler_view);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            // mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3)); // Grid view

        try {
            mDatabaseService = DatabaseService.getInstance(getActivity());
        } catch (IOException e) {
            e.printStackTrace();
        }

            // Set dynamic table list
               getMainMenu();
        return view;
    }
    /**
     * Fetch table list
     */
    private void getMainMenu() {

        // Get table wise order from transaction

        ArrayList<Order> orders = mDatabaseService.getTotalOrde(1);
        Log.d(TAG, "sub menu size:" + MyApplication.SUBMENU.size());

        mAdapter = new OrderAdapter(getActivity(),orders);
        mRecyclerView.setAdapter(mAdapter);

    }

}
