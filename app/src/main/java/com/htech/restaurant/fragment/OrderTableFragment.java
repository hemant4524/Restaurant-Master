package com.htech.restaurant.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.htech.restaurant.R;
import com.htech.restaurant.adapter.ViewPagerAdapter;
import com.htech.restaurant.common.SlidingTabLayout;
import com.htech.restaurant.common.Utils;
import com.htech.restaurant.db.DatabaseService;
import com.htech.restaurant.db.KeyValueStore;

import java.io.IOException;

/**
 * Sliding tab http://www.android4devs.com/2015/01/how-to-make-material-design-sliding-tabs.html
 *
 * https://github.com/codepath/android_guides/wiki/Sliding-Tabs-with-PagerSlidingTabStrip
 */
public class OrderTableFragment extends Fragment
{

	ViewPager pager;
	ViewPagerAdapter adapter;
	SlidingTabLayout mTabLayout;
	CharSequence Titles[] =
	{ "FAVORITES", "CATEGORIES", "ORDER" };
	int Numboftabs = 3;
	private SlidingTabLayout tabs;
	private String TAG = OrderTableFragment.class.getSimpleName();
	private DatabaseService mDatabaseService;
	private int tableId = 0;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{

		return inflater.inflate(R.layout.fragment_ordertable, container, false);

	}

	public void setTableIndex(int tableIndex)
	{
		try {
			mDatabaseService = DatabaseService.getInstance(getActivity());
		} catch (IOException e) {
			e.printStackTrace();
		}
		tableId = tableIndex;
		// Create blank order blank record in order master table
		createNewOrder();

	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		// Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
		adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), Titles, Numboftabs);

		// Assigning ViewPager View and setting the adapter
		pager = (ViewPager) view.findViewById(R.id.pager);
		pager.setAdapter(adapter);

		// Assiging the Sliding Tab Layout View
		tabs = (SlidingTabLayout) view.findViewById(R.id.tabs);
		tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

		// Setting Custom Color for the Scroll bar indicator of the Tab View
		tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer()
		{
			@Override
			public int getIndicatorColor(int position)
			{
				return getResources().getColor(R.color.tabsScrollColor);
			}
		});

		pager.setCurrentItem(1);
		// Setting the ViewPager For the SlidingTabsLayout
		tabs.setViewPager(pager);



	}

	@Override
	public void onResume()
	{
		super.onResume();
	}

	/**
	 * Create new oder for customer
	 * 
	 *
	 */
	public void createNewOrder()
	{
		new DataBaseOperationAsyn().execute();
	}

	/**
	 * Create new oder record in background
	 */
	class DataBaseOperationAsyn extends AsyncTask<String, String, String>
	{

		@Override
		protected String doInBackground(String... params)
		{
			int id = mDatabaseService.createOrder(Utils.getCurrentDateTime(), ""+tableId, "1");
			Log.d(TAG, "order created id:" + id);
			return "" + id;
		}

		@Override
		protected void onPostExecute(String oderId)
		{
			super.onPostExecute(oderId);

			Utils.saveValueInPreferences(getActivity(), KeyValueStore.KEY_ORDER_ID, "" + oderId);

			// Log.d(TAG,"value from sharedpreferences :"+Utils.readValueFromPreferences(MenuDetailActivity.this,KeyValueStore.KEY_ORDER_ID));
		}
	}

}
