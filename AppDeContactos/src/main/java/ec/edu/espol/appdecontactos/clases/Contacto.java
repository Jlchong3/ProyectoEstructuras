package ec.edu.espol.appdecontactos.clases;

import java.util.Locale;
import java.util.Scanner;

public class Contacto{

    public ArrayList<Integer> telefono;
    public String redesSociales;
    public DoubleCircularLinkedList<String> foto;
    public String correoPersonal;
    public String correoTrabajo; 
    public String correoProvisional;
    public String fechas;
    public Contacto contactoRelacionado;
    public String nota;

    public Contacto(ArrayList<Integer> telefono, String redesSociales, DoubleCircularLinkedList<String> foto, String correoPersonal, String correoTrabajo, String correoProvisional, String fechas, Contacto contactoRelacionado, String nota) {
        this.telefono = telefono;
        this.redesSociales = redesSociales;
        this.foto = foto;
        this.correoPersonal = correoPersonal;
        this.correoTrabajo = correoTrabajo;
        this.correoProvisional = correoProvisional;
        this.fechas = fechas;
        this.contactoRelacionado = contactoRelacionado;
        this.nota = nota;
    }

    public void registrarContacto()
    {
        
    }

}