package com.example.expenditure_manager;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.Time;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

public class StatusActivity extends Activity {
EditText esav,eext,eextd;
SQLiteDatabase sampleDB;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_status);
		
		eext=(EditText)findViewById(R.id.editText1);
		eextd=(EditText)findViewById(R.id.editText2);
		esav=(EditText)findViewById(R.id.editText3);
		
		final String SAMPLE_DB_NAME = "workshop";
		 final String SAMPLE_TABLE_NAME1 = "expenditures";
		 final String SAMPLE_TABLE_NAME2 = "settings";
		 final String SAMPLE_TABLE_NAME3 = "incomes";
			
		 sampleDB =  this.openOrCreateDatabase(SAMPLE_DB_NAME, MODE_PRIVATE, null);
		 sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " + SAMPLE_TABLE_NAME1 + " (eno INT(10), name VARCHAR,amt FLOAT(10), type VARCHAR,doe VARCHAR);");
		 sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " + SAMPLE_TABLE_NAME3 + " (ino INT(10), name VARCHAR,amt FLOAT(10), type VARCHAR,doi VARCHAR);");
		 sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " + SAMPLE_TABLE_NAME2 + " (name VARCHAR, val INT(10));");
		 int p=0;
		 Time t=new Time(Time.getCurrentTimezone());
		 t.setToNow();
		if((t.month==1)||(t.month==3)||(t.month==5)||(t.month==7)||(t.month==8)||(t.month==10)||(t.month==12))
			p=31;
		else if(t.month==2)
			p=28;
		else p=30;
		p=p-t.monthDay;
		Toast.makeText(StatusActivity.this, "p="+p, Toast.LENGTH_LONG).show();
		 final Cursor c = sampleDB.rawQuery("SELECT  * FROM " +	SAMPLE_TABLE_NAME2 +" where name like 'Savings';" , null);
			c.moveToNext();
			int a=c.getInt(c.getColumnIndex("val"));
			
			float b=0;
			final Cursor c1 = sampleDB.rawQuery("SELECT * FROM incomes;" , null);
			c1.moveToNext();
			 if(c1!=null)
			 {	 c1.moveToFirst();
			    do{
			   b= b+ c1.getFloat(2);
			   }while(c1.moveToNext());
			 }
			
			 float d=0;
			 final Cursor c2 = sampleDB.rawQuery("SELECT  * FROM " +	SAMPLE_TABLE_NAME1 +" where type not like 'Yearly';" , null);
			 c2.moveToNext();
			 if(c2!=null)
			 {	 c2.moveToFirst();
			    do{
			   d= d+ c2.getFloat(2);
			   }while(c2.moveToNext());
			 }
			 float sav=a*b/100;
				esav.setText(String.valueOf(a*b/100));
				eext.setText(String.valueOf(b-d-sav));
				eextd.setText(String.valueOf((b-d-sav)/p));
			//esav.setText(String.valueOf(a));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_status, menu);
		return true;
	}

}
