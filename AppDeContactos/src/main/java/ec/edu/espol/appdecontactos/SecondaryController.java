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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button editarBoton;

    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(!contactos.isEmpty() && primer == null){
            it = contactos.CircularIterator();
            primer = it.next();
            actualizarPagina(primer);
        }
        else if(!contactos.isEmpty()){
            it = contactos.CircularIterator();
            Contacto c = null;
            while(it.hasNext() && c != primer){
                c = it.next();
            };
            actualizarPagina(primer);
        }
        else{
            gridTop.getChildren().clear();
            gridMid.getChildren().clear();
            Text text = new Text("No hay contactos");
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
            primer = it.next();
            actualizarPagina(primer);
        }
    }

    @FXML
    private void next(MouseEvent event) {
        primer = it.next();
        clear();
        actualizarPagina(primer);
    }
    
    public void clear(){
        FotoPerfil.getChildren().clear();
        telefonos.getChildren().clear();
        correos.getChildren().clear();
        redesSociales.getChildren().clear();
        notas.getChildren().clear();
    }
    
    private void aplicarEstiloTexto(Text texto){
        texto.setFont(Font.font("Century Gothic", 13));
        texto.setTranslateX(10);
        texto.setTranslateY(10);
    }
    
    public void actualizarPagina(Contacto c){
        StackPane sp = new StackPane();
        sp.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
        });
        gridTop.setBackground(new Background(new BackgroundFill(Color.rgb(223, 223, 223), CornerRadii.EMPTY, Insets.EMPTY)));
        telefonos.setBackground(new Background(new BackgroundFill(Color.rgb(234, 234, 234), CornerRadii.EMPTY, Insets.EMPTY)));
        correos.setBackground(new Background(new BackgroundFill(Color.rgb(234, 234, 234), CornerRadii.EMPTY, Insets.EMPTY)));
        redesSociales.setBackground(new Background(new BackgroundFill(Color.rgb(234, 234, 234), CornerRadii.EMPTY, Insets.EMPTY)));
        scrollPane.setBackground(new Background(new BackgroundFill(Color.rgb(255, 254, 206), CornerRadii.EMPTY, Insets.EMPTY)));
        gridMid.setBackground(new Background(new BackgroundFill(Color.rgb(255, 254, 206), CornerRadii.EMPTY, Insets.EMPTY)));
        notas.setBackground(new Background(new BackgroundFill(Color.rgb(255, 254, 206), CornerRadii.EMPTY, Insets.EMPTY)));
        
        Image img = new Image(c.getFoto(0),100,100,true,true);
        ImageView imv = new ImageView(img);
        imv.setTranslateY(5);
        sp.getChildren().add(imv);
        FotoPerfil.setAlignment(Pos.CENTER);
        FotoPerfil.getChildren().add(sp);
        VBox tels = new VBox();
        VBox corrs = new VBox();
        VBox reds = new VBox();
        if(c instanceof Persona){
             Persona p = (Persona) c;
             Text contacto = new Text(p.getNombre()+" "+p.getApellido());
             contacto.setTranslateY(3);
             contacto.setFont(Font.font("Century Gothic",15));
             FotoPerfil.getChildren().add(contacto);
             
             Text personal = new Text("Personal: " + p.getTelefonoPrincipal());
             Text trabajo = new Text("Trabajo: " + p.getTelefonoTrabajo());
             Text casa = new Text("Casa: " + p.getTelefonoCasa()); 
             
            aplicarEstiloTexto(personal);
            aplicarEstiloTexto(trabajo);
            aplicarEstiloTexto(casa);
                     
            tels.getChildren().addAll(personal, trabajo , casa);
             
                         
            Text principal = new Text("Principal: " + p.getCorreoPrincipal());
            Text trabajoTelf = new Text("Trabajo: " + p.getCorreoTrabajo());
            Text provisional = new Text("Provisional: " + p.getCorreoProvisional());
            
            aplicarEstiloTexto(principal);
            aplicarEstiloTexto(trabajoTelf);
            aplicarEstiloTexto(provisional);
             
            corrs.getChildren().addAll(principal, trabajoTelf ,provisional);
        }
        else{
            Empresa e = (Empresa) c;
            Text contactoEmpresa = new Text(e.getNombre());
            contactoEmpresa.setTranslateY(3);
            contactoEmpresa.setFont(Font.font("Century Gothic",15));
            FotoPerfil.getChildren().add(contactoEmpresa);
            
            Text principal = new Text("Principal: " + e.getTelefonoPrincipal());
            Text whatsApp =  new Text("Whatsapp: " + e.getTelefonoWha());
            Text provisional = new Text("Provisional: " + e.getCorreoProvisional());
            
            aplicarEstiloTexto(principal);
            aplicarEstiloTexto(whatsApp);
            aplicarEstiloTexto(provisional);

            tels.getChildren().addAll(principal, whatsApp, provisional);
            
            
            Text principalEmpresa = new Text("Principal: " + e.getCorreoPrincipal());
            Text secundarioEmpresa = new Text("Secundario: " + e.getCorreoSecundario());
            Text provisionalEmpresa =  new Text("Provisional: " + e.getCorreoProvisional());    
            
            aplicarEstiloTexto(principalEmpresa);
            aplicarEstiloTexto(secundarioEmpresa);
            aplicarEstiloTexto(provisionalEmpresa);            
            
            corrs.getChildren().addAll(principalEmpresa, secundarioEmpresa, provisionalEmpresa);
        }
        
        Text x = new Text("X: " + c.getRedesSociales().get(0));
        Text facebook = new Text("Facebook: " + c.getRedesSociales().get(1));
        Text tiktok = new Text("Tiktok: " + c.getRedesSociales().get(2));
        
        aplicarEstiloTexto(x);
        aplicarEstiloTexto(facebook);
        aplicarEstiloTexto(tiktok);        

        reds.getChildren().addAll(x, facebook, tiktok);
        telefonos.getChildren().add(tels);
        correos.getChildren().add(corrs);
        redesSociales.getChildren().add(reds);
        notas.getChildren().add(new Text(c.getNota()));
    }

    @FXML
    private void volverPrincipal(MouseEvent event) {
        try {
            App.setRoot("primary");
        } catch (IOException ex) {
           
        }
    }

    @FXML
    private void verFotos(MouseEvent event) {
        if(!contactos.isEmpty()){
            SessionManager.getInstance().setContacto(primer);
            try{
                App.setRoot("verFotos");
            }
            catch(IOException e){

            }
        }
    }

    @FXML

    private void verContactosAsociados(MouseEvent event) {
        try{
                App.setRoot("contactosAsociados");
            }
            catch(IOException e){

            }
    }

    @FXML
    private void editarContacto(MouseEvent event) {
        try {
            App.setRoot("craerContacto");
        } catch (IOException e) {
           
        }
    }
    

}