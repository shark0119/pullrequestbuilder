package com.shark.commom;

import org.apache.log4j.Logger;

/**
 * 获取 Logger 类
 *
 * @author shark
 */
public abstract class AbstractLogable {
    protected static Logger logger;

    public AbstractLogable() {
        logger = Logger.getLogger(this.getClass());
    }
}