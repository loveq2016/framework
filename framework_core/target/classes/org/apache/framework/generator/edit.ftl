<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglibs.jsp" %><#assign  modelNameVariable="${StringUtils.firstLetterToLowerCase('${domainObjectName}')!}"/>
<div style="padding: 10px 0 10px 60px">  

    <%-- 设置 form 的action 地址，id为空，为添加的地址，否则为修改的地址 --%>
    <c:choose>
        <c:when test="${r'${empty'} ${modelNameVariable }.${r'id}' }"><c:set var="action" value="${modelNameVariable }/add"></c:set></c:when>
        <c:otherwise><c:set var="action" value="${modelNameVariable }/update"></c:set></c:otherwise>
    </c:choose>
	<form:form id="form" method="post" action="${r'${action }'}" modelAttribute="${modelNameVariable }">
	    <form:hidden path="id"/>
		<table>
		<#list columns as column>
        <#if column.className == "String">
		   <tr>
				<td align="right"><span class="requiredField">*</span>${column.remarks}:</td>
				<td>
				   <form:input path="${column.propertyName}" class="easyui-validatebox"  data-options="required:true,validType:['length[0,20]']"/>
				</td>
			</tr>
        <#elseif column.className == "Integer">
            <tr>
				<td align="right">${column.remarks}:</td>
				<td>
				  <form:input path="${column.propertyName}" class="easyui-numberbox"  max="10000"   data-options="required:false"/> 
				</td>
			</tr>
        <#elseif column.className == "Long">
            <tr>
				<td align="right">${column.remarks}:</td>
				<td>
				  <form:input path="${column.propertyName}" class="easyui-numberbox"  max="10000"   data-options="required:false"/> 
				</td>
			</tr>
        </#if>
		</#list>
		</table>
	</form:form>
</div>

<div style="text-align: center; padding: 5px">
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm();">提交</a> 
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="resetForm();">重置</a>
</div>
