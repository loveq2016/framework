<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglibs.jsp" %>
<div style="padding: 10px 0 10px 60px">  

    <%-- 设置 form 的action 地址，id为空，为添加的地址，否则为修改的地址 --%>
    <c:choose>
        <c:when test="${empty code.id}"><c:set var="action" value="code/add"></c:set></c:when>
        <c:otherwise><c:set var="action" value="code/update"></c:set></c:otherwise>
    </c:choose>
	<form:form id="form" method="post" action="${action }" modelAttribute="code">
	    <form:hidden path="id"/>
		<table>
		   <tr>
				<td align="right"><span class="requiredField">*</span>组编号:</td>
				<td>
				   <form:input path="groupNo" class="easyui-validatebox"  data-options="required:true,validType:['length[0,30]']"/>
				</td>
			</tr>
		   <tr>
				<td align="right"><span class="requiredField">*</span>组名称:</td>
				<td>
				   <form:input path="groupName" class="easyui-validatebox"  data-options="required:true,validType:['length[0,30]']"/>
				</td>
			</tr>
		   <tr>
				<td align="right"><span class="requiredField">*</span>节点编号:</td>
				<td>
				   <form:input path="itemNo" class="easyui-validatebox"  data-options="required:true,validType:['length[0,30]']"/>
				</td>
			</tr>
		   <tr>
				<td align="right"><span class="requiredField">*</span>节点键:</td>
				<td>
				   <form:input path="itemKey" class="easyui-validatebox"  data-options="required:true,validType:['length[0,30]']"/>
				</td>
			</tr>
		   <tr>
				<td align="right"><span class="requiredField">*</span>节点值:</td>
				<td>
				   <form:input path="itemValue" class="easyui-validatebox"  data-options="required:true,validType:['length[0,30]']"/>
				</td>
			</tr>
            <tr>
				<td align="right">排序号:</td>
				<td>
				  <form:input path="sequence" class="easyui-numberbox"  max="10000"   data-options="required:false"/> 
				</td>
			</tr>
		</table>
	</form:form>
</div>

<div style="text-align: center; padding: 5px">
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm();">提交</a> 
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="resetForm();">重置</a>
</div>
