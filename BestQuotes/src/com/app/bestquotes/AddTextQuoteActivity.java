package com.app.bestquotes;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddTextQuoteActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_text_quote);
		  Button b_save=(Button)findViewById(R.id.b_save);
		 final EditText ed=(EditText)findViewById(R.id.et_save);
		  
		  
		 final DatabaseHandler db=new DatabaseHandler(getApplicationContext());
		  List<Categories> catsList=db.getAllCategories();
		  ArrayList<String>catss=new ArrayList<String>();
			for(Categories cat:catsList)
			{
				//Log.d("Categories",cat.getCategory());
				catss.add(cat.getCategory());
			}
			
		  
		  final Spinner spinn = (Spinner) findViewById(R.id.spin_text_save);
		    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
		            this, android.R.layout.simple_spinner_item, catss);
		    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    spinn.setAdapter(adapter); 
		    
		 
		  b_save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(ed.getText().toString()!="")
				{				
				   String cattid=spinn.getSelectedItem().toString();
				    Log.d("Spinner Selected cat", cattid);
				  final  int cid=db.getCategoryID(cattid);
				  Log.d("Spinner Selected cat id", " "+ cid);
				
				Quotes quote=new Quotes();
					
				
				quote.setCAT_ID(cid);
				quote.setQuote(ed.getText().toString());
				quote.setTYPE(0);
				
				db.addQuote(quote);
				
				
				
				Toast.makeText(AddTextQuoteActivity.this, "Quote Added Success fully", Toast.LENGTH_LONG).show();
				Log.d("Quote Added" , "Quote Added Success fully" + ed.getText().toString() +"category ID" + cid);
				Log.d("Quotes in this category " , " tottal quotes " + db.getQuotesCount(cid, 0));
				ed.setText("");
				}else
				{
					Toast.makeText(AddTextQuoteActivity.this, "You can not add empty Quotes.", Toast.LENGTH_LONG).show();
					
				}
				
			}
		});
		  
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.text_detail_activity, menu);
		return true;
	}

}
