package com.dtstack.flinkx.catalog;

import com.dtstack.flinkx.catalog.dt.catalog.DTCatalog;
import org.apache.flink.configuration.PipelineOptions;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.StatementSet;
import org.apache.flink.table.api.TableResult;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.catalog.Catalog;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wujuan
 * @version 1.0
 * @date 2022/4/7 21:44 星期四
 * @email wujuan@dtstack.com
 * @company www.dtstack.com
 */
public class DTCatalogTableTest {

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
    public void listTable() {
        String catalogSQL =
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
        tableEnv.executeSql(catalogSQL);

        String createDatabase = "create database if not exists catalog1.default_database";
        tableEnv.executeSql(createDatabase);

        Catalog catalog = tableEnv.getCatalog("catalog1").get();
        System.out.println(catalog.listDatabases().toString());

        String showTables = "show tables";
        TableResult tableResult = tableEnv.executeSql(showTables);
        tableResult.print();

    }

    @Test
    public void listTable1() {
        String catalogSQL =
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
        tableEnv.executeSql(catalogSQL);

        String createDatabase = "create database if not exists catalog1.default_database";
        tableEnv.executeSql(createDatabase);

        Catalog catalog = tableEnv.getCatalog("catalog1").get();
        System.out.println(catalog.listDatabases().toString());

        tableEnv.executeSql("use catalog catalog1");
        String showTables = "show tables";
        TableResult tableResult = tableEnv.executeSql(showTables);
        tableResult.print();

    }

    @Test
    public void createTable() {
        String catalogSQL =
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
        tableEnv.executeSql(catalogSQL);

        String createDatabase = "create database if not exists catalog1.default_database";
        tableEnv.executeSql(createDatabase);

        String createTableSQL =
                ""
                        + "CREATE TABLE if not exists catalog1.default_database.table1 (\n"
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
        tableEnv.executeSql(createTableSQL);
    }

    @Test
    public void dropTable() {
        String catalogSQL =
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
        tableEnv.executeSql(catalogSQL);

        String createDatabase = "create database if not exists catalog1.default_database";
        tableEnv.executeSql(createDatabase);

        String createTableSQL =
                ""
                        + "CREATE TABLE if not exists catalog1.default_database.table1 (\n"
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
        tableEnv.executeSql(createTableSQL);

        String deleteTable = "drop table if exists catalog1.default_database.table1";
        tableEnv.executeSql(deleteTable);
    }










}
