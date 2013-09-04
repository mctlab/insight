/** CountMap */
package cn.mctlab.insight.common.datastructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * HashMap(K, Integer)的wrapper, 用于计数
 *
 * @param <K> 要被计数的数据类型, 通常为String
 */
public class CountMap<K> {

    //-- public finals --//
    //-- private finals --//
    //-- properties --//

    private Map<K, Integer> map = new HashMap<K, Integer>();

    //-- constructors --//
    //-- destructors --//
    //-- implements --//

    /**
     * 相当于map的get, key不存在时返回0
     */
    public final int get(final K key) {
        if (containsKey(key)) {
            return map.get(key);
        } else {
            return 0;
        }
    }

    /**
     * 相当于map的put
     */
    public final void put(final K key, int i) {
        map.put(key, i);
    }

    /**
     * 相当于map的containsKey
     */
    public final boolean containsKey(final K key) {
        return map.containsKey(key);
    }

    //-- un-implements --//
    //-- methods --//

    /**
     * @param key 哪个key增加(增加1)
     * @return key的当前Count
     */
    public int addToMap(final K key) {
        if (containsKey(key)) {
            put(key, get(key) + 1);
        } else {
            put(key, 1);
        }
        return get(key);
    }

    /**
     * @param key   哪个key增加
     * @param count 增加多少
     * @return key的当前Count
     */
    public int addToMap(final K key, int count) {
        if (containsKey(key)) {
            put(key, get(key) + count);
        } else {
            put(key, count);
        }
        return get(key);
    }

    public void trimValueUnderX(int value) {
        while (true) {
            List<K> list = new ArrayList<K>();
            Iterator<K> iter = map.keySet().iterator();
            for (int i = 0; i < Math.min(10000, map.size()); i++) {
                list.add(iter.next());
            }
            boolean modify = false;
            iter = list.iterator();
            while (iter.hasNext()) {
                K key = iter.next();
                if (get(key) < value) {
                    map.remove(key);
                    modify = true;
                }
            }
            list.clear();
            if (!modify) {
                break;
            }
        }
    }

    /**
     * <li>Entry按Count排序</li>
     * <li>当x大于Entry总数时, 返回全部Entry</li>
     *
     * @param x 希望得到的Entry数
     * @return Count最高的x个Entry
     */
    public List<Entry<K, Integer>> getTopX(int x) {
        List<Entry<K, Integer>> l = getSortedList();
        return l.subList(0, Math.min(x, l.size()));
    }

    /**
     * @return Entry列表(无序)
     */
    public List<Entry<K, Integer>> getList() {
        return new ArrayList<Entry<K, Integer>>(map.entrySet());
    }

    /**
     * @return 按Count排序的Entry列表(从大到小)
     */
    public List<Entry<K, Integer>> getSortedList() {
        List<Entry<K, Integer>> l = getList();
        Collections.sort(l, comparator);
        return l;
    }

    /**
     * @return 按Key字母序排序的Entry列表(从小到大)
     */
    public List<Entry<K, Integer>> getLetterSortedList() {
        List<Entry<K, Integer>> l = getList();
        Collections.sort(l, letterComparator);
        return l;
    }

    //-- functions --//

    private final Comparator<Entry<K, Integer>> comparator =
            new Comparator<Entry<K, Integer>>() {
                public int compare(final Entry<K, Integer> o1, final Entry<K, Integer> o2) {
                    int v1 = o1.getValue();
                    int v2 = o2.getValue();
                    return (v1 < v2) ? 1 : ((v1 == v2) ? 0 : -1);
                }
            };

    private final Comparator<Entry<K, Integer>> letterComparator =
            new Comparator<Entry<K, Integer>>() {
                public int compare(final Entry<K, Integer> o1, final Entry<K, Integer> o2) {
                    String v1 = o1.getKey().toString();
                    String v2 = o2.getKey().toString();
                    return v1.compareTo(v2);
                }
            };

    //-- utils --//
    //-- getters & setters --//
    //-- iWritables --//
    //-- inner classes --//
}
