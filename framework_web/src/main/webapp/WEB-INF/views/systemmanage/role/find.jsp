<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglibs.jsp" %> 
<%
request.setAttribute("namespace", "role");
%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/views/common/common.jsp" %>
</head>
<body>
    <shiro:hasPermission name="user_delete">
         <input type="hidden" id="deleteAuth" value="Y"/>
    </shiro:hasPermission>
	<table class="easyui-datagrid" fit="true" style="height: 515px;" <shiro:hasPermission name="role_find">url="<c:url value="/role/find"> </c:url>"</shiro:hasPermission>  id="${namespace }Grid"  title="数据列表" 
	     data-options="<shiro:hasPermission name="role_update">onDblClickCell: function(index,field,value){update('role/toUpdate','修改',400,220);}</shiro:hasPermission>" 
	     singleSelect="true" rownumbers="true" pagination="true" toolbar="#toolbar">
		<thead>
			<tr>
				<th data-options="field:'name',fit:true">名称</th>
				<th data-options="field:'description',fit:true">描述</th>
				<th data-options="field:'createTime',fit:true" formatter=dateFormatByEasyui>创建时间</th>
				<th data-options="field:'action',fit:true" formatter="formatterAction">操作</th>
			</tr>
		</thead>
	</table>

	<div id="toolbar" style="padding: 5px; height: auto">
		<div style="margin-bottom: 5px">
		    <shiro:hasPermission name="role_add">
		        <a href="javascript:void(0);" onclick="add('role/toAdd','添加',400,220);" class="easyui-linkbutton" iconCls="icon-add" plain="true" title="添加">添加</a>
		    </shiro:hasPermission>
			
			
			<shiro:hasPermission name="role_update">
		        <a href="javascript:void(0);" onclick="update('role/toUpdate','修改',400,220);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" title="修改">修改</a>
		    </shiro:hasPermission>
			
		    
		    <shiro:hasPermission name="role_assign_resources">
		         <a href="javascript:void(0);" class="easyui-linkbutton" onclick="assignResources();" plain="true" title="分配访问资源">分配访问资源</a>
		    </shiro:hasPermission>
		    
		</div>
		<div id="${namespace }SearchDiv">
		      <shiro:hasPermission name="role_find">
		                     名称: <input style="width: 80px" name="name"> 
				<a href="javascript:void(0);" onclick="searchData();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
		      </shiro:hasPermission>
		</div>
	</div>
	<script type="text/javascript">
	    setNamespace("${namespace }");
	    
		function formatterAction(value, row, index) {
			var deleteAuth = $("#deleteAuth").val();
			if ("Y" == deleteAuth) {
				return "<a href='javascript:void(0);' onclick='delById(\"role/delete\","+row.id+");'>删除</a>";
			}
			return "";
		}
		
		function assignResources() {
			var row = getSelected();
			if (row) {
				$("#dialogDiv").dialog({
				    title: '分配访问资源',
				    href: 'resources/toAssignResources?roleId='+row.id,
				    width: 400,
				    height: 490
				}).dialog('open');
			}
		}
	</script>
</body>
</html>