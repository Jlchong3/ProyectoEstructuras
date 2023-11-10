package ec.edu.espol.appdecontactos.clases;

public class Persona extends Contacto{
    public String nombre;
    public String apellido;

        public Persona(ArrayList<Integer> telefono, String redesSociales, DoubleCircularLinkedList<String> foto, String correoPersonal, String correoTrabajo, String correoProvisional, String fechas, Contacto contactoRelacionado, String nota) {
        this.nombre=nombre;
        this.apellido=apellido;
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
}