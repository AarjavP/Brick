package com.github.aarjavp.brick.core.model

import java.time.Duration
import java.time.Instant

open class Piece(val id: String, val playerID: String, val team: String, val baseStats: PieceStats): Occupant {
    private val powerups: MutableList<Powerup> = mutableListOf()
    var lastMoved: Instant = Instant.now()
    var resultStats: PieceStats = baseStats
    fun resolveStats() {resultStats = powerups.fold(baseStats) { stats, powerup -> powerup.applyTo(stats) }}

    fun addPowerup(powerup: Powerup) = powerups.add(powerup).also { resolveStats() }
    fun getPowerups(): List<Powerup> = powerups
    fun removeFirstPowerupIf(predicate: (Powerup) -> Boolean) {
        val iter = powerups.iterator()
        while (iter.hasNext()) {
            val next = iter.next()
            if (predicate(next)) {
                iter.remove()
                break
            }
        }
        resolveStats()
    }
}



data class PieceStats(val lineOfSight: Int, val cooldown: Duration, val maxDistance: Int)
object BaseStats {
    val kingStat: PieceStats = PieceStats(7, Duration.ofMillis(1000), 1)
    val bishopStat: PieceStats = PieceStats(7, Duration.ofMillis(800), 7)
    val rookStat: PieceStats = PieceStats(7, Duration.ofMillis(800), 7)
    val queenStat: PieceStats = PieceStats(7, Duration.ofMillis(1200), 7)
    val pawnStat: PieceStats = PieceStats(7, Duration.ofMillis(500), 1)
    val knightStat: PieceStats = PieceStats(7, Duration.ofMillis(1000), 3)
}

class King(id: String, playerID: String, team: String): Piece(id, playerID, team, BaseStats.kingStat)
class Knight(id: String, playerID: String, team: String): Piece(id, playerID, team, BaseStats.knightStat)
class Bishop(id: String, playerID: String, team: String): Piece(id, playerID, team, BaseStats.bishopStat)
class Rook(id: String, playerID: String, team: String): Piece(id, playerID, team, BaseStats.rookStat)
class Queen(id: String, playerID: String, team: String): Piece(id, playerID, team, BaseStats.queenStat)
class Pawn(id: String, playerID: String, team: String): Piece(id, playerID, team, BaseStats.pawnStat)