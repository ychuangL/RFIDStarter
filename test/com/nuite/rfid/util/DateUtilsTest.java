package com.nuite.rfid.util;

import com.nuite.rfid.option.AntennaEnum;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * @Author: yangchuang
 * @Date: 2018/11/9 15:58
 * @Version: 1.0
 */

public class DateUtilsTest {

    @Test
    public void test(){

        System.out.println(LocalDateTime.now());
        System.out.println(LocalDateTime.now().toLocalDate());
        System.out.println(LocalDateTime.now().toLocalTime());
        System.out.println(LocalDateTime.now().getYear());
        System.out.println(LocalDateTime.now().getMonthValue());
        System.out.println(LocalDateTime.now().getDayOfMonth());
        System.out.println(LocalDateTime.now().getHour());
        System.out.println(LocalDateTime.now().getMinute());
        System.out.println(LocalDateTime.now().getSecond());
        System.out.println(LocalDateTime.now().getNano());
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDateTime.now().getDayOfYear());
    }

    @Test
    public void test1(){
        AntennaEnum[] antennaEnums=AntennaEnum.values();
        for (AntennaEnum antennaEnum : antennaEnums) {
            System.out.println(antennaEnum.getName() + " " + antennaEnum.ordinal());
        }

        System.out.println(AntennaEnum.valueOf("ANTENNA_1"));
    }
}