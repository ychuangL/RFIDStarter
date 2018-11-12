package com.nuite.rfid.option;

/**
 * 天线选项
 *
 * @Author: yangchuang
 * @Date: 2018/11/6 15:55
 * @Version: 1.0
 */

public enum AntennaEnum {

    ANTENNA_1((byte) 0x00, "天线一"),
    ANTENNA_2((byte) 0x01, "天线二"),
    ANTENNA_3((byte) 0x02, "天线三"),
    ANTENNA_4((byte) 0x03, "天线四");

    byte value;
    String name;

    AntennaEnum(byte value, String name) {
        this.value = value;
        this.name = name;
    }

    public byte getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static String getNameByValue(byte btAntenna) {
        AntennaEnum[] antennaEnums = AntennaEnum.values();
        for (AntennaEnum antenna : antennaEnums) {
            if (antenna.getValue() == btAntenna) {
                return antenna.getName();
            }
        }
        return null;
    }
}
