package com.app.bestquotes;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

public class BestQuotesActivity extends Activity {
    /** Called when the activity is first created. */
    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        DatabaseHandler db = new DatabaseHandler(this);
             
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
       /* db.addCategory(new Categories(2,"Yoga"));
        db.addCategory(new Categories(3,"Business"));
        db.addQuote(new Quotes(2,1, "Don't be novice."));       
        db.addQuote(new Quotes(3,2, "Second Quote.")); 
        db.addQuote(new Quotes(4,2, "Third Quote.")); 
        db.addQuote(new Quotes(5,3, "Fifth Quote.")); 
        db.addQuote(new Quotes(6,3, "Eights Quote.")); */
         
        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Quotes> quotes = db.getAllQuotes();      
         
        String quot="";
        
       for (Quotes cn : quotes) {
            String log = "Id: "+cn.getID()+" ,Category: " + db.getCategory(cn.getCAT_ID()).getCategory() + " ,Quote: " + cn.getQuote();
                // Writing Contacts to log
        Log.d("Quote: ", log);
        
        quot=quot+" \n" +log;
         
       
        
    }
        Double d=Math.random()*40;
        Quotes quote=db.getQuote(d.intValue());
       // Quotes quote=quotes.get(d.intValue());
       
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("Quote"+quote.getQuote());
        Log.d("Quote id:",""+quote.getID());
        
        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("com.app.bestquotes", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        } catch (NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
}
   
}