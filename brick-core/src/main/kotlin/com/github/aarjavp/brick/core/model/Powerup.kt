package com.github.aarjavp.brick.core.model

interface Powerup {
    fun applyTo(pieceStats: PieceStats): PieceStats
}

class LoSPowerup(): Powerup {
    override fun applyTo(pieceStats: PieceStats): PieceStats
            = pieceStats.copy(lineOfSight = pieceStats.lineOfSight + 1)
}