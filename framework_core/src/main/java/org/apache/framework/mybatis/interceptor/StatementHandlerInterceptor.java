package org.apache.framework.mybatis.interceptor;

import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.framework.requestbinding.RequestBinding;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;

@Intercepts({ @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class }) })
public class StatementHandlerInterceptor implements Interceptor {
  /**
   * Logger for this class
   */
   

  private static String pid = "unknow";

  static {
    pid = ManagementFactory.getRuntimeMXBean().getName();
  }

  public Object intercept(Invocation invocation) throws Throwable {

    StatementHandler statementHandler = (StatementHandler) invocation.getTarget();

    MetaObject metaStatementHandler = MetaObject.forObject(statementHandler);

    Configuration configuration = (Configuration) metaStatementHandler
        .getValue("delegate.configuration");

    BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");

    metaStatementHandler.setValue("delegate.boundSql.sql",
        buildSql(boundSql.getSql(), configuration));
    // 将执行权交给下一个拦截器
    Object returnObject = invocation.proceed();
    return returnObject;
  }

  private String buildSql(String sql, Configuration configuration) {

    if (sql.indexOf(" /*requestId:") != -1) {
      return sql;
    } else {
      String db = null;
      Environment env = null;
      if (configuration != null) {
        env = configuration.getEnvironment();
      }
      if (env != null) {
        db = env.getId();
      }
      /**
       * 删除sql \/*...*\/的多行注释，和以 -- 开始的单行注释
       */
      Pattern p = Pattern.compile("(?ms)('(?:''|[^'])*')|--.*?$|/\\*.*?\\*/");  
      sql = p.matcher(sql).replaceAll("$1");
      
      StringBuilder sb = new StringBuilder(sql);
      sb.append(" /*requestId:");
      sb.append(RequestBinding.getRequestId());
      sb.append(" requestUrl:");
      sb.append(RequestBinding.getRequestUrl());
      sb.append(pid);
      sb.append(" ");
      sb.append("spid()");
      sb.append(" ");
      sb.append(db);
      sb.append("*/");
      
      sql = sb.toString().replaceAll("\n", " ").replaceAll("\t", " ").replaceAll("[\\s]+", " ");

      return sql;
    }
  }

  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  public void setProperties(Properties properties) {
	  
  }
  
}
