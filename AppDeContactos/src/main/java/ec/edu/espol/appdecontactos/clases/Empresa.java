package ec.edu.espol.appdecontactos.clases; 

public class Empresa extends Contacto{
    public String nombre;

    public Empresa(int telefono, String redesSociales, String foto, String correoPersonal, String correoTrabajo, String correoProvisional, String fechas, Contacto contactoRelacionado, String nota, String nombre) {
        super(telefono,  redesSociales,  foto, correoPersonal,  correoTrabajo,  correoProvisional,  fechas,  contactoRelacionado,  nota);
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}

