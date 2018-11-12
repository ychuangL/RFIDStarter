package com.nuite.rfid.util;

import com.module.interaction.ReaderHelper;
import com.nuite.rfid.core.CMDHelper;
import com.nuite.rfid.core.ReaderCore;
import com.rfid.RFIDReaderHelper;
import com.rfid.ReaderConnector;
import org.junit.Test;

/**
 * reader测试工具类
 *
 * @Author: yangchuang
 * @Date: 2018/11/7 9:36
 * @Version: 1.0
 */

public class ReaderTester {

    /**
     * 初始化硬件参数测试
     * @param ip
     * @param port
     * @return true：设置成功，false：设置失败
     */
    public static boolean init(String ip,int port){

        ReaderConnector connector =new ReaderConnector();
        ReaderHelper readerHelper = connector.connectNet(ip, port);
        if (readerHelper == null) {
            connector.disConnect();
            return false;
        }
        connector.disConnect();
        return true;
    }

    @Test
    public void test() throws InterruptedException {

        RFIDReaderHelper readerHelper = ReaderCore.getInstance().init("192.168.0.178", 4001);

        ReaderCore.getInstance().registerObserver();

        byte antConnectionDetector = CMDHelper.getAntConnectionDetector(readerHelper);

        System.out.println("antConnectionDetector = " + antConnectionDetector);

        PrintUtils.printBtToStr(antConnectionDetector);
    }
}
