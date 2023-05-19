
package StudentInformationSystem.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interface of BaseDaoImpl
 *
 */public interface BaseDao {
	 
	/**
	 * Connect with database
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Connection getConnection() throws SQLException, ClassNotFoundException;

	/**
	 * Close connect with database
	 * 
	 * @param conn
	 * @throws SQLException
	 */
	public void closeConnection(Connection conn) throws SQLException;

}
