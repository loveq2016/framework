<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglibs.jsp" %>
<div style="padding: 10px 0 10px 60px">  

    <%-- 设置 form 的action 地址，id为空，为添加的地址，否则为修改的地址 --%>
    <c:choose>
        <c:when test="${empty parameter.id}"><c:set var="action" value="parameter/add"></c:set></c:when>
        <c:otherwise><c:set var="action" value="parameter/update"></c:set></c:otherwise>
    </c:choose>
	<form:form id="form" method="post" action="${action }" modelAttribute="parameter">
	    <form:hidden path="id"/>
		<table>
            <tr>
				<td align="right">主键:</td>
				<td>
				  <form:input path="id" class="easyui-numberbox"  max="10000"   data-options="required:false"/> 
				</td>
			</tr>
		   <tr>
				<td align="right"><span class="requiredField">*</span>参数名称:</td>
				<td>
				   <form:input path="name" class="easyui-validatebox"  data-options="required:true,validType:['length[0,20]']"/>
				</td>
			</tr>
		   <tr>
				<td align="right"><span class="requiredField">*</span>参数key:</td>
				<td>
				   <form:input path="key" class="easyui-validatebox"  data-options="required:true,validType:['length[0,20]']"/>
				</td>
			</tr>
		   <tr>
				<td align="right"><span class="requiredField">*</span>参数value:</td>
				<td>
				   <form:input path="value" class="easyui-validatebox"  data-options="required:true,validType:['length[0,20]']"/>
				</td>
			</tr>
		   <tr>
				<td align="right"><span class="requiredField">*</span>描述:</td>
				<td>
				   <form:input path="description" class="easyui-validatebox"  data-options="required:true,validType:['length[0,20]']"/>
				</td>
			</tr>
            <tr>
				<td align="right">锁版本号:</td>
				<td>
				  <form:input path="lockVersion" class="easyui-numberbox"  max="10000"   data-options="required:false"/> 
				</td>
			</tr>
            <tr>
				<td align="right">创建人:</td>
				<td>
				  <form:input path="createUserId" class="easyui-numberbox"  max="10000"   data-options="required:false"/> 
				</td>
			</tr>
            <tr>
				<td align="right">更新人id:</td>
				<td>
				  <form:input path="updateUserId" class="easyui-numberbox"  max="10000"   data-options="required:false"/> 
				</td>
			</tr>
		   <tr>
				<td align="right"><span class="requiredField">*</span>是否锁定， Y=是， N=否， 用于并发控制，比如审核:</td>
				<td>
				   <form:input path="isLocked" class="easyui-validatebox"  data-options="required:true,validType:['length[0,20]']"/>
				</td>
			</tr>
		   <tr>
				<td align="right"><span class="requiredField">*</span>是否删除， Y=是， N=否:</td>
				<td>
				   <form:input path="isDelete" class="easyui-validatebox"  data-options="required:true,validType:['length[0,20]']"/>
				</td>
			</tr>
		   <tr>
				<td align="right"><span class="requiredField">*</span>备用字段1:</td>
				<td>
				   <form:input path="rsv1" class="easyui-validatebox"  data-options="required:true,validType:['length[0,20]']"/>
				</td>
			</tr>
		   <tr>
				<td align="right"><span class="requiredField">*</span>备用字段1:</td>
				<td>
				   <form:input path="rsv2" class="easyui-validatebox"  data-options="required:true,validType:['length[0,20]']"/>
				</td>
			</tr>
		   <tr>
				<td align="right"><span class="requiredField">*</span>备用字段1:</td>
				<td>
				   <form:input path="rsv3" class="easyui-validatebox"  data-options="required:true,validType:['length[0,20]']"/>
				</td>
			</tr>
		</table>
	</form:form>
</div>

<div style="text-align: center; padding: 5px">
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm();">提交</a> 
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="resetForm();">重置</a>
</div>
