package com.nuite.rfid.core;

import com.nuite.rfid.util.DateUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 标签池，存放读取到的标签
 *
 * @Author: yangchuang
 * @Date: 2018/11/8 8:33
 * @Version: 1.0
 */

public class TagPool {
    private static TagPool tagPool = new TagPool();
    /**
     * 标签池，单例
     */
    private Map<String, Instant> pool = Collections.synchronizedMap(new LinkedHashMap(100));
    /**
     * 时间间隔,秒
     */
    private long interval = 3;

    private TagPool() {
    }

    public static TagPool getInstance() {
        return tagPool;
    }

    /**
     * 将标签存入标签池
     *
     * @param tagID
     */
    public void saveTag(String tagID) {
        Instant now = Instant.now();
        String datetime = DateUtils.format(now);
        if (pool.containsKey(tagID)) {
            System.out.println("更新： " + tagID + "  " + datetime);
            pool.replace(tagID, now);
        } else {
            System.out.println("新增： " + tagID + "  " + datetime);
            pool.put(tagID, now);
        }
    }

    /**
     * 清空标签池
     */
    public void clearPool() {
        pool.clear();
    }

    /**
     * 判断是否非空
     *
     * @return
     */
    public boolean isNotEmpty() {
        return !pool.isEmpty();
    }

    /**
     * 移除所有过期的
     */
    public void removeExpired() {
        ArrayList<String> tagList = new ArrayList<>(49);
        for (Map.Entry<String, Instant> entry : pool.entrySet()) {
            //大于设定的间隔时间,秒，则删除
            if ((Instant.now().getEpochSecond() - entry.getValue().getEpochSecond()) > interval) {
                tagList.add(entry.getKey());
            }
        }

        for (String tag : tagList) {
            pool.remove(tag);
        }
    }

    /**
     * 获取标签池中标签数量
     *
     * @return
     */
    public int size() {
        return pool.size();
    }

    /**
     * 打印标签池key-value
     */
    public void printPool() {
        System.out.println("已盘存的标签总数量:" + size());
        for (Map.Entry<String, Instant> entry : this.pool.entrySet()) {
            System.out.println(entry.getKey() + "  " + DateUtils.format(entry.getValue()));
        }
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }
}
