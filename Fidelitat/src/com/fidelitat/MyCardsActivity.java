package com.fidelitat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.card.GridViewExampleActivity;

public class MyCardsActivity extends Activity {

	private Button btnMyCard;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_cards);
		
		btnMyCard = (Button) findViewById(R.id.buttonMycard);
		btnMyCard.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent intent = new Intent(MyCardsActivity.this,
						GridViewExampleActivity.class);
				startActivity(intent);
			}
		});
	}
}
