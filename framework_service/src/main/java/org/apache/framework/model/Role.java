package org.apache.framework.model;

import java.util.Date;
import org.apache.framework.util.*;

public class Role extends BaseModel {

   /**
	 * 
	 */
	private static final long serialVersionUID = 3330860225013608802L;

/**
     * 主键
     */
    private Long id;
    
   /**
     * 角色名称
     */
    private String name;
    
   /**
     * 项目标识
     */
    private String projectCode;
    
   /**
     * 描述
     */
    private String description;
    
   /**
     * 创建人
     */
    private Long createUserId;
    
   /**
     * 创建时间
     */
    private Date createTime;
    
   /**
     * 更新时间
     */
    private Date updateTime;
    
   /**
     * 备用字段1
     */
    private String rsv1;
    
   /**
     * 备用字段1
     */
    private String rsv2;
    
   /**
     * 备用字段1
     */
    private String rsv3;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();       
    }
    
    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();       
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();       
    }
    
    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }
    
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public String getRsv1() {
        return rsv1;
    }

    public void setRsv1(String rsv1) {
        this.rsv1 = rsv1 == null ? null : rsv1.trim();       
    }
    
    public String getRsv2() {
        return rsv2;
    }

    public void setRsv2(String rsv2) {
        this.rsv2 = rsv2 == null ? null : rsv2.trim();       
    }
    
    public String getRsv3() {
        return rsv3;
    }

    public void setRsv3(String rsv3) {
        this.rsv3 = rsv3 == null ? null : rsv3.trim();       
    }
    
    
}