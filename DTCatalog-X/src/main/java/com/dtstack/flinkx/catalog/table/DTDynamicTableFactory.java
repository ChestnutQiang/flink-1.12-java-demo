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

package com.dtstack.flinkx.catalog.table;

import org.apache.flink.annotation.Internal;
import org.apache.flink.configuration.ConfigOption;
import org.apache.flink.table.api.ValidationException;
import org.apache.flink.table.catalog.CatalogTable;
import org.apache.flink.table.catalog.config.CatalogConfig;
import org.apache.flink.table.connector.sink.DynamicTableSink;
import org.apache.flink.table.connector.source.DynamicTableSource;
import org.apache.flink.table.factories.DynamicTableSinkFactory;
import org.apache.flink.table.factories.DynamicTableSourceFactory;
import org.apache.flink.table.factories.FactoryUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.apache.flink.table.catalog.config.CatalogConfig.IS_GENERIC;
import static org.apache.flink.table.factories.FactoryUtil.CONNECTOR;

/**
 * Factory for creating configured instances of {@link JdbcDynamicTableSource} and {@link
 * JdbcDynamicTableSink}.
 */
@Internal
public class DTDynamicTableFactory implements DynamicTableSourceFactory, DynamicTableSinkFactory {

    @Override
    public DynamicTableSource createDynamicTableSource(Context context) {
        final String connectorOption = context.getCatalogTable().getOptions().get(CONNECTOR.key());
        if (connectorOption == null) {
            throw new ValidationException(
                    String.format(
                            "Table options do not contain an option key '%s' for discovering a connector.",
                            CONNECTOR.key()));
        }
        return FactoryUtil.discoverFactory(
                        context.getClassLoader(), DynamicTableSourceFactory.class, connectorOption)
                .createDynamicTableSource(context);
    }

    @Override
    public DynamicTableSink createDynamicTableSink(Context context) {
        final String connectorOption = context.getCatalogTable().getOptions().get(CONNECTOR.key());
        if (connectorOption == null) {
            throw new ValidationException(
                    String.format(
                            "Table options do not contain an option key '%s' for discovering a connector.",
                            CONNECTOR.key()));
        }
        return FactoryUtil.discoverFactory(
                        context.getClassLoader(), DynamicTableSinkFactory.class, connectorOption)
                .createDynamicTableSink(context);
    }

    @Override
    public String factoryIdentifier() {
        throw new UnsupportedOperationException("DT factory is only work for catalog.");
    }

    @Override
    public Set<ConfigOption<?>> requiredOptions() {
        throw new UnsupportedOperationException("DT factory is only work for catalog.");
    }

    @Override
    public Set<ConfigOption<?>> optionalOptions() {
        throw new UnsupportedOperationException("DT factory is only work for catalog.");
    }
}
