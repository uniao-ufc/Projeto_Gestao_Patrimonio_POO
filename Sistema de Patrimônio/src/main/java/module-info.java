module br.ufc.sistemapatrimonio {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.ufc.sistemapatrimonio to javafx.fxml;
    exports br.ufc.sistemapatrimonio;
}