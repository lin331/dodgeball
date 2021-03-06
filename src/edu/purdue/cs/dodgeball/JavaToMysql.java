package edu.purdue.cs.dodgeball;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JavaToMysql {  
    static JSONArray jArray;
    static String result = null;
    static InputStream is = null;
    static StringBuilder sb = null;
    static String username[] = new String[1024] ;
    static int highscore[] = new int[1024];
    static int flag = 0;
    
    // Connect to database
    public static void getConnection() {    
    	try{
    		HttpClient httpclient = new DefaultHttpClient();   
    		HttpGet httpget = new HttpGet("http://lemonbear.cu.cc/CS252/readDESC.php"); 
    		HttpResponse response = httpclient.execute(httpget);
    		HttpEntity entity = response.getEntity();
    		is = entity.getContent();
    		System.out.println("Connection Set");
    	} catch(Exception e) {
            Log.e("log_tag", "Error in http connection" + e.toString());
    	}
    }
    
    // Add data to database
    public static int insert_data(String str1, String str2){
		ArrayList<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("username",str1));
		nameValuePairs.add(new BasicNameValuePair("score",str2));
		try{
		    HttpClient httpclient = new DefaultHttpClient();
		    HttpPost httppost = new HttpPost("http://lemonbear.cu.cc/CS252/insert.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			/* HttpResponse response = */
			httpclient.execute(httppost);
        } catch(Exception e) {
             Log.e("log_tag", "Error in http connection"+e.toString());
        }
    	return 0;
    }
    
    // Prints data from database
    public static void print_data() {
    	flag = 0;
    	getConnection();
    	try {
            BufferedReader reader = new BufferedReader(
                   new InputStreamReader(is, "iso-8859-1"), 8);
            sb = new StringBuilder();
            sb.append(reader.readLine() + "\n");

            String line = "0";
            while ((line = reader.readLine()) != null) {
               sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            System.out.println(result);
        } catch (Exception e) {
            Log.e("log_tag", "Error converting result " + e.toString());
        }
    	JSONArray jArray;
		try {
			jArray = new JSONArray(result);
			for(int i=0;i<jArray.length();i++){                    
	    		JSONObject json_data = jArray.getJSONObject(i);    
	    		highscore[i]=json_data.getInt("HighestScore");
	    		username[i]=json_data.getString("Username");
	    		System.out.println("User:"+username[i]+" Highest Score:"+highscore[i]);
	    	}
		} catch (JSONException e) {
			e.printStackTrace();
		}           
		flag = 1;
    }
}
