/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.tresenraya;

import clases.*;
import java.io.IOException;
import java.net.URL;
import javafx.util.Duration;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Migi
 */
public class TableroController implements Initializable {

    @FXML
    private BorderPane mainpane;
    private Tablero tablero;
    private Computer maquina;
    private Tipo tipoJugador = SessionManager.getInstance().getTipo();
    private Tipo turno = Tipo.EQUIS;
    private boolean pvp = SessionManager.getInstance().getPvp();
    private final int tamañoBoton = 100; // Cambié el tamaño para que se vea mejor en un tablero más pequeño
    private GridPane pane;
    private boolean juegoTerminado = false;
    private Timeline timeline;
    @FXML
    private Button regresar;
    @FXML
    private Label turnoTxt;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String jug1;
        String jug2;
        if(pvp){
            jug1 = "Jugador 1";
            jug2 = "Jugador 2"; 
        }
        else{
            jug1 = "Jugador";
            jug2 = "Manquina";
        }

        pane = new GridPane();
        tablero = new Tablero();
        if(!pvp){
            maquina = new Computer(tablero, tipoJugador.opuesto());
            timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> movimientoMaquina())
            );
        }

        System.out.println(jug1 + ":" + tipoJugador);
        System.out.println(jug2 + ":" + tipoJugador.opuesto());
       
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button boton = new Button();
                boton.setMinSize(tamañoBoton, tamañoBoton);
                boton.getStyleClass().add("button1");
                boton.setFont(Font.font(30)); // Ajusté el tamaño de la fuente

                // Crear variables finales para usar en la expresión lambda
                final int filaFinal = i;
                final int columnaFinal = j;

                // Configurar evento de clic para manejar la jugada
                boton.setOnAction(e -> manejarJugada(boton, filaFinal, columnaFinal));
                
                boton.setOnMouseEntered(e -> boton.setOpacity(1.0));
                boton.setOnMouseExited(e -> boton.setOpacity(0.7));
               
                pane.add(boton, j, i);
            }
        }
 
        // Centrar el GridPane en el BorderPane
        mainpane.setCenter(pane);

        // Configurar alineación en el BorderPane
        BorderPane.setAlignment(pane, Pos.CENTER);
        BorderPane.setMargin(pane, new Insets(100)); // Ajusta este valor según sea necesario
        
        
        if(!pvp && maquina.getTipo().equals(Tipo.EQUIS)){
            timeline.play();
        }
    }

    private void manejarJugada(Button boton, int fila, int columna) {
        if (!juegoTerminado && tablero.getFicha(fila, columna) == null) {
            // Realizar la jugada si el botón está vacío
            
            if(pvp){
                boton.setText(turno.toString());
                tablero.setFicha(fila, columna, turno);
                estadoDeJuego(turno);
            }
            else if(turno == tipoJugador){
                boton.setText(tipoJugador.toString());
                tablero.setFicha(fila, columna, tipoJugador);
                estadoDeJuego(tipoJugador);
                if(!juegoTerminado){
                    timeline.play();
                }
            }
        if (!juegoTerminado) 
            turnoTxt.setText(""+turno);
        }
     }
    
    private void estadoDeJuego(Tipo tipo){
        if (hayGanador()) {
                // Implementar lógica para manejar el final del juego
                System.out.println("Fichas ganadoras: " + tipo );
                juegoTerminado = true;// Marcar el juego como terminado 
        }
        else if(tablero.isFull()){
            System.out.println("Empate");
            juegoTerminado = true;
        }
        turno = (turno == Tipo.EQUIS) ? tipo.CIRCULO : tipo.EQUIS;
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
        
    public void movimientoMaquina(){
        int[] move = maquina.mejorOpcion();
        Button b = (Button) pane.getChildren().get(move[0]*3 + move[1]);
        b.setText(maquina.getTipo().toString()); 
        tablero.setFicha(move[0], move[1], maquina.getTipo());
        timeline.stop();
        estadoDeJuego(maquina.getTipo());
        turnoTxt.setText("" + turno);
    }

    @FXML
    private void volver(MouseEvent event) {
        try {
            App.setRoot("primary");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}