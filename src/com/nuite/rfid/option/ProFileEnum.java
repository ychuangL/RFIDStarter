package com.nuite.rfid.option;

/**
 * @Author: yangchuang
 * @Date: 2018/11/6 16:15
 * @Version: 1.0
 */

public enum ProFileEnum {

    /**
     * 默认无
     */
    DEFAULT(0, (byte) 0xff),
    /**
     * 配置0  Tari 25uS; FM0 40KHz0xD0
     */
    FM040KHz(1, (byte) 0xD0),
    /**
     * 配置1(推荐且为默认)   Tari 25uS; Miller 4 250KHz0xD1
     */
    Miller250KHz(2, (byte) 0xD1),
    /**
     * 配置2  Tari 25uS; Miller 4 300KHz;0xD2
     */
    Miller300KHz(3, (byte) 0xD2),
    /**
     * 配置3 Tari 6.25uS; FM0 400KHz;0xD3
     */
    FM0400KHz(4, (byte) 0xD3);

    /**
     * 数据表中的配置值
     */
    private int intValue;
    private byte byteValue;

    ProFileEnum(int intValue, byte byteValue) {
        this.intValue = intValue;
        this.byteValue = byteValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public byte getByteValue() {
        return byteValue;
    }

    public static byte getByteValueByInt(int intValue) {
        ProFileEnum[] proFileEnums = ProFileEnum.values();

        for (ProFileEnum proFileEnum : proFileEnums) {
            if (proFileEnum.getIntValue() == intValue) {
                return proFileEnum.getByteValue();
            }
        }
        return -1;
    }

    /**
     * 判断元素是否存在
     *
     * @param intValue
     * @return
     */
    public static boolean isExist(int intValue) {
        ProFileEnum[] proFileEnums = ProFileEnum.values();

        for (ProFileEnum proFileEnum : proFileEnums) {
            if (proFileEnum.getIntValue() == intValue) {
                return true;
            }
        }
        return false;
    }
}
