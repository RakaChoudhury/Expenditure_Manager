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
import android.widget.TextView;

public class IncomeActivity extends Activity {
	SQLiteDatabase sampleDB;
	private TextView tvq;
	private EditText edt;
	private EditText edtp;
	private String s;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_income);
		sampleDB=null;
		
		  tvq = (TextView)findViewById(R.id.textView3);
		 edt = (EditText)findViewById(R.id.editText1);
		 edtp = (EditText)findViewById(R.id.editText2);
		  
		Button login=(Button)findViewById(R.id.button1);
		//Button register=(Button)findViewById(R.id.button2);
		//final String firstName1;
		//final String pass1;
		//tvq.setText(s);
		final String SAMPLE_DB_NAME = "workshop";
		 final String SAMPLE_TABLE_NAME = "student";
		 sampleDB =  this.openOrCreateDatabase(SAMPLE_DB_NAME, MODE_PRIVATE, null);
			sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " + SAMPLE_TABLE_NAME + " (sno INT(10), name VARCHAR, password VARCHAR);");
			//sampleDB.execSQL("INSERT INTO " + SAMPLE_TABLE_NAME + " Values (1,'Rakesh','r');");
				        	//sampleDB.execSQL("INSERT INTO " + SAMPLE_TABLE_NAME + " Values (2,'Shashank','rr');");
				
				final Cursor c = sampleDB.rawQuery("SELECT  * FROM " +	SAMPLE_TABLE_NAME , null);
				c.moveToNext();
				
				//final String firstName,pass;
				//tvq.setText(firstName);
				
				
				login.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View arg0) {
						boolean a=false;
						c.moveToNext();
						String firstName;
						String pass;
						s = edt.getText().toString();
						try
						{
						if (c != null ) {
			        		//if  (c.moveToFirst()) {
							c.moveToFirst();
			        			do {
			        			
			        				firstName = c.getString(c.getColumnIndex("name"));
			        				 pass = c.getString(c.getColumnIndex("password"));
			        				 tvq.setText(firstName);
			        				 boolean f = s.equals(firstName);
			        				 
			    				if(f)
			    				{
			        				//tvq.setText("hello");
			        				if((edtp.getText().toString()).compareTo(pass)==0)
			        				{
			        					tvq.setText("login");
			        					a=true;
			        					break;
			        					
			        				}
			        			}
			        			}while(c.moveToNext());
			        			if (a)
			        			{	// Bundle b1=new Bundle();
			        				//b1.putString("sc", s);
			        				Intent i= new Intent(IncomeActivity.this,MenuActivity.class);
			        				//i.putExtras(b1);
		        			        startActivity(i);
			        			}
			        			else tvq.setText("No such record found");
						}
						//}
						}
						catch (SQLiteException se ) {
				        	Log.e(getClass().getSimpleName(), "Could not create or Open the database");
				        } finally {
				        	//sampleDB.execSQL("DROP TABLE " + SAMPLE_TABLE_NAME + ";");
				        		
				        }
			        		
						//tvq.setText(firstName);
			        	//this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,results));
			   		}});   	
				        	
			    
					
			/*register.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0) {
					
					 Intent i= new Intent(IncomeActivity.this,MenuActivity.class);
					
	                startActivity(i);
					
				}});*/
		
			
			sampleDB.close(); 
			}
	
				
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.income, menu);
		
		return true;
	}

					}
