package com.nuite.rfid.core;

import com.module.interaction.ReaderHelper;
import com.nuite.rfid.option.AntennaMode;
import com.nuite.rfid.option.BeeperMode;
import com.nuite.rfid.option.FrequencyRegionMode;
import com.nuite.rfid.option.ProFileIDMode;
import com.rfid.RFIDReaderHelper;
import com.rfid.ReaderConnector;
import com.rfid.rxobserver.RXObserver;

/**
 * reader核心,单例模式
 * 负责读写器 连接、默认设置、注销
 *
 * @Author: yangchuang
 * @Date: 2018/11/7 9:39
 * @Version: 1.0
 */

public class ReaderCore {

    private ReaderConnector connector;
    private RFIDReaderHelper readerHelper;
    private RXObserver mObserver;
    private static ReaderCore readerCore = new ReaderCore();

    private ReaderCore() {
    }

    /**
     * 获取readcore单实例
     *
     * @return
     */
    public static ReaderCore getInstance() {
        return readerCore;
    }

    /**
     * 初始化硬件参数
     *
     * @param ip
     * @param port
     * @return reader对象，或null
     */
    public RFIDReaderHelper init(String ip, int port) {

        connector = new ReaderConnector();
        ReaderHelper helper = connector.connectNet(ip, port);

        if (helper == null) {
            connector.disConnect();
            return null;
        }
        readerHelper = (RFIDReaderHelper) helper;
        return readerHelper;
    }

    /**
     * 初始化读写器，
     * 注册观察者，
     * 设置监听器，
     * 并执行默认设置
     *
     * @param ip
     * @param port
     * @return
     * @throws InterruptedException
     */
    public RFIDReaderHelper initAndDefaultSetting(String ip, int port) throws InterruptedException {
        RFIDReaderHelper readerHelper = init(ip, port);
        if (readerHelper == null) {
            throw new NullPointerException("initAndDefaultSetting is failed,reader is null");
        }
        registerObserver();
        //setRXTXListener();
        defaultSetting();
        return readerHelper;
    }

    /**
     * 注册观察者
     */
    public void registerObserver() {
        if (mObserver == null) {
            mObserver = new RXObserverChild();
        }

        if (readerHelper != null) {
            readerHelper.registerObserver(mObserver);
        }
    }

    /**
     * 注销观察者
     */
    public void unRegisterObserver() {
        if (mObserver != null && readerHelper != null) {
            readerHelper.unRegisterObserver(mObserver);
            mObserver = null;
        }
    }

    /**
     * 设置监听器
     */
    public void setRXTXListener() {

        if (readerHelper != null) {
            readerHelper.setRXTXListener(new RXTXListener0());
        }
    }

    /**
     * 移除监听器
     */
    public void removeRXTXListener() {
        if (readerHelper != null) {
            readerHelper.setRXTXListener(null);
        }
    }

    /**
     * 停止盘存，盘存结束，不可恢复
     */
    public void stopInventory() {
        CMDHelper.stopFlag = true;
    }

    /**
     * 暂停盘存操作
     */
    public void pauseInventory() {
        CMDHelper.runFlag = false;
    }
    /**
     * 继续盘存操作
     */
    public void continueInventory() {
        CMDHelper.runFlag = true;
    }

    /**
     * 释放资源
     */
    public void destroy() {
        stopInventory();
        unRegisterObserver();
        removeRXTXListener();

        if (connector != null) {
            connector.disConnect();
        }
        TagPool.getInstance().clearPool();
    }

    /**
     * 默认配置
     *
     * @throws InterruptedException
     */
    public void defaultSetting() throws InterruptedException {
        //设置蜂鸣器状态  0x00安静  0x01每次盘存后鸣响  0x02 每读到一张标签鸣响
        CMDHelper.setBeeperMode(readerHelper, BeeperMode.MUTE);

        //设置读写器工作天线 0x00 天线一  0x01  0x02  0x03 天线四
        CMDHelper.setWorkAntenna(readerHelper, AntennaMode.ANTENNA_1);

        //设置射频输出功率 25-30,
        CMDHelper.setOutputPower(readerHelper, (byte) 0x1b);

        //设置频谱范围 0x01 FCC
        CMDHelper.setFrequencyRegion(readerHelper, FrequencyRegionMode.FCC, (byte) 0x07, (byte) 0x3B);

        //设置射频链路的通讯速率（0xD0 ,0xD1 ,0xD2 ,0xD3）
        CMDHelper.setRfLinkProfile(readerHelper, ProFileIDMode.DEFAULT);

        //设置天线灵敏度回波损耗阀值
        CMDHelper.setAntConnectionDetector(readerHelper, (byte) 10);
    }
}
