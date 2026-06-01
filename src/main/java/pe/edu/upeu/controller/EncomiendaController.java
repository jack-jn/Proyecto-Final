package pe.edu.upeu.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import pe.edu.upeu.utils.AlertUtil;
import java.time.LocalDate;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;

import pe.edu.upeu.model.Encomienda;
import pe.edu.upeu.service.EncomiendaServiceImp;


public class EncomiendaController {

    private Encomienda encomiendaSeleccionada;

    private final EncomiendaServiceImp service =
            new EncomiendaServiceImp();

    @FXML
    private TableView<Encomienda> tablaEncomiendas;

    @FXML
    private javafx.scene.control.TextField txtBuscar;
    @FXML
    private Label lblTotal;

    @FXML
    private Label lblPendiente;

    @FXML
    private Label lblEnCamino;

    @FXML
    private Label lblEntregado;

    @FXML
    private ComboBox<String> cbFiltroEstado;


    @FXML
    private TableColumn<Encomienda, String> colCodigo;

    @FXML
    private TableColumn<Encomienda, String> colRemitente;

    @FXML
    private TableColumn<Encomienda, String> colDestinatario;

    @FXML
    private TableColumn<Encomienda, String> colOrigen;

    @FXML
    private TableColumn<Encomienda, String> colDestino;

    @FXML
    private TableColumn<Encomienda, Object> colEstado;

    @FXML
    private TableColumn<Encomienda, Double> colPeso;

    @FXML
    private TableColumn<Encomienda, Double> colPrecio;

    @FXML
    public void initialize() {

        cbFiltroEstado.getItems().addAll(
                "TODOS",
                "PENDIENTE",
                "EN_CAMINO",
                "ENTREGADO"
        );

        cbFiltroEstado.setValue("TODOS");

        cbFiltroEstado.setOnAction(
                e -> filtrarPorEstado()
        );

        cargarDatos();

        tablaEncomiendas.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldValue, newValue) -> {

                    encomiendaSeleccionada = newValue;

                });
    }

    @FXML
    private void nuevo() {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/view/nueva_encomienda.fxml")
            );

            Scene scene = new Scene(loader.load());
            NuevaEncomiendaController controller =
                    loader.getController();

            controller.setEncomiendaController(this);

            Stage stage = new Stage();

            stage.setTitle("Nueva Encomienda");
            stage.setScene(scene);

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void editar() {

        if (encomiendaSeleccionada == null) {

            AlertUtil.mostrarError(
                    "Error",
                    "Seleccione una encomienda"
            );

            return;
        }

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/view/editar_encomienda.fxml"
                    )
            );

            Scene scene =
                    new Scene(loader.load());


            EditarEncomiendaController controller =
                    loader.getController();

            controller.cargarEncomienda(
                    encomiendaSeleccionada
            );
            controller.setEncomiendaController(this);

            Stage stage = new Stage();

            stage.setTitle("Editar Encomienda");
            stage.setScene(scene);

            stage.show();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    private void eliminar() {

        if (encomiendaSeleccionada == null) {

            AlertUtil.mostrarError(
                    "Error",
                    "Seleccione una encomienda"
            );

            return;
        }

        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION
        );

        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText(
                "¿Desea eliminar la encomienda?"
        );

        if (alert.showAndWait().orElse(ButtonType.CANCEL)
                == ButtonType.OK) {

            boolean eliminado =
                    service.eliminar(
                            encomiendaSeleccionada.getId()
                    );

            if (eliminado) {

                AlertUtil.mostrarInfo(
                        "Éxito",
                        "Registro eliminado"
                );

                cargarDatos();

            } else {

                AlertUtil.mostrarError(
                        "Error",
                        "No se pudo eliminar"
                );
            }
        }
    }

    @FXML
    private void actualizarEstado() {

        if (encomiendaSeleccionada == null) {

            AlertUtil.mostrarError(
                    "Error",
                    "Seleccione una encomienda"
            );

            return;
        }

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/view/actualizar_estado.fxml"
                            )
                    );

            Scene scene =
                    new Scene(loader.load());

            ActualizarEstadoController controller =
                    loader.getController();

            controller.cargarEncomienda(
                    encomiendaSeleccionada
            );
            controller.setEncomiendaController(this);

            Stage stage = new Stage();

            stage.setTitle(
                    "Actualizar Estado"
            );

            stage.setScene(scene);

            stage.show();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void cargarDatos() {

        colCodigo.setCellValueFactory(
                new PropertyValueFactory<>("codigo"));

        colRemitente.setCellValueFactory(
                new PropertyValueFactory<>("remitente"));

        colDestinatario.setCellValueFactory(
                new PropertyValueFactory<>("destinatario"));

        colOrigen.setCellValueFactory(
                new PropertyValueFactory<>("origen"));

        colDestino.setCellValueFactory(
                new PropertyValueFactory<>("destino"));

        colEstado.setCellValueFactory(
                new PropertyValueFactory<>("estado"));

        colPeso.setCellValueFactory(
                new PropertyValueFactory<>("peso"));

        colPrecio.setCellValueFactory(
                new PropertyValueFactory<>("precio"));

        ObservableList<Encomienda> lista =
                FXCollections.observableArrayList(
                        service.listar()
                );

        tablaEncomiendas.setItems(lista);
        tablaEncomiendas.refresh();
        cargarEstadisticas();
    }
    @FXML
    private void buscar() {

        String codigo =
                txtBuscar.getText().trim();

        if (codigo.isEmpty()) {

            cargarDatos();
            return;
        }

        Encomienda encomienda =
                service.buscarPorCodigo(codigo);

        if (encomienda == null) {

            AlertUtil.mostrarError(
                    "No encontrado",
                    "No existe una encomienda con ese código"
            );

            return;
        }

        ObservableList<Encomienda> lista =
                FXCollections.observableArrayList();

        lista.add(encomienda);

        tablaEncomiendas.setItems(lista);
    }
    private void cargarEstadisticas() {

        lblTotal.setText(
                String.valueOf(
                        service.contarTotal()
                )
        );

        lblPendiente.setText(
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

        lblEntregado.setText(
                String.valueOf(
                        service.contarPorEstado(
                                "ENTREGADO"
                        )
                )
        );
    }
    @FXML
    private void filtrarPorEstado() {

        String estado =
                cbFiltroEstado.getValue();

        if ("TODOS".equals(estado)) {

            cargarDatos();
            return;
        }

        ObservableList<Encomienda> filtradas =
                FXCollections.observableArrayList();

        for (Encomienda e : service.listar()) {

            if (e.getEstado().name()
                    .equals(estado)) {

                filtradas.add(e);
            }
        }

        tablaEncomiendas.setItems(
                filtradas
        );
    }
}