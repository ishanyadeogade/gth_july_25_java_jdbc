//WAP to update the salary of an employee using its id.

package app;

import java.sql.*;
import java.util.Scanner;

public class App005 {
	
	static String url = "jdbc:postgresql://localhost:5432/gth_july_25_java_jdbc";
	static String username="postgres";
	static String password="postgres";
	
	public static void main(String[] args) throws Exception {
		
		Driver driver = new org.postgresql.Driver();
		
		DriverManager.registerDriver(driver);
		
		Connection conn = DriverManager.getConnection(url,username,password);
		
		if(conn!=null) {
			
			Scanner sc = new Scanner(System.in);
			
			//update the employee by using id 
			
			Statement stmt =  conn.createStatement();
			
			System.out.print("Enter the id of an employee : ");
			int id = sc.nextInt();
			sc.nextLine();
			System.out.print("Enter the new salary : ");
			int salary = sc.nextInt();
			
			int res = stmt.executeUpdate("update employee set salary="+salary+" where id="+id);
			
			if(res>0) {
				System.out.println("Employee salary updated Successfully");
			}else {
				System.out.println("Something went wrong...");
			}
			
		}else {
			System.out.println("Database Connection Failed...");
		}
	}
}
/*



gth_july_25_java_jdbc=# select * from employee;
 id |  name  | salary | age
----+--------+--------+-----
  2 | pranav |  25000 |  25
(1 row)

Enter the id of an employee : 2
Enter the new salary :40000
Employee salary updated Successfully

gth_july_25_java_jdbc=# select * from employee;
 id |  name  | salary | age
----+--------+--------+-----
  2 | pranav |  40000 |  25
(1 row)


*/
