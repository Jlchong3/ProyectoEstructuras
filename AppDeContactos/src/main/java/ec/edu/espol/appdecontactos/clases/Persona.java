package ec.edu.espol.appdecontactos.clases;

public class Persona extends Contacto{
    public String nombre;
    public String apellido;
    public String telefonoTrabajo;
    public String telefonoCasa;
    public String correoTrabajo; 
    public String correoProvisional;

    public Persona() {
    }

    public Persona(String telefonoPrincipal,ArrayList<String> redesSociales,DoubleCircularLinkedList<String> fotos, String correoPrincipal, String fechas, DoubleCircularLinkedList<Contacto> contactosRelacionados, String nota, String nombre, String apellido, String telefonoTrabajo, String telefonoCasa, String correoTrabajo, String correoProvisional) {
        super( telefonoPrincipal, redesSociales, fotos,  correoPrincipal,  fechas,  contactosRelacionados,  nota);
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefonoTrabajo = telefonoTrabajo;
        this.telefonoCasa = telefonoCasa;
        this.correoTrabajo = correoTrabajo;
        this.correoProvisional = correoProvisional;
    }



    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefonoTrabajo() {
        return this.telefonoTrabajo;
    }

    public void setTelefonoTrabajo(String telefonoTrabajo) {
        this.telefonoTrabajo = telefonoTrabajo;
    }

    public String getTelefonoCasa() {
        return this.telefonoCasa;
    }

    public void setTelefonoCasa(String telefonoCasa) {
        this.telefonoCasa = telefonoCasa;
    }

    public String getCorreoTrabajo() {
        return this.correoTrabajo;
    }

    public void setCorreoTrabajo(String correoTrabajo) {
        this.correoTrabajo = correoTrabajo;
    }

    public String getCorreoProvisional() {
        return this.correoProvisional;
    }

    public void setCorreoProvisional(String correoProvisional) {
        this.correoProvisional = correoProvisional;
    }

    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", apellido=" + apellido + ", telefonoTrabajo=" + telefonoTrabajo + ", telefonoCasa=" + telefonoCasa + ", correoTrabajo=" + correoTrabajo + ", correoProvisional=" + correoProvisional + '}';
    }


}