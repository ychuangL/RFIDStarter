package com.nuite.rfid;

import com.nuite.rfid.core.CMDHelper;
import com.nuite.rfid.core.ReaderCore;
import com.nuite.rfid.option.AntennaMode;
import com.rfid.RFIDReaderHelper;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 读写器启动器
 *
 * @Author: yangchuang
 * @Date: 2018/10/26 11:06
 * @Version: 1.0
 */

public class ReaderStarter {

    /**
     * 读写器内核
     */
    private static final ReaderCore core = ReaderCore.getInstance();
    private static RFIDReaderHelper readerHelper;

    private ReaderStarter() {
    }

    /**
     * 启动读写器
     */
    public static void start(String ip, int port) throws Exception {
        readerHelper = core.initAndDefaultSetting(ip, port);
        if (readerHelper == null) {
            throw new RuntimeException("reader is null!");
        }
    }

    /**
     * 盘存标签(实时上传标签数据) 单天线
     *
     * @param antenna
     * @throws InterruptedException
     */
    public static void executeRealTimeInventory(byte antenna) throws InterruptedException {
        CMDHelper.setWorkAntenna(readerHelper, antenna);
        CMDHelper.executeRealTimeInventory(readerHelper);
    }

    /**
     * 盘存标签(实时上传标签数据) 多天线
     *
     * @param antennas
     * @throws InterruptedException
     */
    public static void executeRealTimeInventory(byte[] antennas) throws InterruptedException {
        CMDHelper.exeRealTimeInventoryBetweenAntennas(readerHelper, antennas);
    }

    /**
     * 关闭读写器
     */
    public static void close() {
        core.destroy();
    }

    public static void main(String[] args) {
        try {
            /*启动读写器*/
            start("192.168.0.178", 4001);

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    //close();
                    core.pauseInventory();
                }
            }, 1000 * 7);


            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    //close();
                    core.continueInventory();
                }
            }, 1000 * 15);
            /*盘存标签(实时上传标签数据) 单天线*/
            executeRealTimeInventory(AntennaMode.ANTENNA_1);
            /*盘存标签(实时上传标签数据) 多天线*/
            //executeRealTimeInventory(new byte[]{AntennaMode.ANTENNA_1, AntennaMode.ANTENNA_2});
        } catch (Exception e) {
            e.printStackTrace();
            close();
        }
    }

}
