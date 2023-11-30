package ec.edu.espol.appdecontactos.clases; 

public class Empresa extends Contacto{
    public String telefonoWha;
    public String telefonoProvisional;

    public String correoSecundario; 
    public String correoProvisional;


    public Empresa() {
    }

    public Empresa(String telefonoPrincipal,ArrayList<String> redesSociales,DoubleCircularLinkedList<String> fotos, String correoPrincipal, String fechas, DoubleCircularLinkedList<Contacto> contactosRelacionados, String nota,String nombre, String telefonoWha, String telefonoProvisional, String correoSecundario, String correoProvisional) {
        super( telefonoPrincipal, redesSociales, fotos,  correoPrincipal,  fechas,  contactosRelacionados,  nota);
        this.nombre = nombre;
        this.telefonoWha = telefonoWha;
        this.telefonoProvisional = telefonoProvisional;
        this.correoSecundario = correoSecundario;
        this.correoProvisional = correoProvisional;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefonoWha() {
        return this.telefonoWha;
    }

    public void setTelefonoWha(String telefonoWha) {
        this.telefonoWha = telefonoWha;
    }

    public String getTelefonoProvisional() {
        return this.telefonoProvisional;
    }

    public void setTelefonoProvisional(String telefonoProvisional) {
        this.telefonoProvisional = telefonoProvisional;
    }

    public String getCorreoSecundario() {
        return this.correoSecundario;
    }

    public void setCorreoSecundario(String correoSecundario) {
        this.correoSecundario = correoSecundario;
    }

    public String getCorreoProvisional() {
        return this.correoProvisional;
    }

    public void setCorreoProvisional(String correoProvisional) {
        this.correoProvisional = correoProvisional;
    }

    @Override
    public String toString() {
        return "Empresa{" + "nombre=" + nombre + ", telefonoWha=" + telefonoWha + ", telefonoProvisional=" + telefonoProvisional + ", correoSecundario=" + correoSecundario + ", correoProvisional=" + correoProvisional + '}';
    }

}

