package ec.edu.espol.appdecontactos.clases; 

public class Empresa extends Contacto{
    public String nombre;
    public int telefonoWha;
    public int telefonoProvisional;

    public String correoSecundario; 
    public String correoProvisional;


    public Empresa() {
    }

    public Empresa(int telefonoPrincipal,ArrayList<String> redesSociales,DoubleCircularLinkedList<String> fotos, String correoPrincipal, String fechas, Contacto contactoRelacionado, String nota,String nombre, int telefonoWha, int telefonoProvisional, String correoSecundario, String correoProvisional) {
        super( telefonoPrincipal, redesSociales, fotos,  correoPrincipal,  fechas,  contactoRelacionado,  nota);
        this.nombre = nombre;
        this.telefonoWha = telefonoWha;
        this.telefonoProvisional = telefonoProvisional;
        this.correoSecundario = correoSecundario;
        this.correoProvisional = correoProvisional;
    }


    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefonoWha() {
        return this.telefonoWha;
    }

    public void setTelefonoWha(int telefonoWha) {
        this.telefonoWha = telefonoWha;
    }

    public int getTelefonoProvisional() {
        return this.telefonoProvisional;
    }

    public void setTelefonoProvisional(int telefonoProvisional) {
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

