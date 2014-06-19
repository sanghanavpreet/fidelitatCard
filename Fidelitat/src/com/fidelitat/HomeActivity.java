package com.fidelitat;


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
import android.view.View;
import android.widget.ImageButton;

public class HomeActivity extends Activity {

	private ImageButton btnMeu;
	private ImageButton btnTots;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		//getActionBar().
		
		btnMeu = (ImageButton) findViewById(R.id.llistarMeus);
		btnTots = (ImageButton) findViewById(R.id.llistarTots);

		btnTots.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent intent = new Intent(HomeActivity.this,
						ShopListActivity.class);
				startActivity(intent);
			}
		});

		btnMeu.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent i = new Intent(HomeActivity.this,
						MyCardsActivity.class);
				startActivity(i);
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	


}
