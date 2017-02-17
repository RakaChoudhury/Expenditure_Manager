package com.example.expenditure_manager;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.Toast;


public class IncActivity extends Activity {
	Button b;
	
	ArrayList<String> listItems;
	SQLiteDatabase sampleDB;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exp);
		b=(Button)findViewById(R.id.button1);
		final String SAMPLE_DB_NAME = "workshop";
		final String SAMPLE_TABLE_NAME = "incomes";
			
		 sampleDB =  this.openOrCreateDatabase(SAMPLE_DB_NAME, MODE_PRIVATE, null);
		 sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " + SAMPLE_TABLE_NAME + " (ino INT(10), name VARCHAR,amt FLOAT(10), type VARCHAR,doi VARCHAR);");
	// sampleDB.execSQL("INSERT INTO " + SAMPLE_TABLE_NAME + " Values (1,'Salary',80000.0,'Yearly','null');");
		 b.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				
				// Intent i= new Intent(IncActivity.this,AddExpenditureActivity.class);
				
	           // startActivity(i);
			
			}});
	
		listItems = new ArrayList<String>();
		final ArrayAdapter<String> adap=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems);
		final ListView lv=(ListView)findViewById(R.id.listView1);
		
	     final Cursor c1 = sampleDB.rawQuery("SELECT  * FROM " +	SAMPLE_TABLE_NAME , null);
	     if (c1.getCount()==0)
	     {
	    	 listItems.add("No record");
	     }
	     else
	   //  if (c1!=null)
	     {
				c1.moveToFirst();
				do{
		        listItems.add((c1.getString(c1.getColumnIndex("name")))+"   "+(c1.getFloat(c1.getColumnIndex("amt"))));
				}while(c1.moveToNext());
	     }

		lv.setAdapter(adap);
	lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> p, View arg1, int pos,
					long arg3) {
				// TODO Auto-generated method stub
				 Toast.makeText(IncActivity.this,listItems.get(pos).toString()+ " Clicked", Toast.LENGTH_LONG).show();
				// tv.setText(listItems.get(pos).toString());
			}});
		
			
			

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_exp, menu);
		return true;
	}

}

