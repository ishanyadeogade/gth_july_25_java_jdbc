package app;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

public class App002 {
	
	static String url="jdbc:postgresql://localhost:5432/gth_july_25_java_jdbc";
	static String username="postgres";
	static String password="postgres";

	public static void main(String ...args) throws Exception {
		
		//Create object of Driver 
		Driver driver = new org.postgresql.Driver(); 
		
		//Register the Driver 
		DriverManager.registerDriver(driver);
		
		//Retrive the connection of database server 
		
		Connection conn = DriverManager.getConnection(url,username,password);
		
		if(conn!=null) {
			
			Statement stmt =  conn.createStatement();
			
			int res = stmt.executeUpdate("insert into employee values(DEFAULT,'john',30000,27)");
			
			if(res>0) {
				System.out.println("record inserted successfully");
			}else {
				System.out.println("Something went wrong ...");
			}
			
		}else {
			System.out.println("Database not connected");
		}
		
	}
}

/*

gth_july_25_java_jdbc=# select * from employee;
 id | name | salary | age
----+------+--------+-----
  1 | john |  30000 |  27
(1 row)

*/
