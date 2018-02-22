package de.kiwisound.shapemaster.geometry

import com.seisw.util.geom.Poly
import javafx.geometry.Point2D
import kotlin.coroutines.experimental.buildSequence

val Poly.points: Sequence<Point2D>
    get() = buildSequence {
        (0..(numPoints-1)).map {
            Point2D(getX(it), getY(it))
        }
    }

fun Poly.addAll(points: List<Point2D>) {
    points.forEach {
        add(it.x, it.y)
    }
}

fun Poly.toDouble() : List<Double> =
    (0..(numPoints-1)).flatMap {
        listOf(getX(it), getY(it))
    }

