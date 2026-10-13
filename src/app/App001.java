package app;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class App001 {
	
	public static String url="jdbc:postgresql://localhost:5432/gth_july_25_java_jdbc";
	public static String username="postgres";
	public static String password="postgres";
	
	
	public static void main(String ...args) throws Exception {
		
		Driver driver = new org.postgresql.Driver();
		DriverManager.registerDriver(driver);
		
		//Class.forName("org.postgresql.Driver");
		
		Connection conn = DriverManager.getConnection(url,username,password);
		if(conn!=null) {
			System.out.println("Database Connected");
		}else {
			System.out.println("Database Not Connected");
		}
	}
	
}

/*
Database Connected
*/
