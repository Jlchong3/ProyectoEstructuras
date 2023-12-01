package ec.edu.espol.appdecontactos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;    
import ec.edu.espol.appdecontactos.clases.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class SecondaryController implements Initializable {
    private boolean isin = SessionManager.getInstance().isFromAsociados();
    Contacto primer =(isin) ? SessionManager.getInstance().getContactoRelacionado() : SessionManager.getInstance().getContacto();
    ListIterator<Contacto> it;
    private DoubleCircularLinkedList<Contacto> contactos = (SessionManager.getInstance().getContactosFiltrados().isEmpty()) ? SessionManager.getInstance().getContactosActuales() : SessionManager.getInstance().getContactosFiltrados();
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
    //private AnchorPane notas;
    @FXML
    private AnchorPane redesSociales;
    @FXML
    private Button editarBoton;
    
    ImageView imv = new ImageView();
    @FXML
    private GridPane Content;
    @FXML
    private HBox rightTop;
    @FXML
    private Text textoNota;
    @FXML
    private Text textoFecha;
    @FXML
    private VBox nota;

    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imv.setTranslateY(5);
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
        primer = it.previous();
        clear();
        actualizarPagina(primer);
    }

    @FXML
    private void delete(MouseEvent event) {
        it.remove();
        clear();
        if(!isin){
            Contacto.updateFile(contactos);
            SessionManager.getInstance().setContactosActuales(contactos);
        }
        else{
            SessionManager.getInstance().getContacto().setContactosRelacionados(contactos);
        }
        if(contactos.isEmpty()){
            gridTop.getChildren().clear();
            gridMid.getChildren().clear();
            gridTop.setBackground(Background.EMPTY);
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
        //notas.getChildren().clear();
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
        nota.setBackground(new Background(new BackgroundFill(Color.rgb(255, 254, 206), CornerRadii.EMPTY, Insets.EMPTY)));
        
        Image img = new Image(c.getFotoPerfil(0),100,100,true,true);
        imv.setImage(img);
        imv.setOnMouseClicked(event -> {
            abrirSelectorDeArchivo();
        });
        
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
            Text provisional = new Text("Provisional: " + e.getTelefonoProvisional());
            
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
        
        Text x = new Text("X: " + c.getRedesSociales().get(3));
        Text facebook = new Text("Facebook: " + c.getRedesSociales().get(0));
        Text tiktok = new Text("Tiktok: " + c.getRedesSociales().get(2));
        Text inst = new Text("Instagram: " + c.getRedesSociales().get(1));
        
        aplicarEstiloTexto(x);
        aplicarEstiloTexto(facebook);
        aplicarEstiloTexto(tiktok); 
        aplicarEstiloTexto(inst); 

        reds.getChildren().addAll(x, facebook, tiktok,inst);
        telefonos.getChildren().add(tels);
        correos.getChildren().add(corrs);
        redesSociales.getChildren().add(reds);
        textoNota.setText(c.getNota());
        textoFecha.setText(c.getFechas());
    }

    @FXML
    private void volverPrincipal(MouseEvent event) {
        DoubleCircularLinkedList<Contacto> filt = SessionManager.getInstance().getContactosFiltrados();
        filt = new DoubleCircularLinkedList<>();
        SessionManager.getInstance().setContactosFiltrados(filt);
        if(isin){
            SessionManager.getInstance().getContacto().setContactosRelacionados(contactos);
            System.out.println(SessionManager.getInstance().getContacto().getContactosRelacionados());
            try{
                App.setRoot("contactosAsociados");
            }
            catch(IOException e){}
        }
        else{
            try {
                App.setRoot("primary");
            } 
            catch (IOException ex) {

            }
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
        if(!contactos.isEmpty()){
            SessionManager.getInstance().setContacto(primer);
            try{
                App.setRoot("contactosAsociados");

            }
            catch(IOException e){
            }
        }
    }

    @FXML
    private void editarContacto(MouseEvent event) {
        if(!contactos.isEmpty()){
            SessionManager.getInstance().setContacto(primer);
            try{
                App.setRoot("editarPersona");
            }
            catch(IOException e){

            }
        }
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
            imv.setImage(new Image(destino.toUri().toString(),100,100,true,true));
            primer.getFotoPerfil().set(0, destino.toUri().toString());
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar el error
        }
    }
//    private void copiarImagenAProyecto(File archivoOrigen) {
//        try {
//            // Suponiendo que 'imagenActual' es la URL de la imagen actual
//            Path destino = convertirURLaPath(Paths.get("file:src/main/resources/ec/edu/espol/appdecontactos/imgs/contactos/", archivoOrigen.getName())); // imagenActual debe ser la URL de la imagen actual
//            if (destino != null) {
//                Files.copy(archivoOrigen.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);
//                imageView.setImage(new Image(destino.toUri().toString()));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Manejar el error
//        }
//    }
//
//    private Path convertirURLaPath(String urlImagen) {
//        try {
//            URL url = new URL(urlImagen);
//            URI uri = url.toURI();
//            return Paths.get(uri);
//        } catch (URISyntaxException | MalformedURLException e) {
//            e.printStackTrace();
//            // Manejar el error
//            return null;
//        }
//    }


}
