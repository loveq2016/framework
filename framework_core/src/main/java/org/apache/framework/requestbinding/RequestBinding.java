package org.apache.framework.requestbinding;

import java.util.HashMap;
import java.util.Map;

import org.apache.framework.util.StringUtils;
import org.apache.framework.util.Uuid32Generator;

public class RequestBinding {

    private static final ThreadLocal<Map<String, String>> REQUEST_ID = new InheritableThreadLocal<Map<String, String>>();

    public static String genId() {
        String uuid = Uuid32Generator.generateUUID();
        return uuid;
    }

    public static void remove() {
        REQUEST_ID.remove();
    }

	public static String getRequestId() {
		Map<String, String> map  = REQUEST_ID.get();
		String requestId;
        if(map == null || map.isEmpty()){
        	requestId = genId();
        	setRequestId(requestId);
        } else {
        	requestId = map.get("requestId");
        }
        return requestId;
	}

	public static void setRequestId(String requestId) {
		String uuid;
        if (StringUtils.isEmpty(requestId)) {
            uuid = genId();
        } else {
            uuid = requestId;
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("requestId", uuid+" ");
        REQUEST_ID.set(map);
	}

	public static String getRequestUrl() {
		Map<String, String> map  = REQUEST_ID.get();
		if(map != null && !map.isEmpty()) {
			return map.get("requestUrl");
		}
		return "";
	}

	public static void setRequestUrl(String requestUrl) {
		Map<String, String> map  = REQUEST_ID.get();
		if(map != null && !map.isEmpty()) {
			map.put("requestUrl", requestUrl+" ");
			REQUEST_ID.set(map);
		}
	}
    
}
