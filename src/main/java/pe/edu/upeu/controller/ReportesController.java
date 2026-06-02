package pe.edu.upeu.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pe.edu.upeu.service.EncomiendaServiceImp;

public class ReportesController {

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblPendientes;

    @FXML
    private Label lblEnCamino;

    @FXML
    private Label lblEntregadas;

    @FXML
    private Label lblCanceladas;

    private final EncomiendaServiceImp service =
            new EncomiendaServiceImp();

    @FXML
    public void initialize() {

        lblTotal.setText(
                String.valueOf(
                        service.listar().size()
                )
        );

        lblPendientes.setText(
                String.valueOf(
                        service.contarPorEstado("PENDIENTE")
                )
        );

        lblEnCamino.setText(
                String.valueOf(
                        service.contarPorEstado("EN_CAMINO")
                )
        );

        lblEntregadas.setText(
                String.valueOf(
                        service.contarPorEstado("ENTREGADO")
                )
        );

        lblCanceladas.setText(
                String.valueOf(
                        service.contarPorEstado("CANCELADO")
                )
        );
    }
}
