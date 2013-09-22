/** CostHelper */
package com.github.mctlab.insight.common.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.mctlab.insight.common.util.TimeUtils;

/**
 * 输出cost-log的静态类
 */
public class CostHelper {

    //-- public finals --//

    public final static String PREFIX = "@@COST@@";

    //-- private finals --//

    private static final Logger LOG = LoggerFactory.getLogger(CostHelper.class);

    //-- properties --//
    //-- constructors --//

    private CostHelper() {}

    //-- destructors --//
    //-- implements --//
    //-- un-implements --//
    //-- methods --//
    //-- functions --//
    //-- utils --//

    /**
     * 输出log, 记录key=value
     */
    public static String cost(String key, long value) {
        String log = String.format("%s %s=%d", PREFIX, key, value);
        LOG.info(log);
        return log;
    }

    /**
     * 输出log, 记录key=now-start
     * <li>主要用于计时(或仅用于计时)</li>
     */
    public static String costNow(String key, long start) {
        return cost(key, TimeUtils.getTimeCost(start));
    }

    //-- getters & setters --//
    //-- iWritables --//
    //-- inner classes --//
}
