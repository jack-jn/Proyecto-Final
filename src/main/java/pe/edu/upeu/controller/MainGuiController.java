package pe.edu.upeu.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainGuiController {

    @FXML
    private AnchorPane contentPane;

    @FXML
    public void initialize() {

    }

    @FXML
    private void abrirEncomiendas() {

        try {

            AnchorPane vista = FXMLLoader.load(
                    getClass().getResource("/view/encomienda.fxml")
            );

            contentPane.getChildren().setAll(vista);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}