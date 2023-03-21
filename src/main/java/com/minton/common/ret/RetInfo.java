package com.minton.common.ret;

public class RetInfo<T> {

    public int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public RetInfo<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RetInfo<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public RetInfo<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "RetInfo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
