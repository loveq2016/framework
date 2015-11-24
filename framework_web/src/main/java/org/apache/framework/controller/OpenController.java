package org.apache.framework.controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.framework.util.CookieUtils;
import org.apache.framework.util.SessionUtils;
import org.apache.framework.util.StringUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OpenController extends BaseController {
 
	/**
	 * 用户无权限访问地址  页面
	 * @return 
	 */
	@RequestMapping(value="accessDenied**")
	public String accessDenied() {
		return "common/403";
	}
	
	/**
	 * 用户无权限访问地址  页面
	 * @return
	 */
	@RequestMapping(value="systemError**")
	public String systemError() {
		return "common/500";
	}
	
	/**
	 * 首页  页面
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="index**",method=RequestMethod.GET)
	public String index(ModelMap modelMap, HttpServletResponse response) {
		String path = getRequest().getContextPath();
		String basePath = getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+path+"/";
		String backUrl = CookieUtils.getCookieValue(getRequest(), "backUrl");
		if (StringUtils.isNotEmpty(backUrl)) {
			CookieUtils.deleteCookie(getRequest(), response, "backUrl");
			try {   
				backUrl = URLDecoder.decode(backUrl, "utf-8");
			} catch (IOException e) {
				e.printStackTrace();
			}
			if ((basePath+"index").equals(backUrl)) {
				return "index";
			} else {
				return redirect(backUrl);
			}
		}
		return "index";
	}
	 
	/**
	 * 登录  页面
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="login**")
	public String login(ModelMap modelMap,HttpServletRequest request) {
		SessionUtils.logout();
		String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
        String error = "";
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名不存在!";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "密码错误";
        } else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        modelMap.addAttribute("error", error);
		return "login";
	}
	
	/**
	 * session超时
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="timeout**",method=RequestMethod.GET)
	public String timeout() {
		return "common/timeout";
	}
	 
}
