package com.github.aarjavp.brick.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.versionOption

class SingleHotSeatGame: CliktCommand() {
    init {
        versionOption("0.1.0")
    }
    override fun run() {
        println("Hello world!")
    }
}

fun main(args: Array<String>) = SingleHotSeatGame().main(args)
