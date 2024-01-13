package ec.edu.espol.tresenraya;

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

public class PrimaryController implements Initializable {

    @FXML
    private ComboBox<String> opcJug;
    @FXML
    private Button comenzar;
    @FXML
    private VBox vbox;
    private String valor; 
        private Text mensajeText;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        
        String[] categorias = {"Un jugador" , "Dos jugadores", "IA vs. IA"};
        
        opcJug.getItems().addAll(categorias);
        System.out.println("a");
    }

    @FXML
    private void filtrarTipo(ActionEvent event) {
        vbox.getChildren().clear();
        ComboBox cb = (ComboBox)event.getSource();
        String s = (String)cb.getValue();
        valor = s;
        
        Stage stage = (Stage) opcJug.getScene().getWindow();
        stage.setHeight(600);
        System.out.println("b");
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

        HBox hbox = new HBox(10, opcion1, opcion2);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(20));
        return hbox;
    }

    private void opcionSeleccionada(ToggleButton seleccionada, ToggleButton otra) {
        if (seleccionada.isSelected()) {
            otra.setSelected(false);
            mensajeText.setText("Jugador tiene la ficha: " + seleccionada.getText() +
                                "\nMaquina tiene la ficha: " + otra.getText());
        } else {
            otra.setSelected(true);
        }
    }
    private void opcionSeleccionada2(ToggleButton seleccionada, ToggleButton otra) {
        if (seleccionada.isSelected()) {
            otra.setSelected(false);
            mensajeText.setText("Jugador 1 tiene la ficha: " + seleccionada.getText() +
                                "\nJugador 2 tiene la ficha: " + otra.getText());
        } else {
            otra.setSelected(true);
        }
    }


    public void registroGeneral(){        
        String s = valor;
        System.out.println("aaaa");
        switch (s) {
            case "Un jugador":
            {
                HBox hbox = crearOpciones();

                // Crear un componente de texto para mostrar mensajes
                mensajeText = new Text("Seleccione una opción para cada jugador.");

                // Crear la escena y mostrarla
                vbox.getChildren().addAll(hbox, mensajeText);

                break;
            }
            case "Dos jugadores":                    
            {
                HBox hbox = crearOpciones2();
                // Crear un componente de texto para mostrar mensajes
                mensajeText = new Text("Seleccione una opción para cada jugador.");
                // Crear la escena y mostrarla
                vbox.getChildren().addAll(hbox, mensajeText);

                break;
            }
            case "IA vs. IA":                    
            {
                break;
            }
            default:
                break;
        }  
     }
    
    
}
