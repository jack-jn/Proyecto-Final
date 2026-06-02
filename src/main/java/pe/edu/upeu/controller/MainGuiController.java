package pe.edu.upeu.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import pe.edu.upeu.utils.AlertUtil;

public class MainGuiController {

    @FXML
    private AnchorPane contentPane;

    @FXML
    public void initialize() {
        abrirDashboard();

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

    @FXML
    private void abrirDashboard() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/view/dashboard.fxml"
                            )
                    );

            AnchorPane pane = loader.load();

            contentPane.getChildren().clear();

            contentPane.getChildren().add(pane);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    @FXML
    private void abrirClientes() {
        cargarVista("/view/clientes.fxml");
    }

    @FXML
    private void abrirReportes() {
        cargarVista("/view/reportes.fxml");
    }

    @FXML
    private void abrirConfiguracion() {
        cargarVista("/view/configuracion.fxml");
    }

    @FXML
    private void cerrarSesion() {

        boolean confirmar =
                AlertUtil.confirmar(
                        "Salir",
                        "¿Desea cerrar la aplicación?"
                );

        if(confirmar){
            System.exit(0);
        }
    }
    private void cargarVista(String ruta) {
        try {
            AnchorPane pane =
                    FXMLLoader.load(
                            getClass().getResource(ruta)
                    );

            contentPane.getChildren().setAll(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}