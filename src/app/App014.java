
//WAP to find the max salary of employee

package app;

import java.sql.*;

public class App014 {

	static String url = "jdbc:postgresql://localhost:5432/gth_july_25_java_jdbc";
	static String username = "postgres";
	static String password = "postgres";

	public static void main(String[] args) throws Exception {

		Driver d = new org.postgresql.Driver();
		DriverManager.registerDriver(d);
		Connection conn = DriverManager.getConnection(url, username, password);

		if (conn != null) {
			PreparedStatement pstmt = conn.prepareStatement(
					"select max(salary) from employee");
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("The max salary of an employee is "+rs.getInt(1));
			}else {
				System.out.println("Data not found");
			}
			
			
		} else {
			System.out.println("Database connection failed...");
		}
	}
}

/*

The max salary of an employee is 80000
 
 */
