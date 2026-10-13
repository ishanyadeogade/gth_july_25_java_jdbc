
//SQL Injection Demo

package app;

import java.sql.*;
import java.util.Scanner;


public class App007 {
	
	static String url="jdbc:postgresql://localhost:5432/gth_july_25_java_jdbc";
	static String username="postgres";
	static String password = "postgres";
	
	public static void main(String[] args) throws Exception {
		
		Driver driver = new org.postgresql.Driver();
		DriverManager.registerDriver(driver);
		
		Connection conn = DriverManager.getConnection(url,username,password);
		
		Scanner sc = new Scanner(System.in);
		
		if(conn!=null) {
			
			Statement stmt = conn.createStatement();
			
			System.out.print("Enter the id : ");
			int id=sc.nextInt();
			sc.nextLine();
			
			System.out.print("Enter the name : ");
			String name=sc.nextLine();
			
			ResultSet rs=  stmt.executeQuery("select * from employee where id="+id+" and name='"+name+"'"); // name='"or true"'
			
			while(rs.next()) {
				System.out.println(rs.getString("id"));
				System.out.println(rs.getString("name"));
				System.out.println(rs.getString("age"));
				System.out.println(rs.getString("salary"));
				System.out.println("============================");
			}
			
		}else {
			System.out.println("Database Connection Failed...");
		}
		
	}
}

/*

Enter the id : 33
Enter the name : "' or 1=1 --'"
2
pranav
25
40000
============================
3
yash
26
30000
============================


*/