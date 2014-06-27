package com.fidelitat;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.card.GridviewSelectedItemAdapter;
import com.digiteix.models.Shop;

public class SelectedItemActivity extends Activity {

	private Shop shop;
	private ImageView imgType, imgShopoLogo;
	private TextView txtShopName, txtDescripcion;
	private String phoneFix, phonMobil, email;
	private int select = 0;
	private GridView gridView;
	private GridviewSelectedItemAdapter mAdapter;
	private ArrayList<Integer> listConImg;
	private ArrayList<String> listContactes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selected_item);

		shop = new Shop();

		Object extras = getIntent().getSerializableExtra("SHOPLIST");
		try {
			if (extras != null) {

				shop = (Shop) extras;

				prepareList();

				if (!shop.getEmail().isEmpty()) {
					listContactes.add("email");
					listConImg.add(R.drawable.ic_message);
				}
				if (!shop.getFacebookUrl().isEmpty()) {
					listContactes.add("facebook");
					listConImg.add(R.drawable.ic_facebookk);
				}
				if (!shop.getTelephones_fix().isEmpty() || !shop.getTelephones_mob().isEmpty()) {
					listContactes.add("telephone");
					listConImg.add(R.drawable.ic_menu_call);
				}
				if (!shop.getTwitterUrl().isEmpty()) {
					listContactes.add("twitter");
					listConImg.add(R.drawable.ic_twitterr);
				}
				if (!shop.getWeb().isEmpty()) {
					listContactes.add("web");
					listConImg.add(R.drawable.ic_menu_search);
				}
				if (!shop.getLatitiud().isEmpty() && !shop.getLongitud().isEmpty()) {
					listContactes.add("map");
					listConImg.add(R.drawable.ic_map_icon);
				}
				gridView.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
						if (listContactes.get(position) == "facebook") {
							Facebook();
						}
						if (listContactes.get(position) == "email") {
							Email();
						}
						if (listContactes.get(position) == "telephone") {
							TelePhone();
						}
						if (listContactes.get(position) == "twitter") {
							Twitter();
						}
						if (listContactes.get(position) == "web") {
							Web();
						}
						if (listContactes.get(position) == "map") {

							Intent i = new Intent(getApplicationContext(), MapsActivity.class);
							Toast.makeText(getApplicationContext(), "extra dins" + shop.getDesceiption() +" - " +  shop.getLongitud(), Toast.LENGTH_SHORT).show();
							i.putExtra("SHOP", shop);
							startActivity(i);
						}

					}

				});

			} else {
				Intent i = new Intent(this, ShopListActivity.class);
				startActivity(i);
				Toast.makeText(getApplicationContext(), "ERROR...", Toast.LENGTH_SHORT).show();
			}
		} catch (NullPointerException e) {
			Intent i = new Intent(this, ShopListActivity.class);
			startActivity(i);
		}
	}

	private void Email() {
		email = shop.getEmail();

		Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
		intent.putExtra("subject", "");
		startActivity(intent);

	}

	private void prepareList() {

		listConImg = new ArrayList<Integer>();
		listContactes = new ArrayList<String>();

		imgType = (ImageView) findViewById(R.id.imgSelectShopType);
		imgShopoLogo = (ImageView) findViewById(R.id.imgSelectShopLogo);
		txtShopName = (TextView) findViewById(R.id.txtSelectShopName);
		txtDescripcion = (TextView) findViewById(R.id.txtSelectShopDescripcion);

		mAdapter = new GridviewSelectedItemAdapter(this, listContactes, listConImg);
		gridView = (GridView) findViewById(R.id.SelectShopGridView1);
		gridView.setAdapter(mAdapter);

		txtShopName.setText(shop.getShopName() + " - " + shop.getTelephones_fix() + " " + shop.getTelephones_mob());
//		txtDescripcion.setText(shop.getDesceiption());
		txtDescripcion.setText(shop.getLatitiud() + " - " +  shop.getLongitud());
	}

	private void TelePhone() {
		phoneFix = shop.getTelephones_fix();
		phonMobil = shop.getTelephones_mob();
		//Log.i("telephone", "fix: " + phoneFix + "  mob: " + phonMobil);

		if (phoneFix.length() >= 9 && phonMobil.length() >= 9) {
			Log.i("telephone fix mobil", "fix: " + phoneFix + "  mob: " + phonMobil);
			AlertDialog.Builder alert = new AlertDialog.Builder(SelectedItemActivity.this);
			CharSequence[] array = { "Telèfon fix", "Telèfon mòbil" };
			alert.setTitle("Seleccionar Telèfon:").setSingleChoiceItems(array, 0,

			new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					select = which;
				}

			}).setIcon(R.drawable.ic_menu_call).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id) {
					if (select == 0) {
						Telephone(phoneFix);
					}
					if (select == 1) {
						Telephone(phonMobil);
					}
				}
			}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id) {

				}
			});
			AlertDialog alertDialog = alert.create();
			alertDialog.show();

		} else {
			if (phoneFix.length() >= 9) {
				Log.i("telephone only fix", "fix: " + phoneFix);
				Telephone(phoneFix);
			}
			if (phonMobil.length() >= 9) {
				Log.i("telephone only mobil", "mob: " + phonMobil);
				Telephone(phonMobil);
			}
		}

	}

	private void Telephone(String phone) {
		String uri = "tel:" + phone;
		Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(uri));
		startActivity(callIntent);

	}

	private void Web() {
		String web = shop.getWeb();
		Intent intent;
		if (web.startsWith("http")) {
			intent = new Intent(Intent.ACTION_VIEW, Uri.parse(web));
		} else {
			intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + web));
		}
		startActivity(intent);

	}

	private void Facebook() {

		String urlFb = shop.getFacebookUrl();

		try {
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/" + urlFb));
			startActivity(intent);
		} catch (Exception e) {
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/pages/" + urlFb)));
		}

	}

	private void Twitter() {

		String twitter = shop.getTwitterUrl();

		try {
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + twitter));
			startActivity(intent);

		} catch (Exception e) {
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/#!/" + twitter)));
		}

	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.my_card, menu);
//		return true;
//	}
//
//	public boolean onOptionsItemSelected(MenuItem item) {
//		int id = item.getItemId();
//		if (id == R.id.action_mapicon) {
//			Intent i = new Intent(this, MapsActivity.class);
//			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			startActivity(i);
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
}
