module Encomienda {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires com.github.librepdf.openpdf;


    opens pe.edu.upeu.controller to javafx.fxml;
    opens pe.edu.upeu.model to javafx.base;

    exports pe.edu.upeu;
    exports pe.edu.upeu.controller;

}