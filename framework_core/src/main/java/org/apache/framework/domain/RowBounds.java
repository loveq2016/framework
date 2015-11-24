package org.apache.framework.domain;

public class RowBounds extends org.apache.ibatis.session.RowBounds {

	public RowBounds(int offset, int limit) {
		super(offset*limit, limit);
	}
}
