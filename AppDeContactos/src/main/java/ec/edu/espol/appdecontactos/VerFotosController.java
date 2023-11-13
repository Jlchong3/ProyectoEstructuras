/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
 
package ec.edu.espol.appdecontactos;
import ec.edu.espol.appdecontactos.clases.Contacto;
import ec.edu.espol.appdecontactos.clases.SessionManager;
import java.io.IOException;
import java.net.URL;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author josel
 */
public class VerFotosController implements Initializable {
    
    Contacto contacto = SessionManager.getInstance().getContacto();
    ListIterator<String> it = contacto.getFotos().CircularIterator();
            
    @FXML
    private StackPane foto;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img = new Image(it.next(),200,0,true,true);
        ImageView imv = new ImageView(img);
        foto.getChildren().add(imv);
    }    

    @FXML
    private void regresar(MouseEvent event) {
        try{
            App.setRoot("secondary");
        }
        catch(IOException e){
        }
    }

    @FXML
    private void adelante(MouseEvent event) {
        foto.getChildren().clear();
        Image img = new Image(it.next(),200,0,true,true);
        ImageView imv = new ImageView(img);
        foto.getChildren().add(imv);
    }

    @FXML
    private void atras(MouseEvent event) {
        foto.getChildren().clear();
        Image img = new Image(it.previous(),200,0,true,true);
        ImageView imv = new ImageView(img);
        foto.getChildren().add(imv);
    }
}
