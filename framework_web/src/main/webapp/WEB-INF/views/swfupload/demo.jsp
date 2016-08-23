<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${basePath }/static/js/jquery/jquery-1.7.2.min.js"></script>
<title>Insert title here</title>
</head>
<body>
     <iframe src="/file/swfupload.do?targetId=aaa&callback=uploadComplete" width="120px;" height="40px;"   frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
     
     <img id="aaa" src="">
     <script type="text/javascript">
        function uploadComplete(resultData) {
        	var url = "http://obojjnevn.bkt.clouddn.com/"+resultData.url;
        	$("#"+resultData.targetId).attr("src", url)
        }
     </script>
</body>
</html>