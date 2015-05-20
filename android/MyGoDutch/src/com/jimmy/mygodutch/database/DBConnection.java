package com.jimmy.mygodutch.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {
	public static final String url = "jdbc:mysql://121.40.91.193/waifood";//"jdbc:mysql://localhost/waifood";//  
    public static final String driver ="com.mysql.jdbc.Drive";  
    public static final String user = "jimmy";  
    public static final String password = "hongwash";  
  
    public Connection conn = null;  
    public PreparedStatement pst = null;  
    
  //加载数据库驱动
  	public DBConnection(){
  		try{
  			Class.forName("com.mysql.jdbc.Driver");
  			System.out.println("OK");
  		}catch(ClassNotFoundException e){
  			System.out.println("数据库驱动没有找到:"+e.getMessage());
  		}
  	}
  	
  	//连接数据库程序
  	public void getConnection(){
  	
  			try {
				conn=DriverManager.getConnection(url,user,password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  	}
  	public static void main(String[] ss)
  	{
  		DBConnection db=new DBConnection();
  		db.getConnection();
  		
  	}
  		
}
