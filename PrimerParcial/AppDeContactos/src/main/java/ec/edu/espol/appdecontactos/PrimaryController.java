package ec.edu.espol.appdecontactos;

import ec.edu.espol.appdecontactos.clases.ButtonContacto;
import ec.edu.espol.appdecontactos.clases.ArrayList;
import ec.edu.espol.appdecontactos.clases.Contacto;
import ec.edu.espol.appdecontactos.clases.DoubleCircularLinkedList;
import ec.edu.espol.appdecontactos.clases.Empresa;
import ec.edu.espol.appdecontactos.clases.Persona;
import ec.edu.espol.appdecontactos.clases.SessionManager;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PrimaryController implements Initializable {
    private ListIterator<Contacto> it;
    private Contacto cursor;
    private DoubleCircularLinkedList<Contacto> contactos = SessionManager.getInstance().getContactosActuales();
    private DoubleCircularLinkedList<Contacto> contactosActuales = SessionManager.getInstance().getContactosActuales().copy();
    @FXML
    private VBox listaDeContactos;
    @FXML
    private GridPane pane;
    @FXML
    private Button agregarContacto;
    @FXML
    private Button recorrer;
    @FXML
    private Button salir;
    @FXML
    private GridPane gridPane;
    @FXML
    private RadioButton filtroNombre;
    @FXML
    private RadioButton filtroCumple;
    @FXML
    private RadioButton filtroEmpresa;
    private ToggleGroup group;
    
    
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

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
    private void agregarNuevoContacto(MouseEvent event) {
        try {
            App.setRoot("craerContacto");
        } catch (IOException ex) {
           
        }
    }

    @FXML
    private void recorrerContactos(MouseEvent event) {
        SessionManager.getInstance().setContacto(null);
        if(filtroNombre.isSelected() || filtroCumple.isSelected() || filtroEmpresa.isSelected())
            SessionManager.getInstance().setContactosFiltrados(contactosActuales);
        try {
            App.setRoot("secondary");
        } catch (IOException ex) {
           
        }
    }

    @FXML
    private void cerrarAplicacion(MouseEvent event) {
        Platform.exit();
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
        for(int i = 0; i < size; i++){
                Contacto c = it.next();
                if(i == 0){
                    cursor = c;
                }
                ButtonContacto b = new ButtonContacto(c);
                b.getStyleClass().add("button1");
                b.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event t) -> {
                    SessionManager.getInstance().setContacto(c);
                    if(filtroNombre.isSelected() || filtroCumple.isSelected() || filtroEmpresa.isSelected())
                        SessionManager.getInstance().setContactosFiltrados(contactosActuales);
                    try {
                        App.setRoot("secondary");
                    }
                    catch (IOException ex) {}
                });
                listaDeContactos.getChildren().add(b);
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
    
    
}
