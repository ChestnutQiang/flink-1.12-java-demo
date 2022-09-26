package com.dtstack.flinkx.catalog;

import com.dtstack.flinkx.catalog.dt.catalog.DTCatalog;
import org.apache.flink.configuration.PipelineOptions;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.StatementSet;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.catalog.Catalog;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * @author wujuan
 * @version 1.0
 * @date 2022/4/7 21:44 星期四
 * @email wujuan@dtstack.com
 * @company www.dtstack.com
 */
public class DTCatalogDatabaseTest {

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
    public void createCatalog() {
        // base-url + database + url-options
        // jdbc:mysql://172.16.83.218:3306/wujuan?autoReconnect=true&failOverReadOnly=false
        //String mysqlCatalogSQL =
        //        "CREATE CATALOG catalog1 WITH(\n"
        //                + "    'type' = 'DT',\n"
        //                + "    'default-database' = 'default_database',\n"
        //                + "    'catalog.base-url' = 'jdbc:mysql://172.16.83.218:3306/',\n"
        //                + "    'catalog.database' = 'wujuan',\n"
        //                + "    'catalog.username' = 'drpeco',\n"
        //                + "    'catalog.password' = 'DT@Stack#123',\n"
        //                + "    'catalog.url-options' = '?autoReconnect=true&failOverReadOnly=false',\n"
        //                + "    'catalog.project-id' = '1',\n"
        //                + "    'catalog.tenant-id' = '1'\n"
        //                + ")";
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

        String useCatalog = "use CATALOG catalog1";
        tableEnv.executeSql(useCatalog);

    }

    @Test
    public void listDatabase() {
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

    }
    @Test
    public void createDatabase() {
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

        String createDatabase = "create database if not exists catalog1.database1";
        tableEnv.executeSql(createDatabase);
        System.out.println(catalog.listDatabases().toString());

    }

    @Test
    public void dropDatabase() {
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

        String createDatabase = "drop database if exists catalog1.database1";
        tableEnv.executeSql(createDatabase);
        System.out.println(catalog.listDatabases().toString());
    }
}
