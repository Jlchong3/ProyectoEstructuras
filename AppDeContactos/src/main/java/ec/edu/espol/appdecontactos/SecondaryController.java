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
import javafx.scene.text.Text;

public class SecondaryController implements Initializable {

    ListIterator<Contacto> it;
    @FXML
    private GridPane contenedorContacto;
    private DoubleCircularLinkedList<Contacto> contactos = new DoubleCircularLinkedList<>();

    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(!contactos.isEmpty()){
            it = contactos.CircularIterator();
            Contacto c = it.next();
            HBox h = new HBox();
            Button previous = new Button();
            previous.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
                Contacto con = it.previous();
                //Agregar las modificaciones
            });

    //        Image img = new Image();
            ImageView imv = new ImageView(/*img*/);

            Button delete = new Button();
            delete.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
                it.remove();
                Contacto con = it.next();
                //Agregar las modificaciones
            });

            Button next = new Button();
            next.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
                Contacto con = it.next();
                //Agregar las modificaciones
            });

            h.getChildren().addAll(previous,imv,delete,next);

            contenedorContacto.add(h, 0, 0);

        }
        else{
            Text text = new Text("No hay contactos");
            contenedorContacto.setGridLinesVisible(true);
            GridPane.setHalignment(text, HPos.CENTER);
            contenedorContacto.add(text,0,1);
            
        }
    }
}