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
			    <th data-options="field:'id',width:80">主键</th>
				
			    <th data-options="field:'name',width:80">参数名称</th>
				
			    <th data-options="field:'key',width:80">参数key</th>
				
			    <th data-options="field:'value',width:80">参数value</th>
				
			    <th data-options="field:'description',width:80">描述</th>
				
			    <th data-options="field:'lockVersion',width:80">锁版本号</th>
				
			    <th data-options="field:'createTime',width:140">创建时间</th>
				
			    <th data-options="field:'createUserId',width:80">创建人</th>
				
			    <th data-options="field:'updateTime',width:140">更新时间</th>
				
			    <th data-options="field:'updateUserId',width:80">更新人id</th>
				
			    <th data-options="field:'isLocked',width:80">是否锁定， Y=是， N=否， 用于并发控制，比如审核</th>
				
			    <th data-options="field:'isDelete',width:80">是否删除， Y=是， N=否</th>
				
			    <th data-options="field:'rsv1',width:80">备用字段1</th>
				
			    <th data-options="field:'rsv2',width:80">备用字段1</th>
				
			    <th data-options="field:'rsv3',width:80">备用字段1</th>
				
				<th field="action" width="80" formatter="formatterAction">操作</th>
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