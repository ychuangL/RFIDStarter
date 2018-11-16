package com.nuite.rfid;

import com.nuite.rfid.core.ReaderCore;
import com.nuite.rfid.domain.ReaderConfigInfo;

/**
 * 读写器启动器
 *
 * @Author: yangchuang
 * @Date: 2018/10/26 11:06
 * @Version: 1.0
 */

public class ReaderStarter {

    /**
     * 读写器内核
     */
    private ReaderCore core = new ReaderCore();

    public ReaderStarter() {
    }

    /**
     * 启动读写器，使用默认配置
     *
     * @param ip
     * @param port
     * @param antennas [1,2,3,4]
     * @throws Exception
     */
    public void start(String ip, int port, int[] antennas) throws Exception {
        core.initAndDefaultSetting(ip, port);
        /*执行盘存*/
        core.executeRealTimeInventory(antennas);
    }

    /**
     * 启动读写器，使用自定义配置
     *
     * @param configInfo 自定义配置
     * @throws Exception
     */
    public void start(ReaderConfigInfo configInfo) throws Exception {

        /*校验配置参数*/
        core.checkSettingParam(configInfo);
        /*初始化连接，获取reader对象*/
        core.init(configInfo.getIP(), Integer.parseInt(configInfo.getPort()));
        /*执行自定义配置*/
        core.doSetting(configInfo);
        /*执行盘存*/
        core.executeRealTimeInventory(configInfo.getUseAntennaPort());

    }

    /**
     * 获取启动器核心
     *
     * @return
     */
    public ReaderCore getCore() {
        return core;
    }

    /**
     * 关闭读写器
     */
    public void close() {
        core.destroy();
    }

}
