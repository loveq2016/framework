<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglibs.jsp" %> 
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/views/common/common.jsp" %>
</head>
<body>
    <shiro:hasPermission name="parameter_delete">
         <input type="hidden" id="deleteAuth" value="Y"/>
    </shiro:hasPermission>
	<table class="easyui-datagrid" fit="true" style="height: 515px;" <shiro:hasPermission name="parameter_find"> url="<c:url value="/parameter/find"> </c:url>" </shiro:hasPermission> id="grid"  title="数据列表" 
	     data-options="<shiro:hasPermission name="parameter_update">onDblClickCell: function(index,field,value){update('parameter/toUpdate','修改',400,420);}</shiro:hasPermission>" 
	     singleSelect="true" rownumbers="true" pagination="true" toolbar="#toolbar">
		<thead>
			<tr>
			    <th data-options="field:'name',fit:true">参数名称</th>
			    <th data-options="field:'parameterKey',fit:true">参数key</th>
			    <th data-options="field:'parameterValue',fit:true">参数value</th>
			    <th data-options="field:'description',fit:true">描述</th>
			    <th data-options="field:'createTime',fit:true" formatter=dateFormatByEasyui>创建时间</th>
				<th data-options="field:'action',fit:true" formatter="formatterAction">操作</th>
			</tr>
		</thead>
	</table>

	<div id="toolbar" style="padding: 5px; height: auto">
		<div style="margin-bottom: 5px">
			<shiro:hasPermission name="parameter_add">
				<a href="javascript:void(0);" onclick="add('parameter/toAdd','添加',400,420);" class="easyui-linkbutton" iconCls="icon-add" plain="true" title="添加">添加</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="parameter_update">
				<a href="javascript:void(0);" onclick="update('parameter/toUpdate','修改',400,420);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" title="修改">修改</a>
			</shiro:hasPermission>
		</div>
		<div id="searchDiv">
		    <shiro:hasPermission name="parameter_find">
		    <form id="searchForm">
		         <table>
		             <tr>
		                 <td>
		                                                         名称:
		                 </td>
		                 <td>
		                     <input style="width: 120px" name="name"  class="easyui-validatebox" data-options="required:false,validType:['length[0,20]']"/>  
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
		function formatterAction(value, row, index) {
			var deleteAuth = $("#deleteAuth").val();
			if ("Y" == deleteAuth) {
				return "<a href='javascript:void(0);' onclick='delById(\"parameter/delete\","+row.id+");'>删除</a>";
			}
			return "";
		}
	</script>
</body>
</html>