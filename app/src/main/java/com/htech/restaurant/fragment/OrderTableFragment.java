package com.htech.restaurant.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.htech.restaurant.R;
import com.htech.restaurant.adapter.ViewPagerAdapter;
import com.htech.restaurant.common.SlidingTabLayout;

/**
 * Sliding tab
 * http://www.android4devs.com/2015/01/how-to-make-material-design-sliding-tabs.html
 *
 * https://github.com/codepath/android_guides/wiki/Sliding-Tabs-with-PagerSlidingTabStrip
 */
public class OrderTableFragment extends Fragment {

    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout mTabLayout;
    CharSequence Titles[]={"FAVORITES","CATEGORIES","ORDER"};
    int Numboftabs =3;
    private SlidingTabLayout tabs;
    private String TAG = OrderTableFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_ordertable, container, false);



    }

    public void setTableIndex(int tableIndex) {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapter(getActivity().getSupportFragmentManager(),Titles,Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) view.findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        pager.setCurrentItem(1);
        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);

    }


    @Override
    public void onResume() {
        super.onResume();
    }
}
