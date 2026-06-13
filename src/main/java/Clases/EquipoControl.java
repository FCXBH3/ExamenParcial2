package Clases;

import java.util.List;

public class EquipoControl {
    private int codigo;
    private List<Miembro> miembros;
    private String especialidad;
    private boolean ocupado;

    public EquipoControl(int codigo, List<Miembro> miembros, String especialidad, boolean ocupado) {
        this.codigo = codigo;
        this.miembros = miembros;
        this.especialidad = especialidad;
        this.ocupado = ocupado;
    }

    public EquipoControl() {
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
    
    

    public List<Miembro> getMiembros() {
        return miembros;
    }
    
    
}
