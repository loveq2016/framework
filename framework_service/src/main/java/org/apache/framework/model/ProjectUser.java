package org.apache.framework.model;

import java.util.Date;
import org.apache.framework.util.*;

public class ProjectUser extends BaseModel {

   /**
	 * 
	 */
	private static final long serialVersionUID = -4309836104398680344L;

/**
     * 项目标识
     */
    private String projectCode;
    
   /**
     * 用户id
     */
    private Long userId;
    

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();       
    }
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    
}