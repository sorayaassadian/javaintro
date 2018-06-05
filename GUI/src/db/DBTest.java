package db;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBTest {

	public static void main(String[] args) {
		try {
		new DBTest();
	} catch (SQLException e) {
		e.printStackTrace(); 
		}
	}
	

	public DBTest() throws SQLException{
		//Verbindung zu DB...
		//
		Connection conn= DriverManager.getConnection("jdbc:ucanaccess://c:/users/sorayaassadian/dbtest.accdb;memory=false");
		//Insert ausführen
		Statement stmt = conn.createStatement();
		stmt.execute("INSERT INTO address ( vorname, nachname, ort, plz )"
				+ "VALUES ('soraka', 'käser', 'wien', 1120)");
		//

	}

}
