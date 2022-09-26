package com.dtstack.flinkx.catalog.jdbc.catalog;

@FunctionalInterface
public interface ThrowingRunnable<E extends Exception> {
    void run() throws E;
}
