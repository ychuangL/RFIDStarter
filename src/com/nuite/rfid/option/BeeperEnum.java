package com.nuite.rfid.option;

/**
 * @Author: yangchuang
 * @Date: 2018/11/6 16:01
 * @Version: 1.0
 */

public enum BeeperEnum {
    /**
     * 默认静音
     */
    DEFAULT(0, (byte) 0xff),
    /**
     * 静音
     */
    MUTE(1, (byte) 0x00),
    /**
     * 每次盘存后鸣响
     */
    ONE_INVENTORY(2, (byte) 0x01),
    /**
     * 每读到一张标签鸣响
     */
    READ_A_TAG(3, (byte) 0x02);

    private int intValue;
    private byte byteValue;

    BeeperEnum(int intValue, byte byteValue) {
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
        BeeperEnum[] beeperEnums = BeeperEnum.values();

        for (BeeperEnum beeperEnum : beeperEnums) {
            if (beeperEnum.getIntValue() == intValue) {
                return beeperEnum.getByteValue();
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
        BeeperEnum[] beeperEnums = BeeperEnum.values();

        for (BeeperEnum beeperEnum : beeperEnums) {
            if (beeperEnum.getIntValue() == intValue) {
                return true;
            }
        }
        return false;
    }
}
