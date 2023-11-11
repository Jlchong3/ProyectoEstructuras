/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.appdecontactos;

import ec.edu.espol.appdecontactos.clases.Contacto;
import ec.edu.espol.appdecontactos.clases.Empresa;
import ec.edu.espol.appdecontactos.clases.Persona;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mariu
 */
public class ContactosController implements Initializable {

    @FXML
    private ComboBox<String> tipoContacto;
    @FXML
    private VBox cuadro1;
    @FXML
    private VBox cuadro2;
    @FXML
    private VBox cuadroTodo;
 
    private TextField telefono;
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
        String[] categorias = {"Empresa","Persona"};
        
        tipoContacto.getItems();
                //.addAll(categorias);
 
    }   
    
    @FXML
    private void filtrarTipo(ActionEvent event) {
        cuadro1.getChildren().clear();
        cuadro2.getChildren().clear();
        ComboBox cb = (ComboBox)event.getSource();
        String s = (String)cb.getValue();
        valor = s;
        
        
        if(s.equals("Empresa")){
            Stage stage = (Stage) tipoContacto.getScene().getWindow();
            stage.setHeight(600);
            registroEmpresa();
            
        }
        else if(s.equals("Persona")){
            Stage stage = (Stage) tipoContacto.getScene().getWindow();
            stage.setHeight(600);
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
//    (ArrayList<Integer> telefono, String redesSociales, DoubleCircularLinkedList<String> foto, String correoPersonal, String correoTrabajo, String correoProvisional, String fechas, Contacto contactoRelacionado, String nota, String nombre) {
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

        
        cuadro1.getChildren().addAll(Nombre,nombre,Apellido,apellido,Telefono,telefono,Correo,correoPersonal);
        cuadro2.getChildren().addAll(RedSocial,redesSociales, Fecha,fecha,Foto, foto, Nota,nota);
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

        
        cuadro1.getChildren().addAll(Nombre,nombre,apellido,Telefono,telefono,Correo,correoPersonal);
        cuadro2.getChildren().addAll(RedSocial,redesSociales, Fecha,fecha,Foto, foto, Nota,nota);
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correoPersonal = correoPersonal;
        this.fecha = fecha;
        this.nota = nota;
    }
    
    
    
}
