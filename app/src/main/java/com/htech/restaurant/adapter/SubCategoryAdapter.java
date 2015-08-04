package com.htech.restaurant.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.htech.restaurant.R;
import com.htech.restaurant.activity.RemarkActivity;
import com.htech.restaurant.vos.SubMenu;

import java.util.List;

/**
 * Created by software on 7/29/15.
 */
public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.TableViewHolder> {
    private List<SubMenu> mCategories;
    private Context mContext;
    private String TAG = SubCategoryAdapter.class.getSimpleName();

    OnItemClickListener onItemClickListener;

    public SubCategoryAdapter(Context pContext, List<SubMenu> pCategories, OnItemClickListener pOnItemClickListener) {
        mContext = pContext;
        mCategories = pCategories;
        onItemClickListener = pOnItemClickListener;
    }

    @Override
    public TableViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_sub_category, null);

        TableViewHolder viewHolder = new TableViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final TableViewHolder tableViewHolder, final int i) {
        SubMenu category = mCategories.get(i);
        Log.d(TAG, "category name" + category.getSubCatName());
        tableViewHolder.textView.setText(category.getSubCatName());

        tableViewHolder.tvTotalOder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableViewHolder.ivMinus.setVisibility(View.VISIBLE);
                onItemClickListener.onItemClickListener(i);
            }
        });
        tableViewHolder.ivPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableViewHolder.ivMinus.setVisibility(View.VISIBLE);
                onItemClickListener.onItemClickListener(i);
            }
        });
        tableViewHolder.ivMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClickListener(i);
            }
        });
        tableViewHolder.ivRemark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RemarkActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != mCategories ? mCategories.size() : 0);
    }

    class TableViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;
        private TextView tvTotalOder;
        private ImageView ivPlus;
        private ImageView ivMinus;
        private final ImageView ivRemark;

        public TableViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.list_row_tvTitle);
            this.imageView = (ImageView) itemView.findViewById(R.id.list_row_ivThumbnail);
            this.tvTotalOder = (TextView) itemView.findViewById(R.id.list_row_tvTotalOder);
            this.ivPlus = (ImageView) itemView.findViewById(R.id.list_row_sub_ivPlus);
            this.ivMinus = (ImageView) itemView.findViewById(R.id.list_row_sub_ivMinus);
            this.ivRemark = (ImageView) itemView.findViewById(R.id.list_row_sub_ivInforamtion);
            ivMinus.setVisibility(View.INVISIBLE);
        }
    }


    public interface OnItemClickListener {
        void onItemClickListener(int position);
    }
}
