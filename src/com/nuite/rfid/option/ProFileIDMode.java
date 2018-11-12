package com.nuite.rfid.option;

/**
 * @Author: yangchuang
 * @Date: 2018/11/6 16:15
 * @Version: 1.0
 */

public interface ProFileIDMode {

    byte DEFAULT = (byte) 0xD1;

    /**
     * 配置0  Tari 25uS; FM0 40KHz0xD0
     */
    byte FM040KHz = (byte) 0xD0;

    /**
     * 配置1(推荐且为默认)   Tari 25uS; Miller 4 250KHz0xD1
     */
    byte Miller250KHz = (byte) 0xD1;
    /**
     * 配置2  Tari 25uS; Miller 4 300KHz;0xD2
     */
    byte Miller300KHz = (byte) 0xD2;
    /**
     * 配置3 Tari 6.25uS; FM0 400KHz;0xD3
     */
    byte FM0400KHz = (byte) 0xD3;

}
