package app;

import java.sql.*;
import java.util.Scanner;

public class App003 {
	
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
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("Enter the name : ");
			String name = sc.nextLine();
			
			System.out.print("Enter the salary : ");
			int salary = sc.nextInt();
			
			sc.nextLine();
			
			System.out.print("Enter the age : ");
			
			int age = sc.nextInt(); 
			
			//here name, salary and age are the variable so we have to seperate them from sql query called as sql injection
			int res = stmt.executeUpdate("insert into employee values(DEFAULT,'"+name+"',"+salary+","+age+")");
			
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

Enter the name : pranav
Enter the salary : 25000
Enter the age : 25
record inserted successfully


gth_july_25_java_jdbc=# select * from employee;
 id |  name  | salary | age
----+--------+--------+-----
  1 | john   |  30000 |  27
  2 | pranav |  25000 |  25
(2 rows)


*/
