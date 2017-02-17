package com.example.expenditure_manager;

import java.util.ArrayList;

import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

 
public class MenuActivity extends Activity {
	 ArrayList<String> listItems;
	 
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_menu);
	        
	        Bundle b=getIntent().getExtras();
			listItems = new ArrayList<String>();
			listItems.add("Add Expenditure");
			listItems.add("Add Income");
			listItems.add("Show Status");
			listItems.add("Show Account Information");
			listItems.add("Manage Groups");
			listItems.add("Settings");

			
			final ArrayAdapter<String> adap=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems);
			final ListView lv=(ListView)findViewById(R.id.listView1);
			
			lv.setAdapter(adap);
		lv.setOnItemClickListener(new OnItemClickListener(){
			
				@Override
				public void onItemClick(AdapterView<?> p, View arg1, int pos,
						long arg3) {
					// TODO Auto-generated method stub
					Intent i;
					 Toast.makeText(MenuActivity.this,listItems.get(pos).toString()+ " Clicked", Toast.LENGTH_LONG).show();
					 if(listItems.get(pos).toString().equalsIgnoreCase("Add Expenditure"))
					 {  i= new Intent(MenuActivity.this,ExpActivity.class);
 			        startActivity(i);
					 }
					 else if(listItems.get(pos).toString().equalsIgnoreCase("Add Income"))
					 {  i= new Intent(MenuActivity.this,IncActivity.class);
 			        startActivity(i);
					 }
					 else if(listItems.get(pos).toString().equalsIgnoreCase("Show Status"))
					 {  i= new Intent(MenuActivity.this,StatusActivity.class);
 			        startActivity(i);
					 }
					 else if(listItems.get(pos).toString().equalsIgnoreCase("Show Account Information"))
					 {  i= new Intent(MenuActivity.this,AccountActivity.class);
 			        startActivity(i);
					 }
					 else if(listItems.get(pos).toString().equalsIgnoreCase("Settings"))
					 {  i= new Intent(MenuActivity.this,SettingsActivity.class);
 			        startActivity(i);
					 }
					 else if(listItems.get(pos).toString().equalsIgnoreCase("Manage Groups"))
					 {  i= new Intent(MenuActivity.this,GroupActivity.class);
 			        startActivity(i);
					 }
					 
				}});
			
	 
	     
    }
}


