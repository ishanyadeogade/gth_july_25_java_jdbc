package app;

import java.sql.*;

public class App006 {
	
	static String url="jdbc:postgresql://localhost:5432/gth_july_25_java_jdbc";
	static String username="postgres";
	static String password="postgres";

	public static void main(String[] args) throws Exception {
		
		Driver driver = new org.postgresql.Driver();
		DriverManager.registerDriver(driver);
		
		Connection conn = DriverManager.getConnection(url,username,password);
		
		if(conn!=null) {
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("select * from employee");
			
			int id,age,salary;
			String name;
			
			while(rs.next()) {
				id = rs.getInt("id");
				name= rs.getString("name");
				salary = rs.getInt("salary");
				age = rs.getInt("age");
				
				System.out.println("id : "+id);
				System.out.println("name : "+name);
				System.out.println("salary : "+salary);
				System.out.println("age : "+age);
				System.out.println("============================");
				
			}
			
		}else {
			System.out.println("Database connection failed");
		}
		
	}
}

/*

id : 2
name : pranav
salary : 40000
age : 25
============================
id : 3
name : yash
salary : 30000
age : 26
============================

*/