package com.htech.restaurant.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.htech.restaurant.R;
import com.htech.restaurant.adapter.OrderAdapter;
import com.htech.restaurant.common.Utils;
import com.htech.restaurant.db.DatabaseService;
import com.htech.restaurant.db.KeyValueStore;
import com.htech.restaurant.vos.Order;

import java.io.IOException;
import java.util.ArrayList;

public class OrderFragment extends Fragment {


    private OrderAdapter mAdapter;

    private RecyclerView mRecyclerView;

    private String TAG = OrderFragment.class.getSimpleName();
    private DatabaseService mDatabaseService;
    private TextView mtvTotalRupees;

    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        mtvTotalRupees = (TextView) view.findViewById(R.id.frag_order_tvTotalRupees);
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
        String order_id = Utils.readValueFromPreferences(getActivity(), KeyValueStore.KEY_ORDER_ID);
        ArrayList<Order> orders = mDatabaseService.getTotalOrde(Integer.parseInt(order_id));
        Log.d(TAG, "order transaction size:" + orders.size());

        mAdapter = new OrderAdapter(getActivity(), orders);
        mRecyclerView.setAdapter(mAdapter);
        calTotalRupees(orders);

    }
    private void calTotalRupees(ArrayList<Order> orders)
    {
        int sum = 0;
        for (Order order:orders) {
            Log.d(TAG, "order transaction size:" + order.getPrice());
            Log.d(TAG, "order transaction size:" + order.getQty());

            sum = sum + (order.getPrice() * order.getQty());
        }

        mtvTotalRupees.setText("Total: " + getActivity().getResources().getString(R.string.Rs) + " " + rupeeFormat(""+sum));
    }

    public static String rupeeFormat(String value){
        value=value.replace(",","");
        char lastDigit=value.charAt(value.length()-1);
        String result = "";
        int len = value.length()-1;
        int nDigits = 0;

        for (int i = len - 1; i >= 0; i--)
        {
            result = value.charAt(i) + result;
            nDigits++;
            if (((nDigits % 2) == 0) && (i > 0))
            {
                result = "," + result;
            }
        }
        return (result+lastDigit);
    }
}
