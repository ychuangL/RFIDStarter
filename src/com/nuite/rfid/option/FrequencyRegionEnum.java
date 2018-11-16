package com.nuite.rfid.option;

/**
 * 射频规范
 *
 * @Author: yangchuang
 * @Date: 2018/11/6 16:26
 * @Version: 1.0
 */

public enum FrequencyRegionEnum {

    DEFAULT((byte) 0x01),
    FCC((byte) 0x01),
    ETSI((byte) 0x02),
    CHN((byte) 0x03);

    private byte byteValue;

    FrequencyRegionEnum(byte byteValue) {
        this.byteValue = byteValue;
    }

    public byte getByteValue() {
        return byteValue;
    }
}
