package com.yinziming.crud.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回类
 */
public class Msg {
    private int code;
    private String msg;
    private Map<String, Object> extend = new HashMap<>();

    public static Msg success() {
        return new Msg(100, "success");
    }

    public static Msg fail() {
        return new Msg(200, "fail");
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    public Msg() {
    }

    public Msg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Msg add(String key, Object value) {
        this.getExtend().put(key, value);
        return this;
    }
}
