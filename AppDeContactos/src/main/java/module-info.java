module ec.edu.espol.appdecontactos {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espol.appdecontactos to javafx.fxml;
    exports ec.edu.espol.appdecontactos;
}
