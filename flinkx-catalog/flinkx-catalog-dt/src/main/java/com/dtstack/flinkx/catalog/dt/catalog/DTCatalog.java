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

import org.apache.flink.annotation.PublicEvolving;
import org.apache.flink.annotation.VisibleForTesting;
import org.apache.flink.table.catalog.CatalogBaseTable;
import org.apache.flink.table.catalog.CatalogDatabase;
import org.apache.flink.table.catalog.ObjectPath;
import org.apache.flink.table.catalog.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/** Catalogs for relational databases via JDBC. */
@PublicEvolving
public class DTCatalog extends AbstractDTCatalog {

    private static final Logger LOG = LoggerFactory.getLogger(DTCatalog.class);

    private final AbstractDTCatalog internal;

    public DTCatalog(
            String catalogName,
            String defaultDatabase,
            String username,
            String pwd,
            String url,
            String driver,
            String projectId,
            String tenantId
            ) {
        super(catalogName, defaultDatabase, username, pwd, url, driver, projectId, tenantId);
        internal =
                DTCatalogUtils.createCatalog(
                        catalogName, defaultDatabase, username, pwd, url, driver, projectId, tenantId);
    }

    @Override
    public void open() throws CatalogException {
        //super.open();
        internal.open();
    }

    @Override
    public void close() throws CatalogException {
        super.close();
        internal.close();
    }

    // ------ databases -----

    @Override
    public List<String> listDatabases() throws CatalogException {
        return internal.listDatabases();
    }

    @Override
    public CatalogDatabase getDatabase(String databaseName)
            throws DatabaseNotExistException, CatalogException {
        return internal.getDatabase(databaseName);
    }

    @Override
    public void createDatabase(String name, CatalogDatabase database, boolean ignoreIfExists)
            throws DatabaseAlreadyExistException, CatalogException {
        internal.createDatabase(name, database, ignoreIfExists);
    }

    @Override
    public void dropDatabase(String name, boolean ignoreIfNotExists, boolean cascade)
            throws DatabaseNotExistException, DatabaseNotEmptyException, CatalogException {
        internal.dropDatabase(name, ignoreIfNotExists, cascade);
    }

    // ------ tables and views ------

    @Override
    public List<String> listTables(String databaseName)
            throws DatabaseNotExistException, CatalogException {
        return internal.listTables(databaseName);
    }

    @Override
    public CatalogBaseTable getTable(ObjectPath tablePath)
            throws TableNotExistException, CatalogException {
        return internal.getTable(tablePath);
    }

    @Override
    public void dropTable(ObjectPath tablePath, boolean ignoreIfNotExists)
            throws TableNotExistException, CatalogException {
        internal.dropTable(tablePath, ignoreIfNotExists);
    }

    @Override
    public void renameTable(ObjectPath tablePath, String newTableName, boolean ignoreIfNotExists)
            throws TableNotExistException, TableAlreadyExistException, CatalogException {
        internal.renameTable(tablePath, newTableName, ignoreIfNotExists);
    }

    @Override
    public void alterTable(
            ObjectPath tablePath, CatalogBaseTable newTable, boolean ignoreIfNotExists)
            throws TableNotExistException, CatalogException {
        internal.alterTable(tablePath, newTable, ignoreIfNotExists);
    }

    @Override
    public boolean tableExists(ObjectPath tablePath) throws CatalogException {
        try {
            return databaseExists(tablePath.getDatabaseName())
                    && listTables(tablePath.getDatabaseName()).contains(tablePath.getObjectName());
        } catch (DatabaseNotExistException e) {
            return false;
        }
    }

    @Override
    public void createTable(ObjectPath tablePath, CatalogBaseTable table, boolean ignoreIfExists) throws TableAlreadyExistException, DatabaseNotExistException, CatalogException {
        internal.createTable(tablePath, table, ignoreIfExists);
    }

    // ------ getters ------

    @VisibleForTesting
    public AbstractDTCatalog getInternal() {
        return internal;
    }

}
