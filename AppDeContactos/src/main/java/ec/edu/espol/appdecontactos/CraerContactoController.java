/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.appdecontactos;

import ec.edu.espol.appdecontactos.clases.Contacto;
import ec.edu.espol.appdecontactos.clases.Empresa;
import ec.edu.espol.appdecontactos.clases.Persona;
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
    private TextField telefonoPersonal;
    private TextField telefonoTrabajo;
    private TextField telefonoCasa;
    private TextField redesSociales;
    private TextField foto;
    private TextField contactos;
    private TextField correoPersonal;
    private TextField correoTrabajo;
    private TextField correoProvisional;
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
    
    public void registrarContacto(Contacto contactoNuevo){
        contacto.añadirContacto(contactoNuevo);
    }
    
    @FXML
    private void confirmarContacto(MouseEvent event) {
        String correoTrabajo= null;
        String correoProvisional= null;
        Contacto contactoRelacionado= null;
        Button b = (Button)event.getSource();
        String s = valor;
        if((nombre.getText().equals(""))||(apellido.getText().equals(""))||(telefono.getText().equals(""))){
            Alert a = new Alert(Alert.AlertType.ERROR,"Datos Principales Incompletos");
            a.show();
        }
        else{
            try{
                switch (s) {
                    case "Empresa":
                    {
                        Empresa e = new Empresa(Integer.parseInt(telefono.getText()), redesSociales.getText(), foto.getText(), correoPersonal.getText(),correoTrabajo,correoProvisional, fecha.getText(), contactoRelacionado, nota.getText(), nombre.getText());
                        registrarContacto(e);
                        
                        //RETROCEDER
                        break;
                    }
                    case "Persona":                    
                    {
                        Persona p = new Persona(Integer.parseInt(telefono.getText()), redesSociales.getText(), foto.getText(), correoPersonal.getText(),correoTrabajo,correoProvisional, fecha.getText(), contactoRelacionado, nota.getText(), nombre.getText(), apellido.getText());
                        registrarContacto(p);
                        
                        //RETROCEDER
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
        
        Text TelefonoPersonal = new Text("Telefono personal:");
        TelefonoPersonal.setFont(new Font(16));
        VBox.setMargin(TelefonoPersonal, new Insets(20, 0, 0, 0));
        TextField telefonoPersonal = new TextField();
        VBox.setMargin(telefonoPersonal, new Insets(5, 0, 0, 0));
        
        Text TelefonoTrabajo = new Text("Telefono Trabajo:");
        TelefonoTrabajo.setFont(new Font(16));
        VBox.setMargin(TelefonoTrabajo, new Insets(20, 0, 0, 0));
        TextField telefonoTrabajo = new TextField();
        VBox.setMargin(telefonoTrabajo, new Insets(5, 0, 0, 0));
        
        
        Text Correo = new Text("Correo:");
        Correo.setFont(new Font(16));
        VBox.setMargin(Correo, new Insets(20, 0, 0, 0));
        TextField correoPersonal = new TextField();
        VBox.setMargin(correoPersonal, new Insets(5, 0, 0, 0));
        
        Text RedSocial = new Text("Red Social:");
        RedSocial.setFont(new Font(16));
        VBox.setMargin(RedSocial, new Insets(20, 0, 0, 0));
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
        TextField foto = new TextField();
        VBox.setMargin(foto, new Insets(5, 0, 0, 0));
        
        
        Text Nota = new Text("Nota:");
        Nota.setFont(new Font(16));
        VBox.setMargin(Nota, new Insets(20, 0, 0, 0));
        TextField nota = new TextField();
        VBox.setMargin(nota, new Insets(5, 0, 0, 0));

        cuadro1.getChildren().addAll(Nombre,nombre, Apellido,apellido);
        cuadro2.getChildren().addAll( Telefono,telefono,Correo,correoPersonal,RedSocial,redesSociales);
        cuadro3.getChildren().addAll( Fecha,fecha,Foto, foto, Nota,nota);
 
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correoPersonal = correoPersonal;
        this.fecha = fecha;
        this.nota = nota;
    }
    
    private void registroEmpresa(){
        Text Nombre = new Text("Nombre:");
        Nombre.setFont(new Font(16));
        VBox.setMargin(Nombre, new Insets(20, 0, 0, 0));
        TextField nombre = new TextField();
        VBox.setMargin(nombre, new Insets(5, 0, 0, 0));
        
        Text Telefono = new Text("Telefono:");
        Telefono.setFont(new Font(16));
        VBox.setMargin(Telefono, new Insets(20, 0, 0, 0));
        TextField telefono = new TextField();
        VBox.setMargin(telefono, new Insets(5, 0, 0, 0));
        
        Text Correo = new Text("Correo:");
        Correo.setFont(new Font(16));
        VBox.setMargin(Correo, new Insets(20, 0, 0, 0));
        TextField correoPersonal = new TextField();
        VBox.setMargin(correoPersonal, new Insets(5, 0, 0, 0));
        
        Text RedSocial = new Text("Red Social:");
        RedSocial.setFont(new Font(16));
        VBox.setMargin(RedSocial, new Insets(20, 0, 0, 0));
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
        TextField foto = new TextField();
        VBox.setMargin(foto, new Insets(5, 0, 0, 0));
        
        Text Nota = new Text("Nota:");
        Nota.setFont(new Font(16));
        VBox.setMargin(Nota, new Insets(20, 0, 0, 0));
        TextField nota = new TextField();
        VBox.setMargin(nota, new Insets(5, 0, 0, 0));

        cuadro1.getChildren().addAll(Nombre,nombre);
        cuadro2.getChildren().addAll( Telefono,telefono,Correo,correoPersonal);
        cuadro3.getChildren().addAll(RedSocial,redesSociales, Fecha,fecha,Foto, foto, Nota,nota);
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correoPersonal = correoPersonal;
        this.fecha = fecha;
        this.nota = nota;
    }

    @FXML
    private void retrocederPantalla(MouseEvent event) {
    } 
}
