/** DataSourceFactory */
package cn.mctlab.insight.common.storage;

import javax.sql.DataSource;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

/**
 * 用于生成datesource的工厂类
 */
public class DataSourceFactory {

    //-- public finals --//
    //-- private finals --//

    private static final boolean DEFAULT_TEST_ON_BORROW = true;
    private static final int DEFAULT_MAX_ACTIVE = 20;

    private static final String DEFAULT_VALIDATION_QUERY = "SELECT 1";
    private static final boolean DEFAULT_READ_ONLY = false;
    private static final boolean DEFAULT_AUTO_COMMIT = true;

    //-- properties --//
    //-- constructors --//

    private static DataSourceFactory factory = new DataSourceFactory();

    public DataSourceFactory() {}

    /**
     * 获得factory
     */
    public static DataSourceFactory getFactory() {
        return factory;
    }

    //-- destructors --//
    //-- implements --//
    //-- un-implements --//
    //-- methods --//

    /**
     * createDataSource系列
     */
    public DataSource createDataSource(String className, String url) {
        return createDataSource(className, url, DEFAULT_TEST_ON_BORROW);
    }

    /**
     * createDataSource系列
     */
    public DataSource createDataSource(String className, String url, boolean testOnBorrow) {
        return createDataSource(className, url, testOnBorrow, DEFAULT_MAX_ACTIVE);
    }

    /**
     * createDataSource系列
     */
    public DataSource createDataSource(String className, String url, boolean testOnBorrow, int maxActive) {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("load class " + className + " failed", e);
        }

        // connectionFactory
        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(url, null);

        // connectionPool
        GenericObjectPool connectionPool = new GenericObjectPool();
        connectionPool.setTestOnBorrow(testOnBorrow);
        connectionPool.setMaxActive(maxActive);

        // PoolableConnectionFactory封装了connectionFactory, 并将自己set到connectionPool中
        // 代码中new PoolableConnectionFactory的时候调用了connectionPool.setFactory(this),
        // 很奇怪的写法, 估计是为了用完之后归还所必须的设计... by liqiang
        new PoolableConnectionFactory(connectionFactory, connectionPool, null,
                DEFAULT_VALIDATION_QUERY, DEFAULT_READ_ONLY, DEFAULT_AUTO_COMMIT);

        // 下面这种写法相当于new PoolableConnectionFactory的内部逻辑, 不过不能这样写, 因为new时参数pool不能为null, by liqiang
        /*
        PoolableConnectionFactory poolableConnectionFactory =
            new PoolableConnectionFactory(connectionFactory, null, null, validationQuery, false, false);
        poolableConnectionFactory.setPool(connectionPool);
        connectionPool.setFactory(poolableConnectionFactory);
        */

        // dataSource.getConnection ->
        // connectionPool.borrowObject ->
        // (new PoolableConnectionFactory).makeObject() ->
        // connectionFactory.createConnection
        return new PoolingDataSource(connectionPool);
    }

    //-- functions --//
    //-- utils --//
    //-- getters & setters --//
    //-- inner classes --//
}
