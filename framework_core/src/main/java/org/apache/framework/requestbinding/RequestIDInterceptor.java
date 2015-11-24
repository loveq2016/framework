package org.apache.framework.requestbinding;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.framework.logging.Logger;
import org.apache.framework.logging.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class RequestIDInterceptor extends HandlerInterceptorAdapter {

	private final static Logger LOGGER = LoggerFactory.getLogger(RequestIDInterceptor.class);

	/**
	 * 获取当前http请求的ip地址
	 * 
	 * @param request
	 * @return
	 */
	public String getClientIpAddress(HttpServletRequest request) {
		String ipAddr = request.getHeader("X-Forwarded-For");
		if (ipAddr == null || ipAddr.isEmpty()
				|| "unknown".equalsIgnoreCase(ipAddr))
			ipAddr = request.getHeader("Proxy-Client-IP");
		if (ipAddr == null || ipAddr.isEmpty()
				|| "unknown".equalsIgnoreCase(ipAddr))
			ipAddr = request.getHeader("WL-Proxy-Client-IP");
		if (ipAddr == null || ipAddr.isEmpty()
				|| "unknown".equalsIgnoreCase(ipAddr))
			ipAddr = request.getRemoteAddr();

		String[] ipArray = ipAddr.split(",");
		ipAddr = ipArray[0].trim();
		return ipAddr;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) {
		String requestId = request.getHeader("requestId");
		String requestUrl = request.getRequestURI();
		RequestBinding.setRequestId(requestId);
		RequestBinding.setRequestUrl(requestUrl);
		if (requestId != null) {
			LOGGER.info("from FEnd {}" + this.getClientIpAddress(request));
		} else {
			LOGGER.info("from {}" + this.getClientIpAddress(request));
		}
		LOGGER.info("HTTP请求的URL="+requestUrl);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		try{
			super.postHandle(request, response, handler, modelAndView);
		}
		catch(Exception ex){
			LOGGER.error(ex);
			ex.printStackTrace();
			throw ex;
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		RequestBinding.remove();
	}
}
