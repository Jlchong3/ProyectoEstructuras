package ec.edu.espol.appdecontactos.clases;

import java.util.Locale;
import java.util.Scanner;

public class Contacto{

    public int telefono;
    public String foto;
    
    public DoubleCircularLinkedList<Contacto> contactos;

    public String redesSociales;
    public String correoPersonal;
    public String correoTrabajo; 
    public String correoProvisional;
    public String fechas;
    public Contacto contactoRelacionado;
    public String nota;

    public Contacto(){
        
    }
    
    public Contacto(int telefono, String redesSociales, String foto, String correoPersonal, String correoTrabajo, String correoProvisional, String fechas, Contacto contactoRelacionado, String nota) {
        this.telefono = telefono;
        this.redesSociales = redesSociales;
        this.foto = foto;
        this.contactos= new DoubleCircularLinkedList<>();
        this.correoPersonal = correoPersonal;
        this.correoTrabajo = correoTrabajo;
        this.correoProvisional = correoProvisional;
        this.fechas = fechas;
        this.contactoRelacionado = contactoRelacionado;
        this.nota = nota;
    }

    public void añadirContacto(Contacto contactoNuevo){
        this.contactos.addLast(contactoNuevo);
    }
    
    
    
    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getRedesSociales() {
        return redesSociales;
    }

    public void setRedesSociales(String redesSociales) {
        this.redesSociales = redesSociales;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCorreoPersonal() {
        return correoPersonal;
    }

    public void setCorreoPersonal(String correoPersonal) {
        this.correoPersonal = correoPersonal;
    }

    public String getCorreoTrabajo() {
        return correoTrabajo;
    }

    public void setCorreoTrabajo(String correoTrabajo) {
        this.correoTrabajo = correoTrabajo;
    }

    public String getCorreoProvisional() {
        return correoProvisional;
    }

    public void setCorreoProvisional(String correoProvisional) {
        this.correoProvisional = correoProvisional;
    }

    public String getFechas() {
        return fechas;
    }

    public void setFechas(String fechas) {
        this.fechas = fechas;
    }

    public Contacto getContactoRelacionado() {
        return contactoRelacionado;
    }

    public void setContactoRelacionado(Contacto contactoRelacionado) {
        this.contactoRelacionado = contactoRelacionado;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }


    

}