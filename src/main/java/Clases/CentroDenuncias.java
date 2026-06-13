package Clases;
public class CentroDenuncias {

    void asignarOrden(Denuncia denuncia, OrdenComposicion ordenCompot2) throws OrdenYaAsignadaException {
        if (denuncia.getOrdenCompo() != null) throw new OrdenYaAsignadaException("Error: Esta denuncia ya tiene una orden compo.");
        
        denuncia.setOrdenCompo(ordenCompot2);
    }
}
