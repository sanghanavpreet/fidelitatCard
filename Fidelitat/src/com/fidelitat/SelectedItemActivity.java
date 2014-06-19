package com.fidelitat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.digiteix.models.ShopList;

public class SelectedItemActivity extends Activity {

	private ShopList shoplist;
	private ImageView imgFacebook, imgTelephone, imgType, imgShopoLogo;
	private TextView txtShopName, txtDescripcion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selected_item);

		imgFacebook = (ImageView) findViewById(R.id.ImgShopFacebook);
		imgTelephone = (ImageView) findViewById(R.id.imgShopTelephone);
		imgType = (ImageView) findViewById(R.id.imgShopType);
		imgShopoLogo = (ImageView) findViewById(R.id.imgShopLogo);
		txtShopName = (TextView) findViewById(R.id.txtShopName);
		txtDescripcion = (TextView) findViewById(R.id.txtShopDescripcionList);
		
		
		shoplist = new ShopList();
		Object extras = getIntent().getSerializableExtra("SHOPLIST");
		if (extras != null) {

			shoplist = (ShopList) extras;
			
			SetInfo(shoplist);

		}

	}

	private void SetInfo(ShopList shoplist) {
		
		txtShopName.setText(shoplist.getArrayShop().get(0).getShopName());
		txtDescripcion.setText(shoplist.getArrayShop().get(0).getDesceiption());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.my_card, menu);
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
		return super.onOptionsItemSelected(item);
	}
}
