/** CountMapWithXSet */
package com.github.mctlab.insight.common.datastructor;

import java.util.HashSet;
import java.util.Set;

/**
 * 带有排重功能的{@link CountMap}
 * <li>请使用addToMap(final E key, String s)</li>
 * <li>addToMap(final K key)方法仅用于构造初始的CountMap</li>
 * <li>addToMap(final K key, int count)方法仅用于构造初始的CountMap</li>
 * <li>put方法仅用于构造初始的CountMap</li>
 */
public class CountMapWithXSet<E> extends CountMap<E> {

    //-- public finals --//
    //-- private finals --//
    //-- properties --//

    private Set<String> set = new HashSet<String>();

    //-- constructors --//
    //-- destructors --//
    //-- implements --//
    //-- un-implements --//
    //-- methods --//

    /**
     * addToMap时使用一个组合的字符串(例如s=key+user)排重, 使得同一个user不能为一个key累加多次
     * <li>成功add时返回当前Count</li>
     * <li>重复add时返回-1</li>
     */
    public int addToMap(final E key, String s) {
        if (set.contains(s)) {
            return -1;
        } else {
            set.add(s);
        }
        if (containsKey(key)) {
            put(key, get(key) + 1);
        } else {
            put(key, 1);
        }
        return get(key);
    }

    //-- functions --//
    //-- utils --//
    //-- getters & setters --//
    //-- iWritables --//
    //-- inner classes --//
}
