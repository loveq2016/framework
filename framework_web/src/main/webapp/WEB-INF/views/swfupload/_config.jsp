<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="global.jsp" %>

<%-- 图片上传配置信息，默认使用~/mf/global.jsp中配置 --%>
<c:set var="basePath" value="${basePath }" />
<c:set var="uploadUrl" value="${uploadUrl }" />
<c:set var="showUrl" value="${showUrl }" />
<c:set var="fileSizeLimit" value="${fileSizeLimit }" />
<c:set var="fileTypes" value="*.jpg;*.png" />
<c:set var="fileTypesDescription" value="${fileTypesDescription }" />
<c:set var="fileUploadLimit" value="${fileUploadLimit }" />
<c:set var="appName" value="${appName }" />
<c:set var="userId" value="${userId }" />
<c:set var="modelName" value="${modelName }" />
<c:set var="approved" value="${approved }" />
<c:set var="userName" value="${userName }" />
<c:set var="multiple" value="${multiple }" />
<c:set var="callbackMethodName" value="${callbackMethodName }" />
<c:set var="progressBar" value="${progressBar }" />
<c:set var="divWidth" value="${divWidth }" />
<c:set var="approved" value="${approved }" />
<c:set var="targetId" value="${targetId }" />
<c:set var="appendPath" value="${appendPath }" />