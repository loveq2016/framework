package org.apache.framework.generator;

import java.io.InputStream;
import java.util.*;

import org.apache.framework.domain.Column;
import org.apache.framework.domain.Property;
import org.apache.framework.domain.Table;
import org.apache.framework.enums.YesNo;
import org.apache.framework.freemarker.FreemarkerHelper;
import org.apache.framework.util.DomUtils;
import org.apache.framework.util.FileUtils;
import org.apache.framework.util.JdbcUtils;
import org.apache.framework.util.StringUtils;
import org.w3c.dom.Element;

public class Generator {
	protected String column;
	
	private static JdbcUtils jdbcUtil;
	
	public JdbcUtils getJdbcUtil() {
		return jdbcUtil;
	}

	
	public  void execute(InputStream is) {
		
		
		
		Element rootElement = DomUtils.getRootElementFromStream(is);
		
		Element view = DomUtils.getElement(rootElement, "view");
		Map<String, String> viewMap = new HashMap<String, String>();
		viewMap.put(Config.TARGET_PACKAGE, view.getAttribute(Config.TARGET_PACKAGE));
		viewMap.put(Config.TARGET_PROJECT, view.getAttribute(Config.TARGET_PROJECT));
		
			
		Element controller = DomUtils.getElement(rootElement, "controller");
		Map<String, String> controllerMap = new HashMap<String, String>();
		controllerMap.put(Config.TARGET_PACKAGE, controller.getAttribute(Config.TARGET_PACKAGE));
		controllerMap.put(Config.TARGET_PROJECT, controller.getAttribute(Config.TARGET_PROJECT));
		
		
		Element javaClient = DomUtils.getElement(rootElement, Config.JAVA_CLIENT);
		Map<String, String> javaClientMap = new HashMap<String, String>();
		javaClientMap.put(Config.TARGET_PACKAGE, javaClient.getAttribute(Config.TARGET_PACKAGE));
		javaClientMap.put(Config.TARGET_PROJECT, javaClient.getAttribute(Config.TARGET_PROJECT));
		
		Element sqlMap = DomUtils.getElement(rootElement, Config.SQL_MAP);
		Map<String, String> sqlMapMap = new HashMap<String, String>();
		sqlMapMap.put(Config.TARGET_PACKAGE, sqlMap.getAttribute(Config.TARGET_PACKAGE));
		sqlMapMap.put(Config.TARGET_PROJECT, sqlMap.getAttribute(Config.TARGET_PROJECT));
		
		
		Element javaModel = DomUtils.getElement(rootElement, Config.JAVA_MODEL);
		Map<String, String> javaModelMap = new HashMap<String, String>();
		javaModelMap.put(Config.TARGET_PACKAGE, javaModel.getAttribute(Config.TARGET_PACKAGE));
		javaModelMap.put(Config.TARGET_PROJECT, javaModel.getAttribute(Config.TARGET_PROJECT));
		
		Element service = DomUtils.getElement(rootElement, "service");
		Map<String, String> serviceMap = new HashMap<String, String>();
		serviceMap.put(Config.TARGET_PACKAGE, service.getAttribute(Config.TARGET_PACKAGE));
		serviceMap.put(Config.TARGET_PROJECT, service.getAttribute(Config.TARGET_PROJECT));
		
		
		
		Element jdbcConnection = DomUtils.getElement(rootElement, Config.JDBC_CONNECTION);
		Map<String, String> jdbcConMap = new HashMap<String, String>();
		jdbcConMap.put(Config.DRIVER_CLASS, jdbcConnection.getAttribute(Config.DRIVER_CLASS));
		jdbcConMap.put(Config.USER_ID, jdbcConnection.getAttribute(Config.USER_ID));
		jdbcConMap.put(Config.PASSWORD, jdbcConnection.getAttribute(Config.PASSWORD));
		jdbcConMap.put(Config.CONNECTION_URL, jdbcConnection.getAttribute(Config.CONNECTION_URL));
		
		List<Element> tableList = DomUtils.getElements(rootElement, Config.TABLE);
		List<Map<String, String>> tables = new ArrayList<Map<String,String>>();
		
		Map<String, List<Property>> aaaa = new HashMap<String, List<Property>>(); 
		if (tableList != null && !tableList.isEmpty()) {
			for (Element element : tableList) {
				Map<String, String> tableMap = new HashMap<String, String>();
				tableMap.put(Config.TABLE_NAME, element.getAttribute(Config.TABLE_NAME));
				tableMap.put(Config.DOMAIN_OBJECT_NAME, element.getAttribute(Config.DOMAIN_OBJECT_NAME));
				tableMap.put(Config.MODULE_NAME, element.getAttribute(Config.MODULE_NAME));
				
				tables.add(tableMap);
				
				List<Element> propertys = DomUtils.getElements(element, "property");
		        if (propertys != null) {
		        	List<Property> propertyss = new ArrayList<Property>();
		        	for (Element element2 : propertys) {
						Property property = new Property();
						property.setName(element2.getAttribute("name"));
						property.setShowTextName(element2.getAttribute("showTextName"));
						property.setCodeGroupNo(element2.getAttribute("codeGroupNo"));
						propertyss.add(property);
					}
		        	aaaa.put(element.getAttribute(Config.DOMAIN_OBJECT_NAME), propertyss);
		        }
				
			}
		} 
		
		jdbcUtil = new JdbcUtils(jdbcConMap.get(Config.CONNECTION_URL), jdbcConMap.get(Config.USER_ID), jdbcConMap.get(Config.PASSWORD), jdbcConMap.get(Config.DRIVER_CLASS));

		if(tables == null || tables.isEmpty()) {
			List<Table> list = jdbcUtil.getTabels();
			for (Table table : list) {
				Map<String, String> tableMap = new HashMap<String, String>();
				tableMap.put(Config.TABLE_NAME, table.getName());
				tableMap.put(Config.DOMAIN_OBJECT_NAME, StringUtils.firstLetterToUpperCase(StringUtils.convertColumn(table.getName())));
				tables.add(tableMap);
			}
		}

		FreemarkerHelper viewEngine = new FreemarkerHelper("/org/apache/framework/generator");
		
		Map<String, Object> params = new HashMap<String, Object>();
		 
		for (Map<String, String> map : tables) {
			params.putAll(map);
			params.putAll(javaModelMap);
			List<Column> columns = jdbcUtil.getColumnByTableName(map.get(Config.TABLE_NAME));
			params.put("columns", columns);
			
			params.put("propertys", aaaa.get(map.get(Config.DOMAIN_OBJECT_NAME)));
			
			StringBuilder columnList = new StringBuilder();
			for (Column column : columns) {
				if (YesNo.YES.getCode().equals(column.getIsPrimaryKey())) {
					params.put("pkColumn", column);
				} 
				columnList.append(column.getName()+", ");
				
				List<Property> propertys = aaaa.get(map.get(Config.DOMAIN_OBJECT_NAME));
				if (propertys != null && !propertys.isEmpty()) {
					for (Property property : propertys) {
						if (property.getName().equals(column.getPropertyName())) {
							property.setRemarks(column.getRemarks());
						}
					}
				}
			}
			columnList.deleteCharAt(columnList.length()-1);
			columnList.deleteCharAt(columnList.length()-1);
			params.put("columnList", columnList.toString());
			
			
			String modelText = viewEngine.parse("model.ftl", params);
			FileUtils.write(modelText, getModelAddress(javaModelMap, map)+Config.JAVA);
			
			String exampleText = viewEngine.parse("example.ftl", params);
			FileUtils.write(exampleText, getExampleAddress(javaModelMap, map)+Config.JAVA);
			
			params.put("modelTargetPackage", javaModelMap.get(Config.TARGET_PACKAGE));
			params.put("daoTargetPackage", javaClientMap.get(Config.TARGET_PACKAGE));
			params.put("serviceTargetPackage", serviceMap.get(Config.TARGET_PACKAGE));
			params.put("modelPackage", javaModelMap.get(Config.TARGET_PACKAGE)+".*");
			params.put("examplePackage", javaModelMap.get(Config.TARGET_PACKAGE)+".example.*");
			String daoText = viewEngine.parse("dao.ftl", params);
			FileUtils.write(daoText, getDaoAddress(javaClientMap, map)+Config.JAVA);
			
			String mapperText = viewEngine.parse("mapper.ftl", params);
			FileUtils.write(mapperText, getMapperAddress(sqlMapMap, map)+Config.XML);
			
			params.put("package", serviceMap.get(Config.TARGET_PACKAGE));
			params.put("servicePackage", serviceMap.get(Config.TARGET_PACKAGE)+".*");
			String serviceText = viewEngine.parse("service.ftl", params);
			String serviceAddress = getServiceAddress(serviceMap, map)+Config.JAVA;
			FileUtils.write(serviceText, serviceAddress);
			
			
			params.put("package", serviceMap.get(Config.TARGET_PACKAGE)+".impl");
			String serviceImplText = viewEngine.parse("service_impl.ftl", params);
			String serviceImplAddress = getServiceImplAddress(serviceMap, map)+Config.JAVA;
			FileUtils.write(serviceImplText, serviceImplAddress);
			
			params.put("package", controllerMap.get(Config.TARGET_PACKAGE)+"."+map.get(Config.MODULE_NAME));
			String controllerText = viewEngine.parse("controller.ftl", params);
			String controllerAddress = getControllerAddress(controllerMap, map)+Config.JAVA;
			FileUtils.write(controllerText, controllerAddress);
			
			String findText = viewEngine.parse("find.ftl", params);
			String findAddress = getFindAddress(viewMap, map)+Config.JSP;
			FileUtils.write(findText, findAddress);
			
			String editText = viewEngine.parse("edit.ftl", params);
			String editAddress = getEditAddress(viewMap, map)+Config.JSP;
			FileUtils.write(editText, editAddress);
		}
	}
	
