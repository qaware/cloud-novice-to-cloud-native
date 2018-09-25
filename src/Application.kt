package de.qaware.ada.lovelace

import io.ktor.application.Application

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.routing.*
import kotlinx.html.*
import java.net.InetAddress

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)

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
            call.respondHtml {
                body {
                    val ip = InetAddress.getLocalHost()
                    val hostname = ip.hostName
                    p { +"I am running on host: $hostname" }
                }
            }
        }
        get("/metrics") {
            call.respondHtml {
                body {
                    val runtime = Runtime.getRuntime()
                    p { +"Just giving you some inside information on my runtime:" }
                    p { +"Available Processors: ${runtime.availableProcessors()}" }
                    p { +"Free Memory: ${runtime.freeMemory()}" }
                    p { +"Total Memory: ${runtime.totalMemory()}" }
                    p { +"Max Memory: ${runtime.maxMemory()}" }
                    p { +"Internally used username: ${System.getProperty("user.name")}" }
                }
            }
        }
    }
}