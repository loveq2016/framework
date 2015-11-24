package org.apache.jsp.WEB_002dINF.views.systemmanage.role;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class assignRole_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write(" <div class=\"easyui-layout\" style=\"width: 100%; height: 100%;\">\r\n");
      out.write("        <div data-options=\"region:'east',split:true\" title=\"已经存在的角色\" style=\"width: 220px;height: 100%;\">\r\n");
      out.write("            <table class=\"easyui-datagrid\"  fit=\"true\" id=\"existRoleGrid\" userId=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.userId }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"\r\n");
      out.write("            data-options=\"rownumbers:true,singleSelect:true,pagination:false,url:'role/findByUserId?userId=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.userId }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("',method:'post'\">\r\n");
      out.write("\t\t        <thead>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th data-options=\"field:'id',hidden:true\">\r\n");
      out.write("\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t<th data-options=\"field:'name',width:100\">\r\n");
      out.write("\t\t\t\t\t\t角色名\r\n");
      out.write("\t\t\t\t\t</th>\r\n");
      out.write("\t\t\t\t\t<th field=\"action\" formatter=\"assignRoleAction\">操作</th>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</thead>\r\n");
      out.write("\t\t    </table>\r\n");
      out.write("        </div>\r\n");
      out.write("         \r\n");
      out.write("        <div data-options=\"region:'center',title:'角色数据'\" style=\"width: 420px;height: 100%;\">\r\n");
      out.write("            <table class=\"easyui-datagrid\" toolbar=\"#tb\"  id=\"roleGrid\"  fit=\"true\"\r\n");
      out.write("            data-options=\"rownumbers:true,singleSelect:true,pagination:true,url:'role/find',method:'post'\">\r\n");
      out.write("\t\t        <thead>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th field=\"id\" hidden=\"true\">id</th>\r\n");
      out.write("\t\t\t\t\t\t<th field=\"name\" width=\"80\">角色名</th>\r\n");
      out.write("\t\t\t\t\t\t<th field=\"createTime\" width=\"200\" formatter=dateFormatByEasyui>创建时间</th>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t</thead>\r\n");
      out.write("\t\t    </table>\r\n");
      out.write("        </div>\r\n");
      out.write("        \r\n");
      out.write("        <div id=\"tb\" style=\"padding: 5px; height: auto\">\r\n");
      out.write("\t\t\t<div id=\"roleSearchDiv\">\r\n");
      out.write("\t\t\t\t角色名: <input style=\"width: 80px\" name=\"name\"> \r\n");
      out.write("\t\t\t\t<a href=\"javascript:void(0);\" onclick=\"searchData('role');\" class=\"easyui-linkbutton\" iconCls=\"icon-search\">搜索</a>\r\n");
      out.write("\t\t\t\t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconcls=\"icon-add\" onclick=\"addUserRole(");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.userId }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write(");\">添加角色</a> \r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("    </div>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("function assignRoleAction(value, row, index) {\r\n");
      out.write("\treturn \"<a href='javascript:void(0);' onclick=\\\"deleteUserRole(\"+row.id+\");\\\">删除</a>\";\r\n");
      out.write("};\r\n");
      out.write("\r\n");
      out.write("function addUserRole(userId) {\r\n");
      out.write("\tvar row = getSelected(\"role\");\r\n");
      out.write("\tif (row) {\r\n");
      out.write("\t\tvar roleId = row.id;\r\n");
      out.write("\t\tif (!isExistRowId(roleId,\"existRole\")) {\r\n");
      out.write("\t\t\tvar sendData = {userId: userId, roleId: roleId};\r\n");
      out.write("\t\t\tajaxPost(\"role/addUserRole\",sendData, function (resultData) {\r\n");
      out.write("\t\t\t\tif (isSuccess(resultData)) {\r\n");
      out.write("\t\t\t\t\t$(\"#existRoleGrid\").datagrid('reload');\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t};\r\n");
      out.write("\t};\r\n");
      out.write("};\r\n");
      out.write("\r\n");
      out.write("function deleteUserRole(roleId) {\r\n");
      out.write("\tvar userId = $(\"#existRoleGrid\").attr(\"userId\");\r\n");
      out.write("\tif (!isEmpty(roleId)) {\r\n");
      out.write("\t\tvar sendData = {userId:userId, roleId:roleId};\r\n");
      out.write("\t\tajaxPost(\"role/deleteUserRole\",sendData, function (resultData) {\r\n");
      out.write("\t\t\tif (isSuccess(resultData)) {\r\n");
      out.write("\t\t\t\t$(\"#existRoleGrid\").datagrid('reload');\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t};\r\n");
      out.write("};\r\n");
      out.write("\r\n");
      out.write("</script>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
