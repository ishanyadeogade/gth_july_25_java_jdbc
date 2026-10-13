
//WAP to insert record in employee table using a PreparedStatement interface

package app;

import java.sql.*;
import java.util.Scanner;

public class App008 {

	static String url="jdbc:postgresql://localhost:5432/gth_july_25_java_jdbc";
	static String username="postgres";
	static String password="postgres";
	
	public static void main(String[] args) throws Exception {
		
		Driver driver = new org.postgresql.Driver();
		DriverManager.registerDriver(driver);
		
		Connection conn = DriverManager.getConnection(url,username,password);
		
		Scanner sc = new Scanner(System.in);
		
		if(conn!=null) {
			
			
			System.out.print("Enter the name of an employee : ");
			String name = sc.nextLine();
			
			System.out.print("Enter the salary of an employee : ");
			int salary = sc.nextInt();
			
			sc.nextLine();
			
			System.out.print("Enter the age of an employee : ");
			int age = sc.nextInt();
			
			
			PreparedStatement pstmt = conn.prepareStatement("insert into employee values(DEFAULT,?,?,?)");
			pstmt.setString(1, name);
			pstmt.setInt(2,salary);
			pstmt.setInt(3, age);
			
			int result = pstmt.executeUpdate();
			
			if(result>0) {
				System.out.println("Record inserted Successfully");
			}else {
				System.out.println("Something went wrong...");
			}
			
		}else {
			System.out.println("Database Connection Failed");
		}
	}
}

/*


Enter the name of an employee : ajay
Enter the salary of an employee : 40000
Enter the age of an employee : 32
Record inserted Successfully


gth_july_25_java_jdbc=# select * from employee;
 id |  name  | salary | age
----+--------+--------+-----
  2 | pranav |  40000 |  25
  3 | yash   |  30000 |  26
  4 | ajay   |  40000 |  32
(3 rows)


*/