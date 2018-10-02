package de.qaware.ada.lovelace

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.html.respondHtml
import io.ktor.jackson.jackson
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import kotlinx.html.body
import kotlinx.html.p
import java.net.InetAddress

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        jackson {
        }
    }

    routing {
        get("/hi") {
            call.respondHtml {
                body {
                    p { +"Hi there!" }
                }
            }
        }
        get("/hi/{name}") {
            call.respondHtml {
                body {
                    val name = call.parameters["name"]
                    p { +"Hi $name!" }
                }
            }
        }
        get("/host") {
            val ip = InetAddress.getLocalHost()
            call.respond(
                    mapOf(
                            "Host name" to ip.hostName,
                            "Host address" to ip.hostAddress)
            )
        }
        get("/metrics") {
            val runtime = Runtime.getRuntime()
            call.respond(
                    mapOf(
                            "Max Memory" to runtime.totalMemory(),
                            "Free Memory" to runtime.freeMemory(),
                            "Total Memory" to runtime.totalMemory(),
                            "Available Processors" to runtime.availableProcessors(),
                            "Internally used username" to System.getProperty("user.name")))
        }
    }
}
