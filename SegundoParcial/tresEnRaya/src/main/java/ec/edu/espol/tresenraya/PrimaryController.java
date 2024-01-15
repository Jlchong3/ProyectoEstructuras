package ec.edu.espol.tresenraya;

import clases.SessionManager;
import clases.Tipo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class PrimaryController implements Initializable {

    @FXML
    private ComboBox<String> opcJug;
    @FXML
    private Button comenzar;
    @FXML
    private VBox vbox;
    private String valor; 
    private Text mensajeText;
    @FXML
    private VBox vb;
    @FXML
    private Text titulo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] categorias = {"Un jugador" , "Dos jugadores", "IA vs. IA"};
        
        opcJug.getItems().addAll(categorias);
        // Aplicar la fuente 'Lucida Console' al ComboBox
        opcJug.setStyle("-fx-font-family: 'Lucida Console';");
       
    }

    @FXML
    private void filtrarTipo(ActionEvent event) {
        vbox.getChildren().clear();
        ComboBox<String> cb = (ComboBox<String>)event.getSource();
        String s = cb.getValue();
        valor = s;
        
        Stage stage = (Stage) opcJug.getScene().getWindow();
        stage.setHeight(600);
        registroGeneral();
    }
    
    private HBox crearOpciones(String valor) {
        ToggleButton opcion1 = new ToggleButton("Circulo");
        ToggleButton opcion2 = new ToggleButton("Equis");

        ToggleGroup grupoOpciones = new ToggleGroup();
        opcion1.setToggleGroup(grupoOpciones);
        opcion2.setToggleGroup(grupoOpciones);

        opcion1.setOnAction(event -> opcionSeleccionada(opcion1, opcion2,valor));
        opcion2.setOnAction(event -> opcionSeleccionada(opcion2, opcion1,valor));

        // Aplicar la fuente 'Lucida Console' a los ToggleButton
        Font lucidaConsoleFont = Font.font("Lucida Console");
        opcion1.setFont(lucidaConsoleFont);
        opcion2.setFont(lucidaConsoleFont);

        HBox hbox = new HBox(10, opcion1, opcion2);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(20));
        return hbox;
    }

    private void opcionSeleccionada(ToggleButton seleccionada, ToggleButton otra, String valor) {
        String jug1;
        String jug2;
        if(valor == "Un jugador"){
            jug1 = "Jugador";
            jug2 = "Manquina";
        }
        else{
            jug1 = "Jugador 1";
            jug2 = "Jugador 2"; 
        }

        if (seleccionada.isSelected()) {
            otra.setSelected(false);
            mensajeText.setText(jug1 + " tiene la ficha: " + seleccionada.getText() + "\n" + jug2 +" tiene la ficha: " + otra.getText());
            
            String tipo1= seleccionada.getText().toUpperCase();
            Tipo tipoEnum1 = Tipo.valueOf(tipo1);

            SessionManager.getInstance().setTipo(tipoEnum1);
            
            System.out.println(jug1 + ": "+ tipoEnum1);
            System.out.println(jug2 + ": "+ tipoEnum1.opuesto());
            
            mensajeText.setStyle("-fx-font-size: 16;");

        } else {
            otra.setSelected(true);
        }
    }

    public void registroGeneral() {
        String s = valor;

        // Crear un componente de texto para mostrar mensajes con la fuente 'Lucida Console'
        mensajeText = new Text("Seleccione una opción para cada jugador.");
        Font lucidaConsoleFont = Font.font("Lucida Console");
        mensajeText.setFont(lucidaConsoleFont);
        mensajeText.setStyle("-fx-font-size: 16;");

        // Ajustar el interlineado del texto (lineSpacing)
        mensajeText.setLineSpacing(10); // Puedes ajustar este valor según tus preferencias
        Button botonCom = new Button("Comenzar juego");
        botonCom.setStyle(
            "-fx-font-family: 'Lucida Console'; " +
            "-fx-font-size: 18; " +
            "-fx-background-color: #2ecc71; " +
            "-fx-text-fill: black; " +
            "-fx-cursor: hand;"
        );
        HBox hbox = crearOpciones(valor);

        switch (s) {
            case "IA vs. IA": 
                botonCom.setOnMouseClicked(event -> comenzarJuego(event, "tablero2Jug"));
                vbox.getChildren().addAll(botonCom);
                break;
            default:
                if(valor == "Dos jugadores"){
                    SessionManager.getInstance().setPvp(true);
                }
                else{
                    SessionManager.getInstance().setPvp(false);
                }
                botonCom.setOnMouseClicked(event -> comenzarJuego(event, "tablero"));
                vbox.getChildren().addAll(hbox,mensajeText,botonCom);
        }
    }
    
    private void comenzarJuego(MouseEvent event, String root) {
        try {
            App.setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
