package com.springcloud.eureka.zuul.client.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * zuul路由处理中，filter处理filterType指定的类型信息，具体处理在run方法中实现
 */
@Component
class ZuulRouteFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(ZuulRouteFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        Object token = requestContext.getRequest().getParameter("token");
        if (token == null) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            try {
                requestContext.getResponse().getWriter().write("token is null ");
            } catch (IOException error) {
                error.printStackTrace();
                logger.warn("熔断处理响应失败,{}", error.getMessage());
            }
        }
        return null;
    }
}
