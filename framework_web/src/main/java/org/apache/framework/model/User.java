package org.apache.framework.model;

import java.util.Date;
import org.apache.framework.util.*;
public class User extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2015112211125175462L;
   /**
     * 主键
     */
    private Long id;
    
   /**
     * 用户名
     */
    private String userName;
    
   /**
     * 密码
     */
    private String password;
    
   /**
     * 国家
     */
    private Integer country;
    
   /**
     * 过期日期
     */
    private Date expiredDate;
    
   /**
     * 过期的凭据
     */
    private String credentialsExpired;
    
   /**
     * 用户全名
     */
    private String fullName;
    
   /**
     * 性别(10代表男性，20代表女性)
     */
    private String gender;
    
   /**
     * 年龄
     */
    private Integer age;
    
   /**
     * 地址
     */
    private String address;
    
   /**
     * 电话
     */
    private String phone;
    
   /**
     * 移动电话
     */
    private String mobile;
    
   /**
     * 邮箱地址
     */
    private String email;
    
   /**
     * 用户类型 (10普通用户 20超级管理员)
     */
    private String userType;
    
   /**
     * 通知模式 (1邮箱通知 2电话通知  3短信通知)
     */
    private String notifyMode;
    
   /**
     * 描述
     */
    private String description;
    
   /**
     * 用户状态 (10能正常使用  20用户帐户锁定 30帐号已过期)
     */
    private String status;
    
   /**
     * 用户默认访问的项目code
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
     * 创造者
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
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();       
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();       
    }
    
    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }
    
    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }
    
    public String getCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(String credentialsExpired) {
        this.credentialsExpired = credentialsExpired == null ? null : credentialsExpired.trim();       
    }
    
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();       
    }
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();       
    }
    
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();       
    }
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();       
    }
    
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();       
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();       
    }
    
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();       
    }
    
    public String getNotifyMode() {
        return notifyMode;
    }

    public void setNotifyMode(String notifyMode) {
        this.notifyMode = notifyMode == null ? null : notifyMode.trim();       
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();       
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();       
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
     * 用户状态 (10能正常使用  20用户帐户锁定 30帐号已过期) 文本展示
     */
    public String getStatusText() {
		return CodeUtils.getItemValue("user_states", status);
	}

    
  
    /**
     * 性别(10代表男性，20代表女性) 文本展示
     */
    public String getGenderText() {
		return CodeUtils.getItemValue("gender", gender);
	}

    
  

	/**
     * 数据库一些默认值初始化，方便批量插入用
     * @return
     */
    public static User initDefaultValue() {
		User user = new User();
		user.setLockVersion(0);
		user.setUserType("10");
		user.setStatus("10");
		user.setIsDelete("N");
		user.setIsLocked("N");
		return user;
	}
}