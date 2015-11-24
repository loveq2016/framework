package org.apache.framework.util;

import org.apache.framework.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class SessionUtils {

	public static Session getSession() {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		return session;
	}
	
	public static void put(String key, Object value) {
		getSession().setAttribute(key, value);
	}
	
	public static Object get(String key) {
		return getSession().getAttribute(key);
	}
	
	public static Long getUserId() {
		User user = (User)get("user");
		if (user != null) {
			return user.getId();
		} else {
			return null;
		}
	}
	
	public static void logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
	}
	
	public static long getSessionTimeout() {
		return getSession().getTimeout();
	}
	
	public static void setSessionTimeout(long maxIdleTimeInMillis) {
		getSession().setTimeout(maxIdleTimeInMillis);
	}
}
