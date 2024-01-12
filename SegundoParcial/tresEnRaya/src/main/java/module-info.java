module ec.edu.espol.tresenraya {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.tresenraya to javafx.fxml;
    exports ec.edu.espol.tresenraya;
}
