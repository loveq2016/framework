<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    //=================================================
    //全局参数设置
    basePath += ""; // 系统资源前缀路径，如有不同需根据实际情况修改。
    String uploadUrl = "http://127.0.0.1:8880/"; // 上传地址
    String showUrl = "http://192.168.9.200:8081/download/upload/"; // 文件绝对路径前缀
    String fileSizeLimit = "100 MB"; // 上传文件大小限制
    String fileTypesDescription = "All Files"; // 文件类型描述
    String fileUploadLimit = "100"; // 上传文件数量限制
    
    //=================================================
    //客户端可传人参数
    String targetId = request.getParameter("targetId");
    String appendPath = request.getParameter("appendPath"); 
    String fileTypes = request.getParameter("fileTypes"); // 上传文件类型限制，多种类型可以用英文逗号隔开 *.jpg,*.png  *.*
    String appName = request.getParameter("appName"); // 应用名称（必填）
    String modelName = request.getParameter("modelName"); // 模块名称，默认为file
    String userId = request.getParameter("userId"); // 用户ID （必填）
    String userName = request.getParameter("userName"); // 用户名称
    String approved = request.getParameter("approved"); // 是否需要审核：true/false，默认false不审核
    String multipleStr = request.getParameter("multiple"); // 多上传标志：true/false，默认true多文件上传
    String callbackMethodName = request.getParameter("callback"); // 回调方法名称
    String progressBar = request.getParameter("progressBar"); // 是否显示上传进度条：true/false，默认true显示
    //备注：显示进度条时候，上传按钮和进度条总宽度为99px；如果不显示进度条总宽度为71px；
    //=================================================

    String multiple = "SWFUpload.BUTTON_ACTION.SELECT_FILES";
    if (multipleStr != null && "false".equals(multipleStr.trim())) {
        multiple = "SWFUpload.BUTTON_ACTION.SELECT_FILE";
    }
    String divWidth = ("false".equals(progressBar) ? "71px" : "99px");
    if (modelName == null || (modelName != null && modelName.trim().length() == 0)) {
        modelName = "file";
    }
    if ("true".equals(approved)) {
        approved = "true";
    }else {
    	approved = "false";
    }
    
    if (fileTypes == null || fileTypes.trim().length() == 0) {
    	fileTypes = " *.jpg;*.png";
    }
%>
<c:set var="basePath" value="<%=basePath%>" />
<c:set var="uploadUrl" value="<%=uploadUrl%>" />
<c:set var="showUrl" value="<%=showUrl%>" />
<c:set var="fileSizeLimit" value="<%=fileSizeLimit%>" />
<c:set var="fileTypes" value="<%=fileTypes%>" />
<c:set var="fileTypesDescription" value="<%=fileTypesDescription%>" />
<c:set var="fileUploadLimit" value="<%=fileUploadLimit%>" />
<c:set var="appName" value="<%=appName%>" />
<c:set var="userId" value="<%=userId%>" />
<c:set var="userName" value="<%=userName%>" />
<c:set var="modelName" value="<%=modelName%>" />
<c:set var="approved" value="<%=approved%>" />
<c:set var="userName" value="<%=userName%>" />
<c:set var="multiple" value="<%=multiple%>" />
<c:set var="callbackMethodName" value="<%=callbackMethodName%>" />
<c:set var="progressBar" value="<%=progressBar%>" />
<c:set var="divWidth" value="<%=divWidth%>" />
<c:set var="approved" value="<%=approved%>" />
<c:set var="targetId" value="<%=targetId%>" />
<c:set var="appendPath" value="<%=appendPath%>" />
