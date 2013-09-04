/**
 * Created by IntelliJ IDEA
 * User: Uraka.Lee
 * Date: 2013-08-21
 *
 * Copyleft 2000 MCTLab.cn
 */
package cn.mctlab.insight.common.datastructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class Pair<X, Y> {

    //-- public finals --//
    //-- private finals --//

    private static final Logger LOG = LoggerFactory.getLogger(Pair.class);

    //-- properties --//

    private X first = null;
    private Y second = null;

    //-- constructors --//

    public Pair(X first, Y second) {
        this.first = first;
        this.second = second;
    }

    //-- destructors --//
    //-- implements --//
    //-- un-implements --//
    //-- methods --//
    //-- functions --//
    //-- utils --//
    //-- getters & setters --//

    public X getFirst() {
        return first;
    }

    public void setFirst(X first) {
        this.first = first;
    }

    public Y getSecond() {
        return second;
    }

    public void setSecond(Y second) {
        this.second = second;
    }

    //-- inner classes --//
}
