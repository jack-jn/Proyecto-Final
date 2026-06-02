package pe.edu.upeu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pe.edu.upeu.repository.ConexionBD;
import javafx.scene.image.Image;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        ConexionBD.crearTablas();

        FXMLLoader fxmlLoader =
                new FXMLLoader(getClass().getResource("/view/maingui.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(
                getClass()
                        .getResource("/css/styles.css")
                        .toExternalForm()
        );

        stage.setTitle("Sistema de Gestión de Encomiendas");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
        stage.getIcons().add(
                new Image(
                        getClass().getResourceAsStream(
                                "/images/package.png"
                        )
                )
        );
    }
}