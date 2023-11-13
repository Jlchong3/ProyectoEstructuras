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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    private HBox cuadro1;
    @FXML
    private VBox cuadro2;
    @FXML
    private VBox cuadro3;
    @FXML
    private Button Agregar;
    @FXML
    private Button Retroceder;

    //yo cree ahorita
    private TextField telefonoPrincipal;
    private TextField telefonoWha;
    private TextField telefonoProvisional;
    private TextField telefonoTrabajo;
    private TextField telefonoCasa;

    private TextField contactos;
    private TextField redesSociales;
    private TextField foto;

    private TextField correoPrincipal;
    private TextField correoSecundario;
    private TextField correoProvisional;
    private TextField correoTrabajo;

    private TextField fecha;
    private TextField contactoRelacionado;        
    private TextField nota;

    private TextField nombre;
    private TextField apellido;
    
    private Contacto contacto;
    private String valor; 


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String[] categorias = {"Empresa" , "Persona"};
        
        tipoContacto.getItems().addAll(categorias);
    }  
    
    @FXML
    private void filtrarTipo(ActionEvent event) {
        cuadro1.getChildren().clear();
        cuadro2.getChildren().clear();
        cuadro3.getChildren().clear();
        ComboBox cb = (ComboBox)event.getSource();
        String s = (String)cb.getValue();
        valor = s;
        
        if(s.equals("Empresa")){
            Stage stage = (Stage) tipoContacto.getScene().getWindow();
            stage.setHeight(550);
            registroEmpresa();
            
        }
        else if(s.equals("Persona")){
            Stage stage = (Stage) tipoContacto.getScene().getWindow();
            stage.setHeight(550);
            registroPersona();

        }
        
    }
    
    public void regresarTContactos(){
        Stage stage = (Stage) tipoContacto.getScene().getWindow();
        stage.setHeight(550);
        try {
            
            
            App.setRoot("primary");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void registrarContacto(Contacto contactoNuevo){
        contactosActuales.addFirst(contactoNuevo);
        contactoNuevo.updateFile(contactosActuales);
        SessionManager.getInstance().setContactosActuales(contactosActuales);
    }
    
    @FXML
    private void confirmarContacto(MouseEvent event) {
        String correoTrabajo=null;
        int telefonoProvisional=0;
        int telefonoCasa=0;
        String correoProvisional=null;
        Contacto contactoRelacionado=null;
        
        Button b = (Button)event.getSource();
        String s = valor;
        if((nombre.getText().equals(""))&&(apellido.getText().equals(""))&&(telefonoPrincipal.getText().equals(""))){
            Alert a = new Alert(Alert.AlertType.ERROR,"Datos Principales Incompletos");
            a.show();
        }
        else{
            try{
                switch (s) {
                    case "Empresa":
                    {
                        int i = Integer.parseInt(telefonoPrincipal.getText());
                        int j = Integer.parseInt(telefonoWha.getText());

                        ArrayList<String> redesSocialessssss = new ArrayList<>();
                        redesSocialessssss.addLast(redesSociales.getText());

                        DoubleCircularLinkedList<String> fotossssss = new DoubleCircularLinkedList<>();
                        fotossssss.addLast(foto.getText());

                        Empresa e = new Empresa(i, redesSocialessssss , fotossssss , correoPrincipal.getText(), fecha.getText(), contactoRelacionado, nota.getText(), nombre.getText(),  j,   telefonoProvisional,  correoSecundario.getText(),  correoProvisional);
                        
                        System.out.println(e.toString());
                        registrarContacto(e);
                        
                        
                        
                        
                        //RETROCEDER
                        regresarTContactos();
                        break;
                    }
                    case "Persona":                    
                    {
                        int i = Integer.parseInt(telefonoPrincipal.getText());
                        int j = Integer.parseInt(telefonoTrabajo.getText());

                        ArrayList<String> redesSocialessssss = new ArrayList<>();
                        redesSocialessssss.addLast(redesSociales.getText());

                        DoubleCircularLinkedList<String> fotossssss = new DoubleCircularLinkedList<>();
                        fotossssss.addLast(foto.getText());

                        Persona p = new Persona(i,redesSocialessssss,fotossssss, correoPrincipal.getText() , fecha.getText() , contactoRelacionado, nota.getText(), nombre.getText(), apellido.getText(), j, telefonoCasa, correoTrabajo, correoProvisional);
                        System.out.println(p.toString());
                        registrarContacto(p);
                        
                        
                        //RETROCEDER
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

    private void registroPersona(){
        Text Nombre = new Text("Nombre:");
        Nombre.setFont(new Font(16));
        VBox.setMargin(Nombre, new Insets(20, 0, 0, 0));
        TextField nombre = new TextField();
        VBox.setMargin(nombre, new Insets(5, 0, 0, 0));
        
        Text Apellido = new Text("Apellido:");
        Apellido.setFont(new Font(16));
        VBox.setMargin(Apellido, new Insets(20, 0, 0, 0));
        TextField apellido = new TextField();
        VBox.setMargin(apellido, new Insets(5, 0, 0, 0));
        
        Text TelefonoPrincipal = new Text("Telefono personal:");
        TelefonoPrincipal.setFont(new Font(16));
        VBox.setMargin(TelefonoPrincipal, new Insets(20, 0, 0, 0));
        TextField telefonoPrincipal = new TextField();
        VBox.setMargin(telefonoPrincipal, new Insets(5, 0, 0, 0));
        
        Text TelefonoTrabajo = new Text("Telefono Trabajo:");
        TelefonoTrabajo.setFont(new Font(16));
        VBox.setMargin(TelefonoTrabajo, new Insets(20, 0, 0, 0));
        TextField telefonoTrabajo = new TextField();
        VBox.setMargin(telefonoTrabajo, new Insets(5, 0, 0, 0));
        
        Text CorreoPrincipal = new Text("Correo Personal:");
        CorreoPrincipal.setFont(new Font(16));
        VBox.setMargin(CorreoPrincipal, new Insets(20, 0, 0, 0));
        TextField correoPrincipal = new TextField();
        VBox.setMargin(correoPrincipal, new Insets(5, 0, 0, 0));

        Text CorreoTrabajo = new Text("Correo Trabajo:");
        CorreoTrabajo.setFont(new Font(16));
        VBox.setMargin(CorreoTrabajo, new Insets(20, 0, 0, 0));
        TextField correoTrabajo = new TextField();
        VBox.setMargin(correoTrabajo, new Insets(5, 0, 0, 0));
        
        Text RedesSociales = new Text("Red Social:");
        RedesSociales.setFont(new Font(16));
        VBox.setMargin(RedesSociales, new Insets(20, 0, 0, 0));
        TextField redesSociales = new TextField();
        VBox.setMargin(redesSociales, new Insets(5, 0, 0, 0));
        
        
        Text Fecha = new Text("Fecha:");
        Fecha.setFont(new Font(16));
        VBox.setMargin(Fecha, new Insets(20, 0, 0, 0));
        TextField fecha = new TextField();
        VBox.setMargin(fecha, new Insets(5, 0, 0, 0));
        
        Text Foto = new Text("Foto:");
        Foto.setFont(new Font(16));
        VBox.setMargin(Foto, new Insets(20, 0, 0, 0));
        //TextField foto = new TextField();
        Button btn = new Button(" Agregar ");
        VBox.setMargin(btn, new Insets(5, 0, 0, 0));  
        
        
        Text Nota = new Text("Nota:");
        Nota.setFont(new Font(16));
        VBox.setMargin(Nota, new Insets(20, 0, 0, 0));
        TextField nota = new TextField();
        VBox.setMargin(nota, new Insets(5, 0, 0, 0));

        cuadro1.getChildren().addAll(Nombre,nombre, Apellido,apellido);
        cuadro2.getChildren().addAll( TelefonoPrincipal, telefonoPrincipal, TelefonoTrabajo, telefonoTrabajo,CorreoPrincipal,correoPrincipal,CorreoTrabajo,correoTrabajo);
        cuadro3.getChildren().addAll( RedesSociales,redesSociales,Fecha,fecha,Foto, btn, Nota,nota);
 
        this.nombre = nombre;
        this.apellido = apellido;

        this.telefonoPrincipal = telefonoPrincipal;
        this.telefonoTrabajo = telefonoTrabajo;
        this.telefonoCasa = telefonoCasa;

        this.correoPrincipal = correoPrincipal;
        this.correoTrabajo=correoTrabajo;
        this.correoProvisional=correoProvisional;
  
        this.redesSociales=redesSociales;
        this.fecha = fecha;
        this.foto = foto;
        this.nota =nota ;
    }
    
    private void registroEmpresa(){
        Text Nombre = new Text("Nombre:");
        Nombre.setFont(new Font(16));
        VBox.setMargin(Nombre, new Insets(20, 0, 0, 0));
        TextField nombre = new TextField();
        VBox.setMargin(nombre, new Insets(5, 0, 0, 0));
        
        Text TelefonoPrincipal = new Text("Telefono Principal:");
        TelefonoPrincipal.setFont(new Font(16));
        VBox.setMargin(TelefonoPrincipal, new Insets(20, 0, 0, 0));
        TextField telefonoPrincipal = new TextField();
        VBox.setMargin(telefonoPrincipal, new Insets(5, 0, 0, 0));
        
        Text TelefonoWha = new Text("Telefono WhatsApp:");
        TelefonoWha.setFont(new Font(16));
        VBox.setMargin(TelefonoWha, new Insets(20, 0, 0, 0));
        TextField telefonoWha = new TextField();
        VBox.setMargin(telefonoWha, new Insets(5, 0, 0, 0));
        
        Text CorreoPrincipal = new Text("Correo Personal:");
        CorreoPrincipal.setFont(new Font(16));
        VBox.setMargin(CorreoPrincipal, new Insets(20, 0, 0, 0));
        TextField correoPrincipal = new TextField();
        VBox.setMargin(correoPrincipal, new Insets(5, 0, 0, 0));

        Text CorreoSecundario = new Text("Correo Secundario:");
        CorreoSecundario.setFont(new Font(16));
        VBox.setMargin(CorreoSecundario, new Insets(20, 0, 0, 0));
        TextField correoSecundario = new TextField();
        VBox.setMargin(correoSecundario, new Insets(5, 0, 0, 0));
        
        Text RedesSociales = new Text("Red Social:");
        RedesSociales.setFont(new Font(16));
        VBox.setMargin(RedesSociales, new Insets(20, 0, 0, 0));
        TextField redesSociales = new TextField();
        VBox.setMargin(redesSociales, new Insets(5, 0, 0, 0));
        
        Text Fecha = new Text("Fecha:");
        Fecha.setFont(new Font(16));
        VBox.setMargin(Fecha, new Insets(20, 0, 0, 0));
        TextField fecha = new TextField();
        VBox.setMargin(fecha, new Insets(5, 0, 0, 0));
        
        Text Foto = new Text("Foto:");
        Foto.setFont(new Font(16));
        VBox.setMargin(Foto, new Insets(20, 0, 0, 0));
        //TextField foto = new TextField();
        Button btn = new Button(" Agregar ");
        VBox.setMargin(btn, new Insets(5, 0, 0, 0));  
        
        
        Text Nota = new Text("Nota:");
        Nota.setFont(new Font(16));
        VBox.setMargin(Nota, new Insets(20, 0, 0, 0));
        TextField nota = new TextField();
        VBox.setMargin(nota, new Insets(5, 0, 0, 0));

        cuadro1.getChildren().addAll(Nombre,nombre);
        cuadro2.getChildren().addAll( TelefonoPrincipal, telefonoPrincipal, TelefonoWha, telefonoWha,CorreoPrincipal,correoPrincipal,CorreoSecundario, correoSecundario);
        cuadro3.getChildren().addAll( RedesSociales,redesSociales,Fecha,fecha,Foto, btn, Nota,nota);
 
        this.nombre = nombre;

        this.telefonoPrincipal = telefonoPrincipal;
        this.telefonoWha = telefonoWha;
        this.telefonoProvisional = telefonoProvisional;

        this.correoPrincipal = correoPrincipal;
        this.correoSecundario=correoSecundario;
        this.correoProvisional=correoProvisional;
  
        this.redesSociales=redesSociales;
        this.fecha = fecha;
        this.foto = foto;
        this.nota =nota;
    }

    @FXML
    private void retrocederPantalla(MouseEvent event) {
        regresarTContactos();
    } 
}
