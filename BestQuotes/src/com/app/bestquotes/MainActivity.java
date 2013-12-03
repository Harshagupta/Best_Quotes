package com.app.bestquotes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends Activity {

	private String TAG=MainActivity.class.getCanonicalName();
	DatabaseHandler db;
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		Log.d(TAG, "from ondestroy");
		db.close();
		Log.d(TAG, "DB Closed");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
		 db=new DatabaseHandler(MainActivity.this);
		List<Categories> cats=db.getAllCategories();
		
		ArrayList<String>catss=new ArrayList<String>();
		for(Categories cat:cats)
		{
			//Log.d("Categories",cat.getCategory());
			catss.add(cat.getCategory());
		}
		
		
		final Spinner spinner = (Spinner) findViewById(R.id.spin_cat_main);
		//populating spinner 2
		final Spinner spin_type = (Spinner) findViewById(R.id.spin_type_main);		
		    ArrayAdapter<CharSequence> aa = ArrayAdapter.createFromResource(
		            this, R.array.spin_type, android.R.layout.simple_spinner_item);
		    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    spin_type.setAdapter(aa);

		    
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
	            this, android.R.layout.simple_spinner_item, catss);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner.setAdapter(adapter);

	    
	    

		
	    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
	    	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
	            String selected = parent.getItemAtPosition(pos).toString();
	            Log.d(TAG, selected+ pos);
	        }

	        public void onNothingSelected(AdapterView parent) {
	            // Do nothing.
	        }
		});
	    
	    
	    Button b_read=(Button)findViewById(R.id.button_view);
	    b_read.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				//Log.d("Main Activity", "Before If");
				Log.d("Selected quote type", "  "+spin_type.getSelectedItemPosition());
				if(spin_type.getSelectedItemPosition()==0)
				{
					
					Intent intent=new Intent(MainActivity.this,TextDetailActivity.class);
					intent.putExtra("cat", spinner.getSelectedItem().toString());
					startActivity(intent);
				}
				if(spin_type.getSelectedItemPosition()==1)
				{
					//Log.d("Main Activity", "Before Intent");
					Intent intent=new Intent(MainActivity.this,GridImage.class);
					intent.putExtra("cat", spinner.getSelectedItem().toString());
					startActivity(intent);
				}
				
			}
		});
		
	    Button b_add=(Button)findViewById(R.id.b_add);
	    b_add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				//Log.d("Main Activity", "Before If");
				Log.d("Selected quote type", "  "+spin_type.getSelectedItemPosition());
				if(spin_type.getSelectedItemPosition()==0)
				{
					
					Intent intent=new Intent(MainActivity.this,AddTextQuoteActivity.class);
					intent.putExtra("cat", spinner.getSelectedItem().toString());
					startActivity(intent);
				}
				
				
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	 public  void CopyAssets(SQLiteDatabase db) {
		 //this function is copying our created database into sd card of cellphone(if database is very heavy.)
		    AssetManager assetManager = getApplicationContext().getAssets();
		    String[] folders={"Arts","Attitude","Birthday","Business","Friendship","Group","Happiness","Hardwork","Nature"};
		    String[] files=null;
		   for( String s : folders)
		   {
		    try {
		    	//DatabaseHandler db=new DatabaseHandler(context);
		    	
		        files = assetManager.list(s);
		    } catch (IOException e) {
		        Log.e("tag", e.getMessage());
		    }

		    for(String filename : files) {
		        Log.d("File name => ",filename);
		        InputStream in = null;
		        OutputStream out = null;
		        try {
		          in = assetManager.open(s+"/"+filename);   // if files resides inside the "Files" directory itself
		          out = new FileOutputStream(Environment.getExternalStorageDirectory().toString()+s+"/" + filename);
		          copyFile(in, out);
		          in.close();
		          in = null;
		          out.flush();
		          out.close();
		          out = null;
		        } catch(Exception e) {
		            Log.e("tag", e.getMessage());
		        }
		    }
		   }
		}
		private void copyFile(InputStream in, OutputStream out) throws IOException {
		    byte[] buffer = new byte[1024];
		    int read;
		    while((read = in.read(buffer)) != -1){
		      out.write(buffer, 0, read);
		    }
		}
	
	

}
