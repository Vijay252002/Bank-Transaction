package bank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
public static Connection provideConnection() {
		
		Connection conn=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url="jdbc:mysql://localhost:3306/banktransaction";
		
		try {
			conn= DriverManager.getConnection(url,"root","root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
		
	}

}

