/**
 * Created by IntelliJ IDEA
 * User: Uraka.Lee
 * Date: 2013-07-14
 *
 * Copyleft 2000 MCTLab.cn
 */
package com.github.mctlab.insight.common.langwrapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class TestLineReader {

    //-- public finals --//
    //-- private finals --//
    //-- properties --//
    //-- constructors --//
    //-- destructors --//
    //-- implements --//
    //-- un-implements --//
    //-- tests --//

    @Test
    public void test() throws Exception {
        InputStream is = TestLineReader.class.getResourceAsStream("/data/LineReader.testdata");
        List<String> lineList = new ArrayList<String>();

        LineReader lr = new LineReader(is);
        String buffer = null;
        while ((buffer = lr.getLine()) != null) {
            lineList.add(buffer);
        }
        lr.close();

        Assert.assertEquals("line0", lineList.get(0));
        Assert.assertEquals("第一行", lineList.get(1));
        Assert.assertEquals("", lineList.get(2));
        Assert.assertEquals(" line3", lineList.get(3));
    }

    //-- functions --//
    //-- utils --//
    //-- getters & setters --//
    //-- inner classes --//
}
