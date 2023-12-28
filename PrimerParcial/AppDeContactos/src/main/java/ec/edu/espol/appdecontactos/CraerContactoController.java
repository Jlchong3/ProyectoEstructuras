/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.appdecontactos;

import ec.edu.espol.appdecontactos.clases.ArrayList;
import ec.edu.espol.appdecontactos.clases.Contacto;
import ec.edu.espol.appdecontactos.clases.DoubleCircularLinkedList;
import ec.edu.espol.appdecontactos.clases.Empresa;
import ec.edu.espol.appdecontactos.clases.Persona;
import ec.edu.espol.appdecontactos.clases.SessionManager;
import java.io.IOException;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mariu
 */
public class CraerContactoController implements Initializable {
    // scenebuilder
    DoubleCircularLinkedList<Contacto> contactosActuales = SessionManager.getInstance().getContactosActuales();
    @FXML
    private ComboBox<String> tipoContacto;
    @FXML
    private VBox cuadro1;
    @FXML
    private VBox cuadr2;
    @FXML
    private VBox cuad3;

    @FXML
    private Button Agregar;
    @FXML
    private Button Retroceder;

    private TextField telefonoPrincipal;
    private TextField telefonoWha;
    private TextField telefonoProvisional;
    private TextField telefonoTrabajo;
    private TextField telefonoCasa;

    private TextField facebook;
    private TextField instagram;
    private TextField tiktok;
    private TextField x;

    private TextField correoPrincipal;
    private TextField correoSecundario;
    private TextField correoProvisional;
    private TextField correoTrabajo;

    private DatePicker fecha;
    private TextField nota;

    private TextField nombre;
    private TextField apellido;
    
    private Contacto contacto;
    private String valor; 
    
    private ArrayList<String> nombresContactos = new ArrayList<>(); 


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String[] categorias = {"Empresa" , "Persona"};
        
