package com.app.bestquotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class TextDetailActivity extends Activity {

	private String TAG=TextDetailActivity.class.getCanonicalName();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_text);
		Intent intent=getIntent();
		String cat=intent.getStringExtra("cat");
		String [] ss={cat};
		final Spinner spin=(Spinner)findViewById(R.id.spinner1);
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ss);
	    aa.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );

	  //	ArrayAdapter<String>aa=new ArrayAdapter<String>(null, 0,ss);
		//aa.add(cat);
		spin.setAdapter(aa);
		
	final TextView txt=(TextView)findViewById(R.id.txt_quote);
		
		//pass your quote id as per req
	Log.d(TAG, "before set text");
final	DatabaseHandler db =new DatabaseHandler(getApplicationContext());
	
		txt.setText(db.getRandomTextQuote(db.getCategoryID(spin.getSelectedItem().toString())).getQuote());
		Log.d(TAG, "after set text" + spin.getSelectedItemPosition());
		Button bswitch=(Button)findViewById(R.id.button_switch);
		bswitch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				txt.setText(db.getRandomTextQuote(db.getCategoryID(spin.getSelectedItem().toString())).getQuote());
				
				
			}
		});
		
	}
	
	
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection either send via sms and email.
	    switch (item.getItemId()) {
	    case R.id.sms:
	        sendSMS();
	        return true;
	    case R.id.email:
	    	sendEmail();
	    	return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}

	private void sendSMS()
	{
		TextView tv=(TextView)findViewById(R.id.txt_quote);
		Intent intent =new Intent(this,TextSMSActivity.class);
		intent.putExtra("SMS",tv.getText().toString());
		Log.d(TAG,tv.getText().toString());
		startActivity(intent);
		
	}

	private void sendEmail()
	{
		TextView tv=(TextView)findViewById(R.id.txt_quote);
		Intent intent =new Intent(this,TextEmailActivity.class);
		intent.putExtra("E-mail",tv.getText().toString());
		Log.d(TAG,tv.getText().toString());
		startActivity(intent);
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.text_detail_activity, menu);
		return true;
	}

}
