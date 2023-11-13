package ec.edu.espol.appdecontactos;

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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(!contactos.isEmpty()){
            for(int i = 0; i < contactos.size(); i++){
                Contacto c = contactos.get(i);
                Button b = new Button();
                if(c instanceof Empresa){
                    Empresa e = (Empresa) c;
                    b.setText(e.getNombre());
                }
                else{
                    Persona p = (Persona) c;
                    b.setText(p.getNombre() + " "+ p.getApellido());
                }
                listaDeContactos.getChildren().add(b);
            }
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
