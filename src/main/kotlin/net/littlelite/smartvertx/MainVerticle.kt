package net.littlelite.smartvertx

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.core.http.HttpServer
import io.vertx.core.http.HttpServerResponse
import io.vertx.core.logging.LoggerFactory as VertxLogger
import io.vertx.ext.web.Router

class MainVerticle : AbstractVerticle()
{
    private val logger: Logger = LoggerFactory.getLogger(MainVerticle::class.java)

    private fun loggerConfig()
    {
        System.setProperty("vertx.logger-delegate-factory-class-name", "io.vertx.core.logging.SLF4JLogDelegateFactory");
        VertxLogger.initialise();
    }

    private fun webConfig()
    {
        val server: HttpServer = vertx.createHttpServer()
        val router: Router = Router.router(vertx)
        val port = 8080
        router.route().handler { routingContext ->

            // This handler will be called for every request
            val response: HttpServerResponse = routingContext.response()
            response.putHeader("content-type", "text/plain")

            // Write to the response and end it
            response.end("Hello World from Vert.x-Web!")
        }
        server.requestHandler(router).listen(port)
        logger.info("Serving http://localhost:$port")

    }
    override fun start(startPromise: Promise<Void>)
    {
        this.loggerConfig()
        this.webConfig()
    }

}
