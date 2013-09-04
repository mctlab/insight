/** LineReader */
package cn.mctlab.insight.common.langwrapper;

import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用于按行读取文本文件
 * <li>使用while循环, 调用{@link #getLine()}</li>
 */
public class LineReader {

    //-- public finals --//
    //-- private finals --//

    private static final Logger LOG = LoggerFactory.getLogger(LineReader.class);

    //-- properties --//

    private FileReader fr = null;
    private BufferedReader br = null;

    //-- constructors --//

    /**
     * 用文件名构造LineReader
     */
    public LineReader(String fileName) throws FileNotFoundException {
        fr = new FileReader(fileName);
        br = new BufferedReader(fr);
    }

    /**
     * 用File对象构造LineReader
     */
    public LineReader(File file) throws FileNotFoundException {
        fr = new FileReader(file);
        br = new BufferedReader(fr);
    }

    /**
     * 用InputStream对象构造LineReader
     */
    public LineReader(InputStream is) throws UnsupportedEncodingException {
        br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
    }

    //-- destructors --//

    /**
     * 关闭LineReader
     */
    public void close() {
        try {
            // BufferedReader会去调用FileReader.close
            // 且BufferedReader.close本身不会抛异常
            // 且只要LineReader对象有效(创建成功), fr和br就一定也有效
            // 所以这里只要调用BufferedReader.close就可以了
            br.close();
        } catch (IOException e) {
            LOG.warn("close failed", e);
        }
    }

    //-- implements --//
    //-- un-implements --//
    //-- methods --//

    /**
     * 读取文本文件的一行
     */
    public String getLine() throws IOException {
        return br.readLine();
    }

    //-- functions --//
    //-- utils --//
    //-- getters & setters --//
    //-- iWritables --//
    //-- inner classes --//
}
