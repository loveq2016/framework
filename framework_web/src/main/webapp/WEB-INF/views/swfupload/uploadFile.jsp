<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<%

String path = request.getContextPath();
String basePath = request.getScheme() + "://"
        + request.getServerName() + ":" + request.getServerPort()
        + path + "/";

String index = request.getParameter("index");
String hiddenName = request.getParameter("hiddenName");
String appendPath = request.getParameter("appendPath"); 
request.setAttribute("index", index);
request.setAttribute("hiddenName", hiddenName);
request.setAttribute("appendPath", appendPath);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/swfupload/js/uploadFile.js"></script>
<title></title>
</head>
<body>
	<div style="margin: 0px; padding: 0px; height: 31px; width: 90%;">
		<div style="float: left; height: 22px; margin-top: 4px;">
			<iframe src="<%=basePath%>/file/swfupload.do?targetId=${index }&callback=uploadComplete&appendPath=${appendPath}"
				width="120px;" height="40px;" frameborder="no" border="0"
				marginwidth="0" marginheight="0" scrolling="no"></iframe>
			<input type="hidden" name="${hiddenName }" id="resultValue${index }" />
		</div>
		<div style="height: 22px; margin-top: 0px">
			<a href="javascript:void(0);" id="resultTip${index }" title=""
				class="easyui-tooltip"> <img src=""
				style="width: 40px; height: 31px;">
			</a>
		</div>
	</div>
</body>
</html>