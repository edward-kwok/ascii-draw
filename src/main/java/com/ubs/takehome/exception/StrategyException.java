package com.ubs.takehome.exception;

/**
 * Parent of all Line, Canvas , Rectangle and Bucket Exception
 *
 */
public class StrategyException extends Exception {

    public StrategyException(String format) {
        super(format);
    }

}
