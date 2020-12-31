package com.github.aarjavp.brick.core.model

open class Item: Occupant

open class PowerupItem(val powerup: List<Powerup>): Item()

open class PieceItem(val piece: Piece): Item()

