package ${package};

<#assign  modelName="${domainObjectName}"/>
<#assign  modelNameVariable="${StringUtils.firstLetterToLowerCase('${domainObjectName}')!}"/>

import ${modelTargetPackage}.${domainObjectName};
import ${modelTargetPackage}.example.${domainObjectName}Example;
import org.apache.framework.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${daoTargetPackage}.${domainObjectName}Dao;
import ${serviceTargetPackage}.${domainObjectName}Service;

@Service(value="${modelNameVariable}Service")
public class ${domainObjectName}ServiceImpl extends BaseServiceImpl<${domainObjectName}, ${domainObjectName}Example>  implements ${domainObjectName}Service  {
	
	private ${domainObjectName}Dao ${modelNameVariable}Dao;

    @Autowired
	public void set${domainObjectName}Dao(${domainObjectName}Dao ${modelNameVariable}Dao) {
		this.${modelNameVariable}Dao = ${modelNameVariable}Dao;
		super.setBaseDao(${modelNameVariable}Dao);
	}
	 
}