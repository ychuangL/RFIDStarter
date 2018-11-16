package com.nuite.rfid.exception;

/**
 * 执行reader设置发生的异常
 *
 * @Author: yangchuang
 * @Date: 2018/11/15 13:52
 * @Version: 1.0
 */

public class ReaderSettingParamException extends RuntimeException {

    public ReaderSettingParamException() {
    }

    public ReaderSettingParamException(String message) {
        super(message);
    }

    public ReaderSettingParamException(String message, Throwable cause) {
        super(message, cause);
    }
}
