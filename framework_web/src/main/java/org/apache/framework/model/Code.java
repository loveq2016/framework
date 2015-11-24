package org.apache.framework.model;

import java.util.Date;
import org.apache.framework.util.*;
public class Code extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2015112211125138351L;
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
     * 是否使用 文本展示
     */
    public String getIsUseText() {
		return CodeUtils.getItemValue("yes_no", isUse);
	}

    
  

	/**
     * 数据库一些默认值初始化，方便批量插入用
     * @return
     */
    public static Code initDefaultValue() {
		Code code = new Code();
		return code;
	}
}