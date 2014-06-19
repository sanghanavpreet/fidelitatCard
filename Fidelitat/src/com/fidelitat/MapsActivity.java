package com.fidelitat;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public class MapsActivity extends Activity implements OnClickListener,
		OnItemSelectedListener {
	private LocationManager locationManager;
	private LocationProvider locationProvider;
	private GoogleMap mapa;
	private LatLng latLng = null;
	private boolean trobat = false;
	LatLngBounds.Builder builder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);
		
		
		mapa = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		mapa.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {

			@Override
			public void onMyLocationChange(Location location) {
				double latitude = location.getLatitude();
				double longitude = location.getLongitude();
				latLng = new LatLng(latitude, longitude);
				if (!trobat) {
					mapa.animateCamera(
							CameraUpdateFactory.newLatLngZoom(latLng, 14),
							2000, null);
					trobat = true;
				}
			}
		});

		configurarMapa();
	}

	private void configurarMapa() {

		if (mapa != null) {

			mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			mapa.getUiSettings().setCompassEnabled(true);
			mapa.setMyLocationEnabled(true);

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.maps, menu);
		return true;
	}

	public void inicialitarLocationProvider() {
		locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);

		locationProvider = locationManager.getProvider(getProviderName());
	}

	private String getProviderName() {

		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setCostAllowed(false);
		return locationManager.getBestProvider(criteria, true);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {

	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {

	}

	

	

}
