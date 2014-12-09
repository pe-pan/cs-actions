package org.eclipse.score.content.ssh.utils;

import com.hp.oo.sdk.content.plugin.GlobalSessionObject;
import com.hp.oo.sdk.content.plugin.SessionResource;
import org.eclipse.score.content.ssh.entities.SSHConnection;
import org.eclipse.score.content.ssh.services.SSHService;
import org.eclipse.score.content.ssh.services.impl.SSHServiceImpl;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.Session;

import java.util.HashMap;
import java.util.Map;

/**
 * @author octavian-h
 * @author ioanvranauhp
 *         Date: 10/29/14
 */
public class CacheUtils {
    /**
     * @param resource the cache.
     * @return the SSH session from cache
     */
    private static Session getSshSession(SessionResource<Map<String, SSHConnection>> resource, String sessionId) {
        if (resource != null) {
            Object obj = resource.get();
            if (obj != null) {
                SSHConnection sshConnection = (SSHConnection) ((Map) obj).get(sessionId);
                if (sshConnection != null) {
                    return sshConnection.getSession();
                }
            }
        }
        return null;
    }

    /**
     * @param resource the cache.
     * @return the SSH channel from cache
     */
    private static Channel getSshChannel(SessionResource<Map<String, SSHConnection>> resource, String sessionId) {
        if (resource != null) {
            Object obj = resource.get();
            if (obj != null) {
                SSHConnection sshConnection = (SSHConnection) ((Map) obj).get(sessionId);
                if (sshConnection != null) {
                    return sshConnection.getChannel();
                }
            }
        }

        return null;
    }

    /**
     * Save the SSH session and the channel in the cache.
     *
     * @param session      The SSH session.
     * @param channel      The SSH channel.
     * @param sessionParam The cache: GlobalSessionObject or SessionObject.
     */
    public static boolean saveSshSessionAndChannel(Session session, Channel channel, GlobalSessionObject<Map<String, SSHConnection>> sessionParam, String sessionId) {
        final SSHConnection sshConnection;
        if( channel != null ) {
            sshConnection = new SSHConnection(session, channel);
        } else {
            sshConnection = new SSHConnection(session);
        }
        if (sessionParam != null) {
            Map<String, SSHConnection> tempMap = sessionParam.get();
            if (tempMap == null) {
                tempMap = new HashMap<>();
            }
            tempMap.put(sessionId, sshConnection);
            sessionParam.setResource(new SSHSessionResource(tempMap));
            return true;
        }
        return false;
    }


    /**
     * Remove the SSH session (and associated channel if any) from the cache.
     *
     * @param sessionParam The cache.
     * @param sessionId    The key to the session in the cache map.
     */
    public static void removeSshSession(GlobalSessionObject<Map<String, SSHConnection>> sessionParam, String sessionId) {
        if(sessionParam != null) {
            SessionResource<Map<String, SSHConnection>> resource = sessionParam.getResource();
            if (resource != null) {
                Map<String, SSHConnection> tempMap = resource.get();
                if (tempMap != null) {
                    tempMap.remove(sessionId);
                }
            }
        }
    }

    /**
     * Get an opened SSH session from cache (Operation Orchestration session).
     *
     * @param sessionResource The session resource.
     * @return the SSH service
     */
    public static SSHService getFromCache(SessionResource<Map<String, SSHConnection>> sessionResource, String sessionId) {
        Session savedSession = CacheUtils.getSshSession(sessionResource, sessionId);
        if (savedSession != null && savedSession.isConnected()) {
            Channel savedChannel = CacheUtils.getSshChannel(sessionResource, sessionId);
            return new SSHServiceImpl(savedSession, savedChannel);
        }

        return null;
    }
}
