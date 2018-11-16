package com.nuite.rfid.core;

import com.nuite.rfid.exception.ReaderCMDException;
import com.nuite.rfid.option.AntennaEnum;
import com.nuite.rfid.util.CoreParam;
import com.rfid.RFIDReaderHelper;

/**
 * 指令类
 * 负责管理所有读写器命令
 *
 * @Author: yangchuang
 * @Date: 2018/11/7 8:37
 * @Version: 1.0
 */

public class CMDHelper {
    /**
     * 命令执行间隔，毫秒
     */
    private static long cmdInternal = 300;

    /**
     * 停止标志
     */
    public static boolean stopFlag = false;

    /**
     * 运行标志，false为暂停
     */
    public static boolean runFlag = true;

    /**
     * 获取reader版本号
     *
     * @param readerCore
     * @return
     */
    public static String getReaderVersion(ReaderCore readerCore) throws InterruptedException {
        RFIDReaderHelper readerHelper = readerCore.getReaderHelper();
        RXObserverChild rxObserverChild = readerCore.getRXObserver();
        if (readerHelper == null || rxObserverChild == null) return null;

        int res = readerHelper.getFirmwareVersion(CoreParam.READER_ID);
        if (res != 0) {
            throw new ReaderCMDException("getReaderVersion is failed");
        }

        Thread.sleep(cmdInternal);
        if (rxObserverChild.getSetting() == null) return null;
        return rxObserverChild.getSetting().btMajor + "." + rxObserverChild.getSetting().btMinor;
    }

    /**
     * 获取当前设备工作温度
     *
     * @param readerCore
     * @return
     */
    public static String getReaderTemperature(ReaderCore readerCore) throws InterruptedException {
        RFIDReaderHelper readerHelper = readerCore.getReaderHelper();
        RXObserverChild rxObserverChild = readerCore.getRXObserver();
        if (readerHelper == null || rxObserverChild == null) return null;

        int res = readerHelper.getReaderTemperature(CoreParam.READER_ID);
        if (res != 0) {
            throw new ReaderCMDException("getReaderTemperature is failed");
        }

        Thread.sleep(cmdInternal);
        if (rxObserverChild.getSetting() == null) return null;
        return rxObserverChild.getSetting().btTemperature + "";
    }

    /**
     * 获取当前天线工作天线
     *
     * @param readerCore
     * @return
     * @throws InterruptedException
     */
    public static Integer getWorkAntenna(ReaderCore readerCore) throws InterruptedException {

        RFIDReaderHelper readerHelper = readerCore.getReaderHelper();
        RXObserverChild rxObserverChild = readerCore.getRXObserver();
        if (readerHelper == null || rxObserverChild == null) return null;

        int res = readerHelper.getWorkAntenna(CoreParam.READER_ID);
        if (res != 0) {
            throw new ReaderCMDException("getWorkAntenna is failed");
        }
        Thread.sleep(cmdInternal);
        if (rxObserverChild.getSetting() == null) return null;
        return rxObserverChild.getSetting().btWorkAntenna + 1;
    }

    /**
     * 获取射频输出功率
     *
     * @param readerCore
     * @return
     * @throws InterruptedException
     */
    public static byte getOutputPower(ReaderCore readerCore) throws InterruptedException {
        RFIDReaderHelper readerHelper = readerCore.getReaderHelper();
        RXObserverChild rxObserverChild = readerCore.getRXObserver();
        if (readerHelper == null || rxObserverChild == null) return -1;

        int res = readerHelper.getOutputPower(CoreParam.READER_ID);
        if (res != 0) {
            throw new ReaderCMDException("getOutputPower is failed");
        }
        Thread.sleep(cmdInternal);
        if (rxObserverChild.getSetting() == null || rxObserverChild.getSetting().btAryOutputPower == null) return -1;
        return rxObserverChild.getSetting().btAryOutputPower[0];
    }

    /**
     * 获取频谱范围
     *
     * @param readerCore
     * @return {射频规范，频谱起点参数，频谱结束点参数}，详细参见【频率参数对应表】
     * @throws InterruptedException
     */
    public static byte[] getFrequencyRegion(ReaderCore readerCore) throws InterruptedException {
        RFIDReaderHelper readerHelper = readerCore.getReaderHelper();
        RXObserverChild rxObserverChild = readerCore.getRXObserver();
        if (readerHelper == null || rxObserverChild == null) return null;

        int res = readerHelper.getFrequencyRegion(CoreParam.READER_ID);
        if (res != 0) {
            throw new ReaderCMDException("getFrequencyRegion is failed");
        }
        Thread.sleep(cmdInternal);

        if (rxObserverChild.getSetting() == null) return null;
        byte[] btArr = {rxObserverChild.getSetting().btRegion, rxObserverChild.getSetting().btFrequencyStart, rxObserverChild.getSetting().btFrequencyEnd};
        return btArr;
    }

