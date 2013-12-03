package com.app.bestquotes;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class GridImage extends Activity {

    public class ImageAdapter extends BaseAdapter {
    	
    	private Context mContext;
    	ArrayList<String> itemList = new ArrayList<String>();
    	
    	public ImageAdapter(Context c) {
    		mContext = c;	
    	}
    	
    	void add(String path){
    		itemList.add(path);	
    	}

		@Override
		public int getCount() {
			return itemList.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return itemList.get(arg0);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView;
	        if (convertView == null) {  // if it's not recycled, initialize some attributes
	            imageView = new ImageView(mContext);
	            imageView.setLayoutParams(new GridView.LayoutParams(220, 220));
	            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
	            imageView.setPadding(8, 8, 8, 8);
	        } else {
	            imageView = (ImageView) convertView;
	        }

	        Bitmap bm = decodeSampledBitmapFromUri(itemList.get(position), 220, 220);

	        imageView.setImageBitmap(bm);
	        return imageView;
		}
		
		public Bitmap decodeSampledBitmapFromUri(String path, int reqWidth, int reqHeight) {
			
			Bitmap bm = null;
			InputStream is=null;
			// First decode with inJustDecodeBounds=true to check dimensions
			final BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			
			
			
			// Calculate inSampleSize
			options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
		     
			// Decode bitmap with inSampleSize set
			options.inJustDecodeBounds = false;
			
			try{
				Log.d("InputStream",path);
			 is = GridImage.this.getResources().getAssets().open(path,GridImage.this.getAssets().ACCESS_BUFFER);
			 
			
			}
			catch(Exception e)
			{
				Log.d("Decoding",e.getMessage());
				
			}
		     
			bm = BitmapFactory.decodeStream(is);
		     
			return bm;  	
		}
		
		public int calculateInSampleSize(
				
			BitmapFactory.Options options, int reqWidth, int reqHeight) {
			// Raw height and width of image
			final int height = options.outHeight;
			final int width = options.outWidth;
			int inSampleSize = 1;
			
			if (height > reqHeight || width > reqWidth) {
				if (width > height) {
					inSampleSize = Math.round((float)height / (float)reqHeight);   	
				} else {
					inSampleSize = Math.round((float)width / (float)reqWidth);   	
				}   
			}
			
			return inSampleSize;   	
		}

	}
    
    ImageAdapter myImageAdapter;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_image);
        
        DatabaseHandler db=new DatabaseHandler(GridImage.this);
		List<Categories> cats=db.getAllCategories();
		
		ArrayList<String>catss=new ArrayList<String>();
		for(Categories cat:cats)
		{
			//Log.d("Categories",cat.getCategory());
			catss.add(cat.getCategory());
		}
		final Spinner spin=(Spinner)findViewById(R.id.spinner1);
		
		  ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, catss);
		    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    spin.setAdapter(adapter);
        
       final GridView gridview = (GridView) findViewById(R.id.gridview);
     
        
       // String ExternalStorageDirectoryPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        
        
        
       // String targetPath = ExternalStorageDirectoryPath + "/test/";
        
       // Toast.makeText(getApplicationContext(), targetPath, Toast.LENGTH_LONG).show();
        //File targetDirector = new File(targetPath);
        
     
       spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
				Log.d("Spinner Selected","Selected Position"+ arg2);
				
				 try{
					 
					    myImageAdapter = new ImageAdapter(GridImage.this);
					    AssetManager am=GridImage.this.getResources().getAssets();
					    String ff=spin.getItemAtPosition(arg2).toString().toLowerCase();
						Log.d("Spinner Selected","Selected item"+ ff);
				        String [] files = am.list(ff);
				        Log.d("files array length",files.length+"");
				        for (String file : files){
				        	myImageAdapter.add(ff+"/"+file);
				        	Log.d("Putting Images",ff+"/"+file );
				        	gridview.setAdapter(myImageAdapter);
				        } 
				        }
				        catch(Exception e)
				        {
				        	
				        }
				   
			       
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
       	
       	
		});
        
       
        gridview.setOnItemClickListener(new OnItemClickListener()
        {
        public void onItemClick(AdapterView<?> parent,
        View v, int position, long id)
        {
      
        
        Intent intent=new Intent(GridImage.this,ImageDetailActivity.class);
        intent.putExtra("file", myImageAdapter.getItem(position).toString());
        intent.putExtra("cat", spin.getSelectedItem().toString() );
        Log.d("Grid Item Selected"," " + position + spin.getSelectedItem().toString());
        startActivity(intent);
        
        }
        });
	    }

	 
    }



          

