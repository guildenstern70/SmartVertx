/*
 * Project SmartVertx
 * Copyright (c) Alessio Saltarin, 2020.
 * MIT License
 */

package net.littlelite.smartvertx.config

import io.vertx.core.Vertx
import io.vertx.core.http.HttpServer
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.LoggerHandler
import io.vertx.ext.web.handler.TemplateHandler
import io.vertx.ext.web.templ.freemarker.FreeMarkerTemplateEngine
import net.littlelite.smartvertx.controller.web.WebController
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object WebConfig
{
    private val logger: Logger = LoggerFactory.getLogger(WebConfig::class.java)

    fun set(vertx: Vertx)
    {
        System.setProperty("vertxweb.environment", "dev")
        val server: HttpServer = vertx.createHttpServer()
        val router: Router = Router.router(vertx)
        val port = 8080

        // Logger Handler
        router.get("/*").handler(LoggerHandler.create())
        router.post("/*").handler(LoggerHandler.create())

        // Controllers
        val controllers = WebController(router)
        controllers.set()

        // Template Engine
        val engine = FreeMarkerTemplateEngine.create(vertx)
        val handler = TemplateHandler.create(engine)
        router.get("/*").handler(handler)

        server.requestHandler(router).listen(port)
        logger.info("Serving http://localhost:$port")

    }

}
