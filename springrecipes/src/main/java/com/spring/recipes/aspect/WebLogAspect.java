package com.spring.recipes.aspect;

import com.alibaba.fastjson.JSON;
import com.spring.recipes.ResponseModel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 日志处理
 */
@Aspect
@Component
@Order(-5)
public class WebLogAspect {

    private static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    public static String getIpAddress(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if( ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client_IP");
        }
        if( ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        } if( ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)){
            ip = request.getHeader("HTTP_CLIENT_IP");
        } if( ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)){
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        } if( ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return  ip ;
    }

    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public Object getIntereceptor(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        return process(proceedingJoinPoint);
    }

    @Around("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object postInterceptor(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return process(proceedingJoinPoint);
    }

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object requestInterceptor(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return process(proceedingJoinPoint);
    }

    private Object process(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest() ;
        String url = request.getRequestURI() ;
        String method = request.getMethod();
        String remoteAddr = getIpAddress(request) ;

        String args = "" ;
        if( proceedingJoinPoint.getArgs() != null && proceedingJoinPoint.getArgs().length > 0 ){
            Object arg = proceedingJoinPoint.getArgs()[0] ;
            if( !(arg instanceof ServletRequest) && !(arg instanceof ServletResponse) && !(url .contains("upload-image"))){
                arg = "with inputs:" + JSON.toJSONString(proceedingJoinPoint.getArgs());
            }
        }
        logger.info(remoteAddr +" \t " + method+" \t " + url +" \t " + args);

        Object result ;
        result = proceedingJoinPoint.proceed() ;
        long costTime = System.currentTimeMillis() - startTime ;

        String output = "";
        if( result instanceof ResponseModel){
            ResponseModel<?> msg = (ResponseModel<?>) result;
            output = "result code: " + msg.getRetCode() +" \t result message: " + msg.getRetMsg() ;
            if( null != msg.getData()){
                output += " \t result Data: " + JSON.toJSONString(msg.getData());
            }else{
                output += " \t result Data: null";
            }
        }

        String msg = remoteAddr +" \t " + method +" \t " + url +" \t " + costTime +"ms" + " \t " + args +" \t " + output ;
        if( costTime > 1000 ){
            logger.warn( msg );
        }else{
            logger.info( msg);
        }
        return result ;
    }
}
