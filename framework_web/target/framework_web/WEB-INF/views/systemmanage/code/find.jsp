<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglibs.jsp" %> 
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/views/common/common.jsp" %>
</head>
<body>
    <shiro:hasPermission name="code_delete">
         <input type="hidden" id="deleteAuth" value="Y"/>
    </shiro:hasPermission>
	<table class="easyui-datagrid" fit="true" style="height: 515px;" <shiro:hasPermission name="code_find"> url="<c:url value="/code/find"> </c:url>" </shiro:hasPermission> id="grid"  title="数据列表" 
	     data-options="<shiro:hasPermission name="code_update">onDblClickCell: function(index,field,value){update('code/toUpdate','修改',400,350);}</shiro:hasPermission>" 
	     singleSelect="true" rownumbers="true" pagination="true" toolbar="#toolbar">
		<thead>
			<tr>
			    <th data-options="field:'groupNo',width:100">组编号</th>
				
			    <th data-options="field:'groupName',width:120">组名称</th>
				
			    <th data-options="field:'itemNo',width:80">节点编号</th>
				
			    <th data-options="field:'itemKey',width:80">节点键</th>
				
			    <th data-options="field:'itemValue',width:80">节点值</th>
				
			    <th data-options="field:'sequence',width:80">排序号</th>
				
			    <th data-options="field:'isUseText',width:80">是否使用</th>
				
			    <th data-options="field:'createTime',width:140" formatter=dateFormatByEasyui>创建时间</th>
				
				<th field="action" width="80" formatter="formatterAction">操作</th>
			</tr>
		</thead>
	</table>

	<div id="toolbar" style="padding: 5px; height: auto">
		<div style="margin-bottom: 5px">
			<shiro:hasPermission name="code_add">
				<a href="javascript:void(0);" onclick="add('code/toAdd','添加',400,350);" class="easyui-linkbutton" iconCls="icon-add" plain="true" title="添加">添加</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="code_update">
				<a href="javascript:void(0);" onclick="update('code/toUpdate','修改',400,350);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" title="修改">修改</a>
			</shiro:hasPermission>
		</div>
		<div id="searchDiv">
		    <shiro:hasPermission name="code_find">
		    <form id="searchForm">
		         <table>
		             <tr>
		                 <td>
		                                                         组编号:
		                 </td>
		                 <td>
		                     <input style="width: 120px" name="groupNo"  class="easyui-validatebox" data-options="required:false,validType:['length[0,30]']"/>  
		                 </td>
		                 <td>
		                                                         组名称:
		                 </td>
		                 <td>
		                     <input style="width: 120px" name="groupName"  class="easyui-validatebox" data-options="required:false,validType:['length[0,30]']" />  
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
		function formatterAction(value, row, index) {
			var deleteAuth = $("#deleteAuth").val();
			if ("Y" == deleteAuth) {
				return "<a href='javascript:void(0);' onclick='delById(\"code/delete\","+row.id+");'>删除</a>";
			}
			return "";
		}
	</script>
</body>
</html>