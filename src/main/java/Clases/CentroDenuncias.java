package Clases;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CentroDenuncias {
    private List<Denuncia> denunciasHechas;
    private List<Semaforo> semaforos;
    private List<Persona> denunciantes;

    public CentroDenuncias() {
        this.denunciasHechas = new ArrayList<>();
        this.semaforos = new ArrayList<>();
        this.denunciantes = new ArrayList<>();
    }
    
    
    
    void asignarOrden(Denuncia denuncia, OrdenComposicion ordenCompot2) throws OrdenYaAsignadaException {
        if (denuncia.getOrdenCompo() != null) throw new OrdenYaAsignadaException("Error: Esta denuncia ya tiene una orden compo.");
        
        denuncia.setOrdenCompo(ordenCompot2);
    }
    
    public void generarDenuncia(int codD, Date fechaDenuncia, Persona personaDenunc, String problemaSemaf, String[] interseccionCalles, String prioridadReparacion, int nroSemaforo){
        
        Persona personaAUX = buscarDenunciante(personaDenunc.getGmail());
        
        if(personaAUX == null){
            denunciantes.add(personaDenunc);
        }
        
        Semaforo semaforoAUX = verificarSemaforo(nroSemaforo);
        
        if(semaforoAUX == null){
            System.out.println("Semaforo no existe.");
            return;
        }
        
        //si tuvieramos base de datos, BD se encargaria de asignarle un id a codD
        Denuncia denuncia = new Denuncia(codD, fechaDenuncia, personaDenunc, problemaSemaf, interseccionCalles, prioridadReparacion, semaforoAUX);
        
        for (Denuncia denunciasLocal : denunciasHechas){
            String gmailL = denunciasLocal.getPersonaDenunciante().getGmail();
            String gmailPersona = denuncia.getPersonaDenunciante().getGmail();
            if(gmailL.equals(gmailPersona) && denunciasLocal.getSemaforoDañado().getNro() == denuncia.getSemaforoDañado().getNro()){
                System.out.println("esta Denuncia ya existe en la base de datos.");
                return;
            }
        }
        
        semaforoAUX.getHistoricoDenuncias().add(denuncia);
        denunciasHechas.add(denuncia);
    }
    
    public Semaforo verificarSemaforo(int nro){
        
        for (Semaforo semaforo : semaforos){
            if(semaforo.getNro() == nro){
                return semaforo;
            }
        }
        
        return null;
    }
    
    
    public Persona buscarDenunciante(String gmail){
        for (Persona denuncs : denunciantes){
            if(denuncs.getGmail().equals(gmail)){
                return denuncs;
            }
        }
        return null;
    }
    
    public int vecesDenunciadoSemaforo(int nro){
        int contador = 0;
        Semaforo semaforoAUX = verificarSemaforo(nro);
        for (Denuncia aux : semaforoAUX.getHistoricoDenuncias()){
            if(aux != null) contador++;
        }
        
        return contador;
    }

    public List<Denuncia> getDenunciasHechas() {
        return denunciasHechas;
    }

    public List<Semaforo> getSemaforos() {
        return semaforos;
    }

    public List<Persona> getDenunciantes() {
        return denunciantes;
    }
    
}
