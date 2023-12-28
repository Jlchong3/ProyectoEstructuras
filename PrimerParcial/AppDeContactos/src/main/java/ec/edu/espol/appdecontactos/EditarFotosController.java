/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.appdecontactos;

import ec.edu.espol.appdecontactos.clases.Contacto;
import ec.edu.espol.appdecontactos.clases.DoubleCircularLinkedList;
import ec.edu.espol.appdecontactos.clases.SessionManager;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
/**
 * FXML Controller class
 *
 * @author mariu
 */
public class EditarFotosController implements Initializable {

    Contacto contacto = SessionManager.getInstance().getContacto();
    DoubleCircularLinkedList<String> fotosLista = contacto.getFotosAsociadas();
    ListIterator<String> it = fotosLista.CircularIterator();            
    
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
            App.setRoot("editarPersona");
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
@FXML
 private void borrarFotoActual(MouseEvent event) {
        it.remove();
        contacto.setFotosAsociadas(fotosLista);
        
        foto.getChildren().clear();
        Image img = new Image(it.next(),200,0,true,true);
        ImageView imv = new ImageView(img);
        foto.getChildren().add(imv);
    }

 @FXML
    private void anadirFoto(MouseEvent event) {
            abrirSelectorDeArchivo();
        }
    
    
    private void abrirSelectorDeArchivo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona una imagen");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File archivoSeleccionado = fileChooser.showOpenDialog(null);
        if (archivoSeleccionado != null) {
            copiarImagenAProyecto(archivoSeleccionado);
        }
    }
    
    private void copiarImagenAProyecto(File archivoOrigen) {
        Path destino = Paths.get("src/main/resources/ec/edu/espol/appdecontactos/imgs/contactos/", archivoOrigen.getName());
        try {
            Files.copy(archivoOrigen.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);
            // Actualizar la imagen en tu aplicación
            fotosLista.addLast(destino.toUri().toString());
            contacto.setFotosAsociadas(fotosLista);
//            imv.setImage(new Image(destino.toUri().toString(),100,100,true,true));
//            primer.getFotoPerfil().set(0, destino.toUri().toString());
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar el error
        }
    }
    
    

}
