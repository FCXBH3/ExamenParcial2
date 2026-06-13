package Clases;

import java.util.Date;

public class OrdenComposicion {
    private int nroOrden;
    private Date fechaRepProgramada;
    private Date fechaEfectivaReparacion = null;
    private String detalle;
    private EquipoControl equipoEncargado = null;

    public OrdenComposicion(int nroOrden, Date fechaRepProgramada, String detalle) {
        this.nroOrden = nroOrden;
        this.fechaRepProgramada = fechaRepProgramada;
        this.detalle = detalle;
    }

    public OrdenComposicion() {
    }
    
    public boolean actualizarOrden(){
        this.setFechaEfectivaReparacion(new Date());
        
        if(equipoEncargado == null) return false;
        equipoEncargado.setOcupado(false);
        
        for (int i = 0; i <= equipoEncargado.getMiembros().size()-1;i++){
            equipoEncargado.getMiembros().get(i).setLibre(true);
        }
        
        return true;
    }
   

    public void setFechaEfectivaReparacion(Date fechaEfectivaReparacion) {
        this.fechaEfectivaReparacion = fechaEfectivaReparacion;
    }
    
    

    public void setFechaRepProgramada(Date fechaRepProgramada) {
        this.fechaRepProgramada = fechaRepProgramada;
    }

    public void setEquipoEncargado(EquipoControl equipoEncargado) {
        this.equipoEncargado = equipoEncargado;
    }

    public Date getFechaEfectivaReparacion() {
        return fechaEfectivaReparacion;
    }

    public EquipoControl getEquipoEncargado() {
        return equipoEncargado;
    }

    
    
    
    
}
