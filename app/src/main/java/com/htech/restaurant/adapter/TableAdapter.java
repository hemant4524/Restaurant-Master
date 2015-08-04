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
import com.htech.restaurant.vos.Table;

import java.util.List;

/**
 * Created by software on 7/29/15.
 */
public class TableAdapter extends RecyclerView.Adapter<TableAdapter.TableViewHolder> {
    private List<Table> mTables;
    private Context mContext;
    private String TAG = TableAdapter.class.getSimpleName();

    OnItemClickListener onItemClickListener;

    public TableAdapter(Context pContext, List<Table> pTable, OnItemClickListener pOnItemClickListener)
    {
        mContext = pContext;
        mTables = pTable;
        onItemClickListener = pOnItemClickListener;
    }
    @Override
    public TableViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row_table,null);

        TableViewHolder viewHolder = new TableViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TableViewHolder tableViewHolder, final int i) {
        Table table = mTables.get(i);
        Log.d(TAG,"table name"+table.getName());
        tableViewHolder.textView.setText(table.getName());

        tableViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClickListener(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return  (null != mTables ? mTables.size() : 0);
    }

    class TableViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private ImageView imageView;

        public TableViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.list_row_tvTitle);
            this.imageView = (ImageView) itemView.findViewById(R.id.list_row_ivThumbnail);
        }
    }


    public interface OnItemClickListener
    {
        void onItemClickListener(int position);
    }
}
