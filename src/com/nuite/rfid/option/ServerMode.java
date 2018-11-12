package com.nuite.rfid.option;

/**
 * @Author: yangchuang
 * @Date: 2018/11/6 15:33
 * @Version: 1.0
 */

public enum ServerMode {
    NONE(0),//无
    NetMode(1),//网络通信
    ComMode(2);//com通信

    private int value;

    ServerMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    }
