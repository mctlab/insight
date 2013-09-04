/** RandomUtils */
package cn.mctlab.insight.common.util;

import java.util.Random;

import cn.mctlab.insight.common.util.string.CharUtils;

/**
 * 随机数工具
 * <li>生成随机数, 随机字符串, 随机名称</li>
 */
public class RandomUtils {

    //-- public finals --//
    //-- private finals --//

    private static final char[] DIGIT;
    private static final int DIGIT_LENGTH;

    static {
        StringBuilder sbDIGITS = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sbDIGITS.append(Integer.toString(i));
        }
        DIGIT = sbDIGITS.toString().toCharArray();
        DIGIT_LENGTH = DIGIT.length;
    }

    private static final char[] ALPHA;
    private static final int ALPHA_LENGTH;

    static {
        StringBuilder sbALPHAS = new StringBuilder();
        for (char c = 'a'; c <= 'z'; c++) {
            sbALPHAS.append(c);
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            sbALPHAS.append(c);
        }
        ALPHA = sbALPHAS.toString().toCharArray();
        ALPHA_LENGTH = ALPHA.length;
    }

    /**
     * @see CharUtils#isAsciiReadable(char)
     */
    private static final char[] READABLE;
    private static final int READABLE_LENGTH;

    static {
        StringBuilder sbASCII = new StringBuilder();
        for (char c = 0; c <= 0x7F; c++) {
            if (CharUtils.isAsciiReadable(c)) {
                sbASCII.append(c);
            }
        }
        READABLE = sbASCII.toString().toCharArray();
        READABLE_LENGTH = READABLE.length;
    }

    private static final char[] ASCII;
    private static final int ASCII_LENGTH;

    static {
        StringBuilder sbASCII = new StringBuilder();
        for (char c = 0; c <= 0x7F; c++) {
            sbASCII.append(c);
        }
        ASCII = sbASCII.toString().toCharArray();
        ASCII_LENGTH = ASCII.length;
    }

    private static final char[] ALL_ASCII;
    private static final int ALL_ASCII_LENGTH;

    static {
        StringBuilder sbALL_ASCII = new StringBuilder();
        for (char c = 0; c <= 0xFF; c++) {
            sbALL_ASCII.append(c);
        }
        ALL_ASCII = sbALL_ASCII.toString().toCharArray();
        ALL_ASCII_LENGTH = ALL_ASCII.length;
    }

    //-- properties --//

    private static Random random = new Random();

    //-- constructors --//

    private static RandomUtils singleton = new RandomUtils();

    /**
     * 获得singleton
     */
    public static RandomUtils getInstance() {
        return singleton;
    }

    //-- destructors --//
    //-- implements --//
    //-- un-implements --//
    //-- methods --//
    //-- functions --//

    /**
     * 生成长度大于等于min, 小于max的字符串, 在chars中选择字符, size为chars中字符的数量
     * <li>min和max必须是大于0的整数</li>
     * <li>min必须小于max</li>
     */
    private static String generateString(int min, int max, char[] chars, int size) {
        if (min <= 0 || max <= 0) {
            throw new IllegalArgumentException("min & max must be positive");
        }
        if (min >= max) {
            throw new IllegalArgumentException("min must be less than max");
        }
        int length = random.nextInt(max - min) + min;

        return generateString(length, chars, size);
    }

    /**
     * 生成长度为length的字符串, 在chars中选择字符, size为chars中字符的数量
     */
    private static String generateString(int length, char[] chars, int size) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars[random.nextInt(size)]);
        }

        return sb.toString();
    }

    //-- utils --//

    /**
     * <li>max必须是大于0的整数</li>
     *
     * @see java.util.Random#nextInt(int)
     */
    public static int generateInt(int max) {
        return random.nextInt(max);
    }

    /**
     * 生成大于等于min, 小于max的整数
     * <li>min和max必须是大于0的整数</li>
     */
    public static int generateInt(int min, int max) {
        if (min <= 0 || max <= 0) {
            throw new IllegalArgumentException("min & max must be positive");
        }
        if (min >= max) {
            throw new IllegalArgumentException("min must be less than max");
        } else if (min + 1 == max) {
            return min;
        } else {
            return min + random.nextInt(max - min);
        }
    }

    /**
     * 生成长度等于length的字符串
     *
     * @see #generateString(int, int)
     */
    public static String generateString(int length) {
        return generateString(length, length + 1);
    }

    /**
     * 生成长度大于等于min, 小于max的字符串
     * <li>字符集为ALL_ASCII</li>
     *
     * @see #generateString(int, int, char[], int)
     */
    public static String generateString(int min, int max) {
        return generateString(min, max, ALL_ASCII, ALL_ASCII_LENGTH);
    }

    /**
     * 生成长度等于length的ASCII字符串
     *
     * @see #generateAscii(int, int)
     */
    public static String generateAscii(int length) {
        return generateAscii(length, length + 1);
    }

    /**
     * 生成长度大于等于min, 小于max的ASCII字符串
     * <li>字符集为ASCII</li>
     *
     * @see #generateString(int, int, char[], int)
     */
    public static String generateAscii(int min, int max) {
        return generateString(min, max, ASCII, ASCII_LENGTH);
    }

    /**
     * 生成长度等于length的可读字符串
     *
     * @see #generateReadable(int, int)
     */
    public static String generateReadable(int length) {
        return generateReadable(length, length + 1);
    }

    /**
     * 生成长度大于等于min, 小于max的可读字符串
     * <li>字符集为READABLE</li>
     *
     * @see CharUtils#isAsciiReadable(char)
     * @see #generateString(int, int, char[], int)
     */
    public static String generateReadable(int min, int max) {
        return generateString(min, max, READABLE, READABLE_LENGTH);
    }

    /**
     * 生成长度等于length的名字
     *
     * @see #generateName(int, int)
     */
    public static String generateName(int length) {
        return generateName(length, length + 1);
    }

    /**
     * 生成长度大于等于min, 小于max的名字
     * <li>字符集为ALPHA</li>
     *
     * @see #generateString(int, int, char[], int)
     */
    public static String generateName(int min, int max) {
        return generateString(min, max, ALPHA, ALPHA_LENGTH);
    }

    /**
     * 生成长度等于length的数字
     *
     * @see #generateNumber(int, int)
     */
    public static String generateNumber(int length) {
        return generateNumber(length, length + 1);
    }

    /**
     * 生成长度大于等于min, 小于max的数字
     * <li>字符集为DIGIT</li>
     *
     * @see #generateString(int, int, char[], int)
     */
    public static String generateNumber(int min, int max) {
        return generateString(min, max, DIGIT, DIGIT_LENGTH);
    }

    /**
     * 生成长度等于length的CodeName
     *
     * @see #generateCodeName(int, int)
     */
    public static String generateCodeName(int length) {
        return generateCodeName(length, length + 1);
    }

    /**
     * 生成长度大于等于min, 小于max的CodeName
     * <li>CodeName由字母和数字组成, 先字母后数字, 字符集为ALPHA和DIGIT</li>
     * <li>min必须是大于1的整数</li>
     * <li>max必须是大于0的整数</li>
     * <li>min必须小于max</li>
     */
    public static String generateCodeName(int min, int max) {
        if (min <= 1) {
            throw new IllegalArgumentException("min must be greater than 1");
        }
        if (max <= 0) {
            throw new IllegalArgumentException("max must be positive");
        }
        if (min >= max) {
            throw new IllegalArgumentException("min must be less than max");
        }
        int length = random.nextInt(max - min) + min;

        // 至少有一个字母, 至多有length-1个字母
        int alphaLength = random.nextInt(length - 1) + 1;
        int digitLength = length - alphaLength;
        return generateName(alphaLength) + generateNumber(digitLength);
    }

    //-- getters & setters --//
    //-- iWritables --//
    //-- inner classes --//
}
