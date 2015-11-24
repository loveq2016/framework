package org.apache.framework.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.framework.util.HttpUtils;
import org.apache.framework.util.JacksonUtils;
import org.apache.framework.util.ResponseUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler
	protected void handleConflict(RuntimeException e, ServletWebRequest request) {  
		String msg;
		String url;
		if (UnauthorizedException.class.getName().equals(e.getClass().getName())) {
			msg = "无权限访问!";
			url = "/accessDenied";
			LOG.info(msg);
		} else {
			e.printStackTrace();
			msg = "系统异常!";
			url = "/systemError";
			LOG.info("总异常捕获："+ e.getMessage(), e);
		}
		if (HttpUtils.isAjaxRequest(request.getRequest())) {
			ResponseUtils.renderText(request.getResponse(), JacksonUtils.writeValueAsString(BaseController.getFailureResult(msg)));
		} else {
			try {
				request.getRequest().getRequestDispatcher(url).forward(request.getRequest(),request.getResponse());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
    } 
	private final Log LOG = LogFactory.getLog(getClass());
}
