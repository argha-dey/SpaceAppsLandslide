package com.example.landslide2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import android.os.AsyncTask;

public class ftpDownload extends AsyncTask<Void, Void, String>{
	/**
    * Download a file from a FTP server. A FTP URL is generated with the
    * following syntax:
    * ftp://user:password@host:port/filePath;type=i.
    *
    * @param ftpServer , FTP server address (optional port ':portNumber').
    * @param user , Optional user name to login.
    * @param password , Optional password for user.
    * @param fileName , Name of file to download (with optional preceeding
    *            relative path, e.g. one/two/three.txt).
    * @param destination , Destination file to save.
    * @throws MalformedURLException, IOException on error.
    */
   /*public void download( String ftpServer, String user, String password,
         String fileName, File destination ) throws MalformedURLException,
         IOException
   {
   }*/

@Override
	protected String doInBackground(Void... params) {
		boolean error = false;
		double checkValue = 0;
	   	FTPClient mFTPClient = new FTPClient(); 
	   	try { 
		    mFTPClient.connect("172.20.186.177", 21);  
	        boolean status = mFTPClient.login("kyriakos", "spooky123");  
	        // now check the reply code, if positive mean connection success  
	        if (FTPReply.isPositiveCompletion(mFTPClient.getReplyCode())) {  
	              	
	        	int reply = mFTPClient.getReplyCode();
	
	            if(!FTPReply.isPositiveCompletion(reply)) {
	            	mFTPClient.disconnect();
	              System.err.println("FTP server refused connection.");
	              System.exit(1);
	            }
	            
	             mFTPClient.setFileType(FTP.ASCII_FILE_TYPE);  
	             mFTPClient.enterLocalPassiveMode(); 
	             
	             FileOutputStream desFileStream = new FileOutputStream(new File("data.txt")); 
	             while(!mFTPClient.retrieveFile("./", desFileStream)) {
	             }
	             desFileStream.close();
	        }
	   	} catch(IOException e) {
	   		System.exit(1);
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
		    }
	  	
	   	try {
	   	    // Create a URL for the desired page
	   	    URL url = new URL("http://kyriakos-pc/data.txt");
	
	   	    // Read all the text returned by the server
	   	    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
	   	    String str = in.readLine();
	   	    checkValue = Double.parseDouble(str);
	   	    in.close();
	   	} catch (MalformedURLException e) {
	   	} catch (IOException e) {
	   	}
	    return String.valueOf(checkValue);
	}
}