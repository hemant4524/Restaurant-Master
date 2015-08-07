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
import com.htech.restaurant.vos.Order;

import java.util.List;

/**
 * Created by software on 7/29/15.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.TableViewHolder> {
    private List<Order> mOrders;
    private Context mContext;
    private String TAG = OrderAdapter.class.getSimpleName();

    public OrderAdapter(Context pContext, List<Order> pCategories) {
        mContext = pContext;
        mOrders = pCategories;
    }

    @Override
    public TableViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row_order, null);

        TableViewHolder viewHolder = new TableViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TableViewHolder tableViewHolder, final int i) {
        Order order = mOrders.get(i);
        Log.d(TAG, "category name" + order.getName());

        tableViewHolder.tvName.setText(order.getName());
        tableViewHolder.tvPrice.setText(" "+mContext.getResources().getString(R.string.Rs) + "" + order.getPrice());

        tableViewHolder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        tableViewHolder.imageViewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != mOrders ? mOrders.size() : 0);
    }

    class TableViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvPrice;
        private ImageView imageView;
        private ImageView imageViewDetail;

        public TableViewHolder(View itemView) {
            super(itemView);
            this.tvName = (TextView) itemView.findViewById(R.id.list_row_order_tvName);
            this.tvPrice = (TextView) itemView.findViewById(R.id.list_row_tvPrice);
            this.imageView = (ImageView) itemView.findViewById(R.id.list_row_ivThumbnail);
            this.imageViewDetail = (ImageView) itemView.findViewById(R.id.list_row_ivDetail);
        }
    }

}
