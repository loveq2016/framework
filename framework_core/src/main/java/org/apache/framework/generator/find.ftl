<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglibs.jsp" %> 
<%
request.setAttribute("namespace", "${modelNameVariable}");
%>
<!DOCTYPE html PUBLIC>
<html><#assign  modelNameVariable="${StringUtils.firstLetterToLowerCase('${domainObjectName}')!}"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/views/common/common.jsp" %>
</head>
<body>
    <shiro:hasPermission name="${modelNameVariable}_delete">
         <input type="hidden" id="deleteAuth" value="Y"/>
    </shiro:hasPermission>
	<table class="easyui-datagrid" fit="true" style="height: 515px;" <shiro:hasPermission name="${modelNameVariable}_find"> url="<c:url value="/${modelNameVariable}/find"> </c:url>" </shiro:hasPermission> id="${namespace }Grid"  title="数据列表" 
	     data-options="<shiro:hasPermission name="${modelNameVariable}_update">onDblClickCell: function(index,field,value){update('${modelNameVariable}/toUpdate','修改',400,420);}</shiro:hasPermission>" 
	     singleSelect="true" rownumbers="true" pagination="true" toolbar="#toolbar">
		<thead>
			<tr>
			    <#list columns as column>
			    <#if column.className == "Date">
			    <th data-options="field:'${column.propertyName}',fit:true" formatter=dateFormatByEasyui>${column.remarks}</th>
			    <#else>
			    <th data-options="field:'${column.propertyName}',fit:true">${column.remarks}</th>
			    </#if>
				</#list>
				<th data-options="field:'action',fit:true" formatter="formatterAction">操作</th>
			</tr>
		</thead>
	</table>

	<div id="toolbar" style="padding: 5px; height: auto">
		<div style="margin-bottom: 5px">
			<shiro:hasPermission name="${modelNameVariable}_add">
				<a href="javascript:void(0);" onclick="add('${modelNameVariable}/toAdd','添加',400,420);" class="easyui-linkbutton" iconCls="icon-add" plain="true" title="添加">添加</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="${modelNameVariable}_update">
				<a href="javascript:void(0);" onclick="update('${modelNameVariable}/toUpdate','修改',400,420);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" title="修改">修改</a>
			</shiro:hasPermission>
		</div>
		<div id="${namespace }SearchDiv">
		    <shiro:hasPermission name="${modelNameVariable}_find">
		    <form id="searchForm">
		         <table>
		             <tr>
		                 <td>
		                                                         用户名:
		                 </td>
		                 <td>
		                     <input style="width: 120px" name="userName"  class="easyui-validatebox" data-options="required:false,validType:['length[0,20]']"/>  
		                 </td>
		                 <td>
		                                                        年龄:
		                 </td>
		                 <td>
		                     <input style="width: 120px" name="age" class="easyui-numberbox"  data-options="min:18,max:199,required:false" />  
		                 </td>
		                 <td>
		                                                         移动电话:
		                 </td>
		                 <td>
		                     <input style="width: 120px" name="mobile"  class="easyui-validatebox" data-options="required:false,validType:['length[11,11]']" />  
		                 </td>
		                 <td>
		                 </td>
		                 <td>
		                     <a href="javascript:void(0);" onclick="searchData();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
		                 </td>
		             </tr>
		         </table>
			</form>
			</shiro:hasPermission>
		</div>
	</div>
	
	<script type="text/javascript">
	    setNamespace("${namespace }");
		function formatterAction(value, row, index) {
			var deleteAuth = $("#deleteAuth").val();
			if ("Y" == deleteAuth) {
				return "<a href='javascript:void(0);' onclick='delById(\"${modelNameVariable}/delete\","+row.id+");'>删除</a>";
			}
			return "";
		}
	</script>
</body>
</html>