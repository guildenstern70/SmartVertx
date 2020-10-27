/*
 * Project SmartVertx
 * Copyright (c) Alessio Saltarin, 2020.
 * MIT License
 */

package net.littlelite.smartvertx

import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import net.littlelite.smartvertx.config.LoggerConfig
import net.littlelite.smartvertx.config.WebConfig

class MainVerticle : AbstractVerticle()
{
    private val logger: Logger = LoggerFactory.getLogger(MainVerticle::class.java)

    override fun start(startPromise: Promise<Void>)
    {
        logger.info("Starting Verticle...")
        val vertx = this.vertx
        LoggerConfig.set()
        WebConfig.set(vertx)
        logger.info("... done.")
    }

}
