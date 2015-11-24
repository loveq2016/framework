package org.apache.framework.aop;


import org.apache.framework.logging.Logger;
import org.apache.framework.logging.LoggerFactory;
import org.apache.framework.requestbinding.RequestBinding;
import org.apache.framework.util.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;

 
public class AccessLoger {

	private final static Logger LOGGER = LoggerFactory.getLogger(AccessLoger.class);
	
    public static Object process(ProceedingJoinPoint pjp) throws Throwable{
        String requestId = RequestBinding.getRequestId();
        if(StringUtils.isEmpty(requestId)){
            RequestBinding.setRequestId(null);
        }
        LOGGER.info("start process");
         
        long time = System.currentTimeMillis();     
        try {
            return pjp.proceed(); 
        } 
        catch(Exception ex){
        	LOGGER.error(ex);
            throw ex;
        }
        finally {
            time = System.currentTimeMillis() - time;
            LOGGER.info(pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName());
            LOGGER.info("end process");
            RequestBinding.remove();
        }
    }
     
}
