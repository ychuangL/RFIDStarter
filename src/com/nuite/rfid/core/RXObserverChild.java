package com.nuite.rfid.core;

import com.nuite.rfid.util.PrintUtils;
import com.rfid.rxobserver.RXObserver;
import com.rfid.rxobserver.ReaderSetting;
import com.rfid.rxobserver.bean.RXInventoryTag;

/**
 * 读写器观察者
 *
 * @Author: yangchuang
 * @Date: 2018/10/26 10:22
 * @Version: 1.0
 */

public class RXObserverChild extends RXObserver {

    private byte exeCMDStatus;
    private RXInventoryTag rxInventoryTag;
    private RXInventoryTag.RXInventoryTagEnd rxInventoryTagEnd;
    private ReaderSetting setting;
    private TagPool tagPool = TagPool.getInstance();

    /**
     * 每读到一个标签，就回调一次
     *
     * @param tag
     */
    @Override
    protected void onInventoryTag(RXInventoryTag tag) {
        //PrintUtils.printRXInventoryTag(tag);
        this.rxInventoryTag = tag;
        this.tagPool.saveTag(tag.strEPC);
    }

    /**
     * 每次盘存指令执行结束后回调此方法
     *
     * @param endTag
     */
    @Override
    protected void onInventoryTagEnd(RXInventoryTag.RXInventoryTagEnd endTag) {
        PrintUtils.printRXInventoryTagEnd(endTag);
        this.rxInventoryTagEnd = endTag;
        if (this.tagPool.isNotEmpty()) {
            this.tagPool.removeExpired();
        }
    }

    /**
     * 读取或设置读写器配置后，会回调此方法
     *
     * @param cmd
     * @param status
     */
    @Override
    protected void onExeCMDStatus(byte cmd, byte status) {
        //System.out.format("CDM: 0x%s   RETURN_STATUS: 0x%S \n", String.format("%02X", cmd), String.format("%02x", status));
        this.exeCMDStatus = status;
    }

    /**
     * 读取或设置配置后，会回调此方法
     *
     * @param readerSetting
     */
    @Override
    protected void refreshSetting(ReaderSetting readerSetting) {
        //PrintUtils.printSetting(readerSetting);
        this.setting = readerSetting;
    }

    public byte getExeCMDStatus() {
        return exeCMDStatus;
    }

    public RXInventoryTag getRxInventoryTag() {
        return rxInventoryTag;
    }

    public RXInventoryTag.RXInventoryTagEnd getRxInventoryTagEnd() {
        return rxInventoryTagEnd;
    }

    public ReaderSetting getSetting() {
        return setting;
    }

    public TagPool getTagPool() {
        return tagPool;
    }
}
