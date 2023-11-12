package ec.edu.espol.appdecontactos.clases;

public class Persona extends Contacto{
    public String nombre;
    public String apellido;
    public int telefonoTrabajo;
    public int telefonoCasa;
    public String correoTrabajo; 
    public String correoProvisional;

    public Persona() {
    }

    public Persona(int telefonoPrincipal,ArrayList<String> redesSociales,DoubleCircularLinkedList<String> fotos, String correoPrincipal, String fechas, Contacto contactoRelacionado, String nota, String nombre, String apellido, int telefonoTrabajo, int telefonoCasa, String correoTrabajo, String correoProvisional) {
        super( telefonoPrincipal, redesSociales, fotos,  correoPrincipal,  fechas,  contactoRelacionado,  nota);
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

    public int getTelefonoTrabajo() {
        return this.telefonoTrabajo;
    }

    public void setTelefonoTrabajo(int telefonoTrabajo) {
        this.telefonoTrabajo = telefonoTrabajo;
    }

    public int getTelefonoCasa() {
        return this.telefonoCasa;
    }

    public void setTelefonoCasa(int telefonoCasa) {
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


}