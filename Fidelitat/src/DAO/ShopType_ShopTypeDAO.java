package DAO;

import MySQLiteHelper.TodoSQLiteHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.digiteix.models.TypeShop;
import com.digiteix.models.TypeShopList;

public class ShopType_ShopTypeDAO {

	private SQLiteDatabase db;
	private TodoSQLiteHelper dbHelper;

	public ShopType_ShopTypeDAO(Context context) {
		dbHelper = new TodoSQLiteHelper(context);
		db = dbHelper.getWritableDatabase();
	}

	// Close the db
	public void close() {
		db.close();
	}

	public void createTodo(TypeShopList typeShop) {

		for (int i = 0; i < typeShop.getArraytypeShop().size(); i++) {
			ContentValues contentValues = new ContentValues();
			contentValues.put("markerTypeId", typeShop.getArraytypeShop().get(i).getId());
			contentValues.put("markerTypeName", typeShop.getArraytypeShop().get(i).getName());
			contentValues.put("description", typeShop.getArraytypeShop().get(i).getDescription());
			contentValues.put("icon", typeShop.getArraytypeShop().get(i).getIcon());
			contentValues.put("status", typeShop.getArraytypeShop().get(i).getStatus());

			db.insert("type_shops", null, contentValues);
		}

	}

	// Get all
	public TypeShop getTodos(int id) {

		// Name of the columns we want to select
		String[] shopType_list = new String[] { "markerTypeId", "markerTypeName", "description", "icon", "status" };

		Cursor c = db.query("type_shops", shopType_list, "markerTypeId=" + id, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		TypeShop contactos = new TypeShop(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4));
		
		return contactos;
	}

}
