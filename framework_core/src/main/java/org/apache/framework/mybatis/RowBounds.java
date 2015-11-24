package org.apache.framework.mybatis;

public class RowBounds extends org.apache.ibatis.session.RowBounds {

	public RowBounds(int offset, int limit) {
		super(offset*limit, limit);
	}
}
