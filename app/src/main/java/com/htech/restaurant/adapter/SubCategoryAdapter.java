package com.htech.restaurant.adapter;

import android.app.Activity;
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
public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.TableViewHolder>
{
	private List<SubMenu> mCategories;
	private Context mContext;
	private String TAG = SubCategoryAdapter.class.getSimpleName();
	public static int ADD_ITEM = 1;
	public static int REMOVE_ITEM = 2;
	public static int ADD_ITEM_REMARK = 3;
	public static String REMARK_TEXT = "remark";

	OnItemClickListener onItemClickListener;

	public SubCategoryAdapter(Context pContext, List<SubMenu> pCategories, OnItemClickListener pOnItemClickListener)
	{
		mContext = pContext;
		mCategories = pCategories;
		onItemClickListener = pOnItemClickListener;

	}

	@Override
	public TableViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
	{
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_sub_category, null);

		TableViewHolder viewHolder = new TableViewHolder(view);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(final TableViewHolder tableViewHolder, final int i)
	{
		final SubMenu category = mCategories.get(i);
		Log.d(TAG, "category name" + category.getSubCatName());
		tableViewHolder.textView.setText(category.getSubCatName());

		tableViewHolder.ivPlus.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{

				int totalQty = onItemClickListener.onItemClickListener(ADD_ITEM, category.getId());
				if (totalQty > 0)
				{
					tableViewHolder.ivMinus.setVisibility(View.VISIBLE);
                    tableViewHolder.tvTotalOder.setVisibility(View.VISIBLE);
					tableViewHolder.tvTotalOder.setText(""+totalQty);
				}
				else
				{
					tableViewHolder.ivMinus.setVisibility(View.INVISIBLE);
                    tableViewHolder.tvTotalOder.setVisibility(View.INVISIBLE);

                }
			}
		});
		tableViewHolder.ivMinus.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{

                int totalQty = 	onItemClickListener.onItemClickListener(REMOVE_ITEM, category.getId());
                if (totalQty > 0)
                {
                    tableViewHolder.ivMinus.setVisibility(View.VISIBLE);
                    tableViewHolder.tvTotalOder.setVisibility(View.VISIBLE);
					tableViewHolder.tvTotalOder.setText(""+totalQty);
                }
                else
                {
                    tableViewHolder.ivMinus.setVisibility(View.INVISIBLE);
					tableViewHolder.tvTotalOder.setVisibility(View.INVISIBLE);

                }
			}
		});
		tableViewHolder.ivRemark.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(mContext, RemarkActivity.class);
				((Activity) mContext).startActivityForResult(intent, ADD_ITEM_REMARK);
			}
		});
	}

	@Override
	public int getItemCount()
	{
		return (null != mCategories ? mCategories.size() : 0);
	}

	class TableViewHolder extends RecyclerView.ViewHolder
	{
		private TextView textView;
		private ImageView imageView;
		private TextView tvTotalOder;
		private ImageView ivPlus;
		private ImageView ivMinus;
		private final ImageView ivRemark;

		public TableViewHolder(View itemView)
		{
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

	public interface OnItemClickListener
	{
		int onItemClickListener(int argument_type, int submenu_id);

		void onItemClickListener(int id, String info);
	}

}
