/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.tresenraya;

import clases.Tablero;
import clases.Computer;
import clases.Tipo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author Migi
 */
public class Tablero2JugController implements Initializable {

    @FXML
    private BorderPane mainpane;
    private Tablero tablero;
    private final int tamañoBoton = 100; // Cambié el tamaño para que se vea mejor en un tablero más pequeño
    private GridPane pane;
    private Tipo turno = Tipo.EQUIS;
    private Timeline timeline;
    @FXML
    private Button regresar;
    @FXML
    private Text modoJuego;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tablero = new Tablero();
        pane = new GridPane();
        
        Computer maquina1 = new Computer(tablero, Tipo.EQUIS);
        Computer maquina2 = new Computer(tablero, Tipo.CIRCULO);


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button boton = new Button();
                boton.setMinSize(tamañoBoton, tamañoBoton);
                boton.getStyleClass().add("button1");
                boton.setFont(Font.font(30)); // Ajusté el tamaño de la fuente

                boton.setOnMouseEntered(e -> boton.setOpacity(1.0));
                boton.setOnMouseExited(e -> boton.setOpacity(0.7));

                pane.add(boton, j, i);
            }
        }
        mainpane.setCenter(pane);
        BorderPane.setAlignment(pane, Pos.CENTER);
        BorderPane.setMargin(pane, new Insets(100)); // Ajusta este valor según sea necesario
        timeline = new Timeline();

        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(1), e -> movimientoMaquina(maquina1));
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(2), e -> movimientoMaquina(maquina2));

        // Repeat the key frames indefinitely until the game is finished
        timeline.getKeyFrames().addAll(keyFrame1, keyFrame2);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();  
    }

    
    private void estadoDeJuego(Tipo tipo) {
        if (hayGanador()) {
            // Implementar lógica para manejar el final del juego
            timeline.stop();
        } else if (tablero.isFull()) {
            Platform.runLater(() -> mostrarEmpateAlerta());
            timeline.stop();
        }
        turno = (turno == Tipo.EQUIS) ? Tipo.CIRCULO : Tipo.EQUIS;
    }

    private void mostrarEmpateAlerta() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Empate");
        alert.setHeaderText(null);
        alert.setContentText("El juego ha terminado en empate.\n El mundo no será destruido!");
        alert.showAndWait();
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

    public void movimientoMaquina(Computer maquina){
        int[] move = maquina.mejorOpcion();
        Button b = (Button) pane.getChildren().get(move[0]*3 + move[1]);
        b.setText(maquina.getTipo().toString()); 
        tablero.setFicha(move[0], move[1], maquina.getTipo());
        estadoDeJuego(maquina.getTipo());
    }

    @FXML
    private void volver(MouseEvent event) {
        try {
            App.setRoot("primary");
            timeline.stop();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
