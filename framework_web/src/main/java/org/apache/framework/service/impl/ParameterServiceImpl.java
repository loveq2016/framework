package org.apache.framework.service.impl;


import org.apache.framework.model.Parameter;
import org.apache.framework.model.example.ParameterExample;
import org.apache.framework.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.framework.dao.ParameterDao;
import org.apache.framework.service.ParameterService;

@Service(value="parameterService")
public class ParameterServiceImpl extends BaseServiceImpl<Parameter, ParameterExample>  implements ParameterService  {
	
	private ParameterDao parameterDao;

    @Autowired
	public void setParameterDao(ParameterDao parameterDao) {
		this.parameterDao = parameterDao;
		super.setBaseDao(parameterDao);
	}
	 
}