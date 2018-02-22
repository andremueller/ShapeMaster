package de.kiwisound.shapemaster.view

import com.seisw.util.geom.PolyDefault
import de.kiwisound.shapemaster.geometry.ChainCodedCurve
import de.kiwisound.shapemaster.geometry.addAll
import de.kiwisound.shapemaster.geometry.toDouble
import javafx.event.EventHandler
import javafx.geometry.Point2D
import javafx.scene.Group
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Polygon
import javafx.scene.shape.Rectangle

class ShapePane : Pane {
    private var _zoomFactor = 1.0
    private var _mouseDownPosition = Point2D.ZERO

    constructor() {
        val group = Group()

        group.children.add(Rectangle(400.0, 400.0, Color.LIGHTGRAY))

        val curve1 = ChainCodedCurve(0, 1, 2, 3, 4, 5, 6, 7)
        curve1.offset = Point2D(100.0, 100.0)
        curve1.scale = 25.0
        val curve2 = ChainCodedCurve(curve1.elements)
        curve2.offset = Point2D(120.0, 150.0)
        curve2.scale = 20.0


        val poly1 = Polygon()
        poly1.fill = Color(1.0, 0.9, 0.9, 0.3)
        poly1.stroke = Color.BLUE
        poly1.points.setAll(curve1.toDouble())
        group.children.add(poly1)

        val poly2 = Polygon()
        poly2.fill = Color(0.9, 1.0, 0.9, 0.3)
        poly2.stroke = Color.GREEN
        poly2.points.setAll(curve2.toDouble())
        group.children.add(poly2)

        val p1 = PolyDefault()
        p1.addAll(curve1.points)
        val p2 = PolyDefault()
        p2.addAll(curve2.points)
        val isect = p1.intersection(p2)

        val inter = Polygon()
        inter.fill = Color(0.5, 0.5, 1.0, 0.8)
        inter.stroke = Color.RED
        inter.points.setAll(isect.toDouble())
        group.children.add(inter)
        /*
        val svg = SVGPath()
        svg.content = "M40,60 C42,48 44,30 25,32"
        svg.fill = Color.AQUA
        svg.stroke = Color.BLUE
        group.children.add(svg)
        */
/*

        val coords1 = curve1.toCoordinateArray()
        val g1 = GeometryFactory().createLinearRing(coords1)
        val coords2 = curve2.toCoordinateArray()
        val g2 = GeometryFactory().createLinearRing(coords2)


        val poly1 = Polygon()
        poly1.points.setAll(g1.coordinates.flatMap { listOf(it.x, it.y) })
        poly1.fill = Color(0.0,0.0,0.0,0.0)
        poly1.stroke = Color.RED

        val poly2 = Polygon()
        poly2.points.setAll(g2.coordinates.flatMap { listOf(it.x, it.y) })
        poly2.fill = Color(0.0,0.0,0.0,0.0)
        poly2.stroke = Color.BLUE

        val g3 = g1.intersection(g2)// OverlayOp.overlayOp(g1, g2, OverlayOp.INTERSECTION)
        val poly3 = Polygon()
        poly3.points.setAll(g3.coordinates.flatMap { listOf(it.x, it.y) })
        poly3.fill = Color(0.0,0.0,1.0,0.9)
        poly3.stroke = Color.GREEN


        val inter = Shape.intersect(poly1, poly2)
        inter.fill = Color(0.5, 0.5, 1.0, 0.8)
        inter.stroke = Color.RED
        if (inter is Path) {
            inter.elements.forEach {
                println(it.javaClass.toString())
            }
        }group.children.addAll(poly1, poly2, poly3, inter)
*/


        children.add(group)

        onScroll = EventHandler {
            _zoomFactor += it.deltaY * 0.1
            when {
                _zoomFactor < 0.1 -> _zoomFactor = 0.1
                _zoomFactor > 5.0 -> _zoomFactor = 5.0
            }
            scaleX = _zoomFactor
            scaleY = _zoomFactor
            println("zoom $_zoomFactor")
        }

        onMousePressed = EventHandler {
            println("Drag")

            _mouseDownPosition = Point2D(it.sceneX - translateX, it.sceneY - translateY)

        }
        onMouseDragged = EventHandler {
            translateX = it.sceneX - _mouseDownPosition.x
            translateY = it.sceneY - _mouseDownPosition.y
        }
    }
}
