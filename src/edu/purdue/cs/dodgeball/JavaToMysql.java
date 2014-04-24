package edu.purdue.cs.dodgeball;

import java.sql.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JavaToMysql {
	static Connection conn; 
    static Statement st; 
    static ResultSet rs;   
    static JSONArray jArray;
    static String result = null;
    static InputStream is = null;
    static StringBuilder sb = null;
    public static void getConnection() {    
    	try{
    		HttpClient httpclient = new DefaultHttpClient();   
    		HttpGet httpget = new HttpGet("http://10.184.196.33/test.php"); 
    		HttpResponse response = httpclient.execute(httpget);
    		HttpEntity entity = response.getEntity();
    		is = entity.getContent();
    		System.out.println("Connection Set");
    	}catch(Exception e){
            Log.e("log_tag", "Error in http connection" + e.toString());
    	}
    }
    public static int insert_data(String str1, String str2){
    	getConnection();
    	return 0;
    }
    public static void print_data(){
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
    	
    	
    	
    }
}
