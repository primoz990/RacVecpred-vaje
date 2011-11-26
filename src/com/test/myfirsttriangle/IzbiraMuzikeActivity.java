package com.test.myfirsttriangle;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class IzbiraMuzikeActivity extends Activity implements OnClickListener{

	Button potrdi;
	Spinner spin, spinO, spinT;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.izbiramuzike);
		
		spin= (Spinner) findViewById(R.id.spinner1);
		spinO= (Spinner) findViewById(R.id.spinnerObjekt);
		spinT= (Spinner) findViewById(R.id.spinnerTextura);
		
		potrdi = (Button) findViewById(R.id.button1);
		potrdi.setOnClickListener(this);
		
		
		
	}
	
	
	@Override
	protected void onStart() 
	{
		super.onStart();
		
		String muzike[] = {"01 - Nocturne", "02 - Paganini", "03 - Piano Sonata", "04 - Prelude BWV", "05 - Waltz"};
		ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, muzike);
		spin.setAdapter(adapter);
		
		String objekti[] = {"01 - Diamant", "02 - Vaza", "03 - Miza", "04 - Sveca"};
		ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, objekti);
		spinO.setAdapter(adapter1);
		
		String texture[] = {"01 - Badlogic", "02 - Rdeca", "03 - Listje"};
		ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, texture);
		spinT.setAdapter(adapter2);
		
	}
	
	public void onClick(View v) 
	{
		this.finish();
		
		izbira a = new izbira((int)spin.getSelectedItemId(), (int)spinO.getSelectedItemId(), (int)spinT.getSelectedItemId());
		Intent dodajPrAct = new Intent(this, MyFirstTriangleAndroid.class);
		this.startActivity(dodajPrAct);
		
		
	}

}
