package org.apache.framework.model;

import java.util.Date;
import org.apache.framework.util.*;
public class Resources extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2015112211125117385L;
   /**
     * 主键
     */
    private Long id;
    
   /**
     * 资源名称
     */
    private String name;
    
   /**
     * 模块URL地址
     */
    private String linkUrl;
    
   /**
     * 描述
     */
    private String description;
    
   /**
     * 父模块ID，如果是顶级模块，为-1
     */
    private Long parentId;
    
   /**
     * 排序号
     */
    private Integer sequence;
    
   /**
     * 模块code，权限控制
     */
    private String code;
    
   /**
     * 资源类型(10是菜单 20是方法)
     */
    private String type;
    
   /**
     * 项目标识
     */
    private String projectCode;
    
   /**
     * 锁版本号
     */
    private Integer lockVersion;
    
   /**
     * 创建时间
     */
    private Date createTime;
    
   /**
     * 创建人
     */
    private Long createUserId;
    
   /**
     * 更新时间
     */
    private Date updateTime;
    
   /**
     * 更新人id
     */
    private Long updateUserId;
    
   /**
     * 是否锁定， Y=是， N=否， 用于并发控制，比如审核
     */
    private String isLocked;
    
   /**
     * 是否删除， Y=是， N=否
     */
    private String isDelete;
    
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
    
    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();       
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();       
    }
    
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    
    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();       
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();       
    }
    
    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();       
    }
    
    public Integer getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }
    
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }
    
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }
    
    public String getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked == null ? null : isLocked.trim();       
    }
    
    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();       
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
    
    
    /**
     * 资源类型(10是菜单 20是方法) 文本展示
     */
    public String getTypeText() {
		return CodeUtils.getItemValue("resources_types", type);
	}

    
  

	/**
     * 数据库一些默认值初始化，方便批量插入用
     * @return
     */
    public static Resources initDefaultValue() {
		Resources resources = new Resources();
		return resources;
	}
}