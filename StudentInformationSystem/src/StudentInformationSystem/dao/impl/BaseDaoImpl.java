
package StudentInformationSystem.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import StudentInformationSystem.dao.BaseDao;
import StudentInformationSystem.util.Constants;

/**
 * Contain common function when call database connect
 * Chứa chức năng chung khi kết nối cơ sở dữ liệu cuộc gọi
 *
 */
public class BaseDaoImpl implements BaseDao {
	protected Connection conn ;
	protected ResultSet rs ;
	protected PreparedStatement pstatement ;	
	/**
	 * Connect with database
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		try {
			Class.forName(Constants.DRIVER);

			String URL = Constants.URL;
			String USERNAME = Constants.USERNAME;
			String PASSWORD = Constants.PASSWORD;
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			if (conn != null) {
				return conn;
			}
		} catch (SQLException e) {
			throw new SQLException();
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException();
		}
		return null;
	}

	/**
	 * Close connect with database
	 * 
	 * @param conn
	 * @throws SQLException
	 */
	@Override
	public void closeConnection(Connection conn) throws SQLException {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new SQLException();
		}
	}


}
