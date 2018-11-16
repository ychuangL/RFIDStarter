package com.nuite.rfid;

/**
 * 读写器工厂
 *
 * @Author: yangchuang
 * @Date: 2018/11/15 17:16
 * @Version: 1.0
 */

public class ReaderStarterFactory {

    public static ReaderStarter createReaderStarter() {
        return new ReaderStarter();
    }

}
