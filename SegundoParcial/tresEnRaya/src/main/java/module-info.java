module ec.edu.espol.tresenraya {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espol.tresenraya to javafx.fxml;
    exports ec.edu.espol.tresenraya;
}
