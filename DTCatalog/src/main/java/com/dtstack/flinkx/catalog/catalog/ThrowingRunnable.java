package com.dtstack.flinkx.catalog.catalog;

@FunctionalInterface
public interface ThrowingRunnable<E extends Exception> {
    void run() throws E;
}
