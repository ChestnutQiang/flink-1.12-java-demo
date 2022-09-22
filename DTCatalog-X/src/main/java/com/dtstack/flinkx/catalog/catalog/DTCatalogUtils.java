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

package com.dtstack.flinkx.catalog.catalog;

import com.dtstack.flinkx.catalog.dialect.DTDialect;
import com.dtstack.flinkx.catalog.dialect.DTDialects;
import com.dtstack.flinkx.catalog.dialect.MySQLDialect;
import com.dtstack.flinkx.catalog.dialect.PostgresDialect;

import static org.apache.flink.util.Preconditions.checkArgument;

/** Utils for {@link DTCatalog}. */
public class DTCatalogUtils {
    /**
     * URL has to be without database, like "jdbc:postgresql://localhost:5432/" or
     * "jdbc:postgresql://localhost:5432" rather than "jdbc:postgresql://localhost:5432/db".
     */
    public static void validateJdbcUrl(String url) {
        String[] parts = url.trim().split("\\/+");

        checkArgument(parts.length == 2);
    }

    /** Create catalog instance from given information. */
    public static AbstractDTCatalog createCatalog(
            String catalogName,
            String defaultDatabase,
            String username,
            String pwd,
            String baseUrl) {
        DTDialect dialect = DTDialects.get(baseUrl).get();

        if (dialect instanceof PostgresDialect) {
            return new PostgresCatalog(catalogName, defaultDatabase, username, pwd, baseUrl);
        } else if (dialect instanceof MySQLDialect) {
            return new MySqlCatalog(catalogName, defaultDatabase, username, pwd, baseUrl);
        } else {
            throw new UnsupportedOperationException(
                    String.format("Catalog for '%s' is not supported yet.", dialect));
        }
    }
}