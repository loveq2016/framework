package org.apache.framework.model;

import java.util.Date;
import org.apache.framework.util.*;

public class RoleResources extends BaseModel {

   /**
	 * 
	 */
	private static final long serialVersionUID = -4769633286916434107L;

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
    
    
}