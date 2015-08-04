package com.htech.restaurant.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.htech.restaurant.R;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainCategoryFragment extends Fragment {


    private String TAG = MainCategoryFragment.class.getSimpleName();

    public MainCategoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maincategory, container, false);
        Button btnDetail = (Button) view.findViewById(R.id.btnDetail);
        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "detail click");
//                FragmentTransaction tran = getActivity().getSupportFragmentManager().beginTransaction();
//                tran.add(R.id.flMainContainer,new CategoryDetailFragment(),"detail container");
//                tran.addToBackStack(null);
//                tran.commit();
                ((MaterialNavigationDrawer)getActivity()).setFragmentChild(new CategoryDetailFragment(),"detail container");
            }
        });
        return view;
    }
}
