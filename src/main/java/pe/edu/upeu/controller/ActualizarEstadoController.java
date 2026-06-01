package pe.edu.upeu.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import pe.edu.upeu.enums.EstadoEncomienda;
import pe.edu.upeu.model.Encomienda;
import pe.edu.upeu.service.EncomiendaServiceImp;
import pe.edu.upeu.utils.AlertUtil;

public class ActualizarEstadoController {

    @FXML
    private ComboBox<EstadoEncomienda> cbEstado;

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
            Encomienda encomienda) {

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
            if(encomiendaController != null){
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
}
