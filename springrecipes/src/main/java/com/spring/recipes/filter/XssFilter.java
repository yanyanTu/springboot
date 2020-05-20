package com.spring.recipes.filter;

import com.spring.recipes.controller.request.XssHttpServletRequestWrapper;
import org.apache.commons.lang.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * xss安全处理
 */
public class XssFilter implements Filter {
    private static  final Logger logger = LoggerFactory.getLogger(XssFilter.class);

    private static boolean IS_INCLUDE_RICH_TEXT = false;

    private List<String> excludes = new ArrayList<String>();

    public void init(FilterConfig filterConfig) throws ServletException {
        String isIncludeRichText = filterConfig.getInitParameter("isIncludeRichText");
        if(!StringUtils.isEmpty(isIncludeRichText)){
            IS_INCLUDE_RICH_TEXT = BooleanUtils.toBoolean(isIncludeRichText);
        }
        String temp = filterConfig.getInitParameter("excludes");
        if( temp != null ){
            String[] url = temp.split(",");
            for(int i =0; url != null && i < url.length ; i++){
                excludes.add(url[i]);
            }
        }
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
if( logger.isDebugEnabled()){
    logger.debug("xss filter is open");
}
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse ;
        if( handleExcludeURL(request, response)){
            filterChain.doFilter(request, response);
            return;
        }
        XssHttpServletRequestWrapper xssHttpServletRequestWrapper = new XssHttpServletRequestWrapper(request, IS_INCLUDE_RICH_TEXT);
        filterChain.doFilter(xssHttpServletRequestWrapper, response);
    }

    private boolean handleExcludeURL(HttpServletRequest request, HttpServletResponse response) {
        if( excludes == null || excludes.isEmpty()){
            return  false;
        }
        String url = request.getServletPath() ;
        for(String pattern : excludes){
            Pattern p = Pattern.compile("^" + pattern);
            Matcher m = p.matcher(url);
            if( m.find()){
                return  true ;
            }
        }
        return  false;
    }

    public void destroy() {

    }
}
