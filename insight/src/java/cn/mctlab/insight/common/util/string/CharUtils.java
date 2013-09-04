/** CharUtils */
package cn.mctlab.insight.common.util.string;

/**
 * char工具
 */
public class CharUtils extends org.apache.commons.lang.CharUtils {

    //-- public finals --//
    //-- private finals --//
    //-- properties --//
    //-- constructors --//

    private static CharUtils singleton = new CharUtils();

    /**
     * 获得singleton
     */
    public static CharUtils getInstance() {
        return singleton;
    }

    //-- destructors --//
    //-- implements --//
    //-- un-implements --//
    //-- methods --//
    //-- functions --//
    //-- utils --//

    /**
     * 是空格
     */
    public static boolean isSpace(char ch) {
        return ch == 0x20;
    }

    /**
     * 是空白(中文/全角空格, 控制字符和空格)
     */
    public static boolean isBlank(char ch) {
        return ch == '　' || Character.isISOControl(ch) || ch == 0x20 || ch == 0xA0;
    }

    /**
     * 是可读的控制字符(json-escape-able)
     *
     * @see org.json.simple.JSONValue#escape(String)
     */
    public static boolean isReadableControl(char ch) {
        return ch == '\b' || ch == '\f' || ch == '\n' || ch == '\r' || ch == '\t';
    }

    /**
     * 是扩展ASC码
     */
    public static boolean isAsciiExtend(char ch) {
        return ch <= 0xFF;
    }

    /**
     * 是可读的ASC码
     */
    public static boolean isAsciiReadable(char ch) {
        return isAsciiPrintable(ch) || isReadableControl(ch);
    }

    /**
     * 是可读的扩展ASC码
     */
    public static boolean isAsciiReadableExtend(char ch) {
        return isAsciiPrintableExtend(ch) || isReadableControl(ch);
    }

    /**
     * 是可打印的扩展ASC码(不是控制字符)
     */
    public static boolean isAsciiPrintableExtend(char ch) {
        return ch <= 0xFF && !Character.isISOControl(ch);
    }

    /**
     * 是可打印的扩展ASC码中的字母或数字
     */
    public static boolean isAsciiAlphanumericExtend(char ch) {
        return isAsciiAlphanumeric(ch) || (ch >= 0xC0 && ch <= 0xFF);
    }

    //-- getters & setters --//
    //-- iWritables --//
    //-- inner classes --//
}
