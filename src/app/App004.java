
//WAP to delete employees by using its id.

package app;

import java.sql.*;
import java.util.Scanner;

public class App004 {
	
	static String url="jdbc:postgresql://localhost:5432/gth_july_25_java_jdbc";
	static String username="postgres";
	static String password = "postgres";

	public static void main(String[] args) throws Exception{
		
		Driver driver = new org.postgresql.Driver();
		DriverManager.registerDriver(driver);
		
		Connection conn = DriverManager.getConnection(url,username,password);
		
		if(conn!=null) {
			
			Statement stmt = conn.createStatement();
			
			Scanner sc = new Scanner(System.in);
			
			//delete the employee by using id 
			
			System.out.print("Enter the id to delete the employee : ");
			int id = sc.nextInt();
			
			int result = stmt.executeUpdate("delete from employee where id="+id);
			
			if(result>0) {
				System.out.println("Employee with id "+id+" has been deleted");
			}else {
				System.out.println("Delete operation failed...");
			}
			
		}else {
			System.out.println("Connection Failed...");
		}
		
	}
}


/*
gth_july_25_java_jdbc=# select * from employee;
 id |  name  | salary | age
----+--------+--------+-----
  1 | john   |  30000 |  27
  2 | pranav |  25000 |  25
(2 rows)


Enter the id to delete the employee : 1
Employee with id 1 has been deleted


gth_july_25_java_jdbc=# select * from employee;
 id |  name  | salary | age
----+--------+--------+-----
  2 | pranav |  25000 |  25
(1 row)



*/