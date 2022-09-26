package com.dtstack.flinkx.catalog;

import com.dtstack.flinkx.catalog.catalog.DTCatalog;
import org.apache.flink.configuration.PipelineOptions;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.StatementSet;
import org.apache.flink.table.api.TableResult;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.catalog.Catalog;
import org.apache.flink.table.catalog.exceptions.DatabaseNotExistException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * @author wujuan
 * @version 1.0
 * @date 2022/4/7 21:44 星期四
 * @email wujuan@dtstack.com
 * @company www.dtstack.com
 */
public class DTCatalogTest {

    private static final Logger LOG = LoggerFactory.getLogger(DTCatalog.class);

    EnvironmentSettings settings;
    StreamExecutionEnvironment env;
    StreamTableEnvironment tableEnv;
    StatementSet statementSet;

    @Rule
    public final EnvironmentVariables environmentVariables
            = new EnvironmentVariables();

    @Before
    public void initStreamEnv() {
        settings = EnvironmentSettings
                .newInstance()
                .inStreamingMode()
                .useBlinkPlanner()
                .build();
        //构建环境信息
        env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        tableEnv = StreamTableEnvironment.create(env, settings);
        statementSet = tableEnv.createStatementSet();
        env.setParallelism(1);
        tableEnv.getConfig().getConfiguration().setString(PipelineOptions.NAME, "wujuan_job");
        System.out.println("初始化 Flink 环境成功!");

        environmentVariables.set("HADOOP_HOME", "");
        //environmentVariables.set("HADOOP_CONF_DIR", "");
        //environmentVariables.set("YARN_CONF_DIR", "");

        environmentVariables.set("HADOOP_CONF_DIR", "/Users/lzq/Desktop/Projects/Flink/flink-1.12-catalog-demo/DTCatalog/src/test/resources/conf");
        environmentVariables.set("YARN_CONF_DIR", "/Users/lzq/Desktop/Projects/Flink/flink-1.12-catalog-demo/DTCatalog/src/test/resources/conf");
        environmentVariables.set("HADOOP_USER_NAME", "root");

    }


