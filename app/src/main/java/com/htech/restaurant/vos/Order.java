package com.htech.restaurant.vos;

/**
 * Created by software on 8/7/15.
 */
public class Order
{
	private int order_trans_id;
	private String name;
	private int price;
	private String remark;
	private int qty;

	public int getOrder_trans_id()
	{
		return order_trans_id;
	}

	public void setOrder_trans_id(int order_trans_id)
	{
		this.order_trans_id = order_trans_id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getPrice()
	{
		return price;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public int getQty()
	{
		return qty;
	}

	public void setQty(int qty)
	{
		this.qty = qty;
	}

}
