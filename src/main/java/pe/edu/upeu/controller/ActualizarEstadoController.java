package pe.edu.upeu.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import pe.edu.upeu.enums.EstadoEncomienda;
import pe.edu.upeu.model.Encomienda;
import pe.edu.upeu.service.EncomiendaServiceImp;
import pe.edu.upeu.utils.AlertUtil;
import javafx.scene.control.Label;

public class ActualizarEstadoController {

    @FXML
    private ComboBox<EstadoEncomienda> cbEstado;
    @FXML
    private Label lblCodigo;

    @FXML
    private Label lblEstadoActual;


    private Encomienda encomienda;
    private EncomiendaController encomiendaController;

    private final EncomiendaServiceImp service =
            new EncomiendaServiceImp();

    @FXML
    public void initialize() {

        cbEstado.getItems().addAll(
                EstadoEncomienda.values()
        );
    }

    public void cargarEncomienda(
            Encomienda encomienda)
    {
        lblCodigo.setText(
                "Código: " +
                        encomienda.getCodigo()
        );

        lblEstadoActual.setText(
                "Estado actual: " +
                        encomienda.getEstado().name()
        );

        this.encomienda = encomienda;

        cbEstado.setValue(
                encomienda.getEstado()
        );
    }
    public void setEncomiendaController(
            EncomiendaController controller) {

        this.encomiendaController = controller;
    }

    @FXML
    private void actualizar() {

        boolean confirmar =
                AlertUtil.confirmar(
                        "Actualizar Estado",
                        "¿Desea cambiar el estado de la encomienda?"
                );

        if (!confirmar) {
            return;
        }

        boolean actualizado =
                service.actualizarEstado(
                        encomienda.getId(),
                        cbEstado.getValue().name()
                );

        if (actualizado) {

            AlertUtil.mostrarInfo(
                    "Éxito",
                    "Estado actualizado"
            );

            if (encomiendaController != null) {
                encomiendaController.cargarDatos();
            }

            cbEstado.getScene()
                    .getWindow()
                    .hide();

        } else {

            AlertUtil.mostrarError(
                    "Error",
                    "No se pudo actualizar"
            );
        }
    }
    @FXML
    private void cancelar() {

        cbEstado.getScene()
                .getWindow()
                .hide();
    }
}
