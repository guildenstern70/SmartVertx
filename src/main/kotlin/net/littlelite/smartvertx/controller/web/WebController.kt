/*
 * Project SmartVertx
 * Copyright (c) Alessio Saltarin, 2020.
 * MIT License
 */

package net.littlelite.smartvertx.controller.web

import io.vertx.core.http.HttpMethod
import io.vertx.ext.web.Router

class WebController(private val router: Router)
{
    fun set()
    {
        this.setIndexGet()
        this.setAnotherPageGet()
    }

    private fun setIndexGet()
    {
        val index = this.router.route(HttpMethod.GET, "/")
        index.handler { routingContext ->
            routingContext.put("version", "0.1")
            routingContext.next()
        }
    }

    private fun setAnotherPageGet()
    {
        val anotherPage = router.route(HttpMethod.GET, "/page")
        anotherPage.handler { routingContext ->
            routingContext.next()
        }
    }
}
