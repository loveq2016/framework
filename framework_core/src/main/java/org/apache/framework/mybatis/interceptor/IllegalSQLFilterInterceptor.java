package org.apache.framework.mybatis.interceptor;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.framework.logging.Logger;
import org.apache.framework.logging.LoggerFactory;
import org.apache.framework.util.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;

@Intercepts({
    @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class IllegalSQLFilterInterceptor implements Interceptor {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(IllegalSQLFilterInterceptor.class);
	
	private Properties properties;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = null;
        if (invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }
        Configuration configuration = mappedStatement.getConfiguration();
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        String sql = StringUtils.trim(boundSql.getSql());
        String DMLCommand= sql.substring(0, 6);
        if ("update".equalsIgnoreCase(DMLCommand) || "delete".equalsIgnoreCase(DMLCommand)) {
        	if (!validateSqlWhere(configuration, boundSql)) {
        		String errorMsg = "非法的DML语句,SQL语句="+showSql(configuration, boundSql)+" delete、update必须带where条件";
        		LOGGER.error(errorMsg);
        		throw new RuntimeException(errorMsg);
        	}
        }
		return invocation.proceed();
	}

    public static String showSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    }
                }
            }
        }
        return sql;
    }
    
    public static boolean validateSqlWhere(Configuration configuration, BoundSql boundSql) {
    	Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        Pattern pattern = Pattern.compile("(?i)where(.*)");
        Matcher matcher = pattern.matcher(sql);
        if (matcher.find()) {
        	String whereSql = matcher.group(matcher.groupCount());
        	pattern = Pattern.compile("(?i)is_delete = \\?(.*)");
            matcher = pattern.matcher(whereSql);
            
            boolean flag = false;
            if (matcher.find()) {
            	if (parameterMappings.size() > 1) {
            		flag = true;
            	}
            } else {
				if (parameterMappings.size() > 0) {
					flag = true;
            	}
            }
        	if (flag && parameterMappings.size() > 0 && parameterObject != null) {
                TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
                if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                	String value = getParameterValue(parameterObject);
                	if (StringUtils.isEmpty(value)) {
                    	return false;
                    }
                } else {
                    MetaObject metaObject = configuration.newMetaObject(parameterObject);
                    for (ParameterMapping parameterMapping : parameterMappings) {
                        String propertyName = parameterMapping.getProperty();
                        if (metaObject.hasGetter(propertyName)) {
                             Object obj = metaObject.getValue(propertyName);
                            if (obj == null) {
                            	return false;
                            }
                        } else if (boundSql.hasAdditionalParameter(propertyName)) {
                            Object obj = boundSql.getAdditionalParameter(propertyName);
                            if (obj == null) {
                            	return false;
                            }
                        }
                    }
                }
            } else {
            	return false;
            }
        } else {
        	return false;
        }
        return true;
    }
    
    private static String getParameterValue(Object obj) {
        String value = null;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }
 
        }
        return value;
    }
    
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	 

}
