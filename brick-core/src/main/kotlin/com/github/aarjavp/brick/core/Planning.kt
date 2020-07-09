package com.github.aarjavp.brick.core

import java.time.Instant


interface PiecePowerup
interface PassivePiecePowerup: PiecePowerup
interface ActivePiecePowerup: PiecePowerup

interface PlayerPowerup
interface PassivePlayerPowerup: PlayerPowerup
interface ActivePlayerPowerup: PlayerPowerup

interface Player {
    val id: String
    val pieces: Set<Piece>
    val powerups: List<PlayerPowerup>
}

interface Board {
    val piecesByLocation: Map<Location, Piece>
    val itemsByLocation: Map<Location, Piece>
    val width: Int
    val height: Int
    var playableArea: LocationArea
    var reducePlayableAreaAt: Instant
}

interface LocationArea {
    val center: Location
    val radius: Int
}

interface Piece {
    var location: Location
    var canMoveAgainAt: Instant
    val powerups: List<PiecePowerup>
}

interface Location {
    val x: Int
    val y: Int
}

interface Engine {
    val board: Board
    val players: List<Player>
    fun initBoard()
}

interface MovementRule {
    fun getPossibleMoves(board: Board, piece: Piece): Set<Location>
}

interface MoveResolver {
    fun movePiece(board: Board, piece: Piece, toLocation: Location)
}
