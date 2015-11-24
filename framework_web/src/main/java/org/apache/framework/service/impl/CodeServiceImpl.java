package org.apache.framework.service.impl;


import org.apache.framework.model.Code;
import org.apache.framework.model.example.CodeExample;
import org.apache.framework.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.framework.dao.CodeDao;
import org.apache.framework.service.CodeService;

@Service(value="codeService")
public class CodeServiceImpl extends BaseServiceImpl<Code, CodeExample>  implements CodeService  {
	
	private CodeDao codeDao;

    @Autowired
	public void setCodeDao(CodeDao codeDao) {
		this.codeDao = codeDao;
		super.setBaseDao(codeDao);
	}
	 
}