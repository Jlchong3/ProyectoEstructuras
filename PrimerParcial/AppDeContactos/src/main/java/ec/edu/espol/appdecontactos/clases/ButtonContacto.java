/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.appdecontactos.clases;
import javafx.scene.control.Button;

/**
 *
 * @author josel
 */
public class ButtonContacto extends Button{
    Contacto c;
    public ButtonContacto(Contacto c){
        this.c = c;
        if(c instanceof Empresa){
            Empresa e = (Empresa) c;
            this.setText(e.getNombre());
        }
        else{
            Persona p = (Persona) c;
            this.setText(p.getNombre() + " " + p.getApellido());
        }
    }
}
