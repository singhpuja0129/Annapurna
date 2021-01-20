package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.model.ItemModel;

public class ItemService {

	private Connection con;

	public ItemService(Connection con) {
		this.con = con;
	}

	public boolean addItem(ItemModel item) {
		boolean result = false;
		try {
			PreparedStatement ps = con.prepareStatement(
					"insert into tiffin_items_table(item_name, item_price, item_type, item_available_status, provider_id) values(?, ?, ?, ?, ?)");
			ps.setString(1, item.getItemName());
			ps.setDouble(2, item.getItemPrice());
			ps.setString(3, item.getItemType());
			ps.setInt(4, item.getItemAvailable());
			ps.setInt(5, item.getProviderId());

			int r = ps.executeUpdate();
			if (r > 0) {
				System.out.println("Item added successfully !");
				result = true;
			} else {
				System.out.println("Item add failed !");
				result = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<ItemModel> viewItems(int p_id){
		List<ItemModel> itemList=new ArrayList<>();
		
		try {
			PreparedStatement ps=con.prepareStatement("select * from tiffin_items_table where provider_id=?");
			ps.setInt(1, p_id);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				ItemModel item=new ItemModel();
				
				item.setItemId(rs.getInt(1));
				item.setItemName(rs.getString(2));
				item.setItemPrice(rs.getDouble(3));
				item.setItemType(rs.getString(4));
				item.setItemAvailable(rs.getInt(5));
				item.setProviderId(rs.getInt(6));
				
				itemList.add(item);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return itemList;
	
	}
	
	public boolean deleteItem(int item_id){
		boolean res=false;
		try {
			PreparedStatement ps=con.prepareStatement("delete from tiffin_items_table where item_id=?");
			ps.setInt(1, item_id);
			ps.executeUpdate();
			res=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}
	public boolean editItem(int p_id,ItemModel item){
		boolean res=false;
		try {
			PreparedStatement ps=con.prepareStatement("update tiffin_items_table set item_available_status=? where provider_id=?");
			ps.setInt(1, item.getItemAvailable());
			ps.setInt(2, p_id);
			ps.executeQuery();
			res=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}
}