	private String getEditAddress(Map<String, String> viewMap,
			Map<String, String> map) {
		return viewMap.get(Config.TARGET_PROJECT)+"/"+map.get(Config.MODULE_NAME)+"/"+StringUtils.firstLetterToLowerCase(map.get(Config.DOMAIN_OBJECT_NAME))+"/"+"edit";
	}
	
	private String getFindAddress(Map<String, String> viewMap,
			Map<String, String> map) {
		return viewMap.get(Config.TARGET_PROJECT)+"/"+map.get(Config.MODULE_NAME)+"/"+StringUtils.firstLetterToLowerCase(map.get(Config.DOMAIN_OBJECT_NAME))+"/"+"find";
	}
	
	private String getControllerAddress(Map<String, String> controllerMap,
			Map<String, String> map) {
		return controllerMap.get(Config.TARGET_PROJECT)+"/"+controllerMap.get(Config.TARGET_PACKAGE).replaceAll("\\.", "/")+"/"+map.get(Config.MODULE_NAME)+"/"+map.get(Config.DOMAIN_OBJECT_NAME)+"Controller";
	}
	
	private String getServiceImplAddress(Map<String, String> serviceMap,
			Map<String, String> map) {
		return serviceMap.get(Config.TARGET_PROJECT)+"/"+serviceMap.get(Config.TARGET_PACKAGE).replaceAll("\\.", "/")+"/impl/"+map.get(Config.DOMAIN_OBJECT_NAME)+"ServiceImpl";
	}


