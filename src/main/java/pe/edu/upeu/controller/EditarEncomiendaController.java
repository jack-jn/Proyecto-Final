package pe.edu.upeu.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import pe.edu.upeu.enums.EstadoEncomienda;
import pe.edu.upeu.model.Encomienda;
import pe.edu.upeu.service.EncomiendaServiceImp;
import pe.edu.upeu.utils.AlertUtil;
import java.time.LocalDate;

public class EditarEncomiendaController {

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtRemitente;

    @FXML
    private TextField txtDestinatario;

    @FXML
    private TextField txtOrigen;

    @FXML
    private TextField txtDestino;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TextField txtPeso;

    @FXML
    private TextField txtPrecio;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private ComboBox<EstadoEncomienda> cbEstado;

    private EncomiendaController encomiendaController;
    private Encomienda encomienda;

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

        txtCodigo.setText(encomienda.getCodigo());
        txtRemitente.setText(encomienda.getRemitente());
        txtDestinatario.setText(encomienda.getDestinatario());
        txtOrigen.setText(encomienda.getOrigen());
        txtDestino.setText(encomienda.getDestino());
        txtDescripcion.setText(encomienda.getDescripcion());

        txtPeso.setText(
                String.valueOf(
                        encomienda.getPeso()
                )
        );

        txtPrecio.setText(
                String.valueOf(
                        encomienda.getPrecio()
                )
        );

        cbEstado.setValue(
                encomienda.getEstado()
        );
        if (encomienda.getFechaEnvio() != null) {

            dpFecha.setValue(
                    LocalDate.parse(
                            encomienda.getFechaEnvio()
                    )
            );
        }
    }
    public void setEncomiendaController(
            EncomiendaController controller) {

        this.encomiendaController = controller;
    }

    @FXML
    private void guardar() {

        try {

            encomienda.setCodigo(
                    txtCodigo.getText()
            );

            encomienda.setRemitente(
                    txtRemitente.getText()
            );

            encomienda.setDestinatario(
                    txtDestinatario.getText()
            );

            encomienda.setOrigen(
                    txtOrigen.getText()
            );

            encomienda.setDestino(
                    txtDestino.getText()
            );

            encomienda.setDescripcion(
                    txtDescripcion.getText()
            );

            encomienda.setPeso(
                    Double.parseDouble(
                            txtPeso.getText()
                    )
            );

            encomienda.setPrecio(
                    Double.parseDouble(
                            txtPrecio.getText()
                    )
            );

            encomienda.setEstado(
                    cbEstado.getValue()
            );
            encomienda.setFechaEnvio(
                    dpFecha.getValue().toString()
            );

            boolean confirmar =
                    AlertUtil.confirmar(
                            "Actualizar",
                            "¿Desea actualizar esta encomienda?"
                    );

            if (!confirmar) {
                return;
            }

            boolean actualizado =
                    service.actualizar(
                            encomienda
                    );

            if (actualizado) {

                AlertUtil.mostrarInfo(
                        "Éxito",
                        "Registro actualizado"
                );
                if(encomiendaController != null){
                    encomiendaController.cargarDatos();
                }
                txtCodigo.getScene()
                        .getWindow()
                        .hide();

            } else {

                AlertUtil.mostrarError(
                        "Error",
                        "No se pudo actualizar"
                );
            }

        } catch (Exception e) {

            AlertUtil.mostrarError(
                    "Error",
                    "Datos inválidos"
            );
        }
    }

    @FXML
    private void cancelar() {

        txtCodigo.getScene()
                .getWindow()
                .hide();
    }
}