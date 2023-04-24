
package StudentInformationSystem.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interface of BaseDaoImpl
 * Giao diện của BaseDaoImpl
 *
 */public interface BaseDao {
	 
	/**
	 * Connect with database
	 * Kết nói với db
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Connection getConnection() throws SQLException, ClassNotFoundException;

	/**
	 * Close connect with database
	 * đóng kết nối vs db
	 * 
	 * @param conn
	 * @throws SQLException
	 */
	public void closeConnection(Connection conn) throws SQLException;

}
