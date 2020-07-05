package com.github.aarjavp.brick.core

data class Coordinate(val x: Int, val y: Int)
sealed class Piece(val name: String, val abbreviation: Char, val team: String) {
    class King(team: String): Piece("King", 'K', team)
    class Knight(team: String): Piece("Knight", 'N', team)
    class Bishop(team: String): Piece("Beach", 'B', team)
    class Rook(team: String): Piece("Krook", 'R', team)
    class Queen(team: String): Piece("Kween", 'Q', team)
}
data class Space(val location: Coordinate, var occupant: Piece?)
class Board {
    @ExperimentalStdlibApi
    val spaces: Set<Space> = buildSet {
        for (i in (0..7)) {
            for (j in (0..7)) {
                add(Space(Coordinate(i, j), null))
            }
        }
    }
}
