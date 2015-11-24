package org.apache.framework.auth.client;

import org.apache.framework.shiro.ShiroSessionService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import java.io.Serializable;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-3-13
 * <p>Version: 1.0
 */
public class ClientSessionDAO extends CachingSessionDAO {

    private ShiroSessionService shiroSessionService;
    
    private String appKey;

    public ShiroSessionService getShiroSessionService() {
		return shiroSessionService;
	}

	public void setShiroSessionService(ShiroSessionService shiroSessionService) {
		this.shiroSessionService = shiroSessionService;
	}


	public void setAppKey(String appKey) {
        this.appKey = appKey;
    }


    @Override
    protected void doDelete(Session session) {
        shiroSessionService.deleteSession(appKey, session);
    }

    @Override
    protected void doUpdate(Session session) {
        shiroSessionService.updateSession(appKey, session);
    }


    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = shiroSessionService.createSession(session);
        assignSessionId(session, sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return shiroSessionService.getSession(appKey, sessionId);
    }
}
