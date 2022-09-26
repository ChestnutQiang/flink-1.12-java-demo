package com.dtstack.flinkx.catalog.dt.catalog;

@FunctionalInterface
public interface ThrowingRunnable<E extends Exception> {
    void run() throws E;
}
