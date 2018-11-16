package com.nuite.rfid.util;

/**
 * 验证器
 *
 * @Author: yangchuang
 * @Date: 2018/11/15 15:09
 * @Version: 1.0
 */

public class Validator {

    /**
     * 检测ip地址是否合法
     *
     * @param ip
     * @return
     */
    public static boolean checkIP(String ip) {
        String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        return ip.matches(regex);
    }

    /**
     * 检测端口是否合法
     *
     * @param port 合法范围： 1~65535
     * @return
     */
    public static boolean checkPort(String port) {
        return port.matches("(^[1-9][0-9]{0,3})|(^[1-6][0-5][0-5][0-3][0-5]$)");
    }

}
