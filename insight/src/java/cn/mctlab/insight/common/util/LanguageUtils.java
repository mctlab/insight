/** LanguageUtils */
package cn.mctlab.insight.common.util;

import java.util.HashSet;
import java.util.Set;

/**
 * 语言工具
 */
public class LanguageUtils {

    //-- public finals --//

    public static final int LANG_UNKNOWN = 0;
    public static final int LANG_EN = 1;
    public static final int LANG_CN = 2;
    public static final int LANG_JP = 3;

    static final int[][] UTF_DELIMITER = {
            {'\'', '‘', '’'}, {'"', '“', '”'}, {',', '，'}, {'.', '。'},
            {';', '；'}, {':', '：'}, {'、'}, {'·'},
            {'/', '／'}, {'?', '？'}, {'\\', '＼'}, {'|', '｜'}, // 标点
            {'`'}, // before 1
            {'~', '～'}, {'!', '！'}, {'@', '＠'}, {'#', '＃'}, {'$', '＄'}, {'¥', '￥'}, // shift before 1 ~ 4
            {'%', '％'}, {'^', '＾', '…'}, {'&', '＆'}, // shift 5 ~ 7
            {'_'}, // shift after 0
            {'(', ')'}, {'（', '）'}, {'[', ']'}, {'［', '］'}, {'{', '}'}, {'<', '>'}, // 括号
            {'+', '＋'}, {'-', '－'}, {'*', '＊', '×'}, {'÷'}, {'=', '＝'} // 运算符
    };
    static final int[][] UTF_DELIMITER_EXT = {
            {0x275e, 0x300d, 0x300f, 0x301e, 0xff63}, // ❞」』〞｣
            {0x3011, 0x3015, 0x3017, 0x3019, 0x301b}, // 】〕〗〙〛
            {0x203a, 0x3009, 0x300b} // ›〉》
    };
    private static Set<Integer> delimiters = new HashSet<Integer>();

    static {
        for (int[] e : UTF_DELIMITER) {
            for (int f : e) {
                delimiters.add(f);
            }
        }
        for (int[] e : UTF_DELIMITER_EXT) {
            for (int f : e) {
                delimiters.add(f - 1);
                delimiters.add(f);
            }
        }
    }

    /**
     * 是标点或分隔符
     */
    public static boolean isDelimiter(char c) {
        return delimiters.contains(Integer.valueOf(c));
    }

    static final int[][] ASCII_DELIMITER = {
            {0x0021, 0x002F}, {0x003A, 0x0040}, {0x005B, 0x0060}, {0x007B, 0x007E}
    };

    /**
     * 是ASCII标点或分隔符
     */
    public static boolean isAsciiDelimiter(char c) {
        for (int[] e : ASCII_DELIMITER) {
            if (c >= e[0] && c <= e[1]) {
                return true;
            }
        }
        return false;
    }

    static final int[] UTF_ASCII_DIGIT_RANGE = {0x0030, 0x0039}; // 0~9
    static final int[] UTF_FULL_WIDTH_DIGIT_RANGE = {0xff10, 0xff19}; // 0~9, UFF00.pdf

    /**
     * 是阿拉伯数字
     */
    public static boolean isArabicDigits(char c) {
        return (c >= UTF_ASCII_DIGIT_RANGE[0] && c <= UTF_ASCII_DIGIT_RANGE[1]) ||
                (c >= UTF_FULL_WIDTH_DIGIT_RANGE[0] && c <= UTF_FULL_WIDTH_DIGIT_RANGE[1]);
    }

    static final int[] UTF_ALPHABET_UPPER_RANGE = {0x0041, 0x005a};   // A~Z
    static final int[] UTF_ALPHABET_LOWER_RANGE = {0x0061, 0x007a};   // a~z
    static final int[] UTF_FULL_WIDTH_UPPER_RANGE = {0xff21, 0xff3a}; // A~Z, UFF00.pdf
    static final int[] UTF_FULL_WIDTH_LOWER_RANGE = {0xff41, 0xff5a}; // a~z, UFF00.pdf

    /**
     * 是英文字母
     */
    public static boolean isEnglishAlphabet(char c) {
        return (c >= UTF_ALPHABET_UPPER_RANGE[0] && c <= UTF_ALPHABET_UPPER_RANGE[1]) ||
                (c >= UTF_ALPHABET_LOWER_RANGE[0] && c <= UTF_ALPHABET_LOWER_RANGE[1]) ||
                (c >= UTF_FULL_WIDTH_UPPER_RANGE[0] && c <= UTF_FULL_WIDTH_UPPER_RANGE[1]) ||
                (c >= UTF_FULL_WIDTH_LOWER_RANGE[0] && c <= UTF_FULL_WIDTH_LOWER_RANGE[1]);
    }

    static final int[] UTF_CHN_CHAR_RANGE = {0x4e00, 0x9fff}; // U4E00.pdf
    static final int[] UTF_CHN_EXT_CHAR_RANGE = {0x3400, 0x4dbf}; // U3400.pdf
    static final int[] UTF_CHN_CPT_CHAR_RANGE = {0xf900, 0xfaff}; // UF900.pdf

    /**
     * 是汉字
     */
    public static boolean isChineseChar(char c) {
        return (c >= UTF_CHN_CHAR_RANGE[0] && c <= UTF_CHN_CHAR_RANGE[1]) ||
                (c >= UTF_CHN_EXT_CHAR_RANGE[0] && c <= UTF_CHN_EXT_CHAR_RANGE[1]) ||
                (c >= UTF_CHN_CPT_CHAR_RANGE[0] && c <= UTF_CHN_CPT_CHAR_RANGE[1]);
    }

    /**
     * http://hi.baidu.com/7636553/item/790a38515d9331d9d48bac31
     */
    static final int[] UTF_JPN_ALPHABET_RANGE = {0x3040, 0x30ff};

    /**
     * 是假名
     */
    public static boolean isJapaneseAlphabet(char c) {
        return (c >= UTF_JPN_ALPHABET_RANGE[0] && c <= UTF_JPN_ALPHABET_RANGE[1]);
    }

    //-- private finals --//
    //-- properties --//
    //-- constructors --//

    private static LanguageUtils singleton = new LanguageUtils();

    /**
     * 获得singleton
     */
    public static LanguageUtils getInstance() {
        return singleton;
    }

    //-- destructors --//
    //-- implements --//
    //-- un-implements --//
    //-- methods --//
    //-- functions --//
    //-- utils --//
    //-- getters & setters --//
    //-- iWritables --//
    //-- inner classes --//
}
