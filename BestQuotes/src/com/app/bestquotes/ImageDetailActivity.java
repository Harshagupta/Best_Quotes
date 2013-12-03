package com.app.bestquotes;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class ImageDetailActivity extends Activity {
	
	private static int RESULT_LOAD_IMAGE = 2;
	private String TAG=ImageDetailActivity.class.getCanonicalName();
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_image);
        Intent intent=getIntent();
        String file=intent.getStringExtra("file");
        String cat=intent.getStringExtra("cat");
               
        Log.d("ImageDetail on create", "file "+ file);
       
       
    	InputStream is = null;
    	try {
    		AssetManager assetManager = getAssets();
    		int count=0;
    		
    	  is = this.getResources().getAssets().open(file);
    	  
    	} catch (IOException e) {
    	  Log.w("EL", e);
    	}
    Log.d("From Image Detail","Before Setting Image");


    	Bitmap image = BitmapFactory.decodeStream(is);

    	ImageView ib2 = (ImageView) findViewById( R.id.imgView);
    	ib2.setImageBitmap( image);
    	     
    }
     
     
   
     
   
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.random_image, menu);
		return true;
	}

}
