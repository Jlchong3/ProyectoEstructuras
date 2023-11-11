package ec.edu.espol.appdecontactos.clases;

public class Persona extends Contacto{
    public String nombre;
    public String apellido;

    public Persona() {
    }

    public Persona(int telefono, int telefonoTrabajo, int telefonoCasa, String redesSociales, String foto, String correoPersonal, String correoTrabajo, String correoProvisional, String fechas, Contacto contactoRelacionado, String nota,String nombre, String apellido) {
        super(telefono, telefonoTrabajo, telefonoCasa, redesSociales, foto, correoPersonal, correoTrabajo, correoProvisional, fechas, contactoRelacionado, nota);
        this.nombre=nombre;
        this.apellido=apellido;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

}