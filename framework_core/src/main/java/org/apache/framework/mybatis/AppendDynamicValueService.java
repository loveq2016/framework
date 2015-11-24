package org.apache.framework.mybatis;

import java.util.Map;

public interface AppendDynamicValueService {

	public Map<String, Object> getInsertData(String namespace);
	
	public Map<String, Object> getUpdateData(String namespace);
}
