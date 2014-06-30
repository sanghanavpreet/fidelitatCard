package com.fidelitat;

import java.util.concurrent.ExecutionException;

import DAO.ShopList_ShopDAO;
import DAO.ShopType_ShopTypeDAO;
import adapter.SimpleAdapterAllshops;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.digiteix.models.Shop;
import com.digiteix.models.ShopList;
import com.digiteix.models.TypeShopList;

import connection.JsonReadShop;
import connection.JsonReadTypeShop;

public class ShopListActivity extends Activity {

	private String urlShop = "http://apps.digiteix.info:9031/tarjetes-fidelitzacio/tarjetes-fidelitzacio/api/json/shops";
	private String urlTypeShop = "http://apps.digiteix.info:9031/tarjetes-fidelitzacio/tarjetes-fidelitzacio/shops/api/json/categories?complete_url_p=0&modified_after=";
	private ShopList shopList;
	private ListView listView;
	private SimpleAdapterAllshops adapter;
	private TypeShopList typeShop;
	private ShopList_ShopDAO shoplist_shopDAO;
	private ShopType_ShopTypeDAO shopTypeList_shopTypeDAO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop_list);

		shopList = new ShopList();
		typeShop = new TypeShopList();

		listView = (ListView) findViewById(R.id.listViewAllShops);

		
		try {
			JsonReadShop task = new JsonReadShop(shopList, urlShop);
			shopList = task.execute(new String[] {}).get();

			JsonReadTypeShop task1 = new JsonReadTypeShop(typeShop, urlTypeShop);
			typeShop = task1.execute(new String[] {}).get();

			if (task.getStatus().FINISHED != null) {
				SaveShopListSQLite(shopList);
				SaveShopTypeSQLite(typeShop);

				GetAllShopsSQLite();
			} else {
				GetAllShopsSQLite();
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long arg3) {

				Shop shop = shopList.getArrayShop().get(position);

				Intent intent = new Intent(ShopListActivity.this, SelectedItemActivity.class);
				intent.putExtra("SHOPLIST", shop);
				startActivity(intent);
			}
		});

	}


	private void GetAllShopsSQLite() {
		shoplist_shopDAO = new ShopList_ShopDAO(this);
		shopList = new ShopList();
		shopList = shoplist_shopDAO.getTodos();

		
		
		
		adapter = new SimpleAdapterAllshops(this, shopList);
		listView.setAdapter(adapter);

	}

	private void SaveShopTypeSQLite(TypeShopList typeShop) {
		shopTypeList_shopTypeDAO = new ShopType_ShopTypeDAO(getApplicationContext());
		shopTypeList_shopTypeDAO.createTodo(typeShop);

	}

	private void SaveShopListSQLite(ShopList shopList) {
		shoplist_shopDAO = new ShopList_ShopDAO(getApplicationContext());
		shoplist_shopDAO.createTodo(shopList);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.shop, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_mapicon) {
			Intent i = new Intent(this, MapsActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			i.putExtra("SHOPLIST", shopList);
			Log.i("shoplist 1 size", Integer.toString(shopList.getArrayShop().size()));
			startActivity(i);
			return true;
		}
		if (id == R.id.search) {
			Intent i = new Intent(this, SearchActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
		}
		return super.onOptionsItemSelected(item);
	}
}
