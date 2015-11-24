package org.apache.framework.mybatis.interceptor;

import java.util.Map;
import java.util.Properties;

import org.apache.framework.mybatis.AppendDynamicValueService;
import org.apache.framework.mybatis.AppendDynamicValueServiceDefaultImpl;
import org.apache.framework.util.ReflectUtils;
import org.apache.framework.util.SpringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

@Intercepts({@Signature(
		  type= Executor.class,
		  method = "update",
		  args = {MappedStatement.class,Object.class})})
public class AppendDynamicValueInterceptor implements Interceptor {

	private Properties properties;

	private static AppendDynamicValueService appendDynamicValueService;
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];  
        String sqlId = mappedStatement.getId();  
        String namespace = sqlId.substring(0, sqlId.lastIndexOf('.'));  
        String methodName = sqlId.substring(sqlId.lastIndexOf('.'), sqlId.length());
        if (appendDynamicValueService == null) {
        	if (SpringUtils.containsBean("appendDynamicValueService")) {
            	appendDynamicValueService = SpringUtils.getBean("appendDynamicValueService", AppendDynamicValueService.class);
            } else {
            	appendDynamicValueService = new AppendDynamicValueServiceDefaultImpl();
            }
        }
        
        Map<String, Object> map = null;
        if (methodName.toLowerCase().indexOf("insert") > 0) {
        	map = appendDynamicValueService.getInsertData(namespace);
        } else if (methodName.toLowerCase().indexOf("update") > 0) {
        	map = appendDynamicValueService.getUpdateData(namespace);
        }
        if (map != null && !map.isEmpty()) {
        	Object parameter = invocation.getArgs()[1];  
    		for (Map.Entry<String, Object> entry : map.entrySet()) {
    			ReflectUtils.setProperty(parameter, entry.getKey(), entry.getValue());
    		}
        }
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
