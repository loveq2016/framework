package org.apache.framework.model;

import java.util.Date;

public class Code extends BaseModel {

   /**
	 * 
	 */
	private static final long serialVersionUID = -4836746851445501250L;

/**
     * 主键
     */
    private Long id;
    
   /**
     * 组编号
     */
    private String groupNo;
    
   /**
     * 组名称
     */
    private String groupName;
    
   /**
     * 节点编号
     */
    private String itemNo;
    
   /**
     * 节点键
     */
    private String itemKey;
    
   /**
     * 节点值
     */
    private String itemValue;
    
   /**
     * 排序号
     */
    private Integer sequence;
    
   /**
     * 是否使用
     */
    private String isUse;
    
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
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo == null ? null : groupNo.trim();       
    }
    
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();       
    }
    
    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo == null ? null : itemNo.trim();       
    }
    
    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey == null ? null : itemKey.trim();       
    }
    
    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue == null ? null : itemValue.trim();       
    }
    
    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
    
    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse == null ? null : isUse.trim();       
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
    
    /**
     * 是否使用 文本展示
     */
    public String getIsUseText() {
		return CodeUtils.getItemValue("yes_no", isUse);
	}
}