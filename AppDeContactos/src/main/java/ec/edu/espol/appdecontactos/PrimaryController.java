package ec.edu.espol.appdecontactos;

import ec.edu.espol.appdecontactos.clases.ArrayList;
import ec.edu.espol.appdecontactos.clases.Contacto;
import ec.edu.espol.appdecontactos.clases.DoubleCircularLinkedList;
import ec.edu.espol.appdecontactos.clases.Empresa;
import ec.edu.espol.appdecontactos.clases.Persona;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PrimaryController implements Initializable {

    private DoubleCircularLinkedList<Contacto> contactos = new DoubleCircularLinkedList<>();
    @FXML
    private VBox listaDeContactos;
    @FXML
    private AnchorPane pane;
    

    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> redes = new ArrayList<>();
        ArrayList<String> redes2 = new ArrayList<>();
        Contacto c1 = new Contacto(); 
        Contacto c2 = new Contacto(); 
        DoubleCircularLinkedList<String> fotos = new DoubleCircularLinkedList<>();
        DoubleCircularLinkedList<String> fotos2 = new DoubleCircularLinkedList<>();
        redes.addLast("@hola");
        redes.addLast("@holaFace");
        redes.addLast("@holaTiktok");
        fotos.addLast("file:src/main/resources/ec/edu/espol/appdecontactos/imgs/contactos/JosePerfil.jpeg");
        fotos.addLast("file:src/main/resources/ec/edu/espol/appdecontactos/imgs/contactos/FotosAsociadas/JoseAsociada1.jpeg");
        fotos2.addLast("file:src/main/resources/ec/edu/espol/appdecontactos/imgs/contactos/MariuPerfil.jpeg");
        fotos2.addLast("file:src/main/resources/ec/edu/espol/appdecontactos/imgs/contactos/FotosAsociadas/MariuAsociada1.jpeg");
        redes2.addLast("@hela");
        redes2.addLast("@helaFace");
        redes2.addLast("@helaTiktok");
        Persona p1 = new Persona(34253245,redes, fotos, "correo@Principal", "Aniversario: 11", c1, "helo", "jose", "chong", 1324234, 4234324, "correo@tabjajo1", "correo@prov2");
        Persona p2 = new Persona(234532345,redes2,fotos2, "correo@Pricipal2", "Aniversario: 11", c2, "hola","alex", "chong", 2343242, 234324324, "correo@tabjajo1", "correo@prov2");
        contactos.addLast(p1);
        contactos.addLast(p2);
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
}
