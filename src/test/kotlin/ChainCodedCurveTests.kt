import com.seisw.util.geom.PolyDefault
import de.kiwisound.shapemaster.geometry.ChainCodedCurve
import de.kiwisound.shapemaster.geometry.addAll
import javafx.geometry.Point2D
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Test

class ChainCodedCurveTests {
    @Test
    fun testArea() {
        val curve = ChainCodedCurve(*((0..7).toList().toIntArray()))
        curve.offset = Point2D(100.0, 150.0)
        curve.scale = 2.0

        println("${curve.area}")

        var poly = PolyDefault()
        poly.addAll(curve.points)

        assertEquals(poly.area, curve.area, 1e-5)
        assertEquals(poly.area, curve.reversed().area, 1e-5)
    }

    @Test
    fun testBendingEnergy() {
        val curve = ChainCodedCurve(*((0..7).toList().toIntArray()))

        println("${curve.bendingEnergy}")

        curve.scale = 2.0
        println("${curve.bendingEnergy}")

    }
}