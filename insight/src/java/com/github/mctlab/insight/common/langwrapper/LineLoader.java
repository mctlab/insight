/**
 * Created by IntelliJ IDEA
 * User: Uraka.Lee
 * Date: 2013-07-14
 *
 * Copyleft 2000 MCTLab.cn
 */
package com.github.mctlab.insight.common.langwrapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用{@link LineReader}读取文件
 * 返回{@link List} of {@link String}
 */
public class LineLoader {

    //-- public finals --//
    //-- private finals --//

    private static final Logger LOG = LoggerFactory.getLogger(LineLoader.class);

    //-- properties --//
    //-- constructors --//
    //-- destructors --//
    //-- implements --//
    //-- un-implements --//
    //-- methods --//
    //-- functions --//

    private static List<String> loadLines(LineReader lr, boolean trim) throws IOException {
        List<String> result = new ArrayList<String>();
        String buffer = null;
        while ((buffer = lr.getLine()) != null) {
            result.add(trim ? buffer.trim() : buffer);
        }
        return result;
    }

    //-- utils --//

    public static List<String> loadFile(String fileName, boolean trim) throws FileNotFoundException {
        LineReader lr = new LineReader(fileName);
        try {
            return loadLines(lr, trim);
        } catch (IOException e) {
            return null;
        } finally {
            lr.close();
        }
    }

    public static List<String> loadResource(InputStream is, boolean trim) throws UnsupportedEncodingException {
        LineReader lr = new LineReader(is);
        try {
            return loadLines(lr, trim);
        } catch (IOException e) {
            return null;
        } finally {
            lr.close();
        }
    }

    //-- getters & setters --//
    //-- inner classes --//
}
