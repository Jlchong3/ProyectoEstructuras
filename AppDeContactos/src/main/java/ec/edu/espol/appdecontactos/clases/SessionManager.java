package ec.edu.espol.appdecontactos.clases;

public class SessionManager {

    private static SessionManager instance = null;
    private DoubleCircularLinkedList<Contacto> contactosActuales = new DoubleCircularLinkedList<>();
    private Contacto contacto;
    
    private SessionManager(){
    }

    public static SessionManager getInstance(){
        if (instance == null)
        {
            instance = new SessionManager();
        }
        return instance;
    }

    public DoubleCircularLinkedList<Contacto> getContactosActuales(){
        return contactosActuales;
    }

    public void setContactosActuales(DoubleCircularLinkedList<Contacto> contactos){
        this.contactosActuales = contactos;
    }

    public void cerrarApp(){
        this.contactosActuales = null;
    }
    
    public Contacto getContacto(){
        return contacto;
    }
    
    public void setContacto(Contacto c){
        this.contacto = c;
    }
}
