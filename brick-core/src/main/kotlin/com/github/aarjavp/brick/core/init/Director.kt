package com.github.aarjavp.brick.core.init

import com.github.aarjavp.brick.core.model.*
import kotlin.math.roundToInt
import kotlin.random.Random

fun main() {
   val players = setOf<Player>(
           Player("1", "honk"),
           Player("2", "kehgan"),
           Player("3", "sammywhammy")
   )
    val game = Game("1", players, Board(players.size*2, players.size*2))
}

class Director(val percentItems: Double, val itemGenerator: ItemGenerator){

    init {
        itemGenerator.itemConstructors.add { DummyItem("test") }
    }

    @ExperimentalStdlibApi
    fun init(game: Game) {
        val occupants = buildList<Occupant> {
            for (player in game.players) {
                add(King(player.id))
            }

            val boardSize: Int = game.board.height*game.board.width
            val availableSpaces = boardSize - game.players.size
            val numItems: Int = (availableSpaces*percentItems).roundToInt()
            val listOfItems: List<Item> = itemGenerator.getItems(numItems)
            addAll(listOfItems)
        }

        val randomLocations = generateSequence {
            Coordinate(Random.nextInt(game.board.width), Random.nextInt(game.board.height))
        }

        for (occupant in occupants) {
            val location = randomLocations.filter { game.board[it] == null }.first()
            game.board.set(location, occupant)
        }
    }
}