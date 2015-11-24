package org.apache.framework;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

public class DmpSessionListener implements SessionListener {

	@Override
	public void onStart(Session session) {
		System.out.println("onStart="+session.getId());
	}

	@Override
	public void onStop(Session session) {
		System.out.println("onStop="+session.getId());
	}

	@Override
	public void onExpiration(Session session) {
		System.out.println("onExpiration="+session.getId());
	}

}