/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.tresenraya;

import clases.Tipo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Migi
 */
public class TableroController implements Initializable {

    @FXML
    private BorderPane mainpane;
    private final int tamañoBoton = 100; // Cambié el tamaño para que se vea mejor en un tablero más pequeño
    private String estiloBoton = "";
    private Tipo turno = Tipo.EQUIS; // Cambié a Tipo en lugar de Jugador
    private GridPane pane;
    private boolean juegoTerminado = false;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pane = new GridPane();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button boton = new Button();
                boton.setMinSize(tamañoBoton, tamañoBoton);
                boton.setStyle(estiloBoton);
                boton.setFont(Font.font(24)); // Ajusté el tamaño de la fuente

                // Crear variables finales para usar en la expresión lambda
                final int filaFinal = i;
                final int columnaFinal = j;

                // Configurar evento de clic para manejar la jugada
                boton.setOnAction(e -> manejarJugada(boton, filaFinal, columnaFinal));

                pane.add(boton, j, i);
            }
        }

        // Centrar el GridPane en el BorderPane
        mainpane.setCenter(pane);

        // Configurar alineación en el BorderPane
        BorderPane.setAlignment(pane, Pos.CENTER);
        BorderPane.setMargin(pane, new Insets(50)); // Ajusta este valor según sea necesario
}

     private void manejarJugada(Button boton, int fila, int columna) {
        if (!juegoTerminado && boton.getText().isEmpty()) {
            // Realizar la jugada si el botón está vacío
            boton.setText(turno.toString());

            // Verificar si hay un ganador o empate
            if (hayGanador() || hayEmpate()) {
                // Implementar lógica para manejar el final del juego
                System.out.println("Juego terminado");
                juegoTerminado = true; // Marcar el juego como terminado
            } else {
                // Cambiar el turno al siguiente jugador
                turno = (turno == Tipo.EQUIS) ? Tipo.CIRCULO : Tipo.EQUIS;
            }
        }
    }
    
     private boolean hayGanador() {
        // Verificar filas y columnas
        for (int i = 0; i < 3; i++) {
            if (esGanador(((Button) pane.getChildren().get(i * 3)).getText(),
                           ((Button) pane.getChildren().get(i * 3 + 1)).getText(),
                           ((Button) pane.getChildren().get(i * 3 + 2)).getText())) {
                return true; // Ganador en fila i
            }

            if (esGanador(((Button) pane.getChildren().get(i)).getText(),
                           ((Button) pane.getChildren().get(i + 3)).getText(),
                           ((Button) pane.getChildren().get(i + 6)).getText())) {
                return true; // Ganador en columna i
            }
        }

        // Verificar diagonales
        if (esGanador(((Button) pane.getChildren().get(0)).getText(),
                       ((Button) pane.getChildren().get(4)).getText(),
                       ((Button) pane.getChildren().get(8)).getText())) {
            return true; // Ganador en diagonal principal
        }

        if (esGanador(((Button) pane.getChildren().get(2)).getText(),
                       ((Button) pane.getChildren().get(4)).getText(),
                       ((Button) pane.getChildren().get(6)).getText())) {
            return true; // Ganador en diagonal secundaria
        }

        return false;
    }

    // Función auxiliar para verificar si tres valores son iguales y no están vacíos
    private boolean esGanador(String a, String b, String c) {
        return a.equals(b) && b.equals(c) && !a.isEmpty();
    }
    

    private boolean hayEmpate() {
        for (Node node : pane.getChildren()) {
            if (((Button) node).getText().isEmpty()) {
                return false; // Todavía hay al menos un espacio vacío, no hay empate
            }
        }
        return true; // Todos los espacios están ocupados, hay empate
    }
    
}