    /**
     * 获取射频链路的通讯速率模式（0xD0 ,0xD1 ,0xD2 ,0xD3）推荐0xD1
     *
     * @param readerCore
     * @return
     * @throws InterruptedException
     */
    public static byte getRfLinkProfile(ReaderCore readerCore) throws InterruptedException {
        RFIDReaderHelper readerHelper = readerCore.getReaderHelper();
        RXObserverChild rxObserverChild = readerCore.getRXObserver();
        if (readerHelper == null || rxObserverChild == null) return -1;

        int res = readerHelper.getRfLinkProfile(CoreParam.READER_ID);
        if (res != 0) {
            throw new ReaderCMDException("getRfLinkProfile is failed");
        }
        Thread.sleep(cmdInternal);

        if (rxObserverChild.getSetting() == null) return -1;
        return rxObserverChild.getSetting().btRfLinkProfile;
    }

    /**
     * 获取天线灵敏度回波损耗阀值
     *
     * @param readerCore
     * @return
     * @throws InterruptedException
     */
    public static byte getAntConnectionDetector(ReaderCore readerCore) throws InterruptedException {
        RFIDReaderHelper readerHelper = readerCore.getReaderHelper();
        RXObserverChild rxObserverChild = readerCore.getRXObserver();
        if (readerHelper == null || rxObserverChild == null) return -1;

        int res = readerHelper.getAntConnectionDetector(CoreParam.READER_ID);
        if (res != 0) {
            throw new ReaderCMDException("getAntConnectionDetector is failed");
        }
        Thread.sleep(cmdInternal);

        if (rxObserverChild.getSetting() == null) return -1;
        return rxObserverChild.getSetting().btAntDetector;
    }

    /**
     * 设置蜂鸣器状态
     *
     * @param readerHelper
     * @param beeperMode   0x00安静  0x01每次盘存后鸣响  0x02 每读到一张标签鸣响
     * @return
     * @throws InterruptedException
     */
    public static boolean setBeeperMode(RFIDReaderHelper readerHelper, byte beeperMode) throws InterruptedException {


        int res = readerHelper.setBeeperMode(CoreParam.READER_ID, beeperMode);
        if (res != 0) {
            throw new ReaderCMDException("setBeeperMode is failed");
        }
        Thread.sleep(cmdInternal);
        return true;
    }

    /**
     * 设置读写器工作天线
     *
     * @param readerHelper
     * @param antennaMode  0x00 天线一  0x01  0x02  0x03 天线四
     * @return
     * @throws InterruptedException
     */
    public static boolean setWorkAntenna(RFIDReaderHelper readerHelper, byte antennaMode) throws InterruptedException {
        int res = readerHelper.setWorkAntenna(CoreParam.READER_ID, antennaMode);
        if (res != 0) {
            throw new ReaderCMDException("setWorkAntenna is failed");
        }
        Thread.sleep(cmdInternal);
        return true;
    }

    /**
     * 设置射频输出功率
     *
     * @param readerHelper
     * @param btOutputPower 推荐值范围25-30
     * @return
     * @throws InterruptedException
     */
    public static boolean setOutputPower(RFIDReaderHelper readerHelper, byte btOutputPower) throws InterruptedException {
        int res = readerHelper.setOutputPower(CoreParam.READER_ID, btOutputPower);
        if (res != 0) {
            throw new ReaderCMDException("setOutputPower is failed");
        }
        Thread.sleep(cmdInternal);
        return true;
    }

    /**
     * 设置频谱范围
     *
     * @param readerHelper
     * @param frequencyRegionMode 0x01 FCC
     * @param btStartRegion       见频率参数对照表
     * @param btEndRegion
     * @return
     * @throws InterruptedException
     */
    public static boolean setFrequencyRegion(RFIDReaderHelper readerHelper, byte frequencyRegionMode, byte btStartRegion, byte btEndRegion) throws InterruptedException {
        int res = readerHelper.setFrequencyRegion(CoreParam.READER_ID, frequencyRegionMode, btStartRegion, btEndRegion);
        if (res != 0) {
            throw new ReaderCMDException("setFrequencyRegion is failed");
        }
        Thread.sleep(cmdInternal);
        return true;
    }

    /**
     * 设置射频链路的通讯速率
     *
     * @param readerHelper
     * @param proFileIDMode 0xD0 ,0xD1(推荐) ,0xD2 ,0xD3
     * @return
     * @throws InterruptedException
     */
    public static boolean setRfLinkProfile(RFIDReaderHelper readerHelper, byte proFileIDMode) throws InterruptedException {
        int res = readerHelper.setRfLinkProfile(CoreParam.READER_ID, proFileIDMode);
        if (res != 0) {
            throw new ReaderCMDException("setRfLinkProfile is failed");
        }
        Thread.sleep(cmdInternal);
        return true;
    }

