package com.dtstack.flinkx.catalog;

import com.dtstack.flinkx.catalog.catalog.DTCatalog;
import org.apache.flink.api.common.JobExecutionResult;
import org.apache.flink.configuration.PipelineOptions;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.StatementSet;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableResult;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.catalog.Catalog;
import org.apache.flink.table.catalog.CatalogDatabase;
import org.apache.flink.table.catalog.exceptions.DatabaseNotExistException;
import org.apache.flink.types.Row;
import org.apache.flink.util.CollectionUtil;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * @author wujuan
 * @version 1.0
 * @date 2022/4/7 21:44 星期四
 * @email wujuan@dtstack.com
 * @company www.dtstack.com
 */
public class DTCatalogX1 {

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
    public void firstBlood() {

        String mysqlCatalogSQL =
                "CREATE CATALOG mysql_catalog WITH(\n"
                        + "    'type' = 'DT',\n"
                        + "    'default-database' = 'wujuan_catalog',\n"
                        + "    'username' = 'drpeco',\n"
                        + "    'password' = 'DT@Stack#123',\n"
                        + "    'base-url' = 'jdbc:mysql://172.16.83.218:3306/',\n"
                        //+ "    'project-id' = '1',\n"
                        + "    'tenant-id' = '1'\n"
                        + ")";
        // project-id = 1
        // tenant-id = 1
        tableEnv.executeSql(mysqlCatalogSQL);

        String useCatalog = "use CATALOG mysql_catalog";
        tableEnv.executeSql(useCatalog);

        // String createDatabase = "create database catalog_database_wujuan";
        // tableEnv.executeSql(createDatabase);

        Optional<Catalog> flink_catalog = tableEnv.getCatalog("mysql_catalog");
        Catalog catalog = flink_catalog.get();

        System.out.println(catalog.listDatabases().toString());

        String createDatabase = "create database if not exists database2";
        tableEnv.executeSql(createDatabase);
        System.out.println(catalog.listDatabases().toString());

        //String dropDatabase = "drop database if exists wujuan_database2";
        //tableEnv.executeSql(dropDatabase);
        //System.out.println(catalog.listDatabases().toString());

        // -----------------------------------------------------------------
        tableEnv.executeSql("drop table if exists mysql_catalog.database2.source");
        String mysqlSource =
                ""
                        + "CREATE TABLE if not exists mysql_catalog.database2.source (\n"
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


        tableEnv.executeSql("drop table if exists mysql_catalog.database2.result1");
        String sinkSql =
                ""
                        + "CREATE TABLE if not exists mysql_catalog.database2.result1 (\n"
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
        String sql = "insert into mysql_catalog.database2.result1 select * from mysql_catalog.database2.source";

        try {
            TableResult tableResult =  tableEnv.executeSql(sql);
            tableResult.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //// 查询结构 mysql 数据源 t2 表
        //TableResult result = tableEnv.executeSql(selectSql);
        //printResult(result);

        //tableResult.print();

        //Table table = tableEnv.sqlQuery(selectSql);
        //TableResult result = table.execute();
        ////result.print();
        //printResult(result);

        //List<Row> rows = CollectionUtil.iteratorToList(result.collect());
        //rows.forEach(System.out::println);

    }

    private void printResult(TableResult result) {
        try {
            result.print();
        } catch (IllegalStateException e) {

        }
    }


    @Test
    public void createCatalog() {

        String mysqlCatalogSQL =
                "CREATE CATALOG mysql_catalog WITH(\n"
                        + "    'type' = 'DT',\n"
                        + "    'default-database' = 'wujuan_catalog',\n"
                        + "    'username' = 'drpeco',\n"
                        + "    'password' = 'DT@Stack#123',\n"
                        + "    'base-url' = 'jdbc:mysql://172.16.83.218:3306/',\n"
                        + "    'project-id' = '1',\n"
                        + "    'tenant-id' = '1'\n"
                        + ")";
        // project-id = 1
        // tenant-id = 1
        tableEnv.executeSql(mysqlCatalogSQL);

        String useCatalog = "use CATALOG mysql_catalog";
        tableEnv.executeSql(useCatalog);

    }

    @Test
    public void createDatabase() {

        String mysqlCatalogSQL =
                "CREATE CATALOG mysql_catalog1 WITH(\n"
                        + "    'type' = 'DT',\n"
                        + "    'default-database' = 'wujuan_catalog',\n"
                        + "    'username' = 'drpeco',\n"
                        + "    'password' = 'DT@Stack#123',\n"
                        + "    'base-url' = 'jdbc:mysql://172.16.83.218:3306/',\n"
                        //+ "    'project-id' = '1',\n"
                        + "    'tenant-id' = '1'\n"
                        + ")";

        tableEnv.executeSql(mysqlCatalogSQL);

        String useCatalog = "use CATALOG mysql_catalog1";
        tableEnv.executeSql(useCatalog);

        Optional<Catalog> flink_catalog = tableEnv.getCatalog("mysql_catalog1");
        Catalog catalog = flink_catalog.get();

        System.out.println(catalog.listDatabases().toString());

        String createDatabase = "create database wujuan_database2";
        tableEnv.executeSql(createDatabase);
        System.out.println(catalog.listDatabases().toString());

        String dropDatabase = "drop database wujuan_database2";
        //tableEnv.executeSql(dropDatabase);
        System.out.println(catalog.listDatabases().toString());
    }

    @Test
    public void dropDatabase() {

        String mysqlCatalogSQL =
                "CREATE CATALOG mysql_catalog2 WITH(\n"
                        + "    'type' = 'DT',\n"
                        + "    'default-database' = 'wujuan_catalog',\n"
                        + "    'username' = 'drpeco',\n"
                        + "    'password' = 'DT@Stack#123',\n"
                        + "    'base-url' = 'jdbc:mysql://172.16.83.218:3306/',\n"
                        + "    'project-id' = '1',\n"
                        + "    'tenant-id' = '1'\n"
                        + ")";

        tableEnv.executeSql(mysqlCatalogSQL);

        String createDatabase = "create database if not exists mysql_catalog2.wujuan_database2";
        tableEnv.executeSql(createDatabase);

        String mysqlSource =
                ""
                        + "CREATE TABLE if not exists mysql_catalog2.wujuan_database2.xxxxxxxxxxxxxxxx (\n"
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

        String dropDatabase = "drop database if exists mysql_catalog2.wujuan_database2 CASCADE";
        tableEnv.executeSql(dropDatabase);
    }



    @Test
    public void listTables() {
        String mysqlCatalogSQL =
                "CREATE CATALOG mysql_catalog1 WITH(\n"
                        + "    'type' = 'DT',\n"
                        + "    'default-database' = 'wujuan_catalog',\n"
                        + "    'username' = 'drpeco',\n"
                        + "    'password' = 'DT@Stack#123',\n"
                        + "    'base-url' = 'jdbc:mysql://172.16.83.218:3306/',\n"
                        + "    'project-id' = '1',\n"
                        + "    'tenant-id' = '1'\n"
                        + ")";

        tableEnv.executeSql(mysqlCatalogSQL);

        String useCatalog = "use CATALOG mysql_catalog1";
        tableEnv.executeSql(useCatalog);

        Optional<Catalog> flink_catalog = tableEnv.getCatalog("mysql_catalog1");
        Catalog catalog = flink_catalog.get();
        System.out.println("databases : " + catalog.listDatabases().toString());

        String createDatabase = "create database if not exists wujuan_database2";
        tableEnv.executeSql(createDatabase);
        System.out.println("databases : " + catalog.listDatabases().toString());

        try {
            List<String> wujuan_database2 = catalog.listTables("wujuan_database2");
            wujuan_database2.forEach(System.out::println);
        } catch (DatabaseNotExistException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void createTable() {

        String mysqlCatalogSQL =
                "CREATE CATALOG mysql_catalog1 WITH(\n"
                        + "    'type' = 'DT',\n"
                        + "    'default-database' = 'wujuan_catalog',\n"
                        + "    'username' = 'drpeco',\n"
                        + "    'password' = 'DT@Stack#123',\n"
                        + "    'base-url' = 'jdbc:mysql://172.16.83.218:3306/',\n"
                        //+ "    'project-id' = '1',\n"
                        + "    'tenant-id' = '1'\n"
                        + ")";
        // project-id = 1
        // tenant-id = 1
        tableEnv.executeSql(mysqlCatalogSQL);

        String useCatalog = "use CATALOG mysql_catalog1";
        tableEnv.executeSql(useCatalog);

        String mysqlSource =
                ""
                        + "CREATE TABLE if not exists mysql_catalog1.wujuan_database2.wujuan_table (\n"
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

    }

    @Test
    public void dropTable() {

        String mysqlCatalogSQL =
                "CREATE CATALOG mysql_catalog2 WITH(\n"
                        + "    'type' = 'DT',\n"
                        + "    'default-database' = 'wujuan_catalog',\n"
                        + "    'username' = 'drpeco',\n"
                        + "    'password' = 'DT@Stack#123',\n"
                        + "    'base-url' = 'jdbc:mysql://172.16.83.218:3306',\n"
                        //+ "    'project-id' = '1',\n"
                        + "    'tenant-id' = '1'\n"
                        + ")";
        // project-id = 1
        // tenant-id = 1
        tableEnv.executeSql(mysqlCatalogSQL);

        String useCatalog = "use CATALOG mysql_catalog2";
        tableEnv.executeSql(useCatalog);

        String createDatabase = "create database if not exists mysql_catalog2.wujuan_database2";
        tableEnv.executeSql(createDatabase);

        String mysqlSource =
                ""
                        + "CREATE TABLE if not exists mysql_catalog2.wujuan_database2.wujuan_table (\n"
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

        String deleteTable = "drop table mysql_catalog2.wujuan_database2.wujuan_table";
        tableEnv.executeSql(deleteTable);
    }


    @Test
    public void catalogLeftJoin() {

        String mysqlCatalogSQL =
                "CREATE CATALOG catalog1 WITH(\n"
                        + "    'type' = 'DT',\n"
                        + "    'default-database' = 'wujuan_catalog',\n"
                        + "    'username' = 'drpeco',\n"
                        + "    'password' = 'DT@Stack#123',\n"
                        + "    'base-url' = 'jdbc:mysql://172.16.83.218:3306/',\n"
                        //+ "    'project-id' = '1',\n"
                        + "    'tenant-id' = '1'\n"
                        + ")";

        tableEnv.executeSql(mysqlCatalogSQL);

        String useCatalog = "use CATALOG catalog1";
        tableEnv.executeSql(useCatalog);

        Optional<Catalog> flink_catalog = tableEnv.getCatalog("catalog1");
        Catalog catalog = flink_catalog.get();

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
                        + "    'default-database' = 'wujuan_catalog',\n"
                        + "    'username' = 'drpeco',\n"
                        + "    'password' = 'DT@Stack#123',\n"
                        + "    'base-url' = 'jdbc:mysql://172.16.83.218:3306/',\n"
                        + "    'project-id' = '1',\n"
                        + "    'tenant-id' = '1'\n"
                        + ")";
        tableEnv.executeSql(catalog1Sql);


        String catalog2Sql =
                "CREATE CATALOG catalog2 WITH(\n"
                        + "    'type' = 'DT',\n"
                        + "    'default-database' = 'wujuan_catalog',\n"
                        + "    'username' = 'drpeco',\n"
                        + "    'password' = 'DT@Stack#123',\n"
                        + "    'base-url' = 'jdbc:mysql://172.16.83.218:3306/',\n"
                        + "    'project-id' = '1',\n"
                        + "    'tenant-id' = '1'\n"
                        + ")";
        tableEnv.executeSql(catalog2Sql);

        String catalog3Sql =
                "CREATE CATALOG catalog3 WITH(\n"
                        + "    'type' = 'DT',\n"
                        + "    'default-database' = 'wujuan_catalog',\n"
                        + "    'username' = 'drpeco',\n"
                        + "    'password' = 'DT@Stack#123',\n"
                        + "    'base-url' = 'jdbc:mysql://172.16.83.218:3306/',\n"
                        + "    'project-id' = '1',\n"
                        + "    'tenant-id' = '1'\n"
                        + ")";
        tableEnv.executeSql(catalog3Sql);
        // -----------------------------------------------------------------
        Catalog catalog1 = tableEnv.getCatalog("catalog1").get();
        Catalog catalog2 = tableEnv.getCatalog("catalog2").get();
        Catalog catalog3 = tableEnv.getCatalog("catalog3").get();

        String dropDatabase1 = "drop database if exists catalog1.database1";
        tableEnv.executeSql(dropDatabase1);
        String createDatabase1 = "create database if not exists catalog1.database1";
        tableEnv.executeSql(createDatabase1);
        System.out.println("catalog1 table : " + catalog1.listDatabases().toString());

        String dropDatabase2 = "drop database if exists catalog2.database1";
        tableEnv.executeSql(dropDatabase2);
        String createDatabase2 = "create database if not exists catalog2.database1";
        tableEnv.executeSql(createDatabase2);
        System.out.println("catalog2 table : " + catalog2.listDatabases().toString());

        String dropDatabase3 = "drop database if exists catalog3.database1";
        tableEnv.executeSql(dropDatabase3);
        String createDatabase3 = "create database if not exists catalog3.database1";
        tableEnv.executeSql(createDatabase3);
        System.out.println("catalog3 table : " + catalog3.listDatabases().toString());
        // -----------------------------------------------------------------


        // -----------------------------------------------------------------
        // 创建 catalog1 表 1
        tableEnv.executeSql("drop table if exists catalog1.database1.table1");
        String table1Sql =
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
        tableEnv.executeSql(table1Sql);

        // 创建 catalog2 表 1
        tableEnv.executeSql("drop table if exists catalog2.database1.table1");
        String table2Sql =
                ""
                        + "CREATE TABLE if not exists catalog2.database1.table1 (\n"
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
        tableEnv.executeSql("drop table if exists catalog3.database1.table1");
        String table3Sql =
                ""
                        + "CREATE TABLE if not exists catalog3.database1.table1 (\n"
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
        String sql = "insert into catalog3.database1.table1 " +
                "select * from catalog1.database1.table1 t1 join catalog2.database1.table1 t2 on t1.id = t2.id";
        try {
            TableResult tableResult =  tableEnv.executeSql(sql);
            tableResult.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
