package com.spring.recipes;

import com.google.common.xml.XmlEscapers;

import java.util.HashMap;
import java.util.Map;

public class ResponseModel<T> {
    private volatile String retCode ;

    private volatile String retMsg ;

    private volatile T data ;

    public static ResponseModel ok(){
        return new ResponseModel();
    }

    public static ResponseModel ok(Object msg){
        return new ResponseModel(msg);
    }

    public static ResponseModel ok(String val, Object msg){
        Map<String, Object> rstMap = new HashMap<String, Object>();
        rstMap.put("flag", msg);
        return  new ResponseModel(rstMap);
    }

    public static ResponseModel fail(String retCode, String retMsg){
        return new ResponseModel(retCode,retMsg);
    }

    public ResponseModel(String retCode, String retMsg, T data) {
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.data = data;
    }

    public ResponseModel(String retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public ResponseModel(T data) {
        this.retCode = "00" ;
        this.retMsg = "执行成功";
        this.data = data;
    }

    public ResponseModel() {
        this.retCode = "00" ;
        this.retMsg = "执行成功";
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
