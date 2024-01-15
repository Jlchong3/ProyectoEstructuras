module ec.edu.espol.tresenraya {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.graphics;

    opens ec.edu.espol.tresenraya to javafx.fxml;
    exports ec.edu.espol.tresenraya;
    exports clases;
}
