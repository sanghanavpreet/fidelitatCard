package com.card;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.fidelitat.MapsActivity;
import com.fidelitat.R;
import com.fidelitat.SearchActivity;

public class GridViewExampleActivity extends Activity {
	/** Called when the activity is first created. */

	private GridviewAdapter mAdapter;
	private ArrayList<String> totalPuntslist;
	private ArrayList<Integer> listpunts;
	private int numTotal = 11;
	private int numcolum = 3;
	private int punt = 8;

	private GridView gridView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview);

		prepareList();

		// preparem arraylist i passem a adapter class
		mAdapter = new GridviewAdapter(this, totalPuntslist, listpunts);

		// Seleccionem custom adapter per gridview
		gridView = (GridView) findViewById(R.id.gridView_Card);

		// indicem num de col que volem donar gridview
		gridView.setNumColumns(numcolum);
		gridView.setAdapter(mAdapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				Toast.makeText(GridViewExampleActivity.this, " ff",
						Toast.LENGTH_SHORT).show();
			}
		});

	}

	/**
	 * metode que dibuxarà quants punt tenim que quants no les punts que tenim
	 * li posarem un sagel i resta quedi blancs
	 */
	public void prepareList() {
		
		// dibuxem quadrats
		totalPuntslist = new ArrayList<String>();
		for (int i = 1; i <= numTotal; i++) {
			totalPuntslist.add("");
		}

		// dibuxem les punts que te client
		listpunts = new ArrayList<Integer>();
		for (int i = 1; i <= punt; i++) {

			listpunts.add(R.drawable.logo_apple);

		}
		punt++;
		for (int i = punt; i <= numTotal; i++) {

			String mDrawableName = "number_" + i;
			int resID = getResources().getIdentifier(mDrawableName, "drawable",
					getPackageName());
			listpunts.add(resID);

			// listpunts.add(punt);

			// listpunts.add(a);
		}

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.my_card, menu);
		return true;
	}

}