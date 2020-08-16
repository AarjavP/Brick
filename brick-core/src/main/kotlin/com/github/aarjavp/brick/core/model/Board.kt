package com.github.aarjavp.brick.core.model

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

interface Occupant
data class Coordinate(val x: Int, val y: Int)
enum class Direction(val x_d: Int, val y_d: Int) {
    North(0, 1),
    NorthEast(1, 1),
    East(1, 0),
    SouthEast(1, -1),
    South(0, -1),
    SouthWest(-1, -1),
    West(-1, 0),
    NorthWest(1,  -1);
infix fun of(cord: Coordinate): Coordinate = Coordinate(cord.x + x_d, cord.y + y_d)
}
class Board(val width: Int, val height: Int) {

    private val occupants: ConcurrentMap<Coordinate, Occupant> = ConcurrentHashMap()
    private val occupantsLock: ReentrantReadWriteLock = ReentrantReadWriteLock()

    fun Coordinate.isValid(): Boolean = x < width && y < height && x >= 0 && y >= 0
    fun Coordinate.isNotValid(): Boolean = !isValid()
//    fun Coordinate.takeIfValid(): Coordinate? = if (isValid()) this else null

    fun move(src: Coordinate, dest: Coordinate, action: (Occupant, Occupant?) -> Occupant) {
        if (src.isNotValid()) throw IllegalStateException("Source input does not exist: $src")
        if (dest.isNotValid()) throw IllegalStateException("Destination does not exist: $dest")

        occupantsLock.write {
            val toMove = occupants.remove(src)
                    ?: throw IllegalStateException("Source coordinate doesn't have an occupant: $src")
            val occupied = occupants[dest]
            occupants[dest] = action(toMove, occupied)
        }
    }

    fun remove(cord:Coordinate, occ: Occupant) = occupantsLock.read { occupants.remove(cord, occ) }

    operator fun get(cord: Coordinate): Occupant? = occupantsLock.read { occupants[cord] }
    operator fun set(cord: Coordinate, occ: Occupant) {
        if (!cord.isValid()) throw IllegalStateException("Space does not exist: $cord")

        occupantsLock.read {
            occupants.compute(cord) { _, prev -> when (prev) {
                null -> occ
                else -> throw IllegalStateException("Space is occupied: $cord")
            } }
        }
    }
    operator fun set(x: Int, y: Int, occ: Occupant) {
        set(Coordinate(x, y), occ)
    }

}
