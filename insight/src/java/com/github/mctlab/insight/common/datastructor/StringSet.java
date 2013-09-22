/** StringSet */
package com.github.mctlab.insight.common.datastructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * HashSet(String)的wrapper
 * <li>主要功能是getSortedList</li>
 */
public class StringSet extends HashSet<String> {

    //-- public finals --//
    //-- private finals --//

    private static final long serialVersionUID = 1L;

    //-- properties --//
    //-- constructors --//
    //-- destructors --//
    //-- implements --//
    //-- un-implements --//
    //-- methods --//

    /**
     * 获得按字母序排序的元素列表
     */
    public List<String> getSortedList() {
        List<String> l = new ArrayList<String>(this);
        Collections.sort(l);
        return l;
    }

    //-- functions --//
    //-- utils --//
    //-- getters & setters --//
    //-- iWritables --//
    //-- inner classes --//
}
