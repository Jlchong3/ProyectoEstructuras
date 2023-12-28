module ec.edu.espol.appdecontactos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.appdecontactos to javafx.fxml;
    exports ec.edu.espol.appdecontactos;
}
