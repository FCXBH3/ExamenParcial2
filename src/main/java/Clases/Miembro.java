package Clases;
public class Miembro {
    private int id;
    private String nombre;
    private String puesto;
    private boolean esResponsable;
    private boolean libre;

    public Miembro(int id, String nombre, String puesto, boolean esResponsable, boolean libre) {
        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.esResponsable = esResponsable;
        this.libre = libre;
    }

    public boolean isLibre() {
        return libre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    
    

    public void setLibre(boolean libre) {
        this.libre = libre;
    }
    
    
}
