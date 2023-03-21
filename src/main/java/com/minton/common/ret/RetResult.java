package com.minton.common.ret;

public class RetResult {

    private final static String SUCCESS = "success";


    public static <T> RetInfo<T> retSuccess() {
        return new RetInfo<T>().setCode(RetCodeEnum.SUCCESS.code).setMsg(SUCCESS);
    }

    public static <T> RetInfo<T> retSuccess(T data) {
        return new RetInfo<T>().setCode(RetCodeEnum.SUCCESS.code).setMsg(SUCCESS).setData(data);
    }

    public static <T> RetInfo<T> retError(String message) {
        return new RetInfo<T>().setCode(RetCodeEnum.FAIL.code).setMsg(message);
    }

    public static <T> RetInfo<T> retInfo(int code, String msg) {
        return new RetInfo<T>().setCode(code).setMsg(msg);
    }

    public static <T> RetInfo<T> retInfo(int code, String msg, T data) {
        return new RetInfo<T>().setCode(code).setMsg(msg).setData(data);
    }
}