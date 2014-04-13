package com.example.landslide2;

import java.io.IOException;
import java.net.SocketException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
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
	double latitude = 0;
    double longitude = 0;
//	GPSTracker gps = new GPSTracker(this);
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
			String provider = LocationManager.NETWORK_PROVIDER;
			LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
//			String provider = service.getBestProvider(criteria, false);
			Location location = service.getLastKnownLocation(provider);
			if (location != null) {
				longitude = location.getLongitude();
				latitude = location.getLatitude();
			}
		}
    }
    
    public void refresh(MenuItem item) throws SocketException, IOException {
    	ImageView image = (ImageView) findViewById(R.id.imgBulb);
        // Do something in response to button
//    	boolean error = false;
//    	ftpDownload ftpDown = new ftpDownload();
//    	ftpDown.download("172.20.186.177","kyriakos","spooky123","landslide/data.txt",new File("/"));
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
    	
//    	try {
//    	    // Create a URL for the desired page
//    	    URL url = new URL("http://kyriakos-pc/data.txt");
//
//    	    // Read all the text returned by the server
//    	    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
////    	    String str = in.readLine();
////    	    checkValue = Double.parseDouble(str);
////    	    in.close();
//    	} catch (MalformedURLException e) {
//    	} catch (IOException e) {
//    	}
    	
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
