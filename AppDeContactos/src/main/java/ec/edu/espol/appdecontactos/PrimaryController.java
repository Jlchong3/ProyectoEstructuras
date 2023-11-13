package ec.edu.espol.appdecontactos;

import ec.edu.espol.appdecontactos.clases.ButtonContacto;
import ec.edu.espol.appdecontactos.clases.ArrayList;
import ec.edu.espol.appdecontactos.clases.Contacto;
import ec.edu.espol.appdecontactos.clases.DoubleCircularLinkedList;
import ec.edu.espol.appdecontactos.clases.Empresa;
import ec.edu.espol.appdecontactos.clases.Persona;
import ec.edu.espol.appdecontactos.clases.SessionManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PrimaryController implements Initializable {
    
    private DoubleCircularLinkedList<Contacto> contactos = SessionManager.getInstance().getContactosActuales();
    @FXML
    private VBox listaDeContactos;
    @FXML
    private AnchorPane pane;
    @FXML
    private Button agregarContacto;
    @FXML
    private Button recorrer;
    @FXML
    private Button salir;
    @FXML
    private GridPane gridPane;
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        listaDeContactos.setStyle("-fx-spacing: 10; -fx-padding: 10;");
        if(!contactos.isEmpty()){
            for(int i = 0; i < contactos.size(); i++){
                Contacto c = contactos.get(i);
                ButtonContacto b = new ButtonContacto(c);
                b.setStyle("-fx-font-family: 'Century Gothic'; -fx-font-size: 14; -fx-min-width: 150;");
                b.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
                    SessionManager.getInstance().setContacto(c);
                    try {
                        App.setRoot("secondary");
                    }
                    catch (IOException ex) {}
                });
                listaDeContactos.getChildren().add(b);
               
            }
        gridPane.add(new Region(), 0, 0);
        gridPane.setStyle("-fx-background-color: linear-gradient(to bottom, #B1CFFF, #B1CFFF);");


        }
        else{
            pane.getChildren().clear();
            Text t = new Text("No tienes contactos");
            pane.getChildren().add(t);
        }
    }

    @FXML
    private void agregarNuevoContacto(MouseEvent event) {
        try {
            App.setRoot("craerContacto");
        } catch (IOException ex) {
           
        }
    }

    @FXML
    private void recorrerContactos(MouseEvent event) {
        SessionManager.getInstance().setContacto(null);
        try {
            App.setRoot("secondary");
        } catch (IOException ex) {
           
        }
    }

    @FXML
    private void cerrarAplicacion(MouseEvent event) {
        Platform.exit();
    }
}
