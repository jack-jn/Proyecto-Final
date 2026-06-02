package pe.edu.upeu.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertUtil {

    public static void mostrarInfo(String titulo, String mensaje) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        alert.showAndWait();
    }

    public static void mostrarError(String titulo, String mensaje) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        alert.showAndWait();
    }
    public static boolean confirmar(
            String titulo,
            String mensaje) {

        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION
        );

        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        return alert.showAndWait()
                .orElse(ButtonType.CANCEL)
                == ButtonType.OK;
    }
}