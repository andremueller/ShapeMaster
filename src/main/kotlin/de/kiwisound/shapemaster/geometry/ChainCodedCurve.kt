package de.kiwisound.shapemaster.geometry

import javafx.geometry.Point2D
import kotlin.math.PI
import kotlin.math.absoluteValue
import kotlin.math.sqrt


class ChainCodedCurve(val elements: List<ChainElement> = emptyList()) : Shape2D {

    enum class ChainElement(val value: Byte) {
        Right(0),
        TopRight(1),
        Top(2),
        TopLeft(3),
        Left(4),
        BottomLeft(5),
        Bottom(6),
        BottomRight(7)
    }

    constructor(vararg el: Int) : this(
            el.toList().map { ChainElement.values()[it] })

    constructor(curve: ChainCodedCurve) : this(curve.elements) {
        offset = curve.offset
        scale = curve.scale
    }

    companion object {
        private val sqrt2: Double = sqrt(2.0)
        private val pi_4: Double = PI / 4.0
        private val ChainOffsetX = arrayOf(+1.0, +1.0, 0.0, -1.0, -1.0, -1.0, 0.0, +1.0)
        private val ChainOffsetY = arrayOf(0.0, -1.0, -1.0, -1.0, 0.0, +1.0, +1.0, +1.0)

        fun getDeltaX(code: ChainElement): Double = ChainOffsetX[code.value.toInt()]
        fun getDeltaY(code: ChainElement): Double = ChainOffsetY[code.value.toInt()]
        fun isDiagonal(code: ChainElement): Boolean = ((code.ordinal % 2) == 1)
    }

    val size: Int
        get() = elements.size

    val isClosed: Boolean
        get() = TODO("implement that!!")

    override var offset : Point2D = Point2D.ZERO

    override var scale: Double = 1.0

    override val area: Double
        get() {
            val x = getX()
            val y = getY()
            var sum = 0.0
            for (i in 0..(size-1)) {
                val k = (i + 1) % size
                sum += x[i] * (y[k] - y[i])
            }
            return sum.absoluteValue
        }

    override val bendingEnergy: Double
        get() {
            var energy: Double = 0.0
            for (i in 0..(size-1)) {
                val i2 = (i + 1) % size
                val deltaAng = angle(elements[i2]) - angle(elements[i])
                val meanLength = 0.5 * (length(elements[i2]) + length(elements[i]))
                fun square(x: Double) = x * x
                energy += square(deltaAng / meanLength)
            }
            energy /= size.toDouble()
            return energy
        }

    fun getX(): List<Double> {
        var x = offset.x
        return listOf(x) +
                elements.map {
                    x += scale * getDeltaX(it)
                    x
                }
    }

    fun getY(): List<Double> {
        var y = offset.y
        return listOf(y) +
                elements.map {
                    y += scale * getDeltaY(it)
                    y
                }
    }

    fun toDouble(): List<Double> {
        var x = offset.x
        var y = offset.y
        return listOf(x, y) +
            elements.flatMap {
                x += scale * getDeltaX(it)
                y += scale * getDeltaY(it)
                listOf(x, y)
            }
    }

    fun reversed(): ChainCodedCurve {
        val rev = ChainCodedCurve(elements.reversed())
        rev.scale = scale
        rev.offset = offset
        return rev
    }

    val points : List<Point2D>
        get() {
            val x = getX()
            val y = getY()
            return (0..(size-1)).map { Point2D(x[it], y[it]) }
        }

    private fun length(code: ChainElement): Double =
        if (isDiagonal(code)) { sqrt2 * scale } else { scale }

    private fun angle(code: ChainElement): Double =
            code.ordinal.toDouble() * pi_4
}
