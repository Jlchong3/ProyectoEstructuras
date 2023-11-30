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
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mariu
 */
public class EditarPersonaController implements Initializable {
    // CONTACTO EN CUESTION
    Contacto contactoEdicion = SessionManager.getInstance().getContacto();
    DoubleCircularLinkedList<Contacto> contactosActuales = SessionManager.getInstance().getContactosActuales();

    @FXML
    private VBox cuadro1;
    @FXML
    private VBox cuadr2;
    @FXML
    private VBox cuad3;
    @FXML
    private Button Retroceder;
    @FXML
    private Button Guardar;
    
    //DATOS DEL CONTACTO
    
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

    private TextField fecha;
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
        actualizarPagina(contactoEdicion);
    }    

    public void actualizarPagina(Contacto c){
        
        Text Nombre = new Text("Nombre:");
        Nombre.setFont(new Font(16));
        HBox.setMargin(Nombre, new Insets(5, 5, 5, 5));
        TextField nombre = new TextField();
        HBox.setMargin(nombre, new Insets(0, 0, 0, 5));
        
        Text CorreoPrincipal = new Text("Correo:");
        CorreoPrincipal.setFont(new Font(16));
        VBox.setMargin(CorreoPrincipal, new Insets(20, 0, 0, 0));
        TextField correoPrincipal = new TextField(c.getCorreoPrincipal());
        VBox.setMargin(correoPrincipal, new Insets(5, 0, 0, 0));
        
        Text TelefonoPrincipal = new Text("Telefono:");
        TelefonoPrincipal.setFont(new Font(16));
        VBox.setMargin(TelefonoPrincipal, new Insets(20, 0, 0, 0));
        TextField telefonoPrincipal = new TextField(c.getTelefonoPrincipal());
        VBox.setMargin(telefonoPrincipal, new Insets(5, 0, 0, 0));
        
        //redesSociales
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
        
        
        Text Fecha = new Text("Fecha:");
        Fecha.setFont(new Font(16));
        VBox.setMargin(Fecha, new Insets(20, 0, 0, 0));
        TextField fecha = new TextField(c.getFechas());
        VBox.setMargin(fecha, new Insets(5, 0, 0, 0));
        
        Text Nota = new Text("Nota:");
        Nota.setFont(new Font(16));
        VBox.setMargin(Nota, new Insets(20, 0, 0, 0));
        TextField nota = new TextField(c.getNota());
        VBox.setMargin(nota, new Insets(5, 0, 0, 0));
           
            this.nombre = nombre;
            this.telefonoPrincipal = telefonoPrincipal;
            this.correoPrincipal = correoPrincipal;

            this.facebook=facebook;
            this.instagram=instagram;
            this.tiktok=tiktok;
            this.x=x;

            this.fecha = fecha;
            this.nota =nota;

        
        if(c instanceof Persona){
            valor="Persona";
            Persona p = (Persona) c;

            nombre = new TextField(p.getNombre());

            Text Apellido = new Text("Apellido:");
            Apellido.setFont(new Font(16));
            HBox.setMargin(Apellido, new Insets(5, 5, 5, 5));
            TextField apellido = new TextField(p.getApellido());
            HBox.setMargin(apellido, new Insets(0, 0, 0, 5));

            //cuadro2
            //telf
            TelefonoPrincipal = new Text("Telefono personal:");
            TelefonoPrincipal.setFont(new Font(16));

            Text TelefonoTrabajo = new Text("Telefono Trabajo:");
            TelefonoTrabajo.setFont(new Font(16));
            VBox.setMargin(TelefonoTrabajo, new Insets(20, 0, 0, 0));
            TextField telefonoTrabajo = new TextField(p.getTelefonoTrabajo());
            VBox.setMargin(telefonoTrabajo, new Insets(5, 0, 0, 0));

            Text TelefonoCasa = new Text("Telefono Casa:");
            TelefonoCasa.setFont(new Font(16));
            VBox.setMargin(TelefonoCasa, new Insets(20, 0, 0, 0));
            TextField telefonoCasa = new TextField(p.getTelefonoCasa());
            VBox.setMargin(telefonoCasa, new Insets(5, 0, 0, 0));

            //correo
            CorreoPrincipal = new Text("Correo Personal:");
            CorreoPrincipal.setFont(new Font(16));


            Text CorreoTrabajo = new Text("Correo Trabajo:");
            CorreoTrabajo.setFont(new Font(16));
            VBox.setMargin(CorreoTrabajo, new Insets(20, 0, 0, 0));
            TextField correoTrabajo = new TextField(p.getCorreoTrabajo());
            VBox.setMargin(correoTrabajo, new Insets(5, 0, 0, 0));

            Text CorreoProvisional = new Text("Correo Provisional:");
            CorreoProvisional.setFont(new Font(16));
            VBox.setMargin(CorreoProvisional, new Insets(20, 0, 0, 0));
            TextField correoProvisional = new TextField(p.getCorreoProvisional());
            VBox.setMargin(correoProvisional, new Insets(5, 0, 0, 0));

            cuadro1.getChildren().addAll(Nombre,nombre, Apellido,apellido);
            cuadr2.getChildren().addAll(TelefonoPrincipal,telefonoPrincipal,TelefonoTrabajo,telefonoTrabajo,TelefonoCasa,telefonoCasa,Facebook,facebook,Instagram,instagram,Fecha,fecha);
            cuad3.getChildren().addAll(CorreoPrincipal,correoPrincipal,CorreoTrabajo,correoTrabajo,CorreoProvisional,correoProvisional,TikTok,tiktok, X,x,Nota,nota);
            
            this.nombre = nombre;
            this.apellido = apellido;

            this.telefonoTrabajo = telefonoTrabajo;
            this.telefonoCasa = telefonoCasa;

            this.correoTrabajo=correoTrabajo;
            this.correoProvisional=correoProvisional;

             
        }
        else{
            valor="Empresa";
            Empresa e= (Empresa) c;

            nombre = new TextField(e.getNombre());

            //cuadro2
            //telf
            TelefonoPrincipal = new Text("Telefono Principal:");
            TelefonoPrincipal.setFont(new Font(16));

            Text TelefonoWha = new Text("Telefono WhatsApp:");
            TelefonoWha.setFont(new Font(16));
            VBox.setMargin(TelefonoWha, new Insets(20, 0, 0, 0));
            TextField telefonoWha = new TextField(e.getTelefonoWha());
            VBox.setMargin(telefonoWha, new Insets(5, 0, 0, 0));

            Text TelefonoProvisional = new Text("Telefono Provisional:");
            TelefonoProvisional.setFont(new Font(16));
            VBox.setMargin(TelefonoProvisional, new Insets(20, 0, 0, 0));
            TextField telefonoProvisional = new TextField(e.getCorreoProvisional());
            VBox.setMargin(telefonoProvisional, new Insets(5, 0, 0, 0));

            //correo
            CorreoPrincipal = new Text("Correo Principal:");
            CorreoPrincipal.setFont(new Font(16));

            Text CorreoSecundario = new Text("Correo Secundario:");
            CorreoSecundario.setFont(new Font(16));
            VBox.setMargin(CorreoSecundario, new Insets(20, 0, 0, 0));
            TextField correoSecundario = new TextField(e.getCorreoSecundario());
            VBox.setMargin(correoSecundario, new Insets(5, 0, 0, 0));

            Text CorreoProvisional = new Text("Correo Provisional:");
            CorreoProvisional.setFont(new Font(16));
            VBox.setMargin(CorreoProvisional, new Insets(20, 0, 0, 0));
            TextField correoProvisional = new TextField(e.getCorreoProvisional());
            VBox.setMargin(correoProvisional, new Insets(5, 0, 0, 0));

            cuadro1.getChildren().addAll(Nombre,nombre);
            cuadr2.getChildren().addAll(TelefonoPrincipal,telefonoPrincipal,TelefonoWha,telefonoWha,TelefonoProvisional,telefonoProvisional,Facebook,facebook,Instagram,instagram,Fecha,fecha);
            cuad3.getChildren().addAll(CorreoPrincipal,correoPrincipal,CorreoSecundario,correoSecundario,CorreoProvisional,correoProvisional,TikTok,tiktok, X,x,Nota,nota);

            this.telefonoWha = telefonoWha;
            this.telefonoProvisional = telefonoProvisional;

            this.correoSecundario=correoSecundario;
            this.correoProvisional=correoProvisional;
        }
    }



    @FXML
    private void retrocederPantalla(MouseEvent event) {
        try {
            App.setRoot("secondary");
        } catch (IOException e) {
           
        }
    }
    
    public void regresarTContactos(){
        try {

            App.setRoot("secondary");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void guardarContacto(MouseEvent event) {
        
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
                        Empresa e= (Empresa) contactoEdicion;
                        
                        e.setNombre(nombre.getText());
                        
                        e.setTelefonoPrincipal(telefonoPrincipal.getText());
                        e.setTelefonoWha(telefonoWha.getText());
                        e.setTelefonoProvisional(telefonoProvisional.getText());
                        
                        e.setCorreoPrincipal(correoPrincipal.getText());
                        e.setCorreoProvisional(correoProvisional.getText());
                        e.setCorreoSecundario(correoSecundario.getText());
                        
                        
                        e.setFechas(fecha.getText());
                        e.setNota(nota.getText());
                        
                        regresarTContactos();
                        
                        break;
                    }
                    case "Persona":                    
                    {
                        Persona p= (Persona) contactoEdicion;
                        
                        p.setNombre(nombre.getText());
                        p.setApellido(apellido.getText());
                        
                        p.setTelefonoPrincipal(telefonoPrincipal.getText());
                        p.setTelefonoCasa(telefonoCasa.getText());
                        p.setTelefonoTrabajo(telefonoTrabajo.getText());
                        
                        p.setCorreoPrincipal(correoPrincipal.getText());
                        p.setCorreoProvisional(correoProvisional.getText());
                        p.setCorreoTrabajo(correoTrabajo.getText());
                        
                        p.setFechas(fecha.getText());
                        p.setNota(nota.getText());
                        
                        
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
        Contacto.updateFile(contactosActuales);
        
    }
    
    
    
}
