module br.ufc.sistemapatrimonio {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.ufc.sistemapatrimonio to javafx.fxml;
    exports br.ufc.sistemapatrimonio;
    exports br.ufc.sistemapatrimonio.model;
    exports br.ufc.sistemapatrimonio.exceptions;
    exports br.ufc.sistemapatrimonio.enums;
    exports br.ufc.sistemapatrimonio.entities;
    opens br.ufc.sistemapatrimonio.controller to javafx.fxml;
    exports br.ufc.sistemapatrimonio.controller;
}