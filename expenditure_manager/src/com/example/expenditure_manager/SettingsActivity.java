package com.example.expenditure_manager;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends Activity {
	SQLiteDatabase sampleDB;
	EditText ethr;
	Button update;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		update=(Button)findViewById(R.id.button1);
		ethr=(EditText)findViewById(R.id.editText1);
		sampleDB=null;
		final String SAMPLE_DB_NAME = "workshop";
		 final String SAMPLE_TABLE_NAME = "settings";
		 sampleDB =  this.openOrCreateDatabase(SAMPLE_DB_NAME, MODE_PRIVATE, null);
			sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " + SAMPLE_TABLE_NAME + " (name VARCHAR, val INT(10));");
			//sampleDB.execSQL("INSERT INTO " + SAMPLE_TABLE_NAME + " Values ('Savings','10');");
				        	//sampleDB.execSQL("INSERT INTO " + SAMPLE_TABLE_NAME + " Values (2,'Shashank','rr');");
				
				final Cursor c = sampleDB.rawQuery("SELECT  * FROM " +	SAMPLE_TABLE_NAME , null);
				c.moveToNext();
				ethr.setText(String.valueOf(c.getInt(c.getColumnIndex("val"))));
				
				 update.setOnClickListener(new OnClickListener(){
						
						@Override
						public void onClick(View arg0) {
							
							 sampleDB.execSQL("UPDATE " +	SAMPLE_TABLE_NAME + " SET  val="+Integer.parseInt(ethr.getText().toString())+" where name like 'Savings';");
							Toast.makeText(SettingsActivity.this,"UPDATE " +	SAMPLE_TABLE_NAME + " SET  val="+Integer.parseInt(ethr.getText().toString())+" where name like 'Savings';", Toast.LENGTH_LONG).show();
							//Toast.makeText(ShowExpActivity.this, "Updated.", Toast.LENGTH_LONG).show();
							// sampleDB.close(); 
						}});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_settings, menu);
		return true;
	}

}
