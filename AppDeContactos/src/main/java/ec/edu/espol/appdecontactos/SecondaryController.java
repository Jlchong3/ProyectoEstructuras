package ec.edu.espol.appdecontactos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import ec.edu.espol.appdecontactos.clases.*;
import java.util.ListIterator;
import javafx.event.Event;
import javafx.geometry.HPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SecondaryController implements Initializable {

    ListIterator<Contacto> it;
    private DoubleCircularLinkedList<Contacto> contactos = new DoubleCircularLinkedList<>();
    @FXML
    private GridPane gridTop;
    @FXML
    private VBox FotoPerfil;
    @FXML
    private GridPane gridMid;

    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> redes = new ArrayList<>();
        ArrayList<String> redes2 = new ArrayList<>();
        Contacto c1 = new Contacto(); 
        Contacto c2 = new Contacto(); 
        DoubleCircularLinkedList<String> fotos = new DoubleCircularLinkedList<>();
        DoubleCircularLinkedList<String> fotos2 = new DoubleCircularLinkedList<>();
        Contacto n = new Contacto();
        redes.addLast("@hola");
        redes.addLast("@holaFace");
        redes.addLast("@holaTiktok");
        fotos.addLast("img/Jose.jpeg");
        fotos.addLast("img/JoseAsociada1.jpeg");
        fotos2.addLast("img/MariuPerfil.jpeg");
        fotos2.addLast("img/MariuAsociada1.jpeg");
        redes2.addLast("@hela");
        redes2.addLast("@helaFace");
        redes2.addLast("@helaTiktok");
        Persona p1 = new Persona(01234, 0320432, 03432, redes, fotos, "correo@", "correo2@", "correo3@", "aniversario 11", c1 , "hola","jose", "luis");
        Persona p2 = new Persona(01243234, 0322340432, 03432432, redes2, fotos2, "correo@otro", "correo2@otro", "correo3@otro", "aniversario 6", c2 , "hello","Mariu", "Andrade");
        contactos.addLast(p1);
        contactos.addLast(p2);
        
        if(!contactos.isEmpty()){
            it = contactos.CircularIterator();
            Contacto actual = it.next();
            Button previous = new Button();
            gridTop.setGridLinesVisible(true);
            previous.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
                Contacto con = it.previous();
                //Agregar las modificaciones
            });

    //        Image img = new Image();
            StackPane sp = new StackPane();
            sp.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
            });
            ImageView imv = new ImageView(/*img*/);

            Button delete = new Button();
            delete.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
                it.remove();
                final Contacto x = it.next();
                //Agregar las modificaciones
            });
            
            Button next = new Button();
            next.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
                final Contacto x = it.next();
                //Agregar las modificaciones
            });

        }
        else{
            Text text = new Text("No hay contactos");
            gridMid.setGridLinesVisible(true);
            GridPane.setHalignment(text, HPos.CENTER);
            gridMid.add(text,0,1);
            
        }
    }
    
    public void actualizaPagina(Contacto c){
        
    }
}