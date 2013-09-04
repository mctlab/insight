/** StorageException */
package cn.mctlab.insight.common.storage;

/**
 * 数据库存储异常
 */
public class StorageException extends Exception {

    //-- public finals --//

    public static final int CODE_UNKNOWN = -1;

    //-- private finals --//

    private static final long serialVersionUID = 1L;

    //-- properties --//

    private int code = CODE_UNKNOWN;

    //-- constructors --//

    /**
     * 构造数据库存储异常
     */
    public StorageException(int code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 构造数据库存储异常
     */
    public StorageException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    /**
     * 构造数据库存储异常
     */
    public StorageException(String message) {
        this(CODE_UNKNOWN, message);
    }

    /**
     * 构造数据库存储异常
     */
    public StorageException(String message, Throwable cause) {
        this(CODE_UNKNOWN, message, cause);
    }

    //-- destructors --//
    //-- implements --//
    //-- un-implements --//
    //-- methods --//
    //-- functions --//
    //-- utils --//
    //-- getters & setters --//

    /**
     * 获得异常代码
     */
    public int getCode() {
        return code;
    }

    //-- iWritables --//
    //-- inner classes --//
}
