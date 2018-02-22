/*
package de.kiwisound.shapemaster.jts

import de.kiwisound.shapemaster.geometry.ChainCodedCurve
import org.locationtech.jts.geom.Coordinate


fun ChainCodedCurve.toCoordinate() : List<Coordinate> =
    this.toDouble().windowed(2, 2) {
        Coordinate(it.component1(), it.component2())
    }

fun ChainCodedCurve.toCoordinateArray() : Array<Coordinate> =
        this.toCoordinate().toTypedArray()
*/