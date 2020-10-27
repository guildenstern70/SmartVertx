/*
 * Project SmartVertx
 * Copyright (c) Alessio Saltarin, 2020.
 * MIT License
 */

package net.littlelite.smartvertx.config

import io.vertx.core.logging.LoggerFactory

object LoggerConfig
{
    fun set()
    {
        System.setProperty("vertx.logger-delegate-factory-class-name", "io.vertx.core.logging.SLF4JLogDelegateFactory")
        LoggerFactory.initialise()
    }
}
