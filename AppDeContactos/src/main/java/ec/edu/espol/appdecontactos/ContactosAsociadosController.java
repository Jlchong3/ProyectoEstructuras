/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.appdecontactos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Migi
 */
public class ContactosAsociadosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { //cargar para cada contacto sus contactos asociados, parecido a las fotos asociadas; en este caso yo agrego los contactos
        // TODO
    }    

    @FXML
    private void agregarContactoAsociado(MouseEvent event) { //Mostrar la ventana (otro controller) de todos los contactos disponibles (sin incluirse a si mismo)
        
    }

    @FXML
    private void volver(MouseEvent event) {
         try {
            App.setRoot("secondary"); //hay que hacer que regrese al contacto seleccionado
        } catch (IOException ex) {
           
        }
    }
    
}
