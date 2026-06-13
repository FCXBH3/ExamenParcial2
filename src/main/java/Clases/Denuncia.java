package Clases;

import java.util.Date;

public class Denuncia {
    private int codD;
    private Date fechaDenuncia;
    private Persona personaDenunciante;
    private String problemaSemaf;
    private OrdenComposicion ordenCompo = null;
    private String[] interseccionCalles;
    private String prioridadReparacion;
    private Semaforo semaforoDañado;

    public Denuncia() {
    }

    public Denuncia(int codD, Date fechaDenuncia, Persona personaDenunciante, String problemaSemaf, String[] interseccionCalles, String prioridadReparacion, Semaforo semaforoDañado) {
        this.codD = codD;
        this.fechaDenuncia = fechaDenuncia;
        this.personaDenunciante = personaDenunciante;
        this.problemaSemaf = problemaSemaf;
        this.interseccionCalles = interseccionCalles;
        this.prioridadReparacion = prioridadReparacion;
        this.semaforoDañado = semaforoDañado;
    }
    
    public void esPrioridadValida(){
        
    }

    public void setOrdenCompo(OrdenComposicion ordenCompo) {
        this.ordenCompo = ordenCompo;
    }

    public OrdenComposicion getOrdenCompo() {
        return ordenCompo;
    }

    public String getPrioridadReparacion() {
        return prioridadReparacion;
    }
    
    
    
    
}
