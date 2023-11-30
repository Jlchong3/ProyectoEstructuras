/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.appdecontactos;

import ec.edu.espol.appdecontactos.clases.ButtonContacto;
import ec.edu.espol.appdecontactos.clases.Contacto;
import ec.edu.espol.appdecontactos.clases.DoubleCircularLinkedList;
import ec.edu.espol.appdecontactos.clases.SessionManager;
import java.io.IOException;
import java.net.URL;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Migi
 */
public class ContactosAsociadosController implements Initializable {
    
    private DoubleCircularLinkedList<Contacto> contactos = SessionManager.getInstance().getContactosActuales();
    Contacto contacto = SessionManager.getInstance().getContacto();
    //ListIterator<Contacto> it = contacto.getContactosRelacionados().CircularIterator();
    @FXML
    private GridPane gridPane;
    @FXML
    private AnchorPane pane;
    @FXML
    private VBox listaDeContactos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { //cargar para cada contacto sus contactos asociados, parecido a las fotos asociadas; en este caso yo agrego los contactos
        gridPane.setStyle("-fx-background-color: linear-gradient(to bottom, #B1CFFF, #B1CFFF);");
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
        //gridPane.add(new Region(), 0, 0);
        }
        else{
            pane.getChildren().clear();
            Text t = new Text("No tienes contactos");
            pane.getChildren().add(t);
        }
        
    }    

    @FXML
    private void agregarContactoAsociado(MouseEvent event) { //Mostrar la ventana los contactos asociados (sin incluirse a si mismo)
        try {
            App.setRoot("agregarAsociado"); //hay que hacer que regrese al contacto seleccionado
        } catch (IOException ex) {
           
        }
    }

    @FXML
    private void volver(MouseEvent event) {
         try {
            App.setRoot("secondary"); //hay que hacer que regrese al contacto seleccionado
        } catch (IOException ex) {
           
        }
    }
    
}
