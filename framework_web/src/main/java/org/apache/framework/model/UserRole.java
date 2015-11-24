package org.apache.framework.model;

import java.util.Date;
import org.apache.framework.util.*;
public class UserRole extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2015112211125101616L;
   /**
     * 用户ID
     */
    private Long userId;
    
   /**
     * 角色id
     */
    private Long roleId;
    

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    
    

	/**
     * 数据库一些默认值初始化，方便批量插入用
     * @return
     */
    public static UserRole initDefaultValue() {
		UserRole userRole = new UserRole();
		return userRole;
	}
}