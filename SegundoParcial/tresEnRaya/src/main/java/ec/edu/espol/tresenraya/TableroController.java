/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.tresenraya;

import clases.Tablero;
import clases.Tipo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
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
    private Tablero tablero;
    private final int tamañoBoton = 100; // Cambié el tamaño para que se vea mejor en un tablero más pequeño
    private String estiloBoton = "";
    private Tipo turno = Tipo.EQUIS; // Cambié a Tipo en lugar de Jugador
    private GridPane pane;
    private boolean juegoTerminado = false;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pane = new GridPane();
        tablero = new Tablero();

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
        if (!juegoTerminado && tablero.getFicha(fila, columna) == null) {
            // Realizar la jugada si el botón está vacío
            boton.setText(turno.toString());
            tablero.setFicha(fila, columna, turno);
            
            // Verificar si hay un ganador o empate
            if (hayGanador()) {
                // Implementar lógica para manejar el final del juego
                System.out.println("Fichas ganadoras: " + turno );
                juegoTerminado = true;// Marcar el juego como terminado 
            }
            else if(tablero.isFull()){
                System.out.println("Empate");
                juegoTerminado = true;
            }
            turno = (turno == Tipo.EQUIS) ? Tipo.CIRCULO : Tipo.EQUIS;
        }
    }
    
     private boolean hayGanador() {
        // Verificar filas y columnas
        for (int i = 0; i < 3; i++) {
            if (esGanador(tablero.getFicha(i, 0),
                           tablero.getFicha(i, 1),
                           tablero.getFicha(i, 2))){
                return true; // Ganador en fila i
            }

            if (esGanador(tablero.getFicha(0, i),
                           tablero.getFicha(1, i),
                           tablero.getFicha(2, i))) {
                return true; // Ganador en columna i
            }
        }

        // Verificar diagonales
        if (esGanador(tablero.getFicha(0, 0),
                       tablero.getFicha(1, 1),
                       tablero.getFicha(2, 2))) {
            return true; // Ganador en diagonal principal
        }

        if (esGanador(tablero.getFicha(0, 2),
                       tablero.getFicha(1, 1),
                       tablero.getFicha(2, 0))) {
            return true; // Ganador en diagonal secundaria
        }

        return false;
    }

    // Función auxiliar para verificar si tres valores son iguales y no están vacíos
    private boolean esGanador(Tipo a, Tipo b, Tipo c) {
        return a == b && b == c && a != null;
    }
}
