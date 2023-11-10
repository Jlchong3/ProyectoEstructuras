package ec.edu.espol.appdecontactos.clases;

public class Persona extends Contacto{
    public String nombre;
    public String apellido;

    public Persona(ArrayList<Integer> telefono, String redesSociales, DoubleCircularLinkedList<String> foto, String correoPersonal, String correoTrabajo, String correoProvisional, String fechas, Contacto contactoRelacionado, String nota, String nombre, String apellido) {
        super(telefono,  redesSociales,  foto,  correoPersonal,  correoTrabajo,  correoProvisional,  fechas,  contactoRelacionado,  nota);
        this.nombre=nombre;
        this.apellido=apellido;
    }
    
    
    
}