	private String getModelAddress(Map<String, String> javaModelMap, Map<String, String> map) {
		return javaModelMap.get(Config.TARGET_PROJECT)+"/"+javaModelMap.get(Config.TARGET_PACKAGE).replaceAll("\\.", "/")+"/"+map.get(Config.DOMAIN_OBJECT_NAME);
	}
	
	private String getExampleAddress(Map<String, String> javaModelMap, Map<String, String> map) {
		return javaModelMap.get(Config.TARGET_PROJECT)+"/"+javaModelMap.get(Config.TARGET_PACKAGE).replaceAll("\\.", "/")+"/example/"+map.get(Config.DOMAIN_OBJECT_NAME)+"Example";
	}
	
	private String getServiceAddress(Map<String, String> serviceMap, Map<String, String> map) {
		return serviceMap.get(Config.TARGET_PROJECT)+"/"+serviceMap.get(Config.TARGET_PACKAGE).replaceAll("\\.", "/")+"/"+map.get(Config.DOMAIN_OBJECT_NAME)+"Service";
	}
	
	private String getDaoAddress(Map<String, String> javaClientMap, Map<String, String> map) {
		return javaClientMap.get(Config.TARGET_PROJECT)+"/"+javaClientMap.get(Config.TARGET_PACKAGE).replaceAll("\\.", "/")+"/"+map.get(Config.DOMAIN_OBJECT_NAME)+"Dao";
	}
	
	private String getMapperAddress(Map<String, String> sqlMapMap, Map<String, String> map) {
		return sqlMapMap.get(Config.TARGET_PROJECT)+"/"+sqlMapMap.get(Config.TARGET_PACKAGE).replaceAll("\\.", "/")+"/"+map.get(Config.DOMAIN_OBJECT_NAME)+"Mapper";
	}
	
}
