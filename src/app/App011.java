package app;

import java.sql.*;

public class App011 {
	
	static String url="jdbc:postgresql://localhost:5432/gth_july_25_java_jdbc";
	static String username="postgres";
	static String password="postgres";

	public static void main(String[] args) throws Exception {
		
		Driver d = new org.postgresql.Driver();
		DriverManager.registerDriver(d);
		Connection conn = DriverManager.getConnection(url,username,password);
		
		if(conn!=null) {
			PreparedStatement pstmt =  conn.prepareStatement("select * from employee");
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

ID		NAME		SALARY		AGE
2		pranav		40000		25
4		john		60000		32

*/
