package com.htech.restaurant.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.htech.restaurant.MyApplication;
import com.htech.restaurant.R;
import com.htech.restaurant.adapter.SubCategoryAdapter;
import com.htech.restaurant.common.Utils;
import com.htech.restaurant.db.DatabaseService;
import com.htech.restaurant.db.KeyValueStore;

import java.io.IOException;

public class MenuDetailActivity extends AppCompatActivity implements SubCategoryAdapter.OnItemClickListener {

    private String TAG = MenuDetailActivity.class.getSimpleName();
    private SubCategoryAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private DatabaseService mDatabaseService;
    private int mMainMenuId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.menu_detail_toolbar);
        // setSupportActionBar(mToolbar);

        mMainMenuId = getIntent().getIntExtra("MenuId", 0);
        Log.d(TAG, "selected menu id:" + mMainMenuId);

        // Get menu name
        String menuName = getIntent().getStringExtra("MenuName");
        Log.d(TAG, "selected menuName :" + menuName);

        mToolbar.setTitle(menuName);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        mRecyclerView = (RecyclerView) findViewById(R.id.activity_menu_detail_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        getSubMenuData(mMainMenuId);



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            Log.d(TAG,"Back pressed");
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }
    /**
     * get submenu from database
     *
     * @param id
     */
    public void getSubMenuData(int id) {
        try {
            mDatabaseService = DatabaseService.getInstance(MenuDetailActivity.this);
            MyApplication.SUBMENU = mDatabaseService.getSubMenu(id);
            Log.d(TAG, "sub menu size:" + MyApplication.SUBMENU.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (MyApplication.SUBMENU.size() > 0) {
            mAdapter = new SubCategoryAdapter(this, MyApplication.SUBMENU, this);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            Toast.makeText(MenuDetailActivity.this, "NO DATA", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int onItemClickListener(int argument_type, int submenu_id) {
        int totalQty = 0;
        String order_id = Utils.readValueFromPreferences(MenuDetailActivity.this, KeyValueStore.KEY_ORDER_ID);

        // Create new row first time
        // add item into oder transaction table
        mDatabaseService.addOrderTransaction(order_id, "" + MyApplication.SUBMENU.get(mMainMenuId).getId(), "" + submenu_id);

        if (argument_type == SubCategoryAdapter.ADD_ITEM) {

            totalQty= mDatabaseService.updateOrderTransaction(order_id, "" + submenu_id, 1);

        } else if (argument_type == SubCategoryAdapter.REMOVE_ITEM) {

            // remove item from data base
            totalQty = mDatabaseService.updateOrderTransaction(order_id, "" + submenu_id, 0);

        }
        return totalQty;
    }

    @Override
    public void onItemClickListener(int id, String info) {
        // add subcategory id into order transaction table

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SubCategoryAdapter.ADD_ITEM_REMARK) {
                if (data != null) {
                    // Change remark value in subcategory array list
                    String message = data.getStringExtra(SubCategoryAdapter.REMARK_TEXT);
                    Log.d(TAG, "remark value" + message);
                }
            }
        }

    }



}
