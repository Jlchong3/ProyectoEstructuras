package ec.edu.espol.appdecontactos.clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;

public class Contacto implements Serializable{

    public int telefonoPrincipal;
    public DoubleCircularLinkedList<String> fotos;
    public ArrayList<String> redesSociales;
    public String correoPrincipal;
    public String fechas;
    public Contacto contactoRelacionado;
    public String nota;
    private static final long serialVersionUID = 3499549809924434319L;
    
    public Contacto(){
        
    }
    
    public Contacto(int telefonoPrincipal,ArrayList<String> redesSociales,DoubleCircularLinkedList<String> fotos, String correoPrincipal, String fechas, Contacto contactoRelacionado, String nota) {
        this.telefonoPrincipal = telefonoPrincipal;
        this.redesSociales = redesSociales;
        this.fotos = fotos;
        this.correoPrincipal = correoPrincipal;
        this.fechas = fechas;
        this.contactoRelacionado = contactoRelacionado;
        this.nota = nota;
    }
    
    public int getTelefonoPrincipal() {
        return telefonoPrincipal;
    }

    public void setTelefonoPrincipal(int telefonoPrincipal) {
        this.telefonoPrincipal = telefonoPrincipal;
    }
    
    public DoubleCircularLinkedList<String> getFotos(){
        return this.fotos;
    }

    public ArrayList<String> getRedesSociales() {
        return redesSociales;
    }

    public void addRedesSociales(String redesSociales) {
        this.redesSociales.addLast(redesSociales);
    }

    public String getFoto(int index) {
        return fotos.get(index);
    }

    public void addFoto(String foto) {
        this.fotos.addLast(foto);
    }

    public String getCorreoPrincipal() {
        return correoPrincipal;
    }

    public void setCorreoPrincipal(String correoPrincipal) {
        this.correoPrincipal = correoPrincipal;
    }

    public String getFechas() {
        return fechas;
    }

    public void setFechas(String fechas) {
        this.fechas = fechas;
    }

    public Contacto getContactoRelacionado() {
        return contactoRelacionado;
    }

    public void setContactoRelacionado(Contacto contactoRelacionado) {
        this.contactoRelacionado = contactoRelacionado;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
    
    public static void saveListSer(DoubleCircularLinkedList<Contacto> lista)
    {
        try(ObjectOutputStream output= new ObjectOutputStream(new FileOutputStream("ContactosSer.txt"))){
            output.writeObject(lista);
        } catch(IOException ioE){
        }
    }
    
    public static DoubleCircularLinkedList<Contacto> readListSer()
    {
        DoubleCircularLinkedList<Contacto> lista= new DoubleCircularLinkedList<>();
        try(ObjectInputStream input= new ObjectInputStream(new FileInputStream("ContactosSer.txt"));){
            lista = (DoubleCircularLinkedList<Contacto>)input.readObject();
        } catch(IOException | ClassNotFoundException ioE){
            File file = new File("Contacto.ser");

            if (!file.exists()) {
                try {
                    // Crear el archivo si no existe
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();

                    throw new RuntimeException("No se pudo crear el archivo Contacto.ser");
                }
            }

        }
        return lista;
    }

    public static void updateFile(DoubleCircularLinkedList<Contacto> contactos)
    {
        Contacto.saveListSer(contactos);
    }

    @Override
    public String toString() {
        return "Contacto{" + "telefonoPrincipal=" + telefonoPrincipal + ", fotos=" + fotos + ", redesSociales=" + redesSociales + ", correoPrincipal=" + correoPrincipal + ", fechas=" + fechas + ", contactoRelacionado=" + contactoRelacionado + ", nota=" + nota + '}';
    }
    
    

}