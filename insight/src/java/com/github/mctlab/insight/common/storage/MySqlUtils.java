/** MySqlUtils */
package com.github.mctlab.insight.common.storage;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据库工具
 * <li>不仅限于MySql</li>
 * <li>主要用于测试</li>
 */
public class MySqlUtils {

    //-- public finals --//
    //-- private finals --//

    private static final Logger LOG = LoggerFactory.getLogger(MySqlUtils.class);

    //-- properties --//
    //-- constructors --//

    private static MySqlUtils singleton = new MySqlUtils();

    /**
     * 获得singleton
     */
    public static MySqlUtils getInstance() {
        return singleton;
    }

    //-- destructors --//
    //-- implements --//
    //-- un-implements --//
    //-- methods --//
    //-- functions --//
    //-- utils --//

    /**
     * 由于hsqldb和mysql不同, 所以需要替换某些描述
     */
    public static String getTestTableProperty(String tableProperty) {
        return tableProperty
                .replace("AUTO_INCREMENT", "IDENTITY")
                .replace(" UNSIGNED", "")
                .replace("TEXT", "VARCHAR");
    }

    /**
     * createDataSource系列, for mysql
     */
    public static DataSource createDataSource(String host, String db, String user, String password) {
        return createDataSource(String.format(
                "jdbc:mysql://%s/%s?user=%s&password=%s", host, db, user, password));
    }

    /**
     * createDataSource系列, for mysql
     */
    public static DataSource createDataSource(String url) {
        return DataSourceFactory.getFactory().createDataSource("com.mysql.jdbc.Driver", url);
    }

    /**
     * createDataSource系列, for hsqldb
     */
    public static DataSource createTestDataSource() {
        String className = "org.hsqldb.jdbcDriver";
        String url = "jdbc:hsqldb:mem:.;user=sa";

        return DataSourceFactory.getFactory().createDataSource(className, url, false);
    }

    /**
     * 列出数据库中的所有table
     */
    public static Set<String> listTables(Connection conn) {
        ResultSet tableNames = null;
        Set<String> tableSet = new HashSet<String>();
        try {
            DatabaseMetaData metadata = conn.getMetaData();
            LOG.info("tables in the database: ");
            LOG.info("------------------------");
            tableNames = metadata.getTables(null, null, "%", new String[]{"TABLE"});
            while (tableNames.next()) {
                String tableName = tableNames.getString("TABLE_NAME");
                LOG.info(tableName);
                tableSet.add(tableName);
            }
            LOG.info("------------------------");
        } catch (SQLException e) {
            LOG.info("list tables failed", e);
        } finally {
            closeResultSet(tableNames);
        }
        return tableSet;
    }

    /**
     * 新建table
     */
    public static boolean createTable(Connection conn, String tableName, String tableProperty) {
        boolean ret = false;
        Statement stat = null;
        try {
            stat = conn.createStatement();
            String sql = "CREATE TABLE " + tableName + " " + tableProperty + ";";
            LOG.info("sql: " + sql);
            stat.execute(sql);
            ret = true;
        } catch (SQLException e) {
            LOG.info("create table: " + tableName + " failed", e);
        } finally {
            closeStatement(stat);
        }
        return ret;
    }

    /**
     * 删除table
     */
    public static boolean deleteTable(Connection conn, String tableName) {
        boolean ret = false;
        Statement stat = null;
        try {
            stat = conn.createStatement();
            String sql = "DROP TABLE " + tableName + ";";
            LOG.info("sql: " + sql);
            stat.execute(sql);
            ret = true;
        } catch (SQLException e) {
            LOG.info("delete table: " + tableName + " failed", e);
        } finally {
            closeStatement(stat);
        }
        return ret;
    }

    /**
     * 关闭连接
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                LOG.info("closeConnection failed", e);
            }
        }
    }

    /**
     * 关闭statement
     */
    public static void closeStatement(Statement stat) {
        if (stat != null) {
            try {
                stat.close();
            } catch (Exception e) {
                LOG.info("closeStatement failed", e);
            }
        }
    }

    /**
     * 关闭resultset
     */
    public static void closeResultSet(ResultSet rst) {
        if (rst != null) {
            try {
                rst.close();
            } catch (Exception e) {
                LOG.info("closeResultSet failed", e);
            }
        }
    }

    //-- getters & setters --//
    //-- iWritables --//
    //-- inner classes --//
}
