package de.kiwisound.shapemaster.geometry

import javafx.geometry.Point2D

interface Shape2D {
    val offset: Point2D
    val scale: Double
    val area: Double
    val bendingEnergy: Double
}
