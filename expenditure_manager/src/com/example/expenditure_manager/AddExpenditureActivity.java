package com.example.expenditure_manager;

import android.os.Bundle;
import android.app.Activity;
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

public class AddExpenditureActivity extends Activity {

	Button add;
	EditText ename,eamt,edate;
	SQLiteDatabase sampleDB;
	 int x;
 RadioButton op1,op2,op3,op4;
	  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_expenditure);
	
	//	sampleDB = null;
		op1 = (RadioButton) findViewById(R.id.radioButton1);
		op2 = (RadioButton) findViewById(R.id.radioButton2);
		op3 = (RadioButton) findViewById(R.id.radioButton3);
		op4 = (RadioButton) findViewById(R.id.radioButton4);
		ename=(EditText)findViewById(R.id.editText1);
		eamt=(EditText)findViewById(R.id.editText2);
		edate=(EditText)findViewById(R.id.editText3);
		add=(Button)findViewById(R.id.button1);
		//ename.setText("declared");
		
		final String SAMPLE_DB_NAME = "workshop";
		 final String SAMPLE_TABLE_NAME = "expenditures";
			
		 sampleDB =  this.openOrCreateDatabase(SAMPLE_DB_NAME, MODE_PRIVATE, null);
		 sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " + SAMPLE_TABLE_NAME + " (eno INT(10), name VARCHAR,amt FLOAT(10), type VARCHAR,doe VARCHAR);");
				        	
				        	//sampleDB.execSQL("INSERT INTO " + SAMPLE_TABLE_NAME + " Values (1,'Food',1000.0,'Daily','null');");
				        	//sampleDB.execSQL("INSERT INTO " + SAMPLE_TABLE_NAME + " Values (2,'Shashank','rr');");
				        	
				
				 
				final Cursor c1 = sampleDB.rawQuery("SELECT  eno FROM " + SAMPLE_TABLE_NAME , null);
				if (c1!=null)
				{
					c1.moveToLast();
					 x = c1.getInt(c1.getColumnIndex("eno"));
					 x=x+1;
				}
				
				
				
				add.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View arg0) {
						
						 Toast.makeText(AddExpenditureActivity.this," Clicked", Toast.LENGTH_LONG).show();
						
						String a,c,d;
						float b;
						
						a = ename.getText().toString();
						b = Float.parseFloat(eamt.getText().toString());
						c = edate.getText().toString();
						d="";
						if (op1.isChecked())
							d="Daily";
						else if (op3.isChecked())
							d="Fortnightly";
						else if (op2.isChecked())
							d="Monthly";
						else if (op2.isChecked())
							d="Yearly";
						//ename.setText("here"+a+b+c+d+x);
			        sampleDB.execSQL("INSERT INTO " + SAMPLE_TABLE_NAME + " Values ("+x+",'"+a+"',"+b+",'"+d+"','"+c+"');");
                   // ename.setText("INSERT INTO " + SAMPLE_TABLE_NAME + " Values ("+x+",'"+a+"',"+b+",'"+d+"','"+c+"');");
			        ename.setText("done");	
						//tvq.setText(firstName);
			        	//this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,results));
			   		}});   	
			   		
	}
				

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add_expenditure, menu);
		return true;
	}

}
