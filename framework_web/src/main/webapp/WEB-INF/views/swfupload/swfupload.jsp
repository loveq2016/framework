<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"
%><%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"
%><%@include file="_config.jsp" 
%>

<%
 String uploadType = request.getParameter("uploadType");
 if (uploadType == null || "".equals(uploadType.trim())) {
	 uploadType = "1";
 }
 
 String smallImgSize = request.getParameter("smallImgSize");

 
 String middleImgSize = request.getParameter("middleImgSize");

 String bigImgSize = request.getParameter("bigImgSize");

 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<script type="text/javascript" src="${basePath }/static/swfupload/swfupload.js"></script>
<script type="text/javascript" src="${basePath }/static/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="${basePath }/static/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="${basePath }/static/swfupload/js/handlers_01.js"></script>
<script type="text/javascript" src="${basePath }/static/swfupload/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
 
    var swfu;
    var params = {
    	targetId:"${targetId }",
    	appendPath:"${appendPath }"
    };
    
    window.onload = function() {
        $("#spanProgressPlaceHolder").hide();
        var settings = {
            flash_url : "${basePath }/static/swfupload/swfupload.swf",
            upload_url: "${basePath }file/fileUpload.do",
            // upload_url: "${uploadUrl }base/fileJson/imgUpload.do",
            post_params: params,
            file_size_limit : "${fileSizeLimit }",
            file_types : "${fileTypes }",
            file_types_description : "${fileTypesDescription }",
            file_upload_limit : "${fileUploadLimit }",
            file_queue_limit : 0,
            file_post_name : "file",
            custom_settings : {
                //progressTarget : "fsUploadProgress",
                //cancelButtonId : "btnCancel"
            },
            debug: false,

            // Button settings
            button_image_url: "${basePath }/static/swfupload/images/xuanze_71x22.png",
            button_width : 71,
            button_height : 22,
            button_placeholder_id: "spanButtonPlaceHolder",
            button_text : "<span class='buttonText'>上传图片</span>",
            button_text_style: '.buttonText { font-size:12px; background-color:#FFF }',
            button_text_top_padding : 1,
            button_text_left_padding : 15,
            button_window_mode : SWFUpload.WINDOW_MODE.TRANSPARENT,
            button_cursor : SWFUpload.CURSOR.HAND,
            button_action : "${multiple }",
            button_window_mode : SWFUpload.WINDOW_MODE.TRANSPARENT,
            
            // The event handler functions are defined in handlers.js
            file_queued_handler : fileQueued,
            file_queue_error_handler : fileQueueError,
            file_dialog_complete_handler : fileDialogComplete,
            upload_start_handler : uploadStart,
            upload_progress_handler : uploadProgress,
            upload_error_handler : uploadError,
            upload_success_handler : uploadSuccess,
            upload_complete_handler : uploadComplete,
            queue_complete_handler : queueComplete    // Queue plugin event
        };

        swfu = new SWFUpload(settings);
    };
     
    /*
     * 用途：检查输入字符串是否为空或者全部都是空格 输入：str 返回：如果全是空返回true,否则返回false
     */
    function isEmpty(str) {
    	if (str == null || str == "" || typeof str == "undefined") {
    		return true;
    	}
    	var regu = "^[ ]+$";
    	var re = new RegExp(regu);
    	return re.test(str);
    };


    function isNotEmpty(str) {
    	return !isEmpty(str);
    };
    
    function uploadSuccess(file, serverData) {
    	serverData = eval('('+serverData+')');
    	if (serverData.result) {
    		//是否回调
    		var callbackMethodName = "${callbackMethodName }";
    		var result = "{\"targetId\": \""+serverData.param.targetId+"\", \"url\": \""+serverData.param.file.url+"\"}"; 
    		if (!isEmpty(callbackMethodName) && "null" != callbackMethodName) {
    			eval('window.parent.'+callbackMethodName+'('+result+')');
    		}
    	} else {
    		//alert("上传出现异常!");
    	}
    }

    /**
     * 得到上传状态,0表示上传成功,其它失败
     */
    function getStatus() {
    	if (swfu.getStats().files_queued == "0") {
    		return 0;
    	} else {
    		return -1;
    	}
    }

    /**
     * 得到上传图片的网络地址
     */
    function getImageUrl() {
    	return $("#imageUrl").attr("src");
    }
    
</script>
</head>
<body> 
    <div style="width:${divWidth };overflow:hidden;">
        <span id="spanButtonPlaceHolder" style="float: left;"></span>
        <c:if test="${progressBar ne 'false'  }">
            <span id="spanProgressPlaceHolder"
                style="float: right; margin-top: 1px; margin-left: 6px;"> <img
                src="${basePath }/static/swfupload/images/progress-33.gif">
            </span>
        </c:if>
    </div>
</body>
</html>