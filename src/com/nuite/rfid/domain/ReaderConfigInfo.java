package com.nuite.rfid.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * reader配置信息类
 * 对应硬件参数设置表
 *
 * @Author: yangchuang
 * @Date: 2018/11/15 10:48
 * @Version: 1.0
 */

public class ReaderConfigInfo {

    /**
     * 序号
     */
    private Integer seq;
    /**
     * 门店序号
     */
    private Integer shopSeq;
    /**
     * 硬件编号
     */
    private String hardwareID;
    /**
     * 硬件名称
     */
    private String name;
    /**
     * 使用天线口标识，用逗号做标识，如果1和3天线口可用，则值为1,3
     */
    private String useAntennaPort;
    /**
     * 读取方式
     * 0服务器端
     * 1客户端
     * 默认：服务器端
     */
    private int readerType = 0;

    /**
     * IP地址
     */
    private String IP;
    /**
     * 端口号
     */
    private String port;
    /**
     * 射频输出功率功率2-255（默认30）
     */
    private Integer outPutPower = 30;
    /**
     * RFID有效时长（秒）
     */
    private String RFIDEffectTime;
    /**
     * 频率最小值
     */
    private BigDecimal minFrequency;
    /**
     * 频率最大值
     */
    private BigDecimal maxFrequency;
    /**
     * 频率间隔
     */
    private BigDecimal frequencySpace;
    /**
     * 蜂鸣器设置
     * 0, //默认静音 0xff
     * 1, //默认静音 0x00
     * 2,//存盘一次就嗡鸣0x01
     * 3,//每读到一张标签鸣响(影响多标签识别 0x02
     */
    private int BleeperMode = 0;
    /**
     * 射频通讯链路
     * 0, //默认无 0xff
     * 1, //配置0  Tari 25uS; FM0 40KHz0xD0
     * 2,//配置1(推荐且为默认)Tari 25uS; Miller 4 250KHz0xD1
     * 3,//配置2  Tari 25uS; Miller 4 300KHz;0xD2
     * 4,//配置3  Tari 6.25uS; FM0 400KHz;0xD3
     */
    private int proFileMode = 1;
    /**
     * 10, 为保护设备，检测到回波损耗大于此阈值将报错并停止读写标签操作。
     * 值为0:关闭此功能
     */
    private int ReturnLoss = 10;
    /**
     * 插入时间
     */
    private Date inputTime;
    /**
     * 删除标记
     */
    private int del;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getShopSeq() {
        return shopSeq;
    }

    public void setShopSeq(Integer shopSeq) {
        this.shopSeq = shopSeq;
    }

    public String getHardwareID() {
        return hardwareID;
    }

    public void setHardwareID(String hardwareID) {
        this.hardwareID = hardwareID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUseAntennaPort() {
        return useAntennaPort;
    }

    public void setUseAntennaPort(String useAntennaPort) {
        this.useAntennaPort = useAntennaPort;
    }

    public int getReaderType() {
        return readerType;
    }

    public void setReaderType(int readerType) {
        this.readerType = readerType;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Integer getOutPutPower() {
        return outPutPower;
    }

    public void setOutPutPower(Integer outPutPower) {
        this.outPutPower = outPutPower;
    }

    public String getRFIDEffectTime() {
        return RFIDEffectTime;
    }

    public void setRFIDEffectTime(String RFIDEffectTime) {
        this.RFIDEffectTime = RFIDEffectTime;
    }

    public BigDecimal getMinFrequency() {
        return minFrequency;
    }

    public void setMinFrequency(BigDecimal minFrequency) {
        this.minFrequency = minFrequency;
    }

    public BigDecimal getMaxFrequency() {
        return maxFrequency;
    }

    public void setMaxFrequency(BigDecimal maxFrequency) {
        this.maxFrequency = maxFrequency;
    }

    public BigDecimal getFrequencySpace() {
        return frequencySpace;
    }

    public void setFrequencySpace(BigDecimal frequencySpace) {
        this.frequencySpace = frequencySpace;
    }

    public int getBleeperMode() {
        return BleeperMode;
    }

    public void setBleeperMode(int bleeperMode) {
        BleeperMode = bleeperMode;
    }

    public int getProFileMode() {
        return proFileMode;
    }

    public void setProFileMode(int proFileMode) {
        this.proFileMode = proFileMode;
    }

    public int getReturnLoss() {
        return ReturnLoss;
    }

    public void setReturnLoss(int returnLoss) {
        ReturnLoss = returnLoss;
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ReaderConfigInfo{");
        sb.append("seq=").append(seq);
        sb.append(", shopSeq=").append(shopSeq);
        sb.append(", hardwareID='").append(hardwareID).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", useAntennaPort='").append(useAntennaPort).append('\'');
        sb.append(", readerType=").append(readerType);
        sb.append(", IP='").append(IP).append('\'');
        sb.append(", port='").append(port).append('\'');
        sb.append(", outPutPower=").append(outPutPower);
        sb.append(", RFIDEffectTime='").append(RFIDEffectTime).append('\'');
        sb.append(", minFrequency=").append(minFrequency);
        sb.append(", maxFrequency=").append(maxFrequency);
        sb.append(", frequencySpace=").append(frequencySpace);
        sb.append(", BleeperMode=").append(BleeperMode);
        sb.append(", proFileMode=").append(proFileMode);
        sb.append(", ReturnLoss=").append(ReturnLoss);
        sb.append(", inputTime=").append(inputTime);
        sb.append(", del=").append(del);
        sb.append('}');
        return sb.toString();
    }
}
