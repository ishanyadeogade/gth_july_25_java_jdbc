
//WAP to input employee id and fetch employee detail by using id.

package app;

import java.sql.*;
import java.util.Scanner;

public class App012 {
	
	static String url="jdbc:postgresql://localhost:5432/gth_july_25_java_jdbc";
	static String username="postgres";
	static String password="postgres";

	public static void main(String[] args) throws Exception {
		
		Driver d = new org.postgresql.Driver();
		DriverManager.registerDriver(d);
		Connection conn = DriverManager.getConnection(url,username,password);
		
		Scanner sc = new Scanner(System.in);
		
		if(conn!=null) {
			PreparedStatement pstmt =  conn.prepareStatement("select * from employee where id = ?");
			System.out.print("Enter the id of an employee : ");
			pstmt.setInt(1, sc.nextInt());
			ResultSet rs = pstmt.executeQuery();
			Integer id = null,salary=null,age=null;
			String name=null;
			
			System.out.println("ID\t\tNAME\t\tSALARY\t\tAGE");
			while(rs.next()) {
				id = rs.getInt(1);
				name = rs.getString(2);
				salary = rs.getInt(3);
				age = rs.getInt(4);
					
				System.out.print(id+"\t\t"+name+"\t\t"+salary+"\t\t"+age);
				System.out.println();
			}
		}	
		else {
			System.out.println("Database connection failed...");
		}
	}
}

/*

Enter the id of an employee : 4
ID		NAME		SALARY		AGE
4		john		60000		32

*/
