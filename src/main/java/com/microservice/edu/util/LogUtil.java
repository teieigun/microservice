package com.microservice.edu.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
    protected final static Logger logger = LoggerFactory.getLogger(LogUtil.class);

    public static void info(String msg) {
        logger.info(msg);
    }
    public static void warn(String msg) {
        logger.warn(msg);
    }
    public static void error(String msg) {
        logger.error(msg);
    }
    public static void debug(String msg) {
        logger.debug(msg);
    }
    public static void trace(String msg) {
        logger.trace(msg);
    }

    public static void info(String msg, Throwable t) {
        logger.info(msg, t);
    }
    public static void warn(String msg, Throwable t) {
        logger.warn(msg, t);
    }
    public static void error(String msg, Throwable t) {
        logger.error(msg, t);
    }
    public static void debug(String msg, Throwable t) {
        logger.debug(msg, t);
    }
    public static void trace(String msg, Throwable t) {
        logger.trace(msg, t);
    }
}
