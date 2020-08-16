package com.github.aarjavp.brick.core.model

open class Piece(val name: String, val abbreviation: Char, val team: String): Occupant
class King(team: String): Piece("King", 'K', team)
class Knight(team: String): Piece("Knight", 'N', team)
class Bishop(team: String): Piece("Bishop", 'B', team)
class Rook(team: String): Piece("Rook", 'R', team)
class Queen(team: String): Piece("Queen", 'Q', team)
class Pawn(team: String): Piece("Pawn", 'P', team)