package com.lzq.catalog.dtcatalog;

import cn.hutool.core.util.ReflectUtil;
import org.apache.flink.configuration.PipelineOptions;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.StatementSet;
import org.apache.flink.table.api.TableResult;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.catalog.Catalog;
import org.apache.flink.table.catalog.CatalogBaseTable;
import org.apache.flink.table.catalog.ObjectPath;
import org.apache.flink.table.catalog.exceptions.DatabaseNotExistException;
import org.apache.flink.table.catalog.exceptions.TableNotExistException;
import org.apache.flink.table.factories.CatalogFactory;
import org.apache.flink.table.factories.TableFactoryService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * @author wujuan
 * @version 1.0
 * @date 2022/4/7 21:44 星期四
 * @email wujuan@dtstack.com
 * @company www.dtstack.com
 */
public class DTCatalogSPI {

    private static final Logger LOG = LoggerFactory.getLogger(DTCatalogSPI.class);

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

        environmentVariables.set("HADOOP_CONF_DIR", "/Users/lzq/Desktop/Projects/Flink/flink-1.12-catalog-demo/LocalTest/src/main/resources/conf");
        environmentVariables.set("YARN_CONF_DIR", "/Users/lzq/Desktop/Projects/Flink/flink-1.12-catalog-demo/LocalTest/src/main/resources/conf");
        environmentVariables.set("HADOOP_USER_NAME", "root");

    }


    @Test
    public void StreamSQLDemo0(){
        ClassLoader classLoader = SPILoadJar();

        String catalogName = "flink_catalog";

        //String dropCatalog = "drop catalog flink_catalog";
        //tableEnv.executeSql(dropCatalog);

        String hiveCatalog = "CREATE CATALOG flink_catalog WITH (\n" +
                "    'type' = 'hive',\n" +
                "    'default-database' = 'catalog_database_wujuan',\n" +
                "    'hive-conf-dir' = '/Users/lzq/Desktop/Projects/Flink/flink-1.12-catalog-demo/LocalTest/src/main/resources/conf'\n" +
                ")";
        tableEnv.executeSql(hiveCatalog);

        String useCatalog = "use CATALOG flink_catalog";
        tableEnv.executeSql(useCatalog);


        //String createDatabase = "create database catalog_database_wujuan";
        //tableEnv.executeSql(createDatabase);

        Optional<Catalog> flink_catalog = tableEnv.getCatalog("flink_catalog");
        Catalog catalog = flink_catalog.get();

        System.out.println(catalog);
        try {
            Map<String, String> catalog_database_wujuan = catalog.getDatabase("catalog_database_wujuan")
                                                                 .getProperties();
            CatalogBaseTable table = catalog.getTable(new ObjectPath("catalog_database_wujuan", "catalog_t3"));

        } catch (DatabaseNotExistException e) {
            throw new RuntimeException(e);
        } catch (TableNotExistException e) {
            throw new RuntimeException(e);
        }
        // -----------------------------------------------------------------

        String mysqlSource =
                ""
                        + "CREATE TABLE if not exists flink_catalog.catalog_database_wujuan.catalog_t3 (\n"
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

        tableEnv.executeSql(mysqlSource);


        String slelectMysql = "select * from flink_catalog.catalog_database_wujuan.catalog_t3";
        //// 查询结构 mysql 数据源 t2 表
        TableResult tableResult = tableEnv.executeSql(slelectMysql);
        //
        tableResult.print();
    }

    private static ClassLoader SPILoadJar() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            ReflectUtil.invoke(classLoader,"addURL",new URL("file:///Users/lzq/Desktop/Projects/Flink/flink-1.12-catalog-demo/jar/flink-connector-hive_2.12-1.12.7.jar"));
            ReflectUtil.invoke(classLoader,"addURL",new URL("file:///Users/lzq/Desktop/Projects/Flink/flink-1.12-catalog-demo/jar/hive-exec-2.3.4.jar"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return classLoader;
    }

}
