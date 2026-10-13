
//WAP to update the name and salary of employee using PreparedStatement 

package app;

import java.sql.*;
import java.util.Scanner;

public class App010 {
	
	static String url="jdbc:postgresql://localhost:5432/gth_july_25_java_jdbc";
	static String username="postgres";
	static String password="postgres";
	
	public static void main(String[] args) throws Exception {
		Driver d = new org.postgresql.Driver();
		DriverManager.registerDriver(d);
		Connection conn = DriverManager.getConnection(url,username,password);
		
		Scanner sc = new Scanner(System.in);
		
		if(conn!=null) {
			
			PreparedStatement pstmt = conn.prepareStatement("update employee set name=?, salary=? where id=?");
			System.out.print("Enter the id of an employee : ");
			int id = sc.nextInt();
			sc.nextLine();
			System.out.print("Enter the new name of an employee : ");
			String name = sc.nextLine(); 
			System.out.print("Enter the new salary of an employee : ");
			int salary = sc.nextInt();
			
			pstmt.setString(1,name);
			pstmt.setInt(2,salary);
			pstmt.setInt(3, id);
			
			int result = pstmt.executeUpdate();
			
			if(result>0) {
				System.out.println("Employee name and salary get updated.");
			}else {
				System.out.println("Employee Not Found");
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
  2 | pranav |  40000 |  25
  4 | ajay   |  40000 |  32
(2 rows)


Enter the id of an employee : 4
Enter the new name of an employee : john
Enter the new salary of an employee : 60000
Employee name and salary get updated.

gth_july_25_java_jdbc=# select * from employee;
 id |  name  | salary | age
----+--------+--------+-----
  2 | pranav |  40000 |  25
  4 | john   |  60000 |  32
(2 rows)


*/
