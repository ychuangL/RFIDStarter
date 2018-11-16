package com.nuite.rfid.core;

import com.module.interaction.ReaderHelper;
import com.nuite.rfid.domain.ReaderConfigInfo;
import com.nuite.rfid.exception.ReaderInitException;
import com.nuite.rfid.exception.ReaderSettingParamException;
import com.nuite.rfid.option.*;
import com.nuite.rfid.util.Validator;
import com.rfid.RFIDReaderHelper;
import com.rfid.ReaderConnector;

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
    private RXObserverChild mObserver;

    public ReaderCore() {
    }

    /**
     * 初始化连接，注册观察者，返回读写器对象
     *
     * @param ip
     * @param port
     * @return 读写器对象 或抛异常
     */
    public RFIDReaderHelper init(String ip, int port) {

        connector = new ReaderConnector();
        ReaderHelper helper = connector.connectNet(ip, port);

        if (helper == null) {
            connector.disConnect();
            throw new ReaderInitException("读写器连接失败！");
        }
        readerHelper = (RFIDReaderHelper) helper;
        /*注册观察者*/
        registerObserver();

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
        init(ip, port);
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
            mObserver.getTagPool().clearPool();
            readerHelper.unRegisterObserver(mObserver);
            mObserver = null;
        }
    }

    /**
     * 设置监听器(监听发送和接收的数据，没啥用，默认不设置)
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
    }

    /**
     * 默认配置
     *
     * @throws InterruptedException
     */
    private void defaultSetting() throws InterruptedException {
        //设置蜂鸣器状态  0x00安静  0x01每次盘存后鸣响  0x02 每读到一张标签鸣响
        CMDHelper.setBeeperMode(readerHelper, BeeperEnum.MUTE.getByteValue());

        //设置读写器工作天线 0x00 天线一  0x01  0x02  0x03 天线四
        //CMDHelper.setWorkAntenna(readerHelper, AntennaEnum.ANTENNA_1.getByteValue());

        //设置射频输出功率 25-30,
        CMDHelper.setOutputPower(readerHelper, (byte) 0x1b);

        //设置频谱范围 0x01 FCC
        CMDHelper.setFrequencyRegion(readerHelper, FrequencyRegionEnum.FCC.getByteValue(), (byte) 0x07, (byte) 0x3B);

        //设置射频链路的通讯速率（0xD0 ,0xD1 ,0xD2 ,0xD3）
        CMDHelper.setRfLinkProfile(readerHelper, ProFileEnum.Miller250KHz.getByteValue());

        //设置天线灵敏度回波损耗阀值
        CMDHelper.setAntConnectionDetector(readerHelper, (byte) 10);
    }

    /**
     * 自定义配置
     *
     * @param configInfo 自定义硬件配置信息
     * @throws InterruptedException
     */
    public void doSetting(ReaderConfigInfo configInfo) throws InterruptedException {
        //设置蜂鸣器状态
        CMDHelper.setBeeperMode(readerHelper, BeeperEnum.getByteValueByInt(configInfo.getBleeperMode()));

        //设置射频输出功率 25-30,
        CMDHelper.setOutputPower(readerHelper, configInfo.getOutPutPower().byteValue());

        //设置频谱范围 0x01 FCC
        CMDHelper.setFrequencyRegion(readerHelper, FrequencyRegionEnum.FCC.getByteValue(), (byte) 0x07, (byte) 0x3B);

        //设置射频链路的通讯速率（0xD0 ,0xD1 ,0xD2 ,0xD3）
        CMDHelper.setRfLinkProfile(readerHelper, ProFileEnum.Miller250KHz.getByteValue());

        //设置天线灵敏度回波损耗阀值
        CMDHelper.setAntConnectionDetector(readerHelper, (byte)configInfo.getReturnLoss());
    }

    /**
     * 执行盘存指令（实时盘存模式）
     *
     * @param antennas
     * @throws InterruptedException
     */
    public void executeRealTimeInventory(byte[] antennas) throws InterruptedException {

        if (antennas.length > 1) {
            CMDHelper.exeRealTimeInventoryBetweenAntennas(readerHelper, antennas, mObserver);
        } else if (antennas.length == 1) {
            CMDHelper.setWorkAntenna(readerHelper, antennas[0]);
            CMDHelper.executeRealTimeInventory(readerHelper, mObserver);
        } else {
            throw new ReaderSettingParamException("天线参数错误！天线数量小于1");
        }
    }

    /**
     * 执行盘存指令（实时盘存模式）
     *
     * @param antennasStr 从数据库获取的字符串，如 1,2
     * @throws InterruptedException
     */
    public void executeRealTimeInventory(String antennasStr) throws InterruptedException {
        String[] strArr = antennasStr.split(",");
        byte[] ants = new byte[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            ants[i] = AntennaEnum.getByteValueByInt(Integer.parseInt(strArr[i]));
        }
        executeRealTimeInventory(ants);
    }

    /**
     * 执行盘存指令（实时盘存模式）
     *
     * @param antennas [1,2,3,4] [天线1，天线2]
     * @throws InterruptedException
     */
    public void executeRealTimeInventory(int[] antennas) throws InterruptedException {
        byte[] ants = new byte[antennas.length];
        for (int i = 0; i < antennas.length; i++) {
            ants[i] = AntennaEnum.getByteValueByInt(antennas[i]);
        }
        executeRealTimeInventory(ants);
    }

    /**
     * 检验硬件配置参数是否合法，执行在初始化之前
     *
     * @param configInfo
     */
    public void checkSettingParam(ReaderConfigInfo configInfo) {

        /*检测configInfo是否为null*/
        if (configInfo == null) {
            throw new ReaderSettingParamException("读卡器配置对象readerConfigInfo为null");
        }

        /*检测readerType*/
        if (configInfo.getReaderType() != 0 && configInfo.getReaderType() != 1) {
            throw new ReaderSettingParamException("readerType 参数错误，只有0和1");
        } else if (configInfo.getReaderType() == 1) {
            //目前仅支持服务端模式
            configInfo.setReaderType(0);
        }

        /*检测ip是否合法*/
        if (configInfo.getIP() == null) {
            throw new ReaderSettingParamException("IP参数为null");
        }
        if (!Validator.checkIP(configInfo.getIP())) {
            throw new ReaderSettingParamException("IP地址不合法");
        }
        /*检测port是否合法*/
        if (configInfo.getPort() == null) {
            throw new ReaderSettingParamException("port参数为null");
        }
        if (!Validator.checkPort(configInfo.getPort())) {
            throw new ReaderSettingParamException("port参数不合法");
        }

        /*射频输出功率功率2-255（默认30）*/
        if (configInfo.getOutPutPower() < 2 || configInfo.getOutPutPower() > 255) {
            throw new ReaderSettingParamException("射频输出功率功率参数超出范围2-255");
        }
        /*蜂鸣器模式*/
        if (!BeeperEnum.isExist(configInfo.getBleeperMode())) {
            throw new ReaderSettingParamException("蜂鸣器模式参数错误，超出范围0-3");
        }
        /*射频通讯链路*/
        if (!ProFileEnum.isExist(configInfo.getProFileMode())) {
            /*超出范围设置默认值*/
            configInfo.setProFileMode(2);
        }
        /*回波损耗阀值*/
        if (configInfo.getReturnLoss() != 10 && configInfo.getReturnLoss() != 0) {
            /*使用推荐值10*/
            configInfo.setReturnLoss(10);
        }
        /*检测天线口*/
        if (configInfo.getUseAntennaPort() == null) {
            throw new ReaderSettingParamException("天线参数useAntennaPort为null");
        } else if (configInfo.getUseAntennaPort().isEmpty()) {
            throw new ReaderSettingParamException("天线参数useAntennaPort为空");
        } else {
            String[] ants = configInfo.getUseAntennaPort().split(",");
            for (String antID : ants) {
                if (!AntennaEnum.isExist(Integer.parseInt(antID))) {
                    throw new ReaderSettingParamException("天线参数异常，天线" + antID + "不存在，取值范围[1,4]");
                }
            }
        }

    }

    /**
     * 获得观察者
     */
    public RXObserverChild getRXObserver() {
        return mObserver;
    }

    /**
     * 获取读卡器对象
     *
     * @return
     */
    public RFIDReaderHelper getReaderHelper() {
        return readerHelper;
    }
}
