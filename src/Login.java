import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Login {
	Connection connection = null;

	public Login() {
		connectToDB();
		createTableNew();
	}

	public boolean login(String username, String password) {
		return getUser(username, password);
	}

	public boolean adminLogin(String username, String password) {
		return getAdmin(username, password);
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

	private boolean getAdmin(String username, String password) {
		String sql = "SELECT USERNAME, PASSWORD, ISADMIN " + "FROM USERTABLE WHERE USERNAME='" + username
				+ "' AND PASSWORD='" + String.valueOf(password) + "' AND ISADMIN = TRUE;";
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

	public boolean isBadgeUnlocked(String username, int grade) {
		String sql = "SELECT USERNAME, BADGE" + grade + " FROM USERTABLE WHERE USERNAME='" + username + "' AND BADGE"
				+ grade + "=TRUE;";
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

	public void unlockBadge(String username, int grade) {
		String sql = "UPDATE USERTABLE SET BADGE" + grade + "=TRUE WHERE USERNAME='" + username + "';";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addUser(String username, String password, String firstName, String lastName, String grade, String dob,
			boolean isAdmin) {
		String sql = "INSERT INTO USERTABLE (USERNAME, PASSWORD, FIRSTNAME, LASTNAME, GRADE, DOB, ISADMIN) VALUES('"
				+ username + "', '" + password + "', '" + firstName + "', '" + lastName + "', '" + grade + "', '" + dob
				+ "', '" + isAdmin + "');";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<ArrayList<String>> getReports() {
		ArrayList<ArrayList<String>> report = new ArrayList<ArrayList<String>>();
		String sql = "SELECT FIRSTNAME, LASTNAME, GRADE, BEST0, TOTAL0, COUNTER0, BEST1, TOTAL1, COUNTER1, BEST2, TOTAL2, COUNTER2, BEST3, TOTAL3, COUNTER3, BEST4, TOTAL4, COUNTER4 FROM USERTABLE;";

		String fname;
		String lname;
		String grade;
		int bestScore = 0;
		int totalScore = 0;
		int scoreCounter = 0;

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			int userCounter = 0;
			int gradeCounter = 0;
			while (rs.next()) {
				ArrayList<String> innerReport = new ArrayList<String>();
				String sqlFName = "FIRSTNAME";
				fname = rs.getString(sqlFName);
				innerReport.add(fname);
				String sqlLName = "LASTNAME";
				lname = rs.getString(sqlLName);
				innerReport.add(lname);
				String sqlGrade = "GRADE";
				grade = rs.getString(sqlGrade);
				innerReport.add(grade);
				for (int i = 0; i < 5; i++) {
					String sqlBest = "BEST" + i;
					bestScore = rs.getInt(sqlBest);
					innerReport.add(String.valueOf(bestScore));
					String sqlTotal = "TOTAL" + i;
					totalScore = rs.getInt(sqlTotal);
					String sqlCounter = "COUNTER" + i;
					scoreCounter = rs.getInt(sqlCounter);
					double average = 0;
					if (scoreCounter != 0) {
						average = (double) totalScore / scoreCounter;
					}
					innerReport.add(String.valueOf(String.format("%.1f", average)));
					innerReport.add(String.valueOf(scoreCounter));
				}
				userCounter++;
				gradeCounter++;

				report.add(innerReport);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return report;
	}

	public void getTestResults(String username, int grade, int newScore) {
		String sql = "SELECT USERNAME, BEST" + grade + ", TOTAL" + grade + ", COUNTER" + grade
				+ " FROM USERTABLE WHERE USERNAME='" + username + "';";
		int bestScore = 0;
		int totalScore = 0;
		int scoreCounter = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				String sqlBest = "BEST" + grade;
				bestScore = rs.getInt(sqlBest);
				String sqlTotal = "TOTAL" + grade;
				totalScore = rs.getInt(sqlTotal);
				String sqlCounter = "COUNTER" + grade;
				scoreCounter = rs.getInt(sqlCounter);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (newScore > bestScore) {
			bestScore = newScore;
		}
		totalScore += newScore;
		scoreCounter++;
		updateTestResult(username, grade, bestScore, totalScore, scoreCounter);
	}

	public void updateTestResult(String username, int grade, int bestScore, int totalScore, int scoreCounter) {
		String sql = "UPDATE USERTABLE SET BEST" + grade + " = " + bestScore + ", TOTAL" + grade + " = " + totalScore
				+ ", COUNTER" + grade + " = " + scoreCounter + " WHERE USERNAME = '" + username + "';";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
				String createTable = "CREATE TABLE USERTABLE (USERNAME VARCHAR2(15), PASSWORD VARCHAR2(15), FIRSTNAME VARCHAR2(20), LASTNAME VARCHAR2(20), DOB DATE, GRADE VARCHAR2(1), ISADMIN BOOLEAN, BADGE0 BOOLEAN, BADGE1 BOOLEAN, BADGE2 BOOLEAN, BADGE3 BOOLEAN, BADGE4 BOOLEAN, BEST0 INT, TOTAL0 INT, COUNTER0 INT, BEST1 INT, TOTAL1 INT, COUNTER1 INT, BEST2 INT, TOTAL2 INT, COUNTER2 INT, BEST3 INT, TOTAL3 INT, COUNTER3 INT, BEST4 INT, TOTAL4 INT, COUNTER4 INT)";
				PreparedStatement statement = connection.prepareStatement(createTable);
				statement.executeUpdate();
				String addAdmin = "INSERT INTO USERTABLE (USERNAME, PASSWORD, FIRSTNAME, LASTNAME, GRADE, DOB, ISADMIN) VALUES('admin', 'admin', 'admin', 'admin', '2020-01-01', 'K', TRUE)";
				statement = connection.prepareStatement(addAdmin);
				statement.executeUpdate();
			}
		} catch (Exception e) {

		}
	}

}
