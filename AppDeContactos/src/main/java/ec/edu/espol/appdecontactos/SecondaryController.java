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
    Contacto primer = SessionManager.getInstance().getContacto();
    ListIterator<Contacto> it;
    private DoubleCircularLinkedList<Contacto> contactos = SessionManager.getInstance().getContactosActuales();;
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
        it = contactos.CircularIterator();
        if(!contactos.isEmpty() && primer == null){
            Contacto primer = it.next();
            actualizarPagina(primer);
        }
        else if(!contactos.isEmpty()){
            Contacto c = null;
            while(it.hasNext() && c == primer){
                c = it.next();
            }
            actualizarPagina(primer);
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
    

    @FXML
    private void previous(MouseEvent event) {
        Contacto c = it.previous();
        clear();
        actualizarPagina(c);
    }

    @FXML
    private void delete(MouseEvent event) {
        it.remove();
        clear();
        Contacto.updateFile(contactos);
        SessionManager.getInstance().setContactosActuales(contactos);
        if(contactos.isEmpty()){
            gridTop.getChildren().clear();
            gridMid.getChildren().clear();
            Text text = new Text("No hay contactos");
            gridMid.setHalignment(text, HPos.CENTER);
            gridMid.add(text,0,1);
        }
        else{
            Contacto c = it.next();
            actualizarPagina(c);
        }
    }

    @FXML
    private void next(MouseEvent event) {
        Contacto c = it.next();
        clear();
        actualizarPagina(c);
    }
    
    public void clear(){
        FotoPerfil.getChildren().clear();
        telefonos.getChildren().clear();
        correos.getChildren().clear();
        redesSociales.getChildren().clear();
        notas.getChildren().clear();
    }
    
    public void actualizarPagina(Contacto c){
        StackPane sp = new StackPane();
        sp.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
        });
        
        Image img = new Image(c.getFoto(0),100,100,true,true);
        ImageView imv = new ImageView(img);
        sp.getChildren().add(imv);
        FotoPerfil.setAlignment(Pos.CENTER);
        FotoPerfil.getChildren().add(sp);
        VBox tels = new VBox();
        VBox corrs = new VBox();
        VBox reds = new VBox();
        if(c instanceof Persona){
             Persona p = (Persona) c;
             FotoPerfil.getChildren().add(new Text(p.getNombre()+" "+p.getApellido()));
             tels.getChildren().addAll(new Text("Personal: " + p.getTelefonoPrincipal()),new Text("Trabajo: " + p.getTelefonoTrabajo()),new Text("Casa: " + p.getTelefonoCasa()));
             corrs.getChildren().addAll(new Text("Principal: " + p.getCorreoPrincipal()),new Text("Trabajo: " + p.getCorreoTrabajo()),new Text("Provisional" + p.getCorreoProvisional()));
        }
        else{
            Empresa e = (Empresa) c;
            FotoPerfil.getChildren().add(new Text(e.getNombre()));
            tels.getChildren().addAll(new Text("Principal: " + e.getTelefonoPrincipal()),new Text("Whatsapp: " + e.getTelefonoWha()),new Text("Provicisonal: " + e.getCorreoProvisional()));
            corrs.getChildren().addAll(new Text("Principal: " + e.getCorreoPrincipal()),new Text("Secundario: " + e.getCorreoSecundario()),new Text("Provisional" + e.getCorreoProvisional()));
        }
        reds.getChildren().addAll(new Text("X: " + c.getRedesSociales().get(0)),new Text("Face: " + c.getRedesSociales().get(1)),new Text("Tiktok: " + c.getRedesSociales().get(2)));
        telefonos.getChildren().add(tels);
        correos.getChildren().add(corrs);
        redesSociales.getChildren().add(reds);
        notas.getChildren().add(new Text(c.getNota()));
    }
}