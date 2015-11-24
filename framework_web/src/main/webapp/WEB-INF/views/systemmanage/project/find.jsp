<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglibs.jsp" %> 
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/views/common/common.jsp" %>
</head>
<body>
    <shiro:hasPermission name="project_delete">
         <input type="hidden" id="deleteAuth" value="Y"/>
    </shiro:hasPermission>
	<table class="easyui-datagrid" fit="true" style="height: 515px;" <shiro:hasPermission name="project_find"> url="<c:url value="/project/find"> </c:url>"</shiro:hasPermission> id="grid"  title="数据列表" 
	     data-options="<shiro:hasPermission name="project_update">onDblClickCell: function(index,field,value){update('project/toUpdate','修改',400,220);}</shiro:hasPermission>" 
	     singleSelect="true" rownumbers="true" pagination="true" toolbar="#toolbar">
		<thead>
			<tr>
			    <th data-options="field:'name',width:80">名称</th>
				
			    <th data-options="field:'code',width:80">标识</th>
				
			    <th data-options="field:'description',width:80">描述</th>
				
			    <th data-options="field:'createTime',width:140" formatter=dateFormatByEasyui>创建时间</th>
				
				<th field="action" width="80" formatter="formatterAction">操作</th>
			</tr>
		</thead>
	</table>

	<div id="toolbar" style="padding: 5px; height: auto">
		<div style="margin-bottom: 5px">
		     <shiro:hasPermission name="project_add">
		         <a href="javascript:void(0);" onclick="add('project/toAdd','添加',400,220);" class="easyui-linkbutton" iconCls="icon-add" plain="true" title="添加">添加</a>
		     </shiro:hasPermission>
		     
		     <shiro:hasPermission name="project_update">
		         <a href="javascript:void(0);" onclick="update('project/toUpdate','修改',400,220);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" title="修改">修改</a>
		     </shiro:hasPermission>
		</div>
		<div id="searchDiv">
		    <shiro:hasPermission name="project_find">
		    <form id="searchForm">
		         <table>
		             <tr>
		                 <td>
		                                                         名称:
		                 </td>
		                 <td>
		                     <input style="width: 120px" name="name"  class="easyui-validatebox" data-options="required:false, validType:['length[0,20]']"/>  
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
			return "<a href='javascript:void(0);' onclick='delById(\"project/delete\","+row.id+");'>删除</a>";
		} else {
			return "";
		}
	}
	</script>
</body>
</html>