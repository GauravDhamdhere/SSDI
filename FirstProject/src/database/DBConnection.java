package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
private Connection con;	
public Connection getConnect(){
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		
		this.con = DriverManager.getConnection("jdbc:mysql://localhost/sample","root","root");
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return this.con;
}


public void closeConnect(){
	
	try {
		this.con.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
