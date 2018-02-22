package de.kiwisound.shapemaster.controller

import javafx.fxml.FXML
import javafx.scene.control.TabPane

class ShapeMasterController {
    @FXML
    lateinit var tabPane: TabPane

    fun initialize() {
        // tabPane.tabs[0].content = ShapePane()
    }
}
