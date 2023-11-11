package ec.edu.espol.appdecontactos.clases;

import java.util.Locale;
import java.util.Scanner;

public class Contacto{

    public int telefonoPersonal;
    public int telefonoTrabajo;
    public int telefonoCasa;
    public DoubleCircularLinkedList<String> foto;
    public ArrayList<String> redesSociales;
    public String correoPersonal;
    public String correoTrabajo; 
    public String correoProvisional;
    public String fechas;
    public Contacto contactoRelacionado;
    public String nota;
    public Contacto(){
        
    }
    
    public Contacto(int telefono, int telefonoTrabajo,int telefonoCasa, String correoPersonal, String correoTrabajo, String correoProvisional, String fechas, Contacto contactoRelacionado, String nota) {
        this.telefonoPersonal = telefono;
        this.telefonoTrabajo = telefonoTrabajo;
        this.telefonoCasa = telefonoCasa;
        this.redesSociales = new ArrayList<>();
        this.foto = new DoubleCircularLinkedList<>();
        this.correoPersonal = correoPersonal;
        this.correoTrabajo = correoTrabajo;
        this.correoProvisional = correoProvisional;
        this.fechas = fechas;
        this.contactoRelacionado = contactoRelacionado;
        this.nota = nota;
    }
    
    public int getTelefonoPersonal() {
        return telefonoPersonal;
    }
    public int getTelefonoCasa() {
        return telefonoCasa;
    }
    public int getTelefonoTrabajo() {
        return telefonoTrabajo;
    }

    public void setTelefonoPersonal(int telefonoPersonal) {
        this.telefonoPersonal = telefonoPersonal;
    }
    public void setTelefonoCasa(int telefonoCasa) {
        this.telefonoCasa = telefonoCasa;
    }
    public void setTelefonoTrabajo(int telefono) {
        this.telefonoTrabajo = telefonoTrabajo;
    }

    public ArrayList<String> getRedesSociales() {
        return redesSociales;
    }

    public void addRedesSociales(String redesSociales) {
        this.redesSociales.addLast(redesSociales);
    }

    public String getFoto(int index) {
        return foto.get(index);
    }

    public void addFoto(String foto) {
        this.foto.addLast(foto);
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