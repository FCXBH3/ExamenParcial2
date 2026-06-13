package Clases;

import java.util.List;

public class EquipoControl {
    private int codigo;
    private List<Miembro> miembros;
    private String especialidad;
    private boolean estado;

    public EquipoControl(int codigo, List<Miembro> miembros, String especialidad, boolean estado) {
        this.codigo = codigo;
        this.miembros = miembros;
        this.especialidad = especialidad;
        this.estado = estado;
    }

    public EquipoControl() {
    }
    
    

    public List<Miembro> getMiembros() {
        return miembros;
    }
    
    
}
