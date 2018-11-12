package com.nuite.rfid.option;

/**
 * 射频规范
 *
 * @Author: yangchuang
 * @Date: 2018/11/6 16:26
 * @Version: 1.0
 */

public interface FrequencyRegionMode {

    byte DEFAULT = (byte) 0x01;
    byte FCC = (byte) 0x01;
    byte ETSI = (byte) 0x02;
    byte CHN = (byte) 0x03;
}
