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
import javafx.event.EventHandler;
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
    
    private void cargarNombres(){
        nombresContactos.addFirst("Alexandre");
        nombresContactos.addFirst("Duolingo");
        nombresContactos.addFirst("Jaime");
        nombresContactos.addFirst("Jose");
        nombresContactos.addFirst("Josedeodo");
        nombresContactos.addFirst("Mariu");
        nombresContactos.addFirst("Nahin");
        nombresContactos.addFirst("Nini");
        nombresContactos.addFirst("Patricio");
        nombresContactos.addFirst("PedidosYa");
        nombresContactos.addFirst("Raul");
        nombresContactos.addFirst("Smash");
        nombresContactos.addFirst("Tia");
    }

    public DoubleCircularLinkedList<String> obtenerDireccionImagen()
    {
        
        cargarNombres();
        String directorioImagenes = "src/main/resources/ec/edu/espol/appdecontactos/imgs/contactos/";
        DoubleCircularLinkedList<String> fotosDelContacto = new DoubleCircularLinkedList<>();
        
        for(String nombreContacto: nombresContactos)
        {
            if(nombre.getText() != null){
                if(nombreContacto.equals(nombre.getText())){
                    String fotoPerfil = directorioImagenes + nombreContacto + "Perfil.jpeg"; //Si el nombre del contacto es alguno de los predeterminados, se accede a su direccion de memoria
                    fotosDelContacto.addLast(fotoPerfil); //añade la foto de perfil del contacto en la posición 0
                    
                    for(int i = 1; i < 5; i++){
                        String fotoAsociada = directorioImagenes + "/FotosAsociadas/" + nombreContacto + "Asociada" + i + ".jepg";
                        fotosDelContacto.addLast(fotoAsociada);
                    }
                     
                }else{
                    String extra = "ExtraX";
                    Random random = new Random();
                    String numero = ""+random.nextInt(4)+1;
                    String fotoPerfil = directorioImagenes + extra + numero + ".jpeg"; //Si el nombre no es de los predeterminados, se escoge una foto aleatoria para ese contacto
                    fotosDelContacto.addLast(fotoPerfil); //añade la foto de perfil del contacto en la posición 0
                    
                    for(int i = 1; i < 5; i++){
                        String numeroA = ""+random.nextInt(14)+1;
                        String fotoAsociada = directorioImagenes + "/Extras/FotosAsociadas/" + "Asociada" + numeroA + ".jepg";
                        fotosDelContacto.addLast(fotoAsociada);
                    }
                }           
            }  
        }
        return null;
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
    
      
    public void llevaFotosTodas(){
        Stage stage = (Stage) tipoContacto.getScene().getWindow();
        stage.setHeight(550);
        try {
            
            
            App.setRoot("FotosTodas");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
    
    public void registrarContacto(Contacto contactoNuevo){
        contactosActuales.addLast(contactoNuevo);
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

                        ArrayList<String> redesSocialesContacto = new ArrayList<>();
                        redesSocialesContacto.addLast(redesSociales.getText());

                        DoubleCircularLinkedList<String> fotosContacto = obtenerDireccionImagen();
                        

                        Empresa e = new Empresa(i, redesSocialesContacto , fotosContacto , correoPrincipal.getText(), fecha.getText(), contactoRelacionado, nota.getText(), nombre.getText(),  j,   telefonoProvisional,  correoSecundario.getText(),  correoProvisional);
                        
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

                        ArrayList<String> redesSocialesContacto = new ArrayList<>();
                        redesSocialesContacto.addLast(redesSociales.getText());

                        DoubleCircularLinkedList<String> fotosContacto = obtenerDireccionImagen();
                        

                        Persona p = new Persona(i,redesSocialesContacto,fotosContacto, correoPrincipal.getText() , fecha.getText() , contactoRelacionado, nota.getText(), nombre.getText(), apellido.getText(), j, telefonoCasa, correoTrabajo, correoProvisional);
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
        Button btn = new Button(" Ver ");
        VBox.setMargin(btn, new Insets(5, 0, 0, 0));  
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                llevaFotosTodas();
            }
        });
        
        
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
        Button btn = new Button(" Ver ");
        VBox.setMargin(btn, new Insets(5, 0, 0, 0));  
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                llevaFotosTodas();
            }
        }); 
        
        
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
