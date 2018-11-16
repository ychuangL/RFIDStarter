package com.nuite.rfid;

import com.nuite.rfid.core.ReaderCore;
import com.nuite.rfid.domain.ReaderConfigInfo;

/**
 * @Author: yangchuang
 * @Date: 2018/11/15 17:26
 * @Version: 1.0
 */

public class ReaderStarterTest {

    public static void main(String[] args) {
        //test1();
        //test2();
        System.out.println((byte)0xff);

    }

    private static void test2() {
        ReaderConfigInfo configInfo=new ReaderConfigInfo();
        try {
            ReaderStarterFactory.createReaderStarter().start(configInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void test1() {
        ReaderStarter starter = ReaderStarterFactory.createReaderStarter();
        ReaderCore readerCore = starter.getCore();
        try {
            /*启动读写器*/
            starter.start("192.168.0.178", 4001,new int[]{1,2});
        } catch (Exception e) {
            e.printStackTrace();
            starter.close();
        }
    }
}