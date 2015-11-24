package org.apache.framework.mybatis;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AppendDynamicValueServiceDefaultImpl implements  AppendDynamicValueService {

	@Override
	public Map<String, Object> getInsertData(String namespace) {
		Map<String, Object> insertData = new HashMap<String, Object>();
		insertData.put("createTime", new Date());
		insertData.put("createUserId", 101);
		return insertData;
	}

	@Override
	public Map<String, Object> getUpdateData(String namespace) {
		Map<String, Object> updateData = new HashMap<String, Object>();
		updateData.put("updateTime", new Date());
		return updateData;
	}

}
