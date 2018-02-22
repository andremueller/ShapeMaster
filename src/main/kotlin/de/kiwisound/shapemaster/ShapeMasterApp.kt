package de.kiwisound.shapemaster

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import java.util.*

class ShapeMasterApp : Application() {

    override fun start(primaryStage: Stage) {
        val resourceBundle = ResourceBundle.getBundle("shapemaster", Locale.getDefault())

        val loader = FXMLLoader(javaClass.getResource("/de/kiwisound/shapemaster/view/ShapeMaster.fxml"))
        loader.resources = resourceBundle
        val root = loader.load<Parent>()

        primaryStage.scene = Scene(root, 600.0, 500.0)
        primaryStage.show()
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            launch(ShapeMasterApp::class.java)
        }
    }
}
