package DAO;

import java.util.ArrayList;

import MySQLiteHelper.TodoSQLiteHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.digiteix.models.Shop;
import com.digiteix.models.ShopList;

public class ShopList_ShopDAO {

	private SQLiteDatabase db;
	private TodoSQLiteHelper dbHelper;

	public ShopList_ShopDAO(Context context) {
		dbHelper = new TodoSQLiteHelper(context);
		db = dbHelper.getWritableDatabase();
	}

	// Close the db
	public void close() {
		db.close();
	}

	public void createTodo(ShopList shopList) {

		for (int i = 0; i < shopList.getArrayShop().size(); i++) {
			ContentValues contentValues = new ContentValues();
			contentValues.put("shopId", shopList.getArrayShop().get(i).getShopId());
			contentValues.put("shopName", shopList.getArrayShop().get(i).getShopName());
			contentValues.put("description", shopList.getArrayShop().get(i).getDesceiption());
			contentValues.put("typeId", shopList.getArrayShop().get(i).getId_type());
			// contentValues.put("icon", shopList.getArrayShop().get(i).get);
			contentValues.put("longitude", shopList.getArrayShop().get(i).getLongitud());
			contentValues.put("latitude", shopList.getArrayShop().get(i).getLatitiud());
			contentValues.put("address", shopList.getArrayShop().get(i).getAddress());
			contentValues.put("email", shopList.getArrayShop().get(i).getEmail());
			contentValues.put("web", shopList.getArrayShop().get(i).getWeb());
			contentValues.put("telephone", shopList.getArrayShop().get(i).getTelephones_fix());
			contentValues.put("cellphone", shopList.getArrayShop().get(i).getTelephones_mob());
			contentValues.put("zipCode", shopList.getArrayShop().get(i).getZipCode());
			contentValues.put("city", shopList.getArrayShop().get(i).getCity());
			contentValues.put("coverSmallUrl", shopList.getArrayShop().get(i).getCoverSmallUrl());
			contentValues.put("coverBigUrl", shopList.getArrayShop().get(i).getCoverBigUrl());
			contentValues.put("status", shopList.getArrayShop().get(i).getStatus());
			contentValues.put("facebookUrl", shopList.getArrayShop().get(i).getFacebookUrl());
			contentValues.put("twitterUrl", shopList.getArrayShop().get(i).getTwitterUrl());
			db.insert("shops", null, contentValues);
		}

	}

	// Get all
	public ShopList getTodos() {
		ShopList todoList = new ShopList();

		// Name of the columns we want to select
		String[] shoplist_shop = new String[] { "shopId", "shopName", "description", "typeId", "longitude", "latitude",
				"address", "email", "web", "telephone", "cellphone", "zipCode", "city", "coverSmallUrl", "coverBigUrl",
				"status", "facebookUrl", "twitterUrl" };

		// Query the database
		Cursor c = db.query("shops", shoplist_shop, null, null, null, null, null);
		c.moveToFirst();

		// Iterate the results
		while (!c.isAfterLast()) {
			Shop shop = new Shop();
			// Take values from the DB
			shop.setShopId(c.getInt(0));
			shop.setShopName(c.getString(1));
			shop.setDesceiption(c.getString(2));
			shop.setId_type(c.getInt(3));
			shop.setLongitud(c.getString(4));
			shop.setLatitiud(c.getString(5));
			shop.setAddress(c.getString(6));
			shop.setEmail(c.getString(7));
			shop.setWeb(c.getString(8));
			shop.setTelephones_fix(c.getString(9));
			shop.setTelephones_mob(c.getString(10));
			shop.setZipCode(c.getString(11));
			shop.setCity(c.getString(12));
			shop.setCoverSmallUrl(c.getString(13));
			shop.setCoverBigUrl(c.getString(14));
			shop.setStatus(c.getString(15));
			shop.setFacebookUrl(c.getString(16));
			shop.setTwitterUrl(c.getString(17));
			
			// Add to the DB
			todoList.addShop(shop);

			// Move to the next result
			c.moveToNext();
		}
		
//		Log.i("get TODO", todoList.getArrayShop().get(0).getShopName());

		return todoList;
	}

}
