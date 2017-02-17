package com.example.expenditure_manager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ShowExpActivity extends Activity {
	
	Bundle b;
	SQLiteDatabase sampleDB;
	EditText ename,eamt,edate;
	Button update;
	RadioButton op1,op2,op3,op4;
	static  String d="",e="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_exp);
		update=(Button)findViewById(R.id.button1);
		
		op1 = (RadioButton) findViewById(R.id.radioButton1);
		op2 = (RadioButton) findViewById(R.id.radioButton2);
		op3 = (RadioButton) findViewById(R.id.radioButton3);
		op4 = (RadioButton) findViewById(R.id.radioButton4);
		b=getIntent().getExtras();
		ename=(EditText)findViewById(R.id.editText1);
		eamt=(EditText)findViewById(R.id.editText2);
		edate=(EditText)findViewById(R.id.editText3);
		String sc=b.getString("sc");
		Toast.makeText(ShowExpActivity.this, sc, Toast.LENGTH_LONG).show();
		 final TextView tvr = (TextView)findViewById(R.id.textView1);
		// tvr.setText(sc);
		 final String SAMPLE_DB_NAME = "workshop";
			final String SAMPLE_TABLE_NAME = "expenditures";
			 // sampleDB = null;
			   sampleDB =  this.openOrCreateDatabase(SAMPLE_DB_NAME, MODE_PRIVATE, null);
			 sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " + SAMPLE_TABLE_NAME + " (eno INT(10), name VARCHAR,amt FLOAT(10), type VARCHAR,doe VARCHAR);");
		 //sampleDB.execSQL("INSERT INTO " + SAMPLE_TABLE_NAME + " Values (1,'Food',1000.0,'Daily','null');");
			  Cursor c1 = sampleDB.rawQuery("SELECT  * FROM " +	SAMPLE_TABLE_NAME + " where name='"+sc+"';", null);
			  
		    /* if (c1.getCount()==0)
		     {
		    	 Toast.makeText(ShowExpActivity.this, "Record not found.", Toast.LENGTH_LONG).show();
		     }*/
		     //else
			 
		     if (c1!=null)
		     {  c1.moveToFirst();
		     
		   
		    	 ename.setText(c1.getString(c1.getColumnIndex("name")));
		    	 eamt.setText(String.valueOf(c1.getFloat(c1.getColumnIndex("amt"))));
		    	 edate.setText(c1.getString(c1.getColumnIndex("doe")));
		    	
		    	 d=c1.getString(c1.getColumnIndex("type"));
		    	 if (d.equalsIgnoreCase(op1.getText().toString()))
						op1.setChecked(true);
					else if (d.equalsIgnoreCase(op2.getText().toString()))
						op2.setChecked(true);
					else if (d.equalsIgnoreCase(op3.getText().toString()))
						op3.setChecked(true);
					else if (d.equalsIgnoreCase(op4.getText().toString()))
						op4.setChecked(true);
		     }
		     else
		    	 ename.setText("no record");
               e=ename.getText().toString();
              // sampleDB.close(); 
              
			 update.setOnClickListener(new OnClickListener(){
				
				@Override
				public void onClick(View arg0) {
					//Object[] bindArgs={ename.getText().toString()};
					
						if(op1.isChecked())
							d=op1.getText().toString();
						else if(op2.isChecked())
							d=op2.getText().toString();
						else if(op3.isChecked())
							d=op3.getText().toString();
						else if(op4.isChecked())
							d=op4.getText().toString();
					
					 sampleDB.execSQL("UPDATE " +	SAMPLE_TABLE_NAME + " SET name='"+ename.getText().toString()+"', amt="+Float.parseFloat(eamt.getText().toString())+", type='"+d+"', doe='"+edate.getText().toString()+"' where name like '"+e+"';");
					Toast.makeText(ShowExpActivity.this,"UPDATE " +	SAMPLE_TABLE_NAME + " SET name='"+ename.getText().toString()+"', amt="+Float.parseFloat(eamt.getText().toString())+", type='"+d+"', doe='"+edate.getText().toString()+"' where name like '"+e+"';", Toast.LENGTH_LONG).show();
					//Toast.makeText(ShowExpActivity.this, "Updated.", Toast.LENGTH_LONG).show();
					// sampleDB.close(); 
				}});
		
		
			// sampleDB.close(); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_show_exp, menu);
		return true;
	}

}
