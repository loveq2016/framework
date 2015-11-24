package org.apache.framework.auth.client;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-3-14
 * <p>Version: 1.0
 */
public class ClientAuthenticationFilter extends AuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        return subject.isAuthenticated();
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        String backUrl = getSuccessUrl();
        System.out.println(getLoginUrl());
        System.out.println(backUrl);
        if (getLoginUrl().indexOf("?backUrl") < 0) {
        	setLoginUrl(getLoginUrl()+"?backUrl="+backUrl);
        }
        redirectToLogin(request, response);
        return false;
    }

}
