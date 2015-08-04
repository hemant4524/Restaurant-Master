package com.htech.restaurant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.htech.restaurant.R;
import com.htech.restaurant.vos.MainMenu;

import java.util.List;

/**
 * Created by software on 7/29/15.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.TableViewHolder> {
    private List<MainMenu> mCategories;
    private Context mContext;
    private String TAG = CategoryAdapter.class.getSimpleName();

    OnItemClickListener onItemClickListener;

    public CategoryAdapter(Context pContext, List<MainMenu> pCategories, OnItemClickListener pOnItemClickListener) {
        mContext = pContext;
        mCategories = pCategories;
        onItemClickListener = pOnItemClickListener;
    }

    @Override
    public TableViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row_category, null);

        TableViewHolder viewHolder = new TableViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TableViewHolder tableViewHolder, final int i) {
        MainMenu category = mCategories.get(i);
        Log.d(TAG, "category name" + category.getCatName());
        tableViewHolder.textView.setText(category.getCatName());

        tableViewHolder.imageViewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClickListener(i);
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
        private ImageView imageViewDetail;

        public TableViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.list_row_tvTitle);
            this.imageView = (ImageView) itemView.findViewById(R.id.list_row_ivThumbnail);
            this.imageViewDetail = (ImageView) itemView.findViewById(R.id.list_row_ivDetail);
        }
    }


    public interface OnItemClickListener {
        void onItemClickListener(int position);
    }
}
