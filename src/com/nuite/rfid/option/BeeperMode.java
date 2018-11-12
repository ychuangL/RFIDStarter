package com.nuite.rfid.option;

/**
 * @Author: yangchuang
 * @Date: 2018/11/6 16:01
 * @Version: 1.0
 */

public interface BeeperMode {

    /**
     * 静音
     */
    byte MUTE = (byte) 0x00;

    /**
     * 每次盘存后鸣响
     */
    byte ONE_INVENTORY = (byte) 0x01;

    /**
     * 每读到一张标签鸣响
     */
    byte READ_A_TAG = (byte) 0x02;

}
