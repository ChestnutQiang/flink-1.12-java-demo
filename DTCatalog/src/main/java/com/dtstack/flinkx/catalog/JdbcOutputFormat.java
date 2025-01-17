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

package com.dtstack.flinkx.catalog;

import com.dtstack.flinkx.catalog.jdbc.internal.JdbcBatchingOutputFormat;
import com.dtstack.flinkx.catalog.jdbc.internal.connection.JdbcConnectionProvider;
import com.dtstack.flinkx.catalog.jdbc.internal.connection.SimpleJdbcConnectionProvider;
import com.dtstack.flinkx.catalog.jdbc.internal.executor.DTBatchStatementExecutor;
import org.apache.flink.annotation.Experimental;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.types.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

import static com.dtstack.flinkx.catalog.jdbc.utils.JdbcUtils.setRecordToStatement;

/**
 * OutputFormat to write Rows into a JDBC database. The OutputFormat has to be configured using the
 * supplied OutputFormatBuilder.
 */
@Experimental
public class JdbcOutputFormat
        extends JdbcBatchingOutputFormat<Row, Row, DTBatchStatementExecutor<Row>> {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(JdbcOutputFormat.class);

    private JdbcOutputFormat(
            JdbcConnectionProvider connectionProvider,
            String sql,
            int[] typesArray,
            int batchSize) {
        super(
                connectionProvider,
                new JdbcExecutionOptions.Builder().withBatchSize(batchSize).build(),
                ctx -> createRowExecutor(sql, typesArray, ctx),
                JdbcBatchingOutputFormat.RecordExtractor.identity());
    }

    private static DTBatchStatementExecutor<Row> createRowExecutor(
            String sql, int[] typesArray, RuntimeContext ctx) {
        JdbcStatementBuilder<Row> statementBuilder =
                (st, record) -> setRecordToStatement(st, typesArray, record);
        return DTBatchStatementExecutor.simple(
                sql,
                statementBuilder,
                ctx.getExecutionConfig().isObjectReuseEnabled() ? Row::copy : Function.identity());
    }

    public static JdbcOutputFormatBuilder buildJdbcOutputFormat() {
        return new JdbcOutputFormatBuilder();
    }

    /** Builder for {@link JdbcOutputFormat}. */
    public static class JdbcOutputFormatBuilder {
        private String username;
        private String password;
        private String drivername;
        private String dbURL;
        private String query;
        private int batchSize = JdbcExecutionOptions.DEFAULT_SIZE;
        private int[] typesArray;

        private JdbcOutputFormatBuilder() {}

        public JdbcOutputFormatBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public JdbcOutputFormatBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public JdbcOutputFormatBuilder setDrivername(String drivername) {
            this.drivername = drivername;
            return this;
        }

        public JdbcOutputFormatBuilder setDBUrl(String dbURL) {
            this.dbURL = dbURL;
            return this;
        }

        public JdbcOutputFormatBuilder setQuery(String query) {
            this.query = query;
            return this;
        }

        public JdbcOutputFormatBuilder setBatchSize(int batchSize) {
            this.batchSize = batchSize;
            return this;
        }

        public JdbcOutputFormatBuilder setSqlTypes(int[] typesArray) {
            this.typesArray = typesArray;
            return this;
        }

        /**
         * Finalizes the configuration and checks validity.
         *
         * @return Configured JdbcOutputFormat
         */
        public JdbcOutputFormat finish() {
            return new JdbcOutputFormat(
                    new SimpleJdbcConnectionProvider(buildConnectionOptions()),
                    query,
                    typesArray,
                    batchSize);
        }

        public JdbcConnectionOptions buildConnectionOptions() {
            if (this.username == null) {
                LOG.info("Username was not supplied.");
            }
            if (this.password == null) {
                LOG.info("Password was not supplied.");
            }

            return new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
                    .withUrl(dbURL)
                    .withDriverName(drivername)
                    .withUsername(username)
                    .withPassword(password)
                    .build();
        }
    }
}