    @Test
    public void readJDBCDataTest() {
        String mysqlCatalogSQL =
                "CREATE CATALOG catalog1 WITH(\n"
                        + "    'type' = 'DT',\n"
                        + "    'default-database' = 'default_database',\n"
                        + "    'driver' = 'com.mysql.cj.jdbc.Driver',\n"
                        + "    'url' = 'jdbc:mysql://172.16.100.186:3306/catalog_default?autoReconnect=true&failOverReadOnly=false',\n"
                        + "    'username' = 'drpeco',\n"
                        + "    'password' = 'DT@Stack#123',\n"
                        + "    'project-id' = '1',\n"
                        + "    'tenant-id' = '1'\n"
                        + ")";
        tableEnv.executeSql(mysqlCatalogSQL);


        Optional<Catalog> flink_catalog = tableEnv.getCatalog("catalog1");
        Catalog catalog = flink_catalog.get();
        System.out.println(catalog.listDatabases().toString());

        String createDatabase1 = "create database if not exists catalog1.database1";
        tableEnv.executeSql(createDatabase1);
        System.out.println(catalog.listDatabases().toString());


        String createDatabase2 = "create database if not exists catalog1.database2";
        tableEnv.executeSql(createDatabase2);
        System.out.println(catalog.listDatabases().toString());
        // -----------------------------------------------------------------
        //tableEnv.executeSql("drop table if exists catalog1.catalog1.table1");
        String mysqlSource =
                ""
                        + "CREATE TABLE if not exists catalog1.database1.table1 (\n"
                        + " id int,\n"
                        + " name string,\n"
                        + " age bigint,\n"
                        + " primary key (id) not enforced\n"
                        + ") with (\n"
                        + " 'connector' = 'jdbc',\n"
                        + " 'url' = 'jdbc:mysql://172.16.83.218:3306/wujuan?useSSL=false',\n"
                        + " 'table-name' = 't2',\n"
                        + " 'username' = 'drpeco',\n"
                        + " 'password' = 'DT@Stack#123'\n"
                        + ")";
        //
        tableEnv.executeSql(mysqlSource);


        //tableEnv.executeSql("drop table if exists catalog1.catalog2.table2");
        String sinkSql =
                ""
                        + "CREATE TABLE if not exists catalog1.database2.table2 (\n"
                        + " id int,\n"
                        + " name string,\n"
                        + " age bigint,\n"
                        + " primary key (id) not enforced\n"
                        + ") with (\n"
                        + " 'connector' = 'print'\n"
                        //+ " 'sink.parallelism' = '1'\n"
                        + ")";
        //
        tableEnv.executeSql(sinkSql);

        //String selectSql = "select * from mysql_catalog.wujuan_catalog.wujuan_table";
        String sql = "insert into catalog1.database2.table2 select * from catalog1.database1.table1";

        try {
            TableResult tableResult =  tableEnv.executeSql(sql);
            tableResult.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void catalogLeftJoin() {

        String mysqlCatalogSQL =
                "CREATE CATALOG catalog1 WITH(\n"
                        + "    'type' = 'DT',\n"
                        + "    'default-database' = 'default_database',\n"
                        + "    'driver' = 'com.mysql.cj.jdbc.Driver',\n"
                        + "    'url' = 'jdbc:mysql://172.16.100.186:3306/catalog_default?autoReconnect=true&failOverReadOnly=false',\n"
                        + "    'username' = 'drpeco',\n"
                        + "    'password' = 'DT@Stack#123',\n"
                        + "    'project-id' = '1',\n"
                        + "    'tenant-id' = '1'\n"
                        + ")";
        tableEnv.executeSql(mysqlCatalogSQL);

        Catalog catalog = tableEnv.getCatalog("catalog1").get();

        System.out.println(catalog.listDatabases().toString());

        String createDatabase = "create database if not exists database1";
        tableEnv.executeSql(createDatabase);
        System.out.println(catalog.listDatabases().toString());


        String table1 =
                ""
                        + "CREATE TABLE if not exists catalog1.database1.table1 (\n"
                        + " id int,\n"
                        + " name string,\n"
                        + " age bigint,\n"
                        + " primary key (id) not enforced\n"
                        + ") with (\n"
                        + " 'connector' = 'jdbc',\n"
                        + " 'url' = 'jdbc:mysql://172.16.83.218:3306/wujuan?useSSL=false',\n"
                        + " 'table-name' = 't2',\n"
                        + " 'username' = 'drpeco',\n"
                        + " 'password' = 'DT@Stack#123'\n"
                        + ")";
        tableEnv.executeSql(table1);

        String table2 =
                ""
                        + "CREATE TABLE if not exists catalog1.database1.table2 (\n"
                        + " id int,\n"
                        + " name string,\n"
                        + " age bigint,\n"
                        + " primary key (id) not enforced\n"
                        + ") with (\n"
                        + " 'connector' = 'jdbc',\n"
                        + " 'url' = 'jdbc:mysql://172.16.83.218:3306/wujuan?useSSL=false',\n"
                        + " 'table-name' = 't2',\n"
                        + " 'username' = 'drpeco',\n"
                        + " 'password' = 'DT@Stack#123'\n"
                        + ")";
        tableEnv.executeSql(table2);

        String slelectMysql = "select * from catalog1.database1.table1 t1 join catalog1.database1.table2 t2 on t1.id = t2.id";
        //// 查询结构 mysql 数据源 t2 表
        TableResult tableResult = tableEnv.executeSql(slelectMysql);
        tableResult.print();

    }


    @Test
    public void threeCatalog() {
        String catalog1Sql =
                "CREATE CATALOG catalog1 WITH(\n"
                        + "    'type' = 'DT',\n"
                        + "    'default-database' = 'default_database',\n"
                        + "    'driver' = 'com.mysql.cj.jdbc.Driver',\n"
                        + "    'url' = 'jdbc:mysql://172.16.100.186:3306/catalog_default?autoReconnect=true&failOverReadOnly=false',\n"
                        + "    'username' = 'drpeco',\n"
                        + "    'password' = 'DT@Stack#123',\n"
                        + "    'project-id' = '1',\n"
                        + "    'tenant-id' = '1'\n"
                        + ")";
        tableEnv.executeSql(catalog1Sql);


        String catalog2Sql =
                "CREATE CATALOG catalog2 WITH(\n"
                        + "    'type' = 'DT',\n"
                        + "    'default-database' = 'default_database',\n"
                        + "    'driver' = 'com.mysql.cj.jdbc.Driver',\n"
                        + "    'url' = 'jdbc:mysql://172.16.100.186:3306/catalog_default?autoReconnect=true&failOverReadOnly=false',\n"
                        + "    'username' = 'drpeco',\n"
                        + "    'password' = 'DT@Stack#123',\n"
                        + "    'project-id' = '1',\n"
                        + "    'tenant-id' = '1'\n"
                        + ")";
        tableEnv.executeSql(catalog2Sql);

        String catalog3Sql =
                "CREATE CATALOG catalog3 WITH(\n"
                        + "    'type' = 'DT',\n"
                        + "    'default-database' = 'default_database',\n"
                        + "    'driver' = 'com.mysql.cj.jdbc.Driver',\n"
                        + "    'url' = 'jdbc:mysql://172.16.100.186:3306/catalog_default?autoReconnect=true&failOverReadOnly=false',\n"
                        + "    'username' = 'drpeco',\n"
                        + "    'password' = 'DT@Stack#123',\n"
                        + "    'project-id' = '1',\n"
                        + "    'tenant-id' = '1'\n"
                        + ")";
        tableEnv.executeSql(catalog3Sql);
        // -----------------------------------------------------------------
        Catalog catalog1 = tableEnv.getCatalog("catalog1").get();
        Catalog catalog2 = tableEnv.getCatalog("catalog2").get();
        Catalog catalog3 = tableEnv.getCatalog("catalog3").get();

        String dropDatabase1 = "drop database if exists catalog1.database11 cascade";
        tableEnv.executeSql(dropDatabase1);
        String createDatabase1 = "create database if not exists catalog1.database11";
        tableEnv.executeSql(createDatabase1);
        System.out.println("catalog1 table : " + catalog1.listDatabases().toString());

        String dropDatabase2 = "drop database if exists catalog2.database22 cascade";
        tableEnv.executeSql(dropDatabase2);
        String createDatabase2 = "create database if not exists catalog2.database22";
        tableEnv.executeSql(createDatabase2);
        System.out.println("catalog2 table : " + catalog2.listDatabases().toString());

        String dropDatabase3 = "drop database if exists catalog3.database33  cascade";
        tableEnv.executeSql(dropDatabase3);
        String createDatabase3 = "create database if not exists catalog3.database33";
        tableEnv.executeSql(createDatabase3);
        System.out.println("catalog3 table : " + catalog3.listDatabases().toString());
        // -----------------------------------------------------------------


        // -----------------------------------------------------------------
        // 创建 catalog1 表 1
        tableEnv.executeSql("drop table if exists catalog1.database11.table1");
        String table1Sql =
                ""
                        + "CREATE TABLE if not exists catalog1.database11.table1 (\n"
                        + " id int,\n"
                        + " name string,\n"
                        + " age bigint,\n"
                        + " primary key (id) not enforced\n"
                        + ") with (\n"
                        + " 'connector' = 'jdbc',\n"
                        + " 'url' = 'jdbc:mysql://172.16.83.218:3306/wujuan?useSSL=false',\n"
                        + " 'table-name' = 't2',\n"
                        + " 'username' = 'drpeco',\n"
                        + " 'password' = 'DT@Stack#123'\n"
                        + ")";
        tableEnv.executeSql(table1Sql);

        // 创建 catalog2 表 1
        tableEnv.executeSql("drop table if exists catalog2.database22.table1");
        String table2Sql =
                ""
                        + "CREATE TABLE if not exists catalog2.database22.table1 (\n"
                        + " id int,\n"
                        + " name string,\n"
                        + " age bigint,\n"
                        + " primary key (id) not enforced\n"
                        + ") with (\n"
                        + " 'connector' = 'jdbc',\n"
                        + " 'url' = 'jdbc:mysql://172.16.83.218:3306/wujuan?useSSL=false',\n"
                        + " 'table-name' = 't2',\n"
                        + " 'username' = 'drpeco',\n"
                        + " 'password' = 'DT@Stack#123'\n"
                        + ")";
        tableEnv.executeSql(table2Sql);

        // 创建 catalog3 表 1
        tableEnv.executeSql("drop table if exists catalog3.database33.table1");
        String table3Sql =
                ""
                        + "CREATE TABLE if not exists catalog3.database33.table1 (\n"
                        + " t1_id int,\n"
                        + " t1_name string,\n"
                        + " t1_age bigint,\n"
                        + " t2_id int,\n"
                        + " t2_name string,\n"
                        + " t2_age bigint\n"
                        + ") with (\n"
                        + " 'connector' = 'print'\n"
                        //+ " 'sink.parallelism' = '1'\n"
                        + ")";
        //
        tableEnv.executeSql(table3Sql);
        // -----------------------------------------------------------------
        String sql = "insert into catalog3.database33.table1 " +
                "select * from catalog1.database11.table1 t1 join catalog2.database22.table1 t2 on t1.id = t2.id";
        try {
            TableResult tableResult =  tableEnv.executeSql(sql);
            tableResult.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
