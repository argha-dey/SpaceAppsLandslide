package com.example.landslide2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	double checkValue =0;
	double latitude = 0;
    double longitude = 0;
    TextView tvIsConnected;
    Spinner dropDownLocations;
    List<String> dropDownLocationList = new ArrayList<String>();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if (savedInstanceState == null) {
//            getFragmentManager().beginTransaction()
//                    .add(R.id.container, new PlaceholderFragment())
//                    .commit();
//        }
        
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
			String provider = LocationManager.NETWORK_PROVIDER;
			LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
			Location location = service.getLastKnownLocation(provider);
			if (location != null) {
				longitude = location.getLongitude();
				latitude = location.getLatitude();
			}
		}
		
		String valueString = "";
		
		HttpAsyncTask asyncTaskObject = new HttpAsyncTask();
		while(valueString == "") {
	    	try {
				valueString = asyncTaskObject.execute("http://uyatashi.byethost7.com/data.txt").get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		valueString = "Limassol,0.12685\nNicosia,1.3181\nPaphos,3.9216\nLimassol,0.12685\nNicosia,1.3181\nPaphos,3.9216\nLimassol,0.12685\nNicosia,1.3181\nPaphos,3.9216";
		
    	String[] lines = valueString.split("\n");
    	String[] values = new String[lines.length];
    	String[] tmp = new String[2];
    	dropDownLocationList = new ArrayList<String>();
    	
    	for (int i=0; i<lines.length; i++) {
    		tmp = lines[i].split(",");
    		dropDownLocationList.add(tmp[0]);
    		values[i] = tmp[1];
    	}
    	
    	dropDownLocations = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<String> dropDownDataAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item, dropDownLocationList);
		dropDownDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	dropDownLocations.setAdapter(dropDownDataAdapter);
    	dropDownLocations.setOnItemSelectedListener(
	        new OnItemSelectedListener() {
	            public void onItemSelected(
	            	AdapterView<?> parent, View view, int position, long id) {
	            		try {
							refresh(null);
						} catch (SocketException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	            	}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub
					
				}
	        }
	    );
    }
    
    public void refresh(MenuItem item) throws SocketException, IOException, InterruptedException, ExecutionException {
    	ImageView image = (ImageView) findViewById(R.id.imgBulb);
    	
    	HttpAsyncTask asyncTaskObject = new HttpAsyncTask();
    	String valueString = asyncTaskObject.execute("http://uyatashi.byethost7.com/data.txt").get();
    	
    	valueString = "Limassol,0.12685\nNicosia,1.3181\nPaphos,3.9216";
    	
    	String[] lines = valueString.split("\n");
    	String[] values = new String[lines.length];
    	String[] tmp = new String[2];
    	
    	for (int i=0; i<lines.length; i++) {
    		tmp = lines[i].split(",");
    		if (String.valueOf(dropDownLocations.getSelectedItem()).equals(tmp[0])) {
    			checkValue = Double.parseDouble(tmp[1]);
    		}
    		values[i] = tmp[1];
    	}
    	
//    	checkValue = Double.parseDouble(valueString);
    	
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

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    /*public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }*/
    
    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {
 
            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
 
            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
 
            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
 
            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";
 
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
 
        return result;
    }
    
    // convert inputstream to String
    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
 
        inputStream.close();
        return result;
 
    }
    
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
 
            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Received!\n" + result, Toast.LENGTH_LONG).show();
       }
    }
}
