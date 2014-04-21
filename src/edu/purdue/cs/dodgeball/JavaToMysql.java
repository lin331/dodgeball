package edu.purdue.cs.dodgeball;

import java.sql.*;
import java.util.*;

public class JavaToMysql {
	static Connection conn; 
    static Statement st; 
    static ResultSet rs;    
    public static Connection getConnection() {    
    	Connection con = null;
        try {    
            Class.forName("com.mysql.jdbc.Driver");  
            String url = "jdbc:mysql://sql3.freemysqlhosting.net:3306/sql337126";
            String user = "sql337126";
            String password = "mB6!mL9*";
            con = DriverManager.getConnection(url,user,password);
        } catch (Exception e) {    
            System.out.println("Connection Error" + e.getMessage());    
        }    
        return con;     
    }
    public static int insert_data(String str1, String str2){
    	conn = getConnection();
    	try{
    		String sql1 = "INSERT INTO CS252Lab6 ";
    		String sql2 = "VALUES";
    		String sql = sql1+str1+sql2+str2;
    		st = (Statement) conn.createStatement();
    		rs = st.executeQuery(sql);
    	}catch(Exception e){
    		return 1;
    	}
    	return 0;
    }
    public static void print_data(){
    	conn = getConnection();
    	try{
    		String sql = "SELECT * FROM  `CS252Lab6` LIMIT 0 , 30";
    		st = (Statement) conn.createStatement();
    		rs = st.executeQuery(sql);
    		while(rs.next()){
    			String username = rs.getString("Username");
    			String country = rs.getString("Country");
    			String email = rs.getString("Email");
    			String highscore = rs.getString("HighestScore");
    			System.out.println("Username:"+username);
    			System.out.println("Country:"+country);
    			System.out.println("Email:"+email);
    			System.out.println("Highest Score:"+highscore);
    		}
        	conn.close();
    	}catch (Exception e){
            System.out.println("Connection Error" + e.getMessage());    
    	}
    }
    public static void main(String[] args) { 
    	print_data();
	}
}
