package org.apache.framework.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-3-13
 * <p>Version: 1.0
 */
public class ShiroSessionServiceImpl implements ShiroSessionService {

    @Autowired
    private SessionDAO sessionDAO;

    
    public Session getSession(String appKey, Serializable sessionId) {
        return sessionDAO.readSession(sessionId);
    }

    
    public Serializable createSession(Session session) {
        return sessionDAO.create(session);
    }

    
    public void updateSession(String appKey, Session session) {
        sessionDAO.update(session);
    }

    
    public void deleteSession(String appKey, Session session) {
        sessionDAO.delete(session);
    }

}
