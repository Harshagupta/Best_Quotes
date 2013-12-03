package com.app.bestquotes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

public class Helper {
	
	
	Helper()
	{
		
	}

	
	 public void CopyAssets(Context context,SQLiteDatabase db) {
		    AssetManager assetManager = context.getAssets();
		    String[] folders={"Amazing","Anniversary","Attitude","Beauty","Birthday","Brainy","Communication","Education","Friendship","God","Happiness","Inspirational","Hardwork","Poetry","Science"};
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
