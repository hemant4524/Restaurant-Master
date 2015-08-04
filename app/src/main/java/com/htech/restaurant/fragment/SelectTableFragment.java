package com.htech.restaurant.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.htech.restaurant.R;
import com.htech.restaurant.adapter.TableAdapter;
import com.htech.restaurant.vos.Table;

import java.util.ArrayList;
import java.util.List;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;

/**
 * Recycle view demo
 * http://javatechig.com/android/android-recyclerview-example
 */
public class SelectTableFragment extends Fragment implements TableAdapter.OnItemClickListener {

    private TableAdapter mAdapter;

    private RecyclerView mRecyclerView;
    private String TAG = SelectTableFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_selecttable_layout, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        // mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        // Set dynamic table list
        getTable();


        return view;
    }

    /**
     * Fetch table list
     */
    private void getTable() {
        List<Table> tables = new ArrayList<Table>();

        for (int i = 0; i < 12; i++) {
            int id = (i + 1);
            Table table = new Table();
            table.setId(id);
            table.setName("" + id);
            tables.add(table);
        }
        mAdapter = new TableAdapter(getActivity(), tables, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClickListener(int position) {
        int table_number = (position+1);
        OrderTableFragment ordertable = new OrderTableFragment();
        ordertable.setTableIndex(table_number);
        //((MaterialNavigationDrawer)this.getActivity()).setFragmentChild(tableOrder, "order_table_fragment");
        ((MaterialNavigationDrawer)this.getActivity()).setFragmentChild(ordertable, "Order for Table " + table_number);
    }
}