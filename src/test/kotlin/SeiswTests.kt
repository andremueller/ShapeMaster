import com.seisw.util.geom.Poly
import com.seisw.util.geom.PolyDefault
import com.seisw.util.geom.PolySimple
import de.kiwisound.shapemaster.geometry.ChainCodedCurve
import de.kiwisound.shapemaster.geometry.addAll
import javafx.geometry.Point2D
import org.junit.Test

class SeiswTests {
    @Test
    fun testIntersect() {
        val p1 = PolyDefault()
        val p2 = PolyDefault()
        val curve1 = ChainCodedCurve(0, 1, 2, 3, 4, 5, 6, 7)
        curve1.offset = Point2D(100.0, 100.0)
        curve1.scale = 25.0
        val curve2 = ChainCodedCurve(curve1.elements)
        curve2.offset = Point2D(120.0, 150.0)
        curve2.scale = 20.0

        p1.addAll(curve1.points)
        p2.addAll(curve2.points)

        val p3 = p1.intersection(p2)
    }
}