package net.littlelite.smartvertx

import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.core.http.HttpServer
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.LoggerHandler
import io.vertx.ext.web.handler.TemplateHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import io.vertx.core.logging.LoggerFactory as VertxLogger
import io.vertx.ext.web.templ.freemarker.FreeMarkerTemplateEngine

class MainVerticle : AbstractVerticle()
{
    private val logger: Logger = LoggerFactory.getLogger(MainVerticle::class.java)

    private fun loggerConfig()
    {
        System.setProperty("vertx.logger-delegate-factory-class-name", "io.vertx.core.logging.SLF4JLogDelegateFactory")
        VertxLogger.initialise()
    }

    private fun webConfig()
    {
        System.setProperty("vertxweb.environment", "dev")
        val server: HttpServer = vertx.createHttpServer()
        val router: Router = Router.router(vertx)
        val port = 8080

        // Logger Handler
        router.get("/*").handler(LoggerHandler.create())
        router.post("/*").handler(LoggerHandler.create())

        // Controllers
        val index = router.route().path("/")
        val anotherPage = router.route().path("/page")
        index.handler { routingContext ->
            routingContext.put("version", "0.1")
            routingContext.next()
        }
        anotherPage.handler { routingContext ->
            routingContext.next()
        }

        // Template Engine
        val engine = FreeMarkerTemplateEngine.create(vertx)
        val handler = TemplateHandler.create(engine)
        router.get("/*").handler(handler)

        server.requestHandler(router).listen(port)
        logger.info("Serving http://localhost:$port")

    }

    override fun start(startPromise: Promise<Void>)
    {
        this.loggerConfig()
        this.webConfig()
    }

}
