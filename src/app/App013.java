
//WAP to input employee id and fetch employee detail by using id.

package app;

import java.sql.*;
import java.util.Scanner;

public class App013 {

	static String url = "jdbc:postgresql://localhost:5432/gth_july_25_java_jdbc";
	static String username = "postgres";
	static String password = "postgres";

	public static void main(String[] args) throws Exception {

		Driver d = new org.postgresql.Driver();
		DriverManager.registerDriver(d);
		Connection conn = DriverManager.getConnection(url, username, password);

		Scanner sc = new Scanner(System.in);

		if (conn != null) {
			PreparedStatement pstmt = conn.prepareStatement(
					"select s.student_id,s.name,c.name from student_course_details scd join student s on (s.student_id=scd.student_id) join course c on (c.course_id=scd.course_id)  where s.student_id = ?");
			System.out.print("Enter the id of an student : ");
			pstmt.setInt(1, sc.nextInt());
			ResultSet rs = pstmt.executeQuery();
			Integer id = null;
			String studentName = null,courseName=null;

			System.out.println("ID\t\tNAME\t\tCOURSE");
			while (rs.next()) {
				id = rs.getInt(1);
				studentName = rs.getString(2);
				courseName = rs.getString(3);
				
				System.out.print(id + "\t\t" + studentName + "\t\t" + courseName);
				System.out.println();
			}
		} else {
			System.out.println("Database connection failed...");
		}
	}
}

/*


 
Enter the id of an student : 3
ID		NAME		COURSE
3		Sara		Data Engineer

 
 */
