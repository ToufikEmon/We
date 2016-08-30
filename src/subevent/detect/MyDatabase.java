package subevent.detect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MyDatabase {

	private static String url = "jdbc:mysql://localhost:3306/matchcoverage?autoReconnect=true&useSSL=false";
	private static String user = "root";
	private static String password = "root";
	private static String query = "select Time from eventdataset";

	private Connection con = null;
	private static Statement st = null;

	public MyDatabase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<CheckTime> getAllTheData() {
		try {
			List<CheckTime> unFiltered = new ArrayList<>();
			st = con.createStatement();
			ResultSet rs2 = st.executeQuery(query);
			while (rs2.next()) {
				unFiltered.add(new CheckTime(rs2.getString("Time").substring(8, 19)));
			}
			return unFiltered;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void printOutput() {
		List<CheckTime> checkTimes = getAllTheData();
		int len = checkTimes.size();
		for (int i = 0; i < len; i++) {
			System.out.println(checkTimes.get(i));
		}
	}

	public ResultSet executeQueryAndGiveBackTheDataList(String query) {
		try {
			st = con.createStatement();
			return st.executeQuery(query);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