    /**
     * 设置天线灵敏度回波损耗阀值
     *
     * @param readerHelper
     * @param btDetectorStatus 10
     * @return
     * @throws InterruptedException
     */
    public static boolean setAntConnectionDetector(RFIDReaderHelper readerHelper, byte btDetectorStatus) throws InterruptedException {
        int res = readerHelper.setAntConnectionDetector(CoreParam.READER_ID, btDetectorStatus);
        if (res != 0) {
            throw new ReaderCMDException("setAntConnectionDetector is failed");
        }
        Thread.sleep(cmdInternal);
        return true;
    }

    /**
     * 盘存操作封装
     */
    private static void exeRealTimeInventory(RFIDReaderHelper readerHelper) throws InterruptedException {
        readerHelper.realTimeInventory(CoreParam.READER_ID, (byte) 0xff);
        Thread.sleep(cmdInternal);
    }

    /**
     * 盘存标签(实时上传标签数据)
     *
     * @param readerHelper
     * @throws InterruptedException 线程中断异常
     */
    public static void executeRealTimeInventory(RFIDReaderHelper readerHelper, RXObserverChild rxObserverChild) throws InterruptedException {
        stopFlag = false;
        while (true) {
            if (runFlag) {
                exeRealTimeInventory(readerHelper);

                rxObserverChild.getTagPool().printPool();
                System.out.println("********************盘存结束**************************\n\n");
            } else {
                //暂停状态
                System.out.println("盘存暂停中 ...");
                Thread.sleep(3000);
            }

            if (stopFlag) {
                break;
            }
        }
    }

    /**
     * 盘存标签（多天线切换模式）
     *
     * @param readerHelper
     * @param btArrAntennas 多天线数组
     * @throws InterruptedException
     */
    public static void exeRealTimeInventoryBetweenAntennas(RFIDReaderHelper readerHelper, byte[] btArrAntennas, RXObserverChild rxObserverChild) throws InterruptedException {
        stopFlag = false;
        //多天线 0x00 天线一  0x01  0x02  0x03 天线四
        //当前天线数组索引
        int antIndex = 0;
        while (true) {
            if (runFlag) {

                antIndex = antIndex < btArrAntennas.length ? antIndex : 0;

                String antennaName = AntennaEnum.getNameByValue(btArrAntennas[antIndex]);
                System.out.println("\n:开始连接" + antennaName);

                setWorkAntenna(readerHelper, btArrAntennas[antIndex]);
                antIndex++;

                exeRealTimeInventory(readerHelper);

                /*如果天线未连接 errorCode 0x22*/
                //PrintUtils.printBtToStr(RXObserverChild.exeCMDStatus);
                if (rxObserverChild.getExeCMDStatus() == (byte) 0x22) {
                    System.out.println("警告： " + antennaName + "未连接！！");
                    continue;
                }
                rxObserverChild.getTagPool().printPool();
                System.out.println("********************盘存结束**************************\n\n");
            } else {
                //暂停状态
                System.out.println("盘存暂停中 ...  3s 后重运行");
                Thread.sleep(3000);
            }

            if (stopFlag) {
                break;
            }

        }
    }

    public static long getCmdInternal() {
        return cmdInternal;
    }

    public static void setCmdInternal(long cmdInternal) {
        CMDHelper.cmdInternal = cmdInternal;
    }

    /**
     * reader操作 测试
     */
    private void inventory(RFIDReaderHelper readerHelper) throws InterruptedException {

        //盘存标签
        //helper.inventory((byte) 0xff, (byte) 0xff);

        //提取标签数据并删除缓存
        //readerHelper.getInventoryBuffer((byte) 0xff);
        //提取标签数据保留缓存备份
        //readerHelper.getAndResetInventoryBuffer((byte) 0xff);
        //查询缓存中已读标签个数
        //readerHelper.getInventoryBufferTagCount((byte) 0xff);

        //清空标签数据缓存
        //readerHelper.resetInventoryBuffer((byte) 0xff);

        //快速轮询多个天线盘存标签
        /*
         * byte btReadId,
         * byte btA, byte btStayA, byte btB, byte btStayB, byte btC, byte btStayC, byte btD, byte btStayD,
         * byte btInterval,
         * byte btRepeat
         */
        /*helper.fastSwitchAntInventory((byte) 0xff,
                (byte) 0x00,(byte) 1,(byte) 0x01,(byte) 1,(byte) 0x02,(byte) 1,(byte) 0x03,(byte) 1,
                (byte) 0x01,(byte) 0xff);*/
    }
}
