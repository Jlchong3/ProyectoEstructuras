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
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    @FXML
    private AnchorPane telefonos;
    @FXML
    private AnchorPane correos;
    @FXML
    private AnchorPane notas;
    @FXML
    private AnchorPane redesSociales;

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
            it = contactos.CircularIterator();
            Persona primer = (Persona)it.next();
            StackPane sp = new StackPane();
            sp.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
            });
            Image img = new Image(primer.getFoto(0),100,100,true,true);
            ImageView imv = new ImageView(img);
            sp.getChildren().add(imv);
            FotoPerfil.setAlignment(Pos.CENTER);
            FotoPerfil.getChildren().add(sp);
            FotoPerfil.getChildren().add(new Text(primer.getNombre()+" "+primer.getApellido()));
            VBox tels = new VBox();
            VBox corrs = new VBox();
            VBox reds = new VBox();
            tels.getChildren().addAll(new Text("Personal: " + primer.getTelefonoPrincipal()),new Text("Trabajo: " + primer.getTelefonoTrabajo()),new Text("Casa: " + primer.getTelefonoCasa()));
            corrs.getChildren().addAll(new Text("Principal: " + primer.getCorreoPrincipal()),new Text("Trabajo: " + primer.getCorreoTrabajo()),new Text("Provisional" + primer.getCorreoProvisional()));
            reds.getChildren().addAll(new Text("X: " + primer.getRedesSociales().get(0)),new Text("Face: " + primer.getRedesSociales().get(1)),new Text("Tiktok: " + primer.getRedesSociales().get(2)));
            telefonos.getChildren().add(tels);
            correos.getChildren().add(corrs);
            redesSociales.getChildren().add(reds);
            notas.getChildren().add(new Text(primer.getNota()));
            
        }
        else{
            gridTop.getChildren().clear();
            gridMid.getChildren().clear();
            Text text = new Text("No hay contactos");
            gridMid.setGridLinesVisible(true);
            GridPane.setHalignment(text, HPos.CENTER);
            gridMid.add(text,0,1);
            
        }
    }
    
    public void actualizaPagina(Contacto c){
        
    }

    @FXML
    private void previous(MouseEvent event) {
        Contacto c = it.previous();
        clear();
        Persona cont = (Persona)c;
        StackPane sp = new StackPane();
        sp.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
        });
        Image img = new Image(cont.getFoto(0),100,100,true,true);
        ImageView imv = new ImageView(img);
        sp.getChildren().add(imv);
        FotoPerfil.setAlignment(Pos.CENTER);
        FotoPerfil.getChildren().add(sp);
        FotoPerfil.getChildren().add(new Text(cont.getNombre()+" "+cont.getApellido()));
        VBox tels = new VBox();
        VBox corrs = new VBox();
        VBox reds = new VBox();
        tels.getChildren().addAll(new Text("Personal: " + cont.getTelefonoPrincipal()),new Text("Trabajo: " + cont.getTelefonoTrabajo()),new Text("Casa: " + cont.getTelefonoCasa()));
        corrs.getChildren().addAll(new Text("Principal: " + cont.getCorreoPrincipal()),new Text("Trabajo: " + cont.getCorreoTrabajo()),new Text("Provisional" + cont.getCorreoProvisional()));
        reds.getChildren().addAll(new Text("X: " + cont.getRedesSociales().get(0)),new Text("Face: " + cont.getRedesSociales().get(1)),new Text("Tiktok: " + cont.getRedesSociales().get(2)));
        telefonos.getChildren().add(tels);
        correos.getChildren().add(corrs);
        redesSociales.getChildren().add(reds);
        notas.getChildren().add(new Text(cont.getNota()));
    }

    @FXML
    private void delete(MouseEvent event) {
        it.remove();
        clear();
        System.out.println(contactos);
        if(contactos.isEmpty()){
            gridTop.getChildren().clear();
            gridMid.getChildren().clear();
            Text text = new Text("No hay contactos");
            gridMid.setHalignment(text, HPos.CENTER);
            gridMid.add(text,0,1);
        }
        else{
            Contacto c = it.next();
            Persona cont = (Persona)c;
            StackPane sp = new StackPane();
            sp.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
            });
            Image img = new Image(cont.getFoto(0),100,100,true,true);
            ImageView imv = new ImageView(img);
            sp.getChildren().add(imv);
            FotoPerfil.setAlignment(Pos.CENTER);
            FotoPerfil.getChildren().add(sp);
            FotoPerfil.getChildren().add(new Text(cont.getNombre()+" "+cont.getApellido()));
            VBox tels = new VBox();
            VBox corrs = new VBox();
            VBox reds = new VBox();
            tels.getChildren().addAll(new Text("Personal: " + cont.getTelefonoPrincipal()),new Text("Trabajo: " + cont.getTelefonoTrabajo()),new Text("Casa: " + cont.getTelefonoCasa()));
            corrs.getChildren().addAll(new Text("Principal: " + cont.getCorreoPrincipal()),new Text("Trabajo: " + cont.getCorreoTrabajo()),new Text("Provisional" + cont.getCorreoProvisional()));
            reds.getChildren().addAll(new Text("X: " + cont.getRedesSociales().get(0)),new Text("Face: " + cont.getRedesSociales().get(1)),new Text("Tiktok: " + cont.getRedesSociales().get(2)));
            telefonos.getChildren().add(tels);
            correos.getChildren().add(corrs);
            redesSociales.getChildren().add(reds);
            notas.getChildren().add(new Text(cont.getNota()));
        }
    }

    @FXML
    private void next(MouseEvent event) {
        Contacto c = it.next();
        clear();
        Persona cont = (Persona)c;
        StackPane sp = new StackPane();
        sp.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
        });
        Image img = new Image(cont.getFoto(0),100,100,true,true);
        ImageView imv = new ImageView(img);
        sp.getChildren().add(imv);
        FotoPerfil.setAlignment(Pos.CENTER);
        FotoPerfil.getChildren().add(sp);
        FotoPerfil.getChildren().add(new Text(cont.getNombre()+" "+cont.getApellido()));
        VBox tels = new VBox();
        VBox corrs = new VBox();
        VBox reds = new VBox();
        tels.getChildren().addAll(new Text("Personal: " + cont.getTelefonoPrincipal()),new Text("Trabajo: " + cont.getTelefonoTrabajo()),new Text("Casa: " + cont.getTelefonoCasa()));
        corrs.getChildren().addAll(new Text("Principal: " + cont.getCorreoPrincipal()),new Text("Trabajo: " + cont.getCorreoTrabajo()),new Text("Provisional" + cont.getCorreoProvisional()));
        reds.getChildren().addAll(new Text("X: " + cont.getRedesSociales().get(0)),new Text("Face: " + cont.getRedesSociales().get(1)),new Text("Tiktok: " + cont.getRedesSociales().get(2)));
        telefonos.getChildren().add(tels);
        correos.getChildren().add(corrs);
        redesSociales.getChildren().add(reds);
        notas.getChildren().add(new Text(cont.getNota()));
    }
    
    public void clear(){
        FotoPerfil.getChildren().clear();
        telefonos.getChildren().clear();
        correos.getChildren().clear();
        redesSociales.getChildren().clear();
        notas.getChildren().clear();
    }
}