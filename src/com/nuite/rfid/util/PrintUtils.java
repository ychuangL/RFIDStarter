package com.nuite.rfid.util;

import com.rfid.rxobserver.ReaderSetting;
import com.rfid.rxobserver.bean.RXInventoryTag;

/**
 * @Author: yangchuang
 * @Date: 2018/10/25 8:43
 * @Version: 1.0
 */

public class PrintUtils {

    public static void printSetting(ReaderSetting setting) {

        System.out.println("**********s***********");
        System.out.println("ReadId: " + setting.btReadId);
        System.out.println("BeeperMode: " + setting.btBeeperMode);
        System.out.println("FrequencyStart: " + setting.btFrequencyStart);
        System.out.println("FrequencyEnd: " + setting.btFrequencyEnd);
        System.out.println("RfLinkProfile: " + setting.btRfLinkProfile);
        System.out.println("Temperature: " + setting.btTemperature);
        System.out.println("Version:  " + setting.btMajor + "." + setting.btMinor);
        System.out.println("AntDetector:  " + setting.btAntDetector);
        System.out.println("WorkAntenna:  天线" + (setting.btWorkAntenna + 1));
        System.out.println("**********e***********");
    }

    public static void printRXInventoryTagEnd(RXInventoryTag.RXInventoryTagEnd endTag) {
        StringBuilder sb = new StringBuilder();
        sb.append("inventory end: \n\t")
                .append("  mTotalRead: ").append(endTag.mTotalRead)
                .append("  mReadRate: ").append(endTag.mReadRate)
                .append("  mTagCount: ").append(endTag.mTagCount)
                .append("  cmd: ").append(endTag.cmd)
                .append("  mCurrentAnt: ").append(endTag.mCurrentAnt);
        System.out.println(sb.toString());
    }

    public static void printRXInventoryTag(RXInventoryTag tag) {
        StringBuilder sb = new StringBuilder();
        sb.append("EPC data: ")
                .append(" EPC: ").append(tag.strEPC)
                .append("  PC: ").append(tag.strPC)
                .append("  ReadCount: ").append(tag.mReadCount)
                .append("  RSSI: ").append(tag.strRSSI)
                .append("  Freq: ").append(tag.strFreq)
                .append("  CRC: ").append(tag.strCRC)
                .append("  AntId: ").append(tag.btAntId)
                .append("  Cmd: ").append(tag.cmd);
        System.out.println(sb.toString());
    }

    public static void printBtToStr(byte bt){
        System.out.println(String.format("%02X", bt));
    }
}
