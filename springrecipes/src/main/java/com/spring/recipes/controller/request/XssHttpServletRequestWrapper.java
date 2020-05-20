package com.spring.recipes.controller.request;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.spring.recipes.util.JsoupUtil;
import org.springframework.util.StringUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private HttpServletRequest orgRequest = null ;
    private boolean isIncludeRichText= false ;

    public XssHttpServletRequestWrapper(HttpServletRequest request, boolean isIncludeRichText) {
        super(request);
        orgRequest = request ;
        this.isIncludeRichText = isIncludeRichText;
    }

    @Override
    public String getHeader(String name) {
        name = JsoupUtil.clean(name);
        String value = super.getHeader(name);
        if( !StringUtils.isEmpty(value)){
            value = JsoupUtil.clean(value);
        }
        return value ;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        String str = getRequestBody(super.getInputStream());
        Map<String, Object> map = JSON.parseObject(str, Map.class);
        Map<String, Object> rstMap = Maps.newHashMap() ;
        for(String key : map.keySet()){
            Object val = map.get(key);
            if( map.get(key) instanceof String){
                rstMap.put(key, JsoupUtil.clean(val.toString()));
            }else{
                rstMap.put(key, val);
            }
        }

        str = JSON.toJSONString(rstMap);
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        } ;
    }

    private String getRequestBody(ServletInputStream inputStream) throws IOException {
        String line = "";
        StringBuilder body = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
        while( (line = reader.readLine()) != null ){
            body.append(line);
        }
        return body.toString();
    }

    @Override
    public String getParameter(String name) {
        boolean flag = ("content".equals(name) || name.endsWith("WithHtml"));
        if( flag && !isIncludeRichText){
            return super.getParameter(name);
        }
        name = JsoupUtil.clean(name);
        String value = super.getParameter(name);
        if( !StringUtils.isEmpty(value)){
            value = JsoupUtil.clean(value);
        }
        return value ;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] arr = super.getParameterValues(name);
        if( arr != null ){
            for(int i=0; i < arr.length; i++){
                arr[i] = JsoupUtil.clean(arr[i]);
            }
        }
        return arr ;
    }

    public HttpServletRequest getOrgRequest() {
        return orgRequest;
    }

    public static HttpServletRequest getOrgRequest(HttpServletRequest request){
        if( request instanceof XssHttpServletRequestWrapper){
            return ((XssHttpServletRequestWrapper) request).getOrgRequest();
        }else{
            return request ;
        }
    }
}
