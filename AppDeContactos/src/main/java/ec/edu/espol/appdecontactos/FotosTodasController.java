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
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mariu
 */
public class FotosTodasController implements Initializable {

    @FXML
    private Button retroceder;
    @FXML
    private ImageView fotoPrincipal;
    @FXML
    private ImageView FotoAsociada;
    @FXML
    private Button atras;
    @FXML
    private Button siguiente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void regresarTContactos(){

    }
    
    @FXML
    private void retrocederPantalla(MouseEvent event) {
        try {
            
            App.setRoot("craerContacto");
        } catch (IOException ex) {
           
        }
    }

    @FXML
    private void fotoAnterior(MouseEvent event) {
    }

    @FXML
    private void fotoSiguiente(MouseEvent event) {
    }
    
}
