package com.fidelitat;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.card.GridviewSelectedItemAdapter;
import com.digiteix.models.Shop;

public class SelectedItemActivity extends Activity {

	private Shop shop;
	private ImageView imgType, imgShopoLogo;
	private TextView txtShopName, txtDescripcion;
	private ImageButton imgFacebook, imgTelephone;
	private String phoneFix, phonMobil;
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

				gridView.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
						Toast.makeText(SelectedItemActivity.this, " ff" + listContactes.get(position), Toast.LENGTH_SHORT).show();
					
					
					
					}
				});

				if (!shop.getEmail().isEmpty()) {
					listContactes.add("email");
					listConImg.add(R.drawable.ic_message);

					imgFacebook.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {

							Email();
						}
					});
				}
				if (!shop.getFacebookUrl().isEmpty()) {
					listContactes.add("facebook");
					listConImg.add(R.drawable.ic_facebookk);

					imgFacebook.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {

							Facebook();
						}
					});
				}
				if (!shop.getTelephones_fix().isEmpty() && !shop.getTelephones_mob().isEmpty()) {
					listContactes.add("teleohone");
					listConImg.add(R.drawable.ic_menu_call);

					imgTelephone.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {

							TelePhone();

						}
					});
				}
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
		// TODO Auto-generated method stub

	}

	private void prepareList() {

		listConImg = new ArrayList<Integer>();
		listContactes = new ArrayList<String>();

		imgFacebook = (ImageButton) findViewById(R.id.ImgSelectShopFacebook);
		imgTelephone = (ImageButton) findViewById(R.id.imgSelectShopTelephone);
		imgType = (ImageView) findViewById(R.id.imgSelectShopType);
		imgShopoLogo = (ImageView) findViewById(R.id.imgSelectShopLogo);
		txtShopName = (TextView) findViewById(R.id.txtSelectShopName);
		txtDescripcion = (TextView) findViewById(R.id.txtSelectShopDescripcion);

		mAdapter = new GridviewSelectedItemAdapter(this, listContactes, listConImg);
		gridView = (GridView) findViewById(R.id.SelectShopGridView1);
		gridView.setAdapter(mAdapter);

		txtShopName.setText(shop.getShopName());
		txtDescripcion.setText(shop.getDesceiption());

	}

	private void TelePhone() {
		phoneFix = shop.getTelephones_fix();
		phonMobil = shop.getTelephones_mob();
		Log.i("telephone", "fix: " + phoneFix + "  mob: " + phonMobil);

		if (phoneFix.length() >= 9 && phonMobil.length() >= 9) {

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
				Telephone(phoneFix);
			}
			if (phonMobil.length() >= 9) {
				Telephone(phonMobil);
			}
		}

	}

	private void Telephone(String phone) {
		String uri = "tel:" + phone;
		Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(uri));
		startActivity(callIntent);

		Toast.makeText(getApplicationContext(), "has seleccionat " + phone, Toast.LENGTH_LONG).show();

	}

	private void Facebook() {
		String urlFb = "fb://page/" + "159787794034679";
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(urlFb));

		PackageManager packageManager = getPackageManager();
		List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
		if (list.size() == 0) {
			String urlBrowser = "https://www.facebook.com/pages/" + "159787794034679";
			intent.setData(Uri.parse(urlBrowser));
		}
		startActivity(intent);

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
