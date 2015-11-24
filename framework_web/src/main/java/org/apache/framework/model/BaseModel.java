package org.apache.framework.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;


/**
 * 这个下面使用的代码生成器,应该尽力脱离代码生成器生成的代码, 因为后台要收集数据，所以把属性写在这里
 * 
 * @author willenfoo
 * 
 */
public abstract class BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4777700470190182805L;

	private String state;
	
	private boolean selected;
	
	private List<?> children; //为了 jquery easyui 树形结构展示， 子节点

	private Set<String> roles;

	private Set<String> permissions;
	
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<?> getChildren() {
		return children;
	}

	public void setChildren(List<?> children) {
		this.children = children;
	}
	
	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	
	public Set<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
