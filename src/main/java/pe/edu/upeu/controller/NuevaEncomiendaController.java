package pe.edu.upeu.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pe.edu.upeu.enums.EstadoEncomienda;
import pe.edu.upeu.model.Encomienda;
import pe.edu.upeu.service.EncomiendaServiceImp;
import pe.edu.upeu.utils.AlertUtil;
import pe.edu.upeu.utils.GeneradorCodigo;

public class NuevaEncomiendaController {

    private EncomiendaController encomiendaController;

    private final EncomiendaServiceImp service =
            new EncomiendaServiceImp();

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

    @FXML
    public void initialize() {


        cbEstado.getItems().addAll(
                EstadoEncomienda.values()
        );

        txtCodigo.setText(
                GeneradorCodigo.generarCodigo()
        );

        txtCodigo.setEditable(false);
    }

    public void setEncomiendaController(
            EncomiendaController controller) {

        this.encomiendaController = controller;
    }

    @FXML
    private void guardar() {

        if (txtRemitente.getText().isBlank()
                || txtDestinatario.getText().isBlank()
                || txtOrigen.getText().isBlank()
                || txtDestino.getText().isBlank()) {

            AlertUtil.mostrarError(
                    "Validación",
                    "Complete todos los campos obligatorios"
            );

            return;
        }

        try {

            Encomienda encomienda = new Encomienda();

            encomienda.setCodigo(txtCodigo.getText());
            encomienda.setRemitente(txtRemitente.getText());
            encomienda.setDestinatario(txtDestinatario.getText());
            encomienda.setOrigen(txtOrigen.getText());
            encomienda.setDestino(txtDestino.getText());
            encomienda.setDescripcion(txtDescripcion.getText());

            double peso =
                    Double.parseDouble(
                            txtPeso.getText()
                    );

            double precio =
                    Double.parseDouble(
                            txtPrecio.getText()
                    );

            if (peso <= 0 || precio <= 0) {

                AlertUtil.mostrarError(
                        "Validación",
                        "Peso y precio deben ser mayores que cero"
                );

                return;
            }
            encomienda.setPeso(
                    Double.parseDouble(txtPeso.getText())
            );

            encomienda.setPrecio(
                    Double.parseDouble(txtPrecio.getText())
            );

            encomienda.setEstado(
                    cbEstado.getValue()
            );

            encomienda.setFechaEnvio(
                    dpFecha.getValue().toString()
            );

            boolean resultado =
                    service.guardar(encomienda);

            if(resultado){

                AlertUtil.mostrarInfo(
                        "Éxito",
                        "Encomienda registrada correctamente"
                );

                if(encomiendaController != null){
                    encomiendaController.cargarDatos();
                }

            }else{

                AlertUtil.mostrarError(
                        "Error",
                        "No se pudo registrar"
                );
            }

        } catch (Exception e) {

            AlertUtil.mostrarError(
                    "Error",
                    "Verifique los datos ingresados"
            );
        }
    }

    @FXML
    private void cancelar() {

        System.out.println("Cancelar");

    }

}