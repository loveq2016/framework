<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglibs.jsp" %>
<div style="padding: 10px 0 10px 60px">  

    <%-- 设置 form 的action 地址，id为空，为添加的地址，否则为修改的地址 --%>
    <c:choose>
        <c:when test="${empty resources.id}"><c:set var="action" value="resources/add"></c:set></c:when>
        <c:otherwise><c:set var="action" value="resources/update"></c:set></c:otherwise>
    </c:choose>
	<form:form id="form" method="post" action="${action }" modelAttribute="resources">
	    <form:hidden path="id"/>
	    <form:hidden path="parentId"/>
		<table>
		    <tr>
				<td align="right">名称<span class="requiredField">*</span>:</td>
				<td>
				   <form:input path="name" class="easyui-validatebox" type="text" data-options="required:true,validType:['length[0,20]']"/>
				</td>
			</tr>
			<tr>
				<td align="right">类型<span class="requiredField">*</span>:</td>
				<td>
				   <input class="easyui-combobox" 
			            name="type"
			            data-options="
			                    required:true,
			                    editable:false,
			                    url:'code/findByGroupNo?groupNo=resources_type&selected=${resources.type }',
			                    method:'get',
			                    valueField:'itemKey',
			                    textField:'itemValue',
			                    panelHeight:'auto'
			            ">
				</td>
			</tr>
			<tr>
				<td align="right">code<span class="requiredField">*</span>:</td>
				<td>
				   <form:input path="code" class="easyui-validatebox" type="text" data-options="required:true,validType:['length[0,30]']"/>
				</td>
			</tr>
		   <tr>
				<td align="right">URL地址:</td>
				<td>
				   <form:input path="linkUrl" class="easyui-validatebox" type="text" data-options="required:false,validType:['length[0,200]']"/>
				</td>
			</tr>
		   <tr>
				<td align="right">描述:</td>
				<td>
				   <form:input path="description" class="easyui-validatebox" type="text" data-options="required:false,validType:['length[0,200]']"/>
				</td>
			</tr>
            <tr>
				<td align="right">排序号:</td>
				<td>
				  <form:input path="sequence" class="easyui-numberbox"  min="0"   max="10000" type="text"  data-options="required:false"/> 
				</td>
			</tr>
		</table>
	</form:form>
</div>

<div style="text-align: center; padding: 5px">
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm();">提交</a> 
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="resetForm();">重置</a>
</div>
