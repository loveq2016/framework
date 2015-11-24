package org.apache.framework.mybatis.dialect.impl;

import org.apache.framework.mybatis.dialect.Dialect;


public class MySqlDialect extends Dialect {

	@Override
	public String getPageSql(String sql, int offset, int limit) {
		if (offset > 0) {
			return sql + " limit " + offset + ", " + limit;
		}
		return sql + " limit " + limit;
	}
	
}