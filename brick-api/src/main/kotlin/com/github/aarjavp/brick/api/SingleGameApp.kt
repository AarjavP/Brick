package com.github.aarjavp.brick.api

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import io.javalin.Javalin
import io.javalin.http.Context
import java.util.Scanner

class SingleGameApp: CliktCommand() {
    val nonInteractive: Boolean by option("--non-interactive",
                                          help = "use this when running in docker"
    ).flag(default = false)

    val port: Int by option("-p", "--port", help = "port to listen on for requests").int().default(2233)

    override fun run() {
        val app = Javalin.create { config ->
            config.enableCorsForAllOrigins()
        }.start(port)
        try {
            app.get("/board") { ctx: Context ->
                ctx.result("TODO :)")
            }
            if (nonInteractive) {
                dontReturnUtilShutdown()
            } else {
                dontReturnUntilQuitInput()
            }
            println("Shutting down!!")
        } finally {
            app.stop()
        }
    }
}

fun main(args: Array<String>) = SingleGameApp().main(args)

fun dontReturnUtilShutdown() {
    val stick = java.lang.Object()
    val stickNotifier = Thread(Runnable { synchronized(stick) { stick.notifyAll() } })
    Runtime.getRuntime().addShutdownHook(stickNotifier)
    synchronized(stick) {
        try {
            stick.wait()
        } catch (ignored: InterruptedException) {
        }
    }
}

fun dontReturnUntilQuitInput() {
    val scanner = Scanner(System.`in`)
    while (scanner.hasNextLine()) {
        val line = scanner.nextLine()
        if (line.equals("quit", ignoreCase = true)) break
    }
}
