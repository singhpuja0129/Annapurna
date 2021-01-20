package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.model.OrderModel;

public class OrderService {
	private Connection con;
	
	public OrderService(Connection con)
	{
		this.con=con;
	}
	public List<OrderModel> viewOrders(int u_id){
		List<OrderModel> orderList=new ArrayList<>();
		
		try {
			//PreparedStatement ps=con.prepareStatement("select * from order_tbl where user_id=?");
			PreparedStatement ps=con.prepareStatement("select order_tbl.order_id, order_tbl.total_price, order_tbl.order_date, order_tbl.location, order_tbl.product_id, order_tbl.provider_id, order_tbl.quantity, product_table.product_name, provider_table.provider_name from order_tbl, product_table, provider_table where order_tbl.user_id=? and order_tbl.product_id=product_table.product_id and order_tbl.provider_id=provider_table.provider_id");
			ps.setInt(1, u_id);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				OrderModel order=new OrderModel();
				
				order.setOderId(rs.getInt(1));
				order.setOrderPrice(rs.getDouble(2));
				order.setOrderDate(rs.getDate(3).toLocalDate());
				order.setLocation(rs.getString(4));
				order.setProductId(rs.getInt(5));
				order.setQuantity(rs.getInt(7));
				order.setProductName(rs.getString(8));
				order.setProviderName(rs.getString(9));
				
				orderList.add(order);
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return orderList;
	
	}
	
}
