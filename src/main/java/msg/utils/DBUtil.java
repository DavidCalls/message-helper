package msg.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import msg.exception.MessageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JDBC工具类
 * @author longjianwei
 */
public class DBUtil {

	//private static Logger logger = LoggerFactory.getLogger(DBUtil.class);

	/**数据库链接*/
	private Connection connection;

	private String driverName;

	private String url;

	private String username;

	private String password;

	public String getDriverName() {
		return driverName;
	}

	public DBUtil(String driverName, String url, String username, String password) throws MessageException {
		this.driverName = driverName;
		this.url = url;
		this.username = username;
		this.password = password;
		try {
			Class.forName(driverName);
			this.connection = DriverManager.getConnection(url, username, password);
		}
		catch (Exception e) {
			throw new MessageException(e.getMessage());
		}
	}

	/**
	 * 得到当前数据库工具操作
	 * @return
	 */
	public static DBUtil getInstance() throws MessageException {
		Properties properties = PropertiesUtil.getProperties();
		return new DBUtil(properties.getProperty("jdbc.driverClassName"),
				properties.getProperty("jdbc.url"),
				properties.getProperty("jdbc.username"),
				properties.getProperty("jdbc.password"));
	}

	/**
	 * 获取数据库连接
	 * @return
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * 关闭数据库连接(不会关闭当前connection)
	 * @param ps
	 * @param rs
	 */
	public void close(ResultSet rs, Statement ps) throws MessageException {
		if (rs != null) {
			try {
				rs.close();
			}
			catch (SQLException e) {
				throw new MessageException(e.getMessage());
			}
		}
		if (ps != null) {
			try {
				ps.close();
			}
			catch (SQLException e) {
				throw new MessageException(e.getMessage());
			}
		}
	}

	/**
	 * 关闭数据库连接
	 * @param rs
	 * @param ps
	 * @param conn
	 */
	public static void close(ResultSet rs, Statement ps, Connection conn) throws MessageException {
		if (rs != null) {
			try {
				rs.close();
			}
			catch (SQLException e) {
				//logger.error("", e);
			}
		}
		if (ps != null) {
			try {
				ps.close();
			}
			catch (SQLException e) {
				//logger.error("", e);
			}
		}

		if (conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e) {
				throw new MessageException(e.getMessage());
			}
		}
	}

	/**
	 * 关闭当前连接
	 */
	public void close() throws MessageException {
		if (connection != null) {
			try {
				connection.close();
			}
			catch (SQLException e) {
				throw new MessageException(e.getMessage());
			}
		}
	}

	/**
	 * 查询数据
	 * @param sql
	 * @return
	 */
	public List<HashMap> query(String sql) throws MessageException {
		Statement stmt = null;
		ResultSet rs = null;
		List<HashMap> lists = new ArrayList<HashMap>();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			ResultSetMetaData data = rs.getMetaData();
			while (rs.next()) {
				HashMap jb = new HashMap();
				int count = data.getColumnCount();
				for (int i = 1; i <= count; i++) {
					// 获得指定列的列名
					String columnName = data.getColumnName(i);
					Object value = rs.getObject(i);
					if (value instanceof Timestamp) {
						jb.put(columnName, rs.getTimestamp(columnName));
						//这个是特殊处理SQLserver的image对象
					}
					else if (value != null && "class [B".equals(value.getClass() + "")) {
						try {
							InputStream inputStram = rs.getBinaryStream(columnName);
							jb.put(columnName, getByte(inputStram));
							inputStram.close();
						}
						catch (IOException e) {
							e.printStackTrace();
						}
//						jb.put(columnName, rs.getBinaryStream(columnLabel));
					}
					else if (value instanceof BigDecimal) {
						jb.put(columnName, value);
					}
					else if (value == null) {
						jb.put(columnName, null);
						//MySql使用   结束
					}
					else if (value instanceof String) {
						jb.put(columnName, value);
					}
					else if (value instanceof Clob) {
						//jb.put(columnName, StringUtil.getClobValue(value));
					}
					else if (value instanceof Blob) {
						jb.put(columnName, value);
					}
					else {
						// 获得指定列的列值
						String columnValue = rs.getString(i);
						columnValue = columnValue == null ? "" : columnValue;
						jb.put(columnName, columnValue);
					}
				}
				lists.add(jb);
			}
		}
		catch (SQLException e) {
			throw new MessageException(e.getMessage());
		}
		finally {
			close(rs, stmt);
		}
		return lists;
	}

	/**
	 * 执行SQL语句
	 * @param sql
	 * @return
	 */
	public boolean executeSql(String sql) throws MessageException {
		Statement stmt = null;
		boolean flag = false;
		try {
			stmt = connection.createStatement();
			stmt.execute(sql);
			flag = true;
		}
		catch (SQLException e) {
			throw new MessageException(e.getMessage());
		}
		finally {
			close(null, stmt);
		}
		return flag;
	}

	public static byte[] getByte(InputStream value) throws MessageException {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		int rc = 0;
		try {
			while ((rc = value.read(buff, 0, 100)) > 0) {
				swapStream.write(buff, 0, rc);
			}
		}
		catch (IOException e) {
			throw new MessageException(e.getMessage());
		}
		byte[] in2b = swapStream.toByteArray();
		return in2b;
	}

//	public static void main(String[] args) {
//		DBUtil.getInstance();
//	}
}























