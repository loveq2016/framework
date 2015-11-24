package org.apache.framework.util;

import java.io.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.naming.NamingException;

import org.apache.framework.domain.Column;
import org.apache.framework.domain.Table;
import org.apache.framework.enums.YesNo;
   
/**
 * jdbc 工具类
 * @author Administrator
 *
 */
public class JdbcUtils {

	private String url = null;
	private String userName = null;
	private String password = null;
	private String driverClass = null;
	Connection conn;
	private Properties props = new Properties();

	/**
	 * 获取链接
	 * @return JDBC的链接
	 */
	public Connection getConnection() throws NamingException, SQLException {
		return this.conn;
	}
	
	/**
	 * 
	 * @param is
	 */
	public JdbcUtils(InputStream is) {
		try {
			props.load(is);
		} catch (Exception e) {
		}
		url = props.getProperty("jdbc.url");
		userName = props.getProperty("jdbc.userName");
		password = props.getProperty("jdbc.password");
		driverClass = props.getProperty("jdbc.driverClass");
		// 注册驱动类
		new JdbcUtils(url, userName, password, driverClass);
	}

	public JdbcUtils(String url, String userName, String password, String driverClass) {
		this.url = url;
		this.userName = userName;
		this.password = password;
		try {
			Class.forName(driverClass);
			this.conn = DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 得到数据库中所有的表
	 * @return
	 */
	public List<Table> getTabels() {
		List<Table> list = new ArrayList<Table>();
		DatabaseMetaData dmd;
		ResultSet rs = null;
		try {
			dmd = conn.getMetaData();
			String catalog = conn.getCatalog(); // catalog 其实也就是数据库名
			rs = dmd.getTables(catalog, null, null, new String[] { "TABLE" });
			while (rs.next()) {
				Table table = new Table();
				table.setName(rs.getString("TABLE_NAME"));
				table.setRemarks(rs.getString("REMARKS"));
				list.add(table);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	/**
	 * 根据表名，得到表中的所有字段
	 * @param tableName
	 * @return
	 */
	public List<Column>  getColumnByTableName(String tableName)  {
		List<Column> columns = new ArrayList<Column>();
		DatabaseMetaData dmd;
		ResultSet rs = null;
		try {
			dmd = conn.getMetaData();
			String pkColumnName = getPkColumnName(tableName);
			rs = dmd.getColumns(null, "%", tableName, "%");
			if (rs != null) {
				while (rs.next()) {
					Column column = new Column();
					column.setName(rs.getString("COLUMN_NAME"));
					column.setRemarks(rs.getString("REMARKS"));
					column.setClassName(JDBCTypesUtils.getClassName(rs.getInt("DATA_TYPE")));
					if (column.getName().equals(pkColumnName)) {
						column.setIsPrimaryKey(YesNo.YES.getCode());
					} else {
						column.setIsPrimaryKey(YesNo.NO.getCode());
					}
					columns.add(column);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return columns;
	}

	/**
	 * 得到 主键 列名称
	 * @param tableName
	 * @return
	 */
	public String getPkColumnName(String tableName) {
		DatabaseMetaData dmd;
		ResultSet rs = null;
		String pkColumnName = null;
		try {
			dmd = conn.getMetaData();
			rs = dmd.getPrimaryKeys(null, null, tableName);
			if (rs != null) {
				while (rs.next()) {
					pkColumnName = rs.getString("COLUMN_NAME");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return pkColumnName;
	}
	
	/**
	 * 查询sql
	 * 
	 * @param sql
	 * @param prepare
	 * @param connection
	 * @return
	 * @throws SQLException
	 */
	private ResultSet query(Connection connection, String sql,
			Object... prepare) throws SQLException {

		PreparedStatement stmt = connection.prepareStatement(sql);
		for (int i = 0; i < prepare.length; i++) {
			Object prepareParam = prepare[i];
			stmt.setObject(i + 1, prepareParam, getType(prepareParam));
		}

		return stmt.executeQuery();
	}

	/**
	 * 执行sql
	 * 
	 * @param sql
	 * @param prepare
	 * @param connection
	 * @return
	 * @throws SQLException
	 */
	private int executeUpdate(Connection connection, String sql,
			Object... prepare) throws SQLException {
		int i = 0;
		PreparedStatement stmt = connection.prepareStatement(sql);
		for (int j = 0; j < prepare.length; j++) {
			Object prepareParam = prepare[i];
			stmt.setObject(j + 1, prepareParam, getType(prepareParam));
		}
		i = stmt.executeUpdate();
		return i;
	}

	/**
	 * 返回参数的sql类型，这段代码很丑，如何改
	 * 
	 * @param prepareParam
	 * @return
	 */
	private int getType(Object prepareParam) {
		if (prepareParam == null)
			return Types.NULL;
		if (prepareParam instanceof String) {
			return Types.VARCHAR;
		} else if (prepareParam instanceof Boolean) {
			return Types.BOOLEAN;
		} else if (prepareParam instanceof Integer) {
			return Types.INTEGER;
		} else if (prepareParam instanceof Long) {
			return Types.BIGINT;
		} else if (prepareParam instanceof Float) {
			return Types.FLOAT;
		} else if (prepareParam instanceof Double) {
			return Types.DOUBLE;
		} else if (prepareParam instanceof BigDecimal) {
			return Types.NUMERIC;
		} else if (prepareParam instanceof Date) {
			return Types.TIMESTAMP;
		} else if (prepareParam instanceof Short) {
			return Types.SMALLINT;
		} else if (prepareParam instanceof InputStream) {
			return Types.BINARY;
		} else {
			throw new RuntimeException("没有此类型");
		}
	}

	/**
	 * 执行update的sql语句
	 * 
	 * @param sql
	 *            修改的update的sql代码,准备参数,连接
	 * @return
	 * @throws SQLException
	 */
	public int update(Connection connection, String sql, Object... prepare)
			throws SQLException {
		return executeUpdate(connection, sql, prepare);
	}

	/**
	 * 执行delete的sql语句
	 * 
	 * @param sql
	 *            修改的delete的sql代码,准备参数,连接
	 * @return
	 * @throws SQLException
	 */
	public int delete(Connection connection, String sql, Object... prepare)
			throws SQLException {
		return executeUpdate(connection, sql, prepare);
	}

	/**
	 * 执行insert的sql语句
	 * 
	 * @param sql
	 *            修改的insert语句,准备参数,连接
	 * @return
	 * @throws SQLException
	 */
	public int insert(Connection connection, String sql, Object... prepare)
			throws SQLException {
		return executeUpdate(connection, sql, prepare);
	}

	/**
	 * 执行select的sql语句
	 * 
	 * @param sql
	 *            修改的select语句
	 * @return
	 * @throws SQLException
	 */
	public ResultSet find(Connection connection, String sql, Object... prepare)
			throws SQLException {
		return query(connection, sql, prepare);
	}
 
	public static void main(String[] args) {
		System.out.println("d");
	}
}
