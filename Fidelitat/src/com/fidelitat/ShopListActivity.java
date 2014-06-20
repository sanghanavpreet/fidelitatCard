package com.fidelitat;

import java.util.concurrent.ExecutionException;

import adapter.SimpleAdapterAllshops;
import android.app.Activity;
import android.content.Context;
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

import connection.JsonReadShop;

public class ShopListActivity extends Activity {

	private String url = "http://apps.digiteix.info:9031/tarjetes-fidelitzacio/tarjetes-fidelitzacio/api/json/shops";
	private ShopList shopList;
	private ListView listView;
	private SimpleAdapterAllshops adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop_list);

		shopList = new ShopList();

		listView = (ListView) findViewById(R.id.listViewAllShops);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long arg3) {
				
				Shop shop = shopList.getArrayShop().get(position);
				

				Intent intent = new Intent(ShopListActivity.this,
						SelectedItemActivity.class);
				intent.putExtra("SHOPLIST", shop);
				startActivity(intent);
			}
		});

		try {
			JsonReadShop task = new JsonReadShop(shopList, url);
			shopList = task.execute(new String[] {}).get();

			Log.i("shoppppp", Integer.toString(shopList.getArrayShop().size()));

			if (task.getStatus().FINISHED != null) {
				adapter = new SimpleAdapterAllshops(getApplicationContext(),
						shopList.getArrayShop());
				listView.setAdapter(adapter);
			}

		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		;

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
