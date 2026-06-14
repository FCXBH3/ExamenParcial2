package Clases;

import java.util.ArrayList;
import java.util.List;

public class Semaforo {
    private int nro;
    private Ubicacion ubicacion;
    private String tipoFaro;
    private List<Luz> luces;
    private boolean cuentaRegresiva;
    private boolean estado;
    private List<Denuncia> historicoDenuncias;

    public Semaforo(int nro, Ubicacion ubicacion, String tipoFaro, boolean cuentaRegresiva, boolean estado) {
        this.nro = nro;
        this.ubicacion = ubicacion;
        this.tipoFaro = tipoFaro;
        this.cuentaRegresiva = cuentaRegresiva;
        this.estado = estado;
        Luz Rojo = new Luz(166,"DanteEmpress", "Rojo", "LED");
        Luz Verde = new Luz(210,"DanteEmpress", "Verde", "LED");
        Luz Amarillo = new Luz(103,"DanteEmpress", "Amarillo", "LED");
        this.luces = new ArrayList<>();
        luces.add(Rojo);
        luces.add(Verde);
        luces.add(Amarillo);
        this.historicoDenuncias = new ArrayList<>();
    }

    public Semaforo() {
    }

    public List<Luz> getLuces() {
        return luces;
    }

    public List<Denuncia> getHistoricoDenuncias() {
        return historicoDenuncias;
    }

    public int getNro() {
        return nro;
    }
    
    
    
    
}
