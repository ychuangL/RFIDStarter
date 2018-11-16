package com.nuite.rfid.exception;

/**
 * reader连接失败异常
 *
 * @Author: yangchuang
 * @Date: 2018/11/15 13:52
 * @Version: 1.0
 */

public class ReaderInitException extends RuntimeException {

    public ReaderInitException() {
    }

    public ReaderInitException(String message) {
        super(message);
    }

    public ReaderInitException(String message, Throwable cause) {
        super(message, cause);
    }
}
