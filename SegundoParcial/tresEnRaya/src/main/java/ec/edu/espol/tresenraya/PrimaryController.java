package ec.edu.espol.tresenraya;

import clases.Jugador;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
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

    private Jugador jugador1 = SessionManager.getInstance().getJug1();
    private Jugador jugador2 = SessionManager.getInstance().getJug2();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        
        String[] categorias = {"Un jugador" , "Dos jugadores", "IA vs. IA"};
        
        opcJug.getItems().addAll(categorias);
        // Aplicar la fuente 'Lucida Console' al ComboBox
        opcJug.setStyle("-fx-font-family: 'Lucida Console';");
       
    }

    @FXML
    private void filtrarTipo(ActionEvent event) {
        vbox.getChildren().clear();
        ComboBox cb = (ComboBox)event.getSource();
        String s = (String)cb.getValue();
        valor = s;
        
        Stage stage = (Stage) opcJug.getScene().getWindow();
        stage.setHeight(600);
        registroGeneral();
        
    }
    
    private HBox crearOpciones() {
        ToggleButton opcion1 = new ToggleButton("Circulo");
        ToggleButton opcion2 = new ToggleButton("Equis");

        ToggleGroup grupoOpciones = new ToggleGroup();
        opcion1.setToggleGroup(grupoOpciones);
        opcion2.setToggleGroup(grupoOpciones);

        opcion1.setOnAction(event -> opcionSeleccionada(opcion1, opcion2));
        opcion2.setOnAction(event -> opcionSeleccionada(opcion2, opcion1));

        // Aplicar la fuente 'Lucida Console' a los ToggleButton
        Font lucidaConsoleFont = Font.font("Lucida Console");
        opcion1.setFont(lucidaConsoleFont);
        opcion2.setFont(lucidaConsoleFont);

        HBox hbox = new HBox(10, opcion1, opcion2);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(20));
        return hbox;
    }

    private HBox crearOpciones2() {
        ToggleButton opcion1 = new ToggleButton("Circulo");
        ToggleButton opcion2 = new ToggleButton("Equis");

        ToggleGroup grupoOpciones = new ToggleGroup();
        opcion1.setToggleGroup(grupoOpciones);
        opcion2.setToggleGroup(grupoOpciones);

        opcion1.setOnAction(event -> opcionSeleccionada2(opcion1, opcion2));
        opcion2.setOnAction(event -> opcionSeleccionada2(opcion2, opcion1));

        // Aplicar la fuente 'Lucida Console' a los ToggleButton
        Font lucidaConsoleFont = Font.font("Lucida Console");
        opcion1.setFont(lucidaConsoleFont);
        opcion2.setFont(lucidaConsoleFont);

        HBox hbox = new HBox(10, opcion1, opcion2);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(20));
        return hbox;
    }

    private void opcionSeleccionada(ToggleButton seleccionada, ToggleButton otra) {
        if (seleccionada.isSelected()) {
            otra.setSelected(false);
            mensajeText.setText("Jugador tiene la ficha: " + seleccionada.getText() + "\nMaquina tiene la ficha: " + otra.getText());
            
            String tipo1= seleccionada.getText().toUpperCase();
            Tipo tipoEnum1 = Tipo.valueOf(tipo1);

            String tipo2= otra.getText().toUpperCase();
            Tipo tipoEnum2 = Tipo.valueOf(tipo2);
                                
            jugador1.setTipoJ(tipoEnum1);
            
            jugador2.setTipoJ(tipoEnum2);
            
            System.out.println("jugador: "+ tipoEnum1);
            System.out.println("maquina: "+ tipoEnum2);

        } else {
            otra.setSelected(true);
        }
    }
    private void opcionSeleccionada2(ToggleButton seleccionada, ToggleButton otra) {
        if (seleccionada.isSelected()) {
            otra.setSelected(false);
            mensajeText.setText("Jugador 1 tiene la ficha: " + seleccionada.getText() + "\nJugador 2 tiene la ficha: " + otra.getText());
            
            String tipo1= seleccionada.getText().toUpperCase();
            Tipo tipoEnum1 = Tipo.valueOf(tipo1);

            String tipo2= otra.getText().toUpperCase();
            Tipo tipoEnum2 = Tipo.valueOf(tipo2);
                    
            jugador1.setTipoJ(tipoEnum1);
            jugador2.setTipoJ(tipoEnum2);
            System.out.println("jugador 1: "+ tipoEnum1);
            System.out.println("jugador 2: "+ tipoEnum2);

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

        // Ajustar el interlineado del texto (lineSpacing)
        mensajeText.setLineSpacing(10); // Puedes ajustar este valor según tus preferencias
        Button botonCom = new Button("Comenzar juego");

        switch (s) {
            case "Un jugador": {
                HBox hbox = crearOpciones();
                botonCom.setOnMouseClicked(event -> comenzarJuego(event, "tablero"));
         
                vbox.getChildren().addAll(hbox,mensajeText,botonCom);
                break;
            }
            case "Dos jugadores": {
                HBox hbox = crearOpciones2();
                botonCom.setOnMouseClicked(event -> comenzarJuego(event, "tablero2Jug"));

                vbox.getChildren().addAll(hbox,mensajeText,botonCom);
                break;
            }
            case "IA vs. IA": {
                break;
            }
            default:
                break;
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
