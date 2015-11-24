package org.apache.framework.model;

import java.util.Date;
import org.apache.framework.util.*;
public class RoleResources extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2015112211125108451L;
   /**
     * 角色ID
     */
    private Long roleId;
    
   /**
     * 资源ID
     */
    private Long resourcesId;
    

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    
    public Long getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(Long resourcesId) {
        this.resourcesId = resourcesId;
    }
    
    

	/**
     * 数据库一些默认值初始化，方便批量插入用
     * @return
     */
    public static RoleResources initDefaultValue() {
		RoleResources roleResources = new RoleResources();
		return roleResources;
	}
}