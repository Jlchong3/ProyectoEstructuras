/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.appdecontactos;

import ec.edu.espol.appdecontactos.clases.ButtonContacto;
import ec.edu.espol.appdecontactos.clases.Contacto;
import ec.edu.espol.appdecontactos.clases.DoubleCircularLinkedList;
import ec.edu.espol.appdecontactos.clases.Empresa;
import ec.edu.espol.appdecontactos.clases.SessionManager;
import ec.edu.espol.appdecontactos.clases.ToggleButtonContacto;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Migi
 */
public class AgregarAsociadoController implements Initializable {
    private ListIterator<Contacto> it;
    private Contacto cursor;
    private Contacto contacto = SessionManager.getInstance().getContacto();
    private DoubleCircularLinkedList<Contacto> contactos = SessionManager.getInstance().getContactosActuales();
    private DoubleCircularLinkedList<Contacto> contactosActuales = SessionManager.getInstance().getContactosActuales().copy();
    private Contacto seleccionado;
    @FXML
    private GridPane pane;
    @FXML
    private RadioButton filtroNombre;
    @FXML
    private RadioButton filtroCumple;
    @FXML
    private RadioButton filtroEmpresa;
    @FXML
    private VBox listaDeContactos;
    @FXML
    private GridPane gridPane;
    private ToggleGroup group;
    private ToggleGroup group2;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        gridPane.setStyle("-fx-background-color: linear-gradient(to bottom, #B1CFFF, #B1CFFF);");
        listaDeContactos.setStyle("-fx-spacing: 10; -fx-padding: 10;");
        group = new ToggleGroup();
        filtroNombre.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
            Comparator<Contacto> cmp = (Contacto c1, Contacto c2) -> {
                return c1.getNombre().compareTo(c2.getNombre());
            };
            contactosActuales = contactos.sorted(cmp);
            System.out.println(contactos);
            System.out.println(contactosActuales);
            actualizarLista();
        });
        filtroCumple.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
            LocalDate currentDate = LocalDate.now();
            Comparator<Contacto> cmp = Comparator.comparing(contacto -> {
            LocalDate contactBirthday = LocalDate.parse(contacto.getFechas());
            contactBirthday = contactBirthday.withYear(currentDate.getYear());

            if (contactBirthday.isBefore(currentDate)) {
                contactBirthday = contactBirthday.withYear(currentDate.getYear() + 1);
            }

            return ChronoUnit.DAYS.between(currentDate, contactBirthday);
            });
            contactosActuales = contactos.sorted(cmp);
            actualizarLista();
        });
        filtroEmpresa.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
            contactosActuales = new DoubleCircularLinkedList<>();
            for(int i = 0; i < contactos.size(); i++){
                Contacto c = contactos.get(i);
                if(c instanceof Empresa){
                    contactosActuales.addLast(c);
                }
            }
            actualizarLista();
        });
        
        
        filtroNombre.setToggleGroup(group);
        filtroCumple.setToggleGroup(group);
        filtroEmpresa.setToggleGroup(group);

        actualizarLista();
    }

    @FXML
    private void up(MouseEvent event) {
        if(contactosActuales.size() > 11){
            it = contactosActuales.CircularIterator();
            Contacto c = null;
            while(it.hasNext() && c != cursor){
                c = it.next();
            };
            it.previous();
            it.previous();
            listaDeContactos.getChildren().clear();
            llenarLista(11, it);
        }
    }

    @FXML
    private void down(MouseEvent event) {
        if(contactosActuales.size() > 11){
            it = contactosActuales.CircularIterator();
            Contacto c = null;
            while(it.hasNext() && c != cursor){
                c = it.next();
            };
            listaDeContactos.getChildren().clear();
            llenarLista(11, it);
        }
    }
    
    private void llenarLista(int size, ListIterator<Contacto> it){
        group2 = new ToggleGroup();
        int sizerela = contacto.getContactosRelacionados().size();
        for(int i = 0; i < size - 1 - sizerela; i++){
                Contacto c = it.next();
                if (c == contacto){
                    i--;
                    continue;
                }
                boolean isin = false;
                for(int j = 0; j < sizerela; j++){
                   if(c == contacto.getContactosRelacionados().get(j)){
                       isin = true;
                   }
                }
                if(isin){
                    i--;
                    continue;
                }
                if(i == 0){
                    cursor = c;
                }
                ToggleButtonContacto b = new ToggleButtonContacto(c);
                if(c == seleccionado){
                    b.setSelected(true);
                }
                b.setStyle("-fx-font-family: 'Century Gothic'; -fx-font-size: 14; -fx-min-width: 150;");
                b.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
                    seleccionado = c;
                });
                listaDeContactos.getChildren().add(b);
                b.setToggleGroup(group2);
            }
    }
    
    
    @FXML
    private void clearFilter(MouseEvent event) {
        filtroNombre.setSelected(false);
        filtroCumple.setSelected(false);
        filtroEmpresa.setSelected(false);
        contactosActuales = contactos.copy();
        listaDeContactos.getChildren().clear();
        actualizarLista();
    }
    
    private void actualizarLista(){
        listaDeContactos.getChildren().clear();
        
        if(pane.getChildren().size() == 5){
            pane.getChildren().remove(4);
        }
        if(!contactosActuales.isEmpty() && contactosActuales.size() <= 11){
            it = contactosActuales.CircularIterator();
            llenarLista(contactosActuales.size(),it);
        }
        else if(!contactosActuales.isEmpty()){
            it = contactosActuales.CircularIterator();
            llenarLista(11,it);
        }
        else{
            listaDeContactos.getChildren().clear();
            Text t = new Text("\t No tienes contactos");
            GridPane.setHalignment(t, HPos.CENTER);
            pane.getChildren().add(t);
        }
    }

    @FXML
    private void regresar(MouseEvent event) {
        try {
            App.setRoot("contactosAsociados"); 
        } catch (IOException ex) {
           
        }
    
    }

    @FXML
    private void asociarContacto(MouseEvent event) {
        if(group2.getToggles().size() != 0){
            contacto.getContactosRelacionados().addLast(seleccionado);
            try{
                App.setRoot("contactosAsociados"); //hay que hacer que regrese al contacto seleccionado
            } catch (IOException ex) {

            }
        }
    }
    
}
