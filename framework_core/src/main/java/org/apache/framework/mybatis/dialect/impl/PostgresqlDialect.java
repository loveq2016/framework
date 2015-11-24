package org.apache.framework.mybatis.dialect.impl;

import org.apache.framework.mybatis.dialect.Dialect;

public class PostgresqlDialect extends Dialect {

	@Override
	public String getPageSql(String sql, int offset, int limit) {
		if (offset > 0) {
			return sql + " limit " + limit + " offset " + offset;
		}
		return sql + " limit " + limit + " offset 0";
	}

}
