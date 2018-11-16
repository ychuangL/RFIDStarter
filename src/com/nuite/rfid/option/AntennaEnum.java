package com.nuite.rfid.option;

/**
 * 天线选项
 *
 * @Author: yangchuang
 * @Date: 2018/11/6 15:55
 * @Version: 1.0
 */

public enum AntennaEnum {

    ANTENNA_1(1, (byte) 0x00, "天线一"),
    ANTENNA_2(2, (byte) 0x01, "天线二"),
    ANTENNA_3(3, (byte) 0x02, "天线三"),
    ANTENNA_4(4, (byte) 0x03, "天线四");

    int intValue;
    byte byteValue;
    String name;

    AntennaEnum(int intValue, byte byteValue, String name) {
        this.intValue = intValue;
        this.byteValue = byteValue;
        this.name = name;
    }

    public int getIntValue() {
        return intValue;
    }

    public byte getByteValue() {
        return byteValue;
    }

    public String getName() {
        return name;
    }

    public static String getNameByValue(byte btAntenna) {
        AntennaEnum[] antennaEnums = AntennaEnum.values();
        for (AntennaEnum antenna : antennaEnums) {
            if (antenna.getByteValue() == btAntenna) {
                return antenna.getName();
            }
        }
        return null;
    }

    public static byte getByteValueByInt(int intValue) {
        AntennaEnum[] antennaEnums = AntennaEnum.values();
        for (AntennaEnum antenna : antennaEnums) {
            if (antenna.getIntValue() == intValue) {
                return antenna.getByteValue();
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
        AntennaEnum[] antennaEnums = AntennaEnum.values();
        for (AntennaEnum antenna : antennaEnums) {
            if (antenna.getIntValue() == intValue) {
                return true;
            }
        }
        return false;
    }
}
