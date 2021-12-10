import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
	Connection connection = null;

	public Login() {
		connectToDB();
		createTableNew();
	}

	public boolean login(String username, String password) {
		return getUser(username, password);
	}

	private boolean getUser(String username, String password) {
		String sql = "SELECT USERNAME, PASSWORD " + "FROM USERTABLE WHERE USERNAME='" + username + "' AND PASSWORD='"
				+ String.valueOf(password) + "';";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			if (rs.isBeforeFirst()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private void connectToDB() {
		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:~/MathFarm", "sa", "");
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void createTableNew() {
		try {
			DatabaseMetaData dmd = connection.getMetaData();
			ResultSet set = dmd.getTables(null, null, "USERTABLE", null);

			if (set.next()) {

			} else {
				System.out.println("no table found");
				String createTable = "CREATE TABLE USERTABLE (USERNAME VARCHAR2(15), PASSWORD VARCHAR2(15), FIRSTNAME VARCHAR2(20), LASTNAME VARCHAR2(20), DOB DATE, GRADE VARCHAR2(1))";
				PreparedStatement statement = connection.prepareStatement(createTable);
				statement.executeUpdate();
				String addAdmin = "INSERT INTO USERTABLE VALUES('admin', 'admin', 'admin', 'admin', '2020-01-01', 'K')";
				statement = connection.prepareStatement(addAdmin);
				statement.executeUpdate();
			}
		} catch (Exception e) {

		}
	}

}
