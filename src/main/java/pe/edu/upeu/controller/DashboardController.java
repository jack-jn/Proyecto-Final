package pe.edu.upeu.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pe.edu.upeu.service.EncomiendaServiceImp;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DashboardController {

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblPendientes;

    @FXML
    private Label lblEntregadas;

    @FXML
    private Label lblEnCamino;

    @FXML
    private Label lblCanceladas;

    @FXML
    private Label lblFecha;

    @FXML
    private Label lblHora;

    @FXML
    public void initialize() {

        EncomiendaServiceImp service =
                new EncomiendaServiceImp();

        lblTotal.setText(
                String.valueOf(
                        service.contarTotal()
                )
        );

        lblPendientes.setText(
                String.valueOf(
                        service.contarPorEstado(
                                "PENDIENTE"
                        )
                )
        );

        lblEnCamino.setText(
                String.valueOf(
                        service.contarPorEstado(
                                "EN_CAMINO"
                        )
                )
        );

        lblEntregadas.setText(
                String.valueOf(
                        service.contarPorEstado(
                                "ENTREGADO"
                        )
                )
        );

        lblCanceladas.setText(
                String.valueOf(
                        service.contarPorEstado(
                                "CANCELADO"
                        )
                )
        );
        iniciarReloj();
    }

    private void iniciarReloj() {

        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(1),
                        e -> {

                            lblFecha.setText(
                                    LocalDate.now()
                                            .format(
                                                    DateTimeFormatter.ofPattern(
                                                            "dd/MM/yyyy"
                                                    )
                                            )
                            );

                            lblHora.setText(
                                    LocalTime.now()
                                            .format(
                                                    DateTimeFormatter.ofPattern(
                                                            "HH:mm:ss"
                                                    )
                                            )
                            );
                        }
                )
        );

        timeline.setCycleCount(
                Timeline.INDEFINITE
        );

        timeline.play();
    }
}