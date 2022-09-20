package com.lzq.catalog.other;

import cn.hutool.core.util.ReflectUtil;
import org.apache.flink.configuration.PipelineOptions;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.StatementSet;
import org.apache.flink.table.api.TableResult;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.catalog.Catalog;
import org.apache.flink.table.catalog.exceptions.DatabaseNotExistException;
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
public class HiveCatalogSPIDemo {

    private static final Logger LOG = LoggerFactory.getLogger(HiveCatalogSPIDemo.class);

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

        String catalogName = "flink_hive_catalog";

        final Map<String, String> options = new HashMap<>();
        options.put("type", "hive");
        options.put("hive-conf-dir","/Users/lzq/Desktop/Projects/Flink/flink-1.12-catalog-demo/LocalTest/src/main/resources/conf");

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            ReflectUtil.invoke(classLoader,"addURL",new URL("file:///Users/lzq/Desktop/Projects/Flink/flink-1.12-catalog-demo/jar/flink-connector-hive_2.12-1.12.7.jar"));
            ReflectUtil.invoke(classLoader,"addURL",new URL("file:///Users/lzq/Desktop/Projects/Flink/flink-1.12-catalog-demo/jar/hive-exec-2.3.4.jar"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        CatalogFactory legacyFactory =
                TableFactoryService.find(CatalogFactory.class, options, classLoader);

        Catalog catalog = legacyFactory.createCatalog(catalogName, options);


        //Catalog actualCatalog =
        //        FactoryUtil.createCatalog(
        //                catalogName, options, null, Thread.currentThread().getContextClassLoader());


        tableEnv.registerCatalog(catalogName, catalog);

        //String useCatalog = "use CATALOG flink_hive_catalog";
        //tableEnv.executeSql(useCatalog);
        // -----------------------------------------------------------------

        String mysqlSource =
                ""
                        + "CREATE TABLE if not exists flink_hive_catalog.wujuan.catalog_t3 (\n"
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


        //String slelectMysql = "select * from flink_hive_catalog.wujuan.t3";
        //// 查询结构 mysql 数据源 t2 表
        //TableResult tableResult = tableEnv.executeSql(slelectMysql);
        //
        //tableResult.print();
    }
    @Test
    public void StreamSQLDemo1(){

        String catalogName = "flink_hive_catalog";

        final Map<String, String> options = new HashMap<>();
        options.put("type", "hive");
        options.put("hive-conf-dir","/Users/lzq/Desktop/Projects/Flink/flink-1.12-catalog-demo/src/main/resources/hiveconf/conf");

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            ReflectUtil.invoke(classLoader,"addURL",new URL("file:///Users/lzq/Desktop/Projects/Flink/flink-1.12-catalog-demo/flinkx-dist/catalog/hive/flinkx-catalog-master.jar"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        CatalogFactory legacyFactory =
                TableFactoryService.find(CatalogFactory.class, options, classLoader);

        Catalog catalog = legacyFactory.createCatalog(catalogName, options);


        //Catalog actualCatalog =
        //        FactoryUtil.createCatalog(
        //                catalogName, options, null, Thread.currentThread().getContextClassLoader());


        tableEnv.registerCatalog(catalogName, catalog);

        //String useCatalog = "use CATALOG flink_hive_catalog";
        //tableEnv.executeSql(useCatalog);
        // -----------------------------------------------------------------


        String mysqlSource =
                ""
                        + "CREATE TABLE if not exists flink_hive_catalog.wujuan.t3 (\n"
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

        TableResult tableResult = tableEnv.executeSql(mysqlSource);


        //String slelectMysql = "select * from flink_hive_catalog.wujuan.t3";
        //// 查询结构 mysql 数据源 t2 表
        //TableResult tableResult = tableEnv.executeSql(slelectMysql);
        //
        //tableResult.print();


    }
    @Test
    public void StreamSQLDemoStatementSet(){

        String catalogName = "flink_hive_catalog";

        final Map<String, String> options = new HashMap<>();
        options.put("type", "hive");
        options.put("hive-conf-dir","/Users/lzq/Desktop/Projects/Flink/flink-1.12-catalog-demo/LocalTest/src/main/resources/conf");

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            ReflectUtil.invoke(classLoader,"addURL",new URL("file:///Users/lzq/Desktop/Projects/Flink/flink-1.12-catalog-demo/flinkx-dist/catalog/hive/flinkx-catalog-master.jar"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        CatalogFactory legacyFactory =
                TableFactoryService.find(CatalogFactory.class, options, classLoader);

        Catalog catalog = legacyFactory.createCatalog(catalogName, options);


        //Catalog actualCatalog =
        //        FactoryUtil.createCatalog(
        //                catalogName, options, null, Thread.currentThread().getContextClassLoader());


        tableEnv.registerCatalog(catalogName, catalog);

        //String useCatalog = "use CATALOG flink_hive_catalog";
        //tableEnv.executeSql(useCatalog);
        // -----------------------------------------------------------------

        // 单独创建 wujuan1
        //tableEnv.executeSql("create database flink_hive_catalog.wujuan1");

        StatementSet statementSet = tableEnv.createStatementSet();


        String sourceSQL =
                ""
                        + "CREATE TABLE if not exists flink_hive_catalog.wujuan.source (\n"
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

        tableEnv.executeSql(sourceSQL);



        //String selectSql = "select * from flink_hive_catalog.wujuan.source";
        //// 查询结构 mysql 数据源 t2 表
        //TableResult tableResult = tableEnv.executeSql(selectSql);
        //tableResult.print();

        tableEnv.executeSql("drop table flink_hive_catalog.wujuan.sink");

        String SinkSQL = "" +
                "create table if not exists flink_hive_catalog.wujuan.sink ( \n" +
                "    id int,\n" +
                "    name string,\n" +
                "    age bigint\n" +
                ") WITH (\n" +
                "   'print-identifier' = 'sink >> ', \n" +
                "   'connector' = 'print' \n" +
                ")";
        tableEnv.executeSql(SinkSQL);


        String insertSql = "insert into flink_hive_catalog.wujuan.sink select * from flink_hive_catalog.wujuan.source";
        statementSet.addInsertSql(insertSql);
        TableResult tableResult = statementSet.execute();



        //String likeSQL = "create table okr with ('connector' = 'print') like flink_hive_catalog.wujuan.sink(EXCLUDING OPTIONS)";
        //tableEnv.executeSql(likeSQL);
        //String selectLikeSQL = "select * from okr";
        //tableEnv.executeSql(selectLikeSQL).print();




        try {
            tableResult.await();
            LOG.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }




/*        Optional<JobClient> jobClient = tableResult.getJobClient();
        if (jobClient.isPresent()) {
            try {
                JobClient jobClient1 = jobClient.get();
                Map<String, Object> stringObjectMap = jobClient1.getAccumulators().get();

                //System.out.println(stringObjectMap);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }*/


        //try {
        //    tableEnv.execute("wujuan"); // 没有 insert 会报错
        //} catch (Exception e) {
        //    throw new RuntimeException(e);
        //}
    }




    @Test
    public void StreamSQLDemoStatementSetKafka(){

        String catalogName = "flink_hive_catalog";

        final Map<String, String> options = new HashMap<>();
        options.put("type", "hive");
        options.put("hive-conf-dir","/Users/lzq/Desktop/Projects/Flink/flink-1.12-catalog-demo/LocalTest/src/main/resources/conf");

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            ReflectUtil.invoke(classLoader,"addURL",new URL("file:///Users/lzq/Desktop/Projects/Flink/flink-1.12-catalog-demo/flinkx-dist/catalog/hive/flinkx-catalog-master.jar"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        CatalogFactory legacyFactory =
                TableFactoryService.find(CatalogFactory.class, options, classLoader);

        Catalog catalog = legacyFactory.createCatalog(catalogName, options);


        //Catalog actualCatalog =
        //        FactoryUtil.createCatalog(
        //                catalogName, options, null, Thread.currentThread().getContextClassLoader());


        tableEnv.registerCatalog(catalogName, catalog);

        //String useCatalog = "use CATALOG flink_hive_catalog";
        //tableEnv.executeSql(useCatalog);
        // -----------------------------------------------------------------

        StatementSet statementSet = tableEnv.createStatementSet();


        String kafkaSource = "" +
                "CREATE TABLE if not exists kafka_source_city (\n" +
                "    id int,\n" +
                "    city string\n" +
                ") WITH (\n" +
                "      'connector' = 'kafka',\n" +
                "      'topic' = 'wujuan_city',\n" +
                "      'properties.bootstrap.servers' = '172.16.100.109:9092',\n" +
                "      'properties.group.id' = 'wujuan_consumer',\n" +
                "      'format' = 'json',\n" +
                "      'scan.startup.mode' = 'earliest-offset'\n" +
                "    )";
        // earliest-offset、group-offsets 、latest-offset 、timestamp 、specific-offsets
        tableEnv.executeSql(kafkaSource);



        //String selectSql = "select * from kafka_source_city";
        //TableResult tableResult = tableEnv.executeSql(selectSql);
        //tableResult.print();


        String SinkSQL = "" +
                "create table if not exists kafka_city_sink ( \n" +
                "    id int,\n" +
                "    city string\n" +
                ") WITH (\n" +
                "   'print-identifier' = 'sink >> ', \n" +
                "   'connector' = 'print' \n" +
                ")";
        tableEnv.executeSql(SinkSQL);

        //flink_hive_catalog.wujuan.
        String insertSql = "insert into kafka_city_sink select * from kafka_source_city";
        tableEnv.executeSql(insertSql);

        statementSet.addInsertSql(insertSql);

        //System.out.println(tableEnv.explainSql(insertSql));

        TableResult tableResult = statementSet.execute();

        try {
            tableResult.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }


        //try {
        //    tableEnv.execute("wujuan"); // 没有 insert 会报错
        //} catch (Exception e) {
        //    throw new RuntimeException(e);
        //}
    }







    @Test
    public void StreamSQLDemoDataGen(){

        String catalogName = "flink_hive_catalog";

        final Map<String, String> options = new HashMap<>();
        options.put("type", "hive");
        options.put("hive-conf-dir","/Users/lzq/Desktop/Projects/Flink/flink-1.12-catalog-demo/LocalTest/src/main/resources/conf");

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            ReflectUtil.invoke(classLoader,"addURL",new URL("file:///Users/lzq/Desktop/Projects/Flink/flink-1.12-catalog-demo/flinkx-dist/catalog/hive/flinkx-catalog-master.jar"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        CatalogFactory legacyFactory =
                TableFactoryService.find(CatalogFactory.class, options, classLoader);
        Catalog catalog = legacyFactory.createCatalog(catalogName, options);


        //List<String> strings = catalog.listDatabases();  ✖    要 先 registerCatalog 才能 list
        //try {
        //    List<String> aDefault = catalog.listTables("default");
        //} catch (DatabaseNotExistException e) {
        //    throw new RuntimeException(e);
        //}
        tableEnv.registerCatalog(catalogName, catalog);


        List<String> strings = catalog.listDatabases();
        try {
            List<String> aDefault = catalog.listTables("default");
        } catch (DatabaseNotExistException e) {
            throw new RuntimeException(e);
        }


        //String useCatalog = "use CATALOG flink_hive_catalog";
        //tableEnv.executeSql(useCatalog);
        // -----------------------------------------------------------------

        // 单独创建 wujuan1
        //tableEnv.executeSql("create database flink_hive_catalog.wujuan1");

        StatementSet statementSet = tableEnv.createStatementSet();

        // create temporary table ... 不会注册到里面
        //String dataGenSQL =
        //        "create  table flink_hive_catalog.wujuan.datagen ("
        //                + " id int,"
        //                + " name string,"
        //                + " age bigint, "
        //                + " primary key (id) not enforced "
        //                + ") with ("
        //                + "'connector'='datagen',"
        //                + "'rows-per-second'='5',"
        //                + "'fields.id.kind'='sequence',"
        //                + "'fields.id.start'='1',"
        //                + "'fields.id.end'='100',"
        //                + "'fields.name.kind'='random',"
        //                + "'fields.name.length'='3',"
        //                + "'fields.age.kind'='random',"
        //                + "'fields.age.min'='1',"
        //                + "'fields.age.max'='21' "
        //                +")";
        //tableEnv.executeSql(dataGenSQL);
        //
        //tableEnv.executeSql("drop table flink_hive_catalog.wujuan.datagen_result");
        //
        //String SinkSQL = "" +
        //        "create table if not exists flink_hive_catalog.wujuan.datagen_result ( \n" +
        //        "    age bigint,\n" +
        //        "    age_cnt bigint\n" +
        //        ") WITH (\n" +
        //        "   'print-identifier' = 'datagen_result', \n" +
        //        "   'connector' = 'print' \n" +
        //        ")";
        //tableEnv.executeSql(SinkSQL);



        String insertSql = "insert into flink_hive_catalog.wujuan.datagen_result select age, count(*) as age_cnt from flink_hive_catalog.wujuan.datagen group by age";

        //System.out.println(tableEnv.explainSql(insertSql));
        statementSet.addInsertSql(insertSql);
        TableResult tableResult = statementSet.execute();


        try {
            tableResult.await();
            LOG.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }





    @Test
    public void StreamSQLDemoDataGenSQLCreateCatalog(){

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            ReflectUtil.invoke(classLoader,"addURL",new URL("file:///Users/lzq/Desktop/Projects/Flink/flink-1.12-catalog-demo/flinkx-dist/catalog/hive/flinkx-catalog-master.jar"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        // 0. 初始化 catalog
        String hiveCatalog = "CREATE CATALOG flink_hive_catalog WITH (\n" +
                "    'type' = 'hive',\n" +
                "    'default-database' = 'wujuan',\n" +
                "    'hive-conf-dir' = '/Users/lzq/Desktop/Projects/Flink/flink-1.12-catalog-demo/LocalTest/src/main/resources/conf'\n" +
                ")";
        tableEnv.executeSql(hiveCatalog);


        Optional<Catalog> flink_hive_catalog = tableEnv.getCatalog("flink_hive_catalog");

        List<String> strings = flink_hive_catalog.get().listDatabases();
        strings.forEach( db -> {
            System.out.println("------------------------------------------ " + db + " -------------------------------------------");
            List<String> tables = null;
            try {
                tables = flink_hive_catalog.get().listTables(db);
            } catch (DatabaseNotExistException e) {
                throw new RuntimeException(e);
            }
            tables.forEach(t -> {
                System.out.println(t);
            });
        });




        // -----------------------------------------------------------------
        // 单独创建 wujuan1
        //tableEnv.executeSql("create database flink_hive_catalog.wujuan1");

        StatementSet statementSet = tableEnv.createStatementSet();
        String dataGenSQL =
                "create table flink_hive_catalog.wujuan.datagen1 ("
                        + " id int,"
                        + " name string,"
                        + " age bigint, "
                        + " primary key (id) not enforced "
                        + ") with ("
                        + "'connector'='datagen',"
                        + "'rows-per-second'='5',"
                        + "'fields.id.kind'='sequence',"
                        + "'fields.id.start'='1',"
                        + "'fields.id.end'='100',"
                        + "'fields.name.kind'='random',"
                        + "'fields.name.length'='3',"
                        + "'fields.age.kind'='random',"
                        + "'fields.age.min'='1',"
                        + "'fields.age.max'='21' "
                        +")";
        tableEnv.executeSql(dataGenSQL);

        //tableEnv.executeSql("drop table flink_hive_catalog.wujuan.datagen_result");
        //
        //String SinkSQL = "" +
        //        "create table if not exists flink_hive_catalog.wujuan.datagen_result ( \n" +
        //        "    age bigint,\n" +
        //        "    age_cnt bigint\n" +
        //        ") WITH (\n" +
        //        "   'print-identifier' = 'datagen_result', \n" +
        //        "   'connector' = 'print' \n" +
        //        ")";
        //tableEnv.executeSql(SinkSQL);
        //
        //
        //
        //String insertSql = "insert into flink_hive_catalog.wujuan.datagen_result select age, count(*) as age_cnt from flink_hive_catalog.wujuan.datagen group by age";
        //statementSet.addInsertSql(insertSql);
        //TableResult tableResult = statementSet.execute();
        //
        //
        //try {
        //    tableResult.await();
        //    LOG.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        //} catch (InterruptedException e) {
        //    throw new RuntimeException(e);
        //} catch (ExecutionException e) {
        //    throw new RuntimeException(e);
        //}
    }



    @Test
    public void StreamSQLDemoDataGenSQLCreateCatalog1(){

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            ReflectUtil.invoke(classLoader,"addURL",new URL("file:///Users/lzq/Desktop/Projects/Flink/flink-1.12-catalog-demo/flinkx-dist/catalog/hive/flinkx-catalog-master.jar"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        // 0. 初始化 catalog
        String hiveCatalog = "CREATE CATALOG flink_hive_catalog_11111 WITH (\n" +
                "    'type' = 'hive',\n" +
                "    'default-database' = 'wujuan',\n" +
                "    'hive-conf-dir' = '/Users/lzq/Desktop/Projects/Flink/flink-1.12-catalog-demo/LocalTest/src/main/resources/conf'\n" +
                ")";
        tableEnv.executeSql(hiveCatalog);


        Optional<Catalog> flink_hive_catalog_11111 = tableEnv.getCatalog("flink_hive_catalog_11111");

        List<String> strings = flink_hive_catalog_11111.get().listDatabases();
        strings.forEach( db -> {
            System.out.println("------------------------------------------ " + db + " -------------------------------------------");
            List<String> tables = null;
            try {
                tables = flink_hive_catalog_11111.get().listTables(db);
            } catch (DatabaseNotExistException e) {
                throw new RuntimeException(e);
            }
            tables.forEach(t -> {
                System.out.println(t);
            });
        });

        /*
        // -----------------------------------------------------------------
        // 单独创建 wujuan1
        //tableEnv.executeSql("create database flink_hive_catalog.wujuan1");

        StatementSet statementSet = tableEnv.createStatementSet();
        String dataGenSQL =
                "create table flink_hive_catalog.wujuan.datagen1 ("
                        + " id int,"
                        + " name string,"
                        + " age bigint, "
                        + " primary key (id) not enforced "
                        + ") with ("
                        + "'connector'='datagen',"
                        + "'rows-per-second'='5',"
                        + "'fields.id.kind'='sequence',"
                        + "'fields.id.start'='1',"
                        + "'fields.id.end'='100',"
                        + "'fields.name.kind'='random',"
                        + "'fields.name.length'='3',"
                        + "'fields.age.kind'='random',"
                        + "'fields.age.min'='1',"
                        + "'fields.age.max'='21' "
                        +")";
        tableEnv.executeSql(dataGenSQL);

        //tableEnv.executeSql("drop table flink_hive_catalog.wujuan.datagen_result");
        //
        //String SinkSQL = "" +
        //        "create table if not exists flink_hive_catalog.wujuan.datagen_result ( \n" +
        //        "    age bigint,\n" +
        //        "    age_cnt bigint\n" +
        //        ") WITH (\n" +
        //        "   'print-identifier' = 'datagen_result', \n" +
        //        "   'connector' = 'print' \n" +
        //        ")";
        //tableEnv.executeSql(SinkSQL);
        //
        //
        //
        //String insertSql = "insert into flink_hive_catalog.wujuan.datagen_result select age, count(*) as age_cnt from flink_hive_catalog.wujuan.datagen group by age";
        //statementSet.addInsertSql(insertSql);
        //TableResult tableResult = statementSet.execute();
        //
        //
        //try {
        //    tableResult.await();
        //    LOG.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        //} catch (InterruptedException e) {
        //    throw new RuntimeException(e);
        //} catch (ExecutionException e) {
        //    throw new RuntimeException(e);
        //}

        */
    }



}
