<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglibs.jsp" %>
<div style="padding: 10px 0 10px 60px">  

    <%-- 设置 form 的action 地址，id为空，为添加的地址，否则为修改的地址 --%>
    <c:choose>
        <c:when test="${empty user.id}"><c:set var="action" value="user/add"></c:set></c:when>
        <c:otherwise><c:set var="action" value="user/update"></c:set></c:otherwise>
    </c:choose>
	<form:form id="form" method="post" action="${action }" modelAttribute="user">
	    <form:hidden path="id"/>
		<table>
			<c:choose>
		        <c:when test="${empty user.id}">
		             <tr>
						<td align="right"><span class="requiredField">*</span>用户名:</td>
						<td>
						   <form:input path="userName" class="easyui-validatebox" onblur="isExistByUserName();" data-options="required:true,validType:['length[0,20]']"/>
						</td>
					</tr>
				    <tr>
						<td align="right"><span class="requiredField">*</span>密码:</td>
						<td>
						   <form:password path="password" class="easyui-validatebox"  data-options="required:true,validType:['length[0,20]']"/>
						</td>
					</tr>
					<tr>
						<td align="right"><span class="requiredField">*</span>确认密码:</td>
						<td>
						   <input id="confirmPassword" name="confirmPassword" class="easyui-validatebox" data-options="required:true"  validType="equalTo['#password']"  type="password" invalidMessage="两次输入密码不匹配"/>
						</td>
					</tr>
		        </c:when>
		        <c:otherwise>
                    <tr>
						<td align="right">用户名:</td>
						<td>
						   ${user.userName }
						</td>
					</tr>
                </c:otherwise>
		    </c:choose>
    
		   <tr>
				<td align="right">用户全名:</td>
				<td>
				   <form:input path="fullName" class="easyui-validatebox"  data-options="required:false,validType:['length[0,200]']"/>
				</td>
			</tr>
			<tr>
				<td align="right">性别:</td>
				<td>
				    <input class="easyui-combobox" 
			            name="gender"
			            data-options="
			                    editable:false,
			                    url:'code/findByGroupNo?groupNo=gender&selected=${user.gender }',
			                    method:'get',
			                    valueField:'itemKey',
			                    textField:'itemValue',
			                    panelHeight:'auto'
			            ">
				</td>
			</tr>
		   <tr>
				<td align="right">年龄:</td>
				<td>
				   <form:input path="age" class="easyui-numberbox"  data-options="min:18,max:199,required:false"/>
				</td>
			</tr>
			<tr>
				<td align="right">移动电话:</td>
				<td>
				   <form:input path="mobile" class="easyui-validatebox"  data-options="required:false,validType:['length[11,11]']"/>
				</td>
			</tr>
		   <tr>
				<td align="right">描述:</td>
				<td>
				   <form:textarea path="description" class="easyui-validatebox"  data-options="required:true,validType:['length[0,200]']" />
				</td>
			</tr>
			
			 
		</table>
	</form:form>
</div>

<div style="text-align: center; padding: 5px">
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="toSubmitForm();">提交</a> 
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="resetForm();">重置</a>
</div>
