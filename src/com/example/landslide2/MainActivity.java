package com.example.landslide2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.Set;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	double checkValue =2;
	GPSTracker gps = new GPSTracker(this);
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        
    	if(!gps.canGetLocation()){
	        // can't get location
	        // GPS or Network is not enabled
	        // Ask user to enable GPS/network in settings
	        gps.showSettingsAlert();
    	}
    }
    
    public void refresh(MenuItem item) throws SocketException, IOException {
    	ImageView image = (ImageView) findViewById(R.id.imgBulb);
        // Do something in response to button
//    	boolean error = false;
    	ftpDownload ftpDown = new ftpDownload();
    	ftpDown.download("172.20.186.177","kyriakos","spooky123","landslide/data.txt",new File("/"));
//    	FTPClient mFTPClient = new FTPClient(); 
    	//try { 
	        // connecting to the host  
	       // mFTPClient.connect("172.20.186.177", 21);  
	//        boolean status = mFTPClient.login("FTP_UID", "FTP_PW");  
	         // now check the reply code, if positive mean connection success  
	       /* if (FTPReply.isPositiveCompletion(mFTPClient.getReplyCode())) {  
	              
	        	
	        	int reply = mFTPClient.getReplyCode();
	
	            if(!FTPReply.isPositiveCompletion(reply)) {
	            	mFTPClient.disconnect();
	              System.err.println("FTP server refused connection.");
	              System.exit(1);
	            }
	            
	             mFTPClient.setFileType(FTP.ASCII_FILE_TYPE);  
	             mFTPClient.enterLocalPassiveMode(); 
	             
	             FileOutputStream desFileStream = new FileOutputStream("./");  
	             boolean status;
	             while(!mFTPClient.retrieveFile("./", desFileStream)) {
	             }
	             desFileStream.close();
	        } */
    	/*} catch(IOException e) {
    		error = true;
    		e.printStackTrace();
	    } finally {
	    	if(mFTPClient.isConnected()) {
	    		try {
	    			mFTPClient.disconnect();
	    		} catch(IOException ioe) {
	    			// do nothing
	    		}
	    	}
	    	System.exit(error ? 1 : 0);
	    }*/
    	TextView textSafety = (TextView) findViewById(R.id.safetyLabel);
    	if (checkValue < 1) {
    		image.setImageResource(R.drawable.red);
    		textSafety.setText("Danger!!! Immediate Evacuation");
    	}
    	else if (checkValue < 3) {
    		image.setImageResource(R.drawable.orange);
    		textSafety.setText("Moderate Danger!!! Prepare to evacuate");
    	}
    	else {
    		image.setImageResource(R.drawable.green);
    		textSafety.setText("Location is safe");
    	}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_info) {
        	double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
        	Context context = getApplicationContext();
        	CharSequence text = "Factor of Safety: " + Double.toString(checkValue) + "\n Latitude: " + latitude + "\n Longitude: " + longitude;
        	int duration = Toast.LENGTH_SHORT;

        	Toast toast = Toast.makeText(context, text, duration);
        	toast.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