        tipoContacto.getItems().addAll(categorias);
    }
    
    public DoubleCircularLinkedList<String> obtenerDireccionImagenPerfil()
    {
        String directorioImagenes = "file:src/main/resources/ec/edu/espol/appdecontactos/imgs/contactos/";
        DoubleCircularLinkedList<String> fotoDePerfil = new DoubleCircularLinkedList<>();
        String fotoPerfil= new String("");
        switch (tipoContacto.getValue()){
            case "Empresa":
                fotoPerfil = directorioImagenes + "DefaultEmpresaPerfil.png"; //Si el nombre no es de los predeterminados, se escoge una foto aleatoria para ese contacto
                fotoDePerfil.addLast(fotoPerfil);
                
            case "Persona":
                fotoPerfil = directorioImagenes + "DefaultPersonaPerfil.png"; //Si el nombre no es de los predeterminados, se escoge una foto aleatoria para ese contacto
                fotoDePerfil.addLast(fotoPerfil);
                
        }
        
        fotoDePerfil.addLast(fotoPerfil);
        return fotoDePerfil;
    }

    public DoubleCircularLinkedList<String> obtenerDireccionImagenesAsociadas(){
        
        String directorioImagenes = "file:src/main/resources/ec/edu/espol/appdecontactos/imgs/contactos/";
        DoubleCircularLinkedList<String> fotosDelContacto = new DoubleCircularLinkedList<>();
      
        Random random = new Random();
//        String numero = ""+(random.nextInt(4)+1);
//        String fotoPerfil = directorioImagenes + "DefaultPerfil.png"; //Si el nombre no es de los predeterminados, se escoge una foto aleatoria para ese contacto
//        fotosDelContacto.addLast(fotoPerfil); //añade la foto de perfil del contacto en la posición 0

        for(int i = 1; i < 5; i++){
            String numeroA = ""+(random.nextInt(14)+1);
            String fotoAsociada = directorioImagenes + "Extras/FotosAsociadas/" + "Asociada" + numeroA + ".jpeg";
            fotosDelContacto.addLast(fotoAsociada);
        }
        System.out.println(fotosDelContacto);
        return fotosDelContacto;
    }
    
    
    @FXML
    private void filtrarTipo(ActionEvent event) {
        cuadro1.getChildren().clear();
        cuadr2.getChildren().clear();
        cuad3.getChildren().clear();
        ComboBox cb = (ComboBox)event.getSource();
        String s = (String)cb.getValue();
        valor = s;
        
        Stage stage = (Stage) tipoContacto.getScene().getWindow();
        stage.setHeight(650);
        registroGeneral();
    }
    
    public void regresarTContactos(){
        Stage stage = (Stage) tipoContacto.getScene().getWindow();
        stage.setHeight(650);
        try {
            App.setRoot("primary");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
    public void registrarContacto(Contacto contactoNuevo){
        contactosActuales.addLast(contactoNuevo);
        Contacto.updateFile(contactosActuales);
        SessionManager.getInstance().setContactosActuales(contactosActuales);
    }
    
    @FXML
    private void confirmarContacto(MouseEvent event) {
        DoubleCircularLinkedList<Contacto> contactosRelacionados = new DoubleCircularLinkedList<>();
        DoubleCircularLinkedList<String> listaFotosAsociadas= obtenerDireccionImagenesAsociadas();
        DoubleCircularLinkedList<String> listaFotoPerfil= obtenerDireccionImagenPerfil();
        
        ArrayList<String> redesSo= new ArrayList<>();
        redesSo.addLast(facebook.getText());
        redesSo.addLast(instagram.getText());
        redesSo.addLast(tiktok.getText());
        redesSo.addLast(x.getText());
        
        
        String s = valor;
        if(((nombre.getText().equals(""))||(telefonoPrincipal.getText().equals("")))){
            Alert a = new Alert(Alert.AlertType.ERROR,"Datos Principales Incompletos");
            a.show();
        }
        else{

            try{
                switch (s) {
                    case "Empresa":
                    {
                        Empresa e = new Empresa(telefonoPrincipal.getText(), redesSo , listaFotoPerfil,listaFotosAsociadas , correoPrincipal.getText(), fecha.getValue().format(DateTimeFormatter.ISO_DATE).toString(), contactosRelacionados, nota.getText(), nombre.getText(),  telefonoWha.getText(),   telefonoProvisional.getText(),  correoSecundario.getText(),  correoProvisional.getText());
                        registrarContacto(e);
                        regresarTContactos();
                        break;
                    }
                    case "Persona":                    
                    {
                        Persona p = new Persona(telefonoPrincipal.getText(),redesSo,listaFotoPerfil,listaFotosAsociadas, correoPrincipal.getText() , fecha.getValue().format(DateTimeFormatter.ISO_DATE).toString() , contactosRelacionados, nota.getText(), nombre.getText(), apellido.getText(), telefonoTrabajo.getText(), telefonoCasa.getText(), correoTrabajo.getText(), correoProvisional.getText());
                        registrarContacto(p);
                        regresarTContactos();
                        break;
                    }
                    default:
                        break;
                }

            }
            catch(NumberFormatException e){
                Alert a = new Alert(Alert.AlertType.ERROR,"ERROR DE NO SE QUE CONTACTOS CONTROLLER");
                a.show();
            }
        }
    }

    
     public void registroGeneral(){
//         
        Text Nombre = new Text("Nombre:");
        Nombre.setFont(new Font(16));
        HBox.setMargin(Nombre, new Insets(5, 5, 5, 5));
        TextField nombre = new TextField();
        HBox.setMargin(nombre, new Insets(0, 0, 0, 5));
//    public String telefonoPrincipal;
        Text TelefonoPrincipal = new Text("Telefono:");
        VBox.setMargin(TelefonoPrincipal, new Insets(20, 0, 0, 0));
        TextField telefonoPrincipal = new TextField();
        VBox.setMargin(telefonoPrincipal, new Insets(5, 0, 0, 0));

//    public DoubleCircularLinkedList<String> fotos;

//    public ArrayList<String> redesSociales;
        Text Facebook = new Text("Facebook:");
        Facebook.setFont(new Font(16));
        VBox.setMargin(Facebook, new Insets(20, 0, 0, 0));
        TextField facebook = new TextField();
        VBox.setMargin(facebook, new Insets(5, 0, 0, 0));

        Text Instagram = new Text("Instagram:");
        Instagram.setFont(new Font(16));
        VBox.setMargin(Instagram, new Insets(20, 0, 0, 0));
        TextField instagram = new TextField();
        VBox.setMargin(instagram, new Insets(5, 0, 0, 0));

        Text TikTok = new Text("TikTok:");
        TikTok.setFont(new Font(16));
        VBox.setMargin(TikTok, new Insets(20, 0, 0, 0));
        TextField tiktok = new TextField();
        VBox.setMargin(tiktok, new Insets(5, 0, 0, 0));

        Text X = new Text("X:");
        X.setFont(new Font(16));
        VBox.setMargin(X, new Insets(20, 0, 0, 0));
        TextField x = new TextField();
        VBox.setMargin(x, new Insets(5, 0, 0, 0));

//    public String correoPrincipal;
        Text CorreoPrincipal = new Text("Correo:");
        CorreoPrincipal.setFont(new Font(16));
        VBox.setMargin(CorreoPrincipal, new Insets(20, 0, 0, 0));
        TextField correoPrincipal = new TextField();
        VBox.setMargin(correoPrincipal, new Insets(5, 0, 0, 0));

//    public String fechas;
        Text Fecha = new Text("Fecha:");
        Fecha.setFont(new Font(16));
        VBox.setMargin(Fecha, new Insets(20, 0, 0, 0));
        DatePicker fecha = new DatePicker();
        VBox.setMargin(fecha, new Insets(5, 0, 0, 0));

//    public Contacto contactoRelacionado;
//    public String nota;
        Text Nota = new Text("Nota:");
        Nota.setFont(new Font(16));
        VBox.setMargin(Nota, new Insets(20, 0, 0, 0));
        TextField nota = new TextField();
        VBox.setMargin(nota, new Insets(5, 0, 0, 0));

        this.nombre=nombre;
        this.telefonoPrincipal = telefonoPrincipal;
        this.correoPrincipal = correoPrincipal;
        this.facebook=facebook;
        this.instagram=instagram;
        this.tiktok=tiktok;
        this.x=x;
        
        this.fecha = fecha;
        this.nota =nota;
        
        
        String s = valor;
        switch (s) {
            case "Empresa":
            {
                //cuadro2
                //telf
                TelefonoPrincipal = new Text("Telefono Principal:");
                TelefonoPrincipal.setFont(new Font(16));

                Text TelefonoWha = new Text("Telefono WhatsApp:");
                TelefonoWha.setFont(new Font(16));
                VBox.setMargin(TelefonoWha, new Insets(20, 0, 0, 0));
                TextField telefonoWha = new TextField();
                VBox.setMargin(telefonoWha, new Insets(5, 0, 0, 0));

                Text TelefonoProvisional = new Text("Telefono Provisional:");
                TelefonoProvisional.setFont(new Font(16));
                VBox.setMargin(TelefonoProvisional, new Insets(20, 0, 0, 0));
                TextField telefonoProvisional = new TextField();
                VBox.setMargin(telefonoProvisional, new Insets(5, 0, 0, 0));

                //correo
                CorreoPrincipal = new Text("Correo Principal:");
                CorreoPrincipal.setFont(new Font(16));
                
                Text CorreoSecundario = new Text("Correo Secundario:");
                CorreoSecundario.setFont(new Font(16));
                VBox.setMargin(CorreoSecundario, new Insets(20, 0, 0, 0));
                TextField correoSecundario = new TextField();
                VBox.setMargin(correoSecundario, new Insets(5, 0, 0, 0));

                Text CorreoProvisional = new Text("Correo Provisional:");
                CorreoProvisional.setFont(new Font(16));
                VBox.setMargin(CorreoProvisional, new Insets(20, 0, 0, 0));
                TextField correoProvisional = new TextField();
                VBox.setMargin(correoProvisional, new Insets(5, 0, 0, 0));

                cuadro1.getChildren().addAll(Nombre,nombre);
                cuadr2.getChildren().addAll(TelefonoPrincipal,telefonoPrincipal,TelefonoWha,telefonoWha,TelefonoProvisional,telefonoProvisional,Facebook,facebook,Instagram,instagram,Fecha,fecha);
                cuad3.getChildren().addAll(CorreoPrincipal,correoPrincipal,CorreoSecundario,correoSecundario,CorreoProvisional,correoProvisional,TikTok,tiktok, X,x,Nota,nota);

                this.telefonoWha = telefonoWha;
                this.telefonoProvisional = telefonoProvisional;

                this.correoSecundario=correoSecundario;
                this.correoProvisional=correoProvisional;

                break;
            }
            case "Persona":                    
            {
//                Text Nombre = new Text("Nombre:");
//                Nombre.setFont(new Font(16));
//                HBox.setMargin(Nombre, new Insets(5, 5, 5, 5));
//                TextField nombre = new TextField();
//                HBox.setMargin(nombre, new Insets(0, 0, 0, 5));

                Text Apellido = new Text("Apellido:");
                Apellido.setFont(new Font(16));
                HBox.setMargin(Apellido, new Insets(5, 5, 5, 5));
                TextField apellido = new TextField();
                HBox.setMargin(apellido, new Insets(0, 0, 0, 5));

                //cuadro2
                //telf
                TelefonoPrincipal = new Text("Telefono personal:");
                TelefonoPrincipal.setFont(new Font(16));

                Text TelefonoTrabajo = new Text("Telefono Trabajo:");
                TelefonoTrabajo.setFont(new Font(16));
                VBox.setMargin(TelefonoTrabajo, new Insets(20, 0, 0, 0));
                TextField telefonoTrabajo = new TextField();
                VBox.setMargin(telefonoTrabajo, new Insets(5, 0, 0, 0));

                Text TelefonoCasa = new Text("Telefono Casa:");
                TelefonoCasa.setFont(new Font(16));
                VBox.setMargin(TelefonoCasa, new Insets(20, 0, 0, 0));
                TextField telefonoCasa = new TextField();
                VBox.setMargin(telefonoCasa, new Insets(5, 0, 0, 0));

                //correo
                CorreoPrincipal = new Text("Correo Personal:");
                CorreoPrincipal.setFont(new Font(16));
                
                Text CorreoTrabajo = new Text("Correo Trabajo:");
                CorreoTrabajo.setFont(new Font(16));
                VBox.setMargin(CorreoTrabajo, new Insets(20, 0, 0, 0));
                TextField correoTrabajo = new TextField();
                VBox.setMargin(correoTrabajo, new Insets(5, 0, 0, 0));

                Text CorreoProvisional = new Text("Correo Provisional:");
                CorreoProvisional.setFont(new Font(16));
                VBox.setMargin(CorreoProvisional, new Insets(20, 0, 0, 0));
                TextField correoProvisional = new TextField();
                VBox.setMargin(correoProvisional, new Insets(5, 0, 0, 0));

                cuadro1.getChildren().addAll(Nombre,nombre, Apellido,apellido);
                cuadr2.getChildren().addAll(TelefonoPrincipal,telefonoPrincipal,TelefonoTrabajo,telefonoTrabajo,TelefonoCasa,telefonoCasa,Facebook,facebook,Instagram,instagram,Fecha,fecha);
                cuad3.getChildren().addAll(CorreoPrincipal,correoPrincipal,CorreoTrabajo,correoTrabajo,CorreoProvisional,correoProvisional,TikTok,tiktok, X,x,Nota,nota);

                this.apellido = apellido;

                this.telefonoTrabajo = telefonoTrabajo;
                this.telefonoCasa = telefonoCasa;

                this.correoTrabajo=correoTrabajo;
                this.correoProvisional=correoProvisional;

                break;
            }
            default:
                break;
        }  
     }
    

    @FXML
    private void retrocederPantalla(MouseEvent event) {
        regresarTContactos();
    } 
    

}