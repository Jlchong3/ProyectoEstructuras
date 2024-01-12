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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GridPane pane = new GridPane();

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

        mainpane.setCenter(pane);
    }

    private void manejarJugada(Button boton, int fila, int columna) {
        if (boton.getText().isEmpty()) {
            // Realizar la jugada si el botón está vacío
            boton.setText(turno.toString());

            // Verificar si hay un ganador o empate
            if (hayGanador() || hayEmpate()) {
                // Implementar lógica para manejar el final del juego
                System.out.println("Juego terminado");
            } else {
                // Cambiar el turno al siguiente jugador
                turno = (turno == Tipo.EQUIS) ? Tipo.CIRCULO : Tipo.EQUIS;
            }
        }
    }
    
     private boolean hayGanador() {
        // Implementar lógica para verificar si hay un ganador
        return false;
    }

    private boolean hayEmpate() {
        // Implementar lógica para verificar si hay un empate
        return false;
    }
    
}
