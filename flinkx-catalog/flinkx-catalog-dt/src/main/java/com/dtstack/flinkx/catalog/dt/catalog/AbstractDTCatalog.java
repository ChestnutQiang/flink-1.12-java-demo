/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dtstack.flinkx.catalog.dt.catalog;



import com.dtstack.flinkx.catalog.dt.table.DTDynamicTableFactory;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.flink.table.api.ValidationException;
import org.apache.flink.table.api.constraints.UniqueConstraint;
import org.apache.flink.table.catalog.*;
import org.apache.flink.table.catalog.exceptions.*;
import org.apache.flink.table.catalog.stats.CatalogColumnStatistics;
import org.apache.flink.table.catalog.stats.CatalogTableStatistics;
import org.apache.flink.table.expressions.Expression;
import org.apache.flink.table.factories.Factory;
import org.apache.flink.table.types.DataType;
import org.apache.flink.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;
import java.util.function.Predicate;

import static org.apache.flink.util.Preconditions.checkArgument;

/** Abstract catalog for any JDBC catalogs. */
public abstract class AbstractDTCatalog extends AbstractCatalog {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractDTCatalog.class);

    protected final String username;
    protected final String pwd;
    protected final String url;
    protected final String driver;

    protected final String defaultUrl;
    protected final String projectId;
    protected final String tenantId;

    protected Connection connection;
    protected QueryRunner queryRunner;

    public AbstractDTCatalog(
            String catalogName,
            String defaultDatabase,
            String username,
            String pwd,
            String url,
            String driver,
            String projectId,
            String tenantId) {
        super(catalogName, defaultDatabase);

        checkArgument(!StringUtils.isNullOrWhitespaceOnly(username));
        checkArgument(!StringUtils.isNullOrWhitespaceOnly(pwd));
        checkArgument(!StringUtils.isNullOrWhitespaceOnly(url));
        checkArgument(!StringUtils.isNullOrWhitespaceOnly(projectId));
        checkArgument(!StringUtils.isNullOrWhitespaceOnly(tenantId));

        DTCatalogUtils.validateJdbcUrl(url);

        this.username = username;
        this.pwd = pwd;
        //this.url = url.endsWith("/") ? url : url + "/";
        this.url = url;
        this.defaultUrl = this.url;
        this.driver = org.apache.commons.lang3.StringUtils.isNotBlank(driver) ? driver  : "com.mysql.jdbc.Driver";
        this.projectId = projectId;
        this.tenantId = tenantId;
    }

    @Override
    public void open() throws CatalogException {
        // test connection, fail early if we cannot connect to database
        try (Connection conn = DriverManager.getConnection(defaultUrl, username, pwd)) {
        } catch (SQLException e) {
            throw new ValidationException(
                    String.format("Failed connecting to %s via JDBC.", defaultUrl), e);
        }

        LOG.info("Catalog {} established connection to {}", getName(), defaultUrl);
    }

    @Override
    public void close() throws CatalogException {
        LOG.info("Catalog {} closing", getName());
    }

    // ----- getters ------

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return pwd;
    }

    public String getUrl() {
        return url;
    }

    public String getDriver() {
        return driver;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getTenantId() {
        return tenantId;
    }

    // ------ retrieve PK constraint ------

    protected Optional<UniqueConstraint> getPrimaryKey(
            DatabaseMetaData metaData, String schema, String table) throws SQLException {

        // According to the Javadoc of java.sql.DatabaseMetaData#getPrimaryKeys,
        // the returned primary key columns are ordered by COLUMN_NAME, not by KEY_SEQ.
        // We need to sort them based on the KEY_SEQ value.
        ResultSet rs = metaData.getPrimaryKeys(null, schema, table);

        Map<Integer, String> keySeqColumnName = new HashMap<>();
        String pkName = null;
        while (rs.next()) {
            String columnName = rs.getString("COLUMN_NAME");
            pkName = rs.getString("PK_NAME"); // all the PK_NAME should be the same
            int keySeq = rs.getInt("KEY_SEQ");
            keySeqColumnName.put(keySeq - 1, columnName); // KEY_SEQ is 1-based index
        }
        List<String> pkFields =
                Arrays.asList(new String[keySeqColumnName.size()]); // initialize size
        keySeqColumnName.forEach(pkFields::set);
        if (!pkFields.isEmpty()) {
            // PK_NAME maybe null according to the javadoc, generate an unique name in that case
            pkName = pkName == null ? "pk_" + String.join("_", pkFields) : pkName;
            return Optional.of(UniqueConstraint.primaryKey(pkName, pkFields));
        }
        return Optional.empty();
    }

    // ------ table factory ------

    @Override
    public Optional<Factory> getFactory() {
        return Optional.of(new DTDynamicTableFactory());
    }

    // ------ databases ------

    @Override
    public boolean databaseExists(String databaseName) throws CatalogException {
        checkArgument(!StringUtils.isNullOrWhitespaceOnly(databaseName));
        return listDatabases().contains(databaseName);
    }

    @Override
    public void createDatabase(String name, CatalogDatabase database, boolean ignoreIfExists)
            throws DatabaseAlreadyExistException, CatalogException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dropDatabase(String name, boolean ignoreIfNotExists, boolean cascade)
            throws DatabaseNotExistException, DatabaseNotEmptyException, CatalogException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void alterDatabase(String name, CatalogDatabase newDatabase, boolean ignoreIfNotExists)
            throws DatabaseNotExistException, CatalogException {
        throw new UnsupportedOperationException();
    }

    // ------ tables and views ------

    @Override
    public void dropTable(ObjectPath tablePath, boolean ignoreIfNotExists)
            throws TableNotExistException, CatalogException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void renameTable(ObjectPath tablePath, String newTableName, boolean ignoreIfNotExists)
            throws TableNotExistException, TableAlreadyExistException, CatalogException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void createTable(ObjectPath tablePath, CatalogBaseTable table, boolean ignoreIfExists)
            throws TableAlreadyExistException, DatabaseNotExistException, CatalogException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void alterTable(
            ObjectPath tablePath, CatalogBaseTable newTable, boolean ignoreIfNotExists)
            throws TableNotExistException, CatalogException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> listViews(String databaseName)
            throws DatabaseNotExistException, CatalogException {
        return Collections.emptyList();
    }

    // ------ partitions ------

    @Override
    public List<CatalogPartitionSpec> listPartitions(ObjectPath tablePath)
            throws TableNotExistException, TableNotPartitionedException, CatalogException {
        return Collections.emptyList();
    }

    @Override
    public List<CatalogPartitionSpec> listPartitions(
            ObjectPath tablePath, CatalogPartitionSpec partitionSpec)
            throws TableNotExistException, TableNotPartitionedException,
                    PartitionSpecInvalidException, CatalogException {
        return Collections.emptyList();
    }

    @Override
    public List<CatalogPartitionSpec> listPartitionsByFilter(
            ObjectPath tablePath, List<Expression> filters)
            throws TableNotExistException, TableNotPartitionedException, CatalogException {
        return Collections.emptyList();
    }

    @Override
    public CatalogPartition getPartition(ObjectPath tablePath, CatalogPartitionSpec partitionSpec)
            throws PartitionNotExistException, CatalogException {
        throw new PartitionNotExistException(getName(), tablePath, partitionSpec);
    }

    @Override
    public boolean partitionExists(ObjectPath tablePath, CatalogPartitionSpec partitionSpec)
            throws CatalogException {
        return false;
    }

    @Override
    public void createPartition(
            ObjectPath tablePath,
            CatalogPartitionSpec partitionSpec,
            CatalogPartition partition,
            boolean ignoreIfExists)
            throws TableNotExistException, TableNotPartitionedException,
                    PartitionSpecInvalidException, PartitionAlreadyExistsException,
                    CatalogException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dropPartition(
            ObjectPath tablePath, CatalogPartitionSpec partitionSpec, boolean ignoreIfNotExists)
            throws PartitionNotExistException, CatalogException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void alterPartition(
            ObjectPath tablePath,
            CatalogPartitionSpec partitionSpec,
            CatalogPartition newPartition,
            boolean ignoreIfNotExists)
            throws PartitionNotExistException, CatalogException {
        throw new UnsupportedOperationException();
    }

    // ------ functions ------

    @Override
    public List<String> listFunctions(String dbName)
            throws DatabaseNotExistException, CatalogException {
        return Collections.emptyList();
    }

    @Override
    public CatalogFunction getFunction(ObjectPath functionPath)
            throws FunctionNotExistException, CatalogException {
        throw new FunctionNotExistException(getName(), functionPath);
    }

    @Override
    public boolean functionExists(ObjectPath functionPath) throws CatalogException {
        return false;
    }

    @Override
    public void createFunction(
            ObjectPath functionPath, CatalogFunction function, boolean ignoreIfExists)
            throws FunctionAlreadyExistException, DatabaseNotExistException, CatalogException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void alterFunction(
            ObjectPath functionPath, CatalogFunction newFunction, boolean ignoreIfNotExists)
            throws FunctionNotExistException, CatalogException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dropFunction(ObjectPath functionPath, boolean ignoreIfNotExists)
            throws FunctionNotExistException, CatalogException {
        throw new UnsupportedOperationException();
    }

    // ------ stats ------

    @Override
    public CatalogTableStatistics getTableStatistics(ObjectPath tablePath)
            throws TableNotExistException, CatalogException {
        return CatalogTableStatistics.UNKNOWN;
    }

    @Override
    public CatalogColumnStatistics getTableColumnStatistics(ObjectPath tablePath)
            throws TableNotExistException, CatalogException {
        return CatalogColumnStatistics.UNKNOWN;
    }

    @Override
    public CatalogTableStatistics getPartitionStatistics(
            ObjectPath tablePath, CatalogPartitionSpec partitionSpec)
            throws PartitionNotExistException, CatalogException {
        return CatalogTableStatistics.UNKNOWN;
    }

    @Override
    public CatalogColumnStatistics getPartitionColumnStatistics(
            ObjectPath tablePath, CatalogPartitionSpec partitionSpec)
            throws PartitionNotExistException, CatalogException {
        return CatalogColumnStatistics.UNKNOWN;
    }

    @Override
    public void alterTableStatistics(
            ObjectPath tablePath, CatalogTableStatistics tableStatistics, boolean ignoreIfNotExists)
            throws TableNotExistException, CatalogException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void alterTableColumnStatistics(
            ObjectPath tablePath,
            CatalogColumnStatistics columnStatistics,
            boolean ignoreIfNotExists)
            throws TableNotExistException, CatalogException, TablePartitionedException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void alterPartitionStatistics(
            ObjectPath tablePath,
            CatalogPartitionSpec partitionSpec,
            CatalogTableStatistics partitionStatistics,
            boolean ignoreIfNotExists)
            throws PartitionNotExistException, CatalogException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void alterPartitionColumnStatistics(
            ObjectPath tablePath,
            CatalogPartitionSpec partitionSpec,
            CatalogColumnStatistics columnStatistics,
            boolean ignoreIfNotExists)
            throws PartitionNotExistException, CatalogException {
        throw new UnsupportedOperationException();
    }

    protected DataType fromJDBCType(ObjectPath tablePath, ResultSetMetaData metadata, int colIndex)
            throws SQLException {
        throw new UnsupportedOperationException();
    }

    protected String getTableName(ObjectPath tablePath) {
        throw new UnsupportedOperationException();
    }

    protected String getSchemaName(ObjectPath tablePath) {
        throw new UnsupportedOperationException();
    }

    protected String getSchemaTableName(ObjectPath tablePath) {
        throw new UnsupportedOperationException();
    }
    protected List<String> extractColumnValuesBySQL(
            String connUrl,
            String sql,
            int columnIndex,
            Predicate<String> filterFunc,
            Object... params) {

        List<String> columnValues = Lists.newArrayList();

        try (Connection conn = DriverManager.getConnection(connUrl, username, pwd);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            if (Objects.nonNull(params) && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String columnValue = rs.getString(columnIndex);
                if (Objects.isNull(filterFunc) || filterFunc.test(columnValue)) {
                    columnValues.add(columnValue);
                }
            }
            return columnValues;
        } catch (Exception e) {
            throw new CatalogException(
                    String.format(
                            "The following SQL query could not be executed (%s): %s", connUrl, sql),
                    e);
        }
    }

    protected List<String> extractColumnValuesBySQLWithTransaction(
            String connUrl,
            List<String> sql,
            int columnIndex,
            Predicate<String> filterFunc,
            Object... params) {

        List<String> columnValues = Lists.newArrayList();

        Connection conn = openConnection(connUrl);
        try {
            // 关闭自动提交:
            conn.setAutoCommit(false);
            // 执行多条SQL语句:
            columnValues = executeSQL(conn, sql, columnIndex, filterFunc, params);
            // 提交事务:
            conn.commit();

            return columnValues;
        } catch (SQLException e) {
            // 回滚事务:
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return columnValues;
    }

    private Connection openConnection(String connUrl) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connUrl, username, pwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    private List<String> executeSQL(
            Connection conn,
            List<String> sqls,
            int columnIndex,
            Predicate<String> filterFunc,
            Object[] params) {

        List<String> columnValues = Lists.newArrayList();

        for (int index = 0; index < sqls.size(); index++) {
            String sql = sqls.get(index);
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                if (Objects.nonNull(params) && params.length > 0) {
                    for (int i = 0; i < params.length; i++) {
                        ps.setObject(i + 1, params[i]);
                    }
                }
                ResultSet rs = ps.executeQuery();
                // 最后一调，返回结果。
                if (index == sqls.size() - 1) {
                    while (rs.next()) {
                        String columnValue = rs.getString(columnIndex);
                        if (Objects.isNull(filterFunc) || filterFunc.test(columnValue)) {
                            columnValues.add(columnValue);
                        }
                    }
                    return columnValues;
                }

            } catch (Exception e) {
                try {
                    throw new CatalogException(
                            String.format(
                                    "The following SQL query could not be executed (%s): %s",
                                    conn.getMetaData().getURL(), sql),
                            e);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        return columnValues;
    }
}
