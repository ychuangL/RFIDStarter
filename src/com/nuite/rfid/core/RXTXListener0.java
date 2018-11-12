package com.nuite.rfid.core;

import com.module.interaction.RXTXListener;
import com.util.StringTool;

/**
 * 读写器监听器
 *
 * @Author: yangchuang
 * @Date: 2018/10/26 11:06
 * @Version: 1.0
 */

public class RXTXListener0 implements RXTXListener {

    @Override
    public void reciveData(byte[] btAryReceiveData) {
        System.out.println("接收数据 ... " + StringTool.byteArrayToString(btAryReceiveData, 0, btAryReceiveData.length));
    }

    @Override
    public void sendData(byte[] btArySendData) {
        System.out.println("发送数据 ..." + StringTool.byteArrayToString(btArySendData, 0, btArySendData.length));
    }

    @Override
    public void onLostConnect() {
        System.out.println("失去连接 ... ");
    }
}
