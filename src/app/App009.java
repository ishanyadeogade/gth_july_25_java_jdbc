
//Delete employee record using a PreparedStatement interface 

package app;

import java.sql.*;
import java.util.Scanner;

public class App009 {
	
	static String url="jdbc:postgresql://localhost:5432/gth_july_25_java_jdbc";
	static String username="postgres";
	static String password="postgres";

	public static void main(String[] args) throws Exception {
		
		Driver d=new org.postgresql.Driver();
		DriverManager.registerDriver(d);
		
		Connection conn= DriverManager.getConnection(url,username,password);
		
		Scanner sc = new Scanner(System.in);
		
		if(conn!=null) {
			 PreparedStatement pstm = conn.prepareStatement("delete from employee where id = ?");
			 System.out.print("Enter the id of an employee you want to delete : ");
			 int id = sc.nextInt();
			 pstm.setInt(1, id);
			 
			 int result = pstm.executeUpdate();
			 
			 if(result>0) {
				 System.out.println("Employee with an id "+id+" get deleted...");
			 }else {
				 System.out.println("Employee not found...");
			 }
			 
		}else {
			System.out.println("Database not Connected...");
		}
	}
}

/*


gth_july_25_java_jdbc=# select * from employee;
 id |  name  | salary | age
----+--------+--------+-----
  2 | pranav |  40000 |  25
  4 | ajay   |  40000 |  32
  3 | yash   |  30000 |  26
(3 rows)


Enter the id of an employee you want to delete : 3
Employee with an id 3 get deleted...


gth_july_25_java_jdbc=# select * from employee;
 id |  name  | salary | age
----+--------+--------+-----
  2 | pranav |  40000 |  25
  4 | ajay   |  40000 |  32
(2 rows)


*/
