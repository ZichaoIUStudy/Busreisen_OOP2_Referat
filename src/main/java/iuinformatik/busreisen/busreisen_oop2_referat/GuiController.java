package iuinformatik.busreisen.busreisen_oop2_referat;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GuiController {

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}