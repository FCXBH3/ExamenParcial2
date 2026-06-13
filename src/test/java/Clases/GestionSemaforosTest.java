package Clases;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class GestionSemaforosTest {
    private CentroDenuncias servicio;
    private Semaforo semaforoDefecto;
    private EquipoControl equipoCon;
    private String[] interseccion;
    private Persona personaDenunc;
    private Denuncia denuncia;
    
    public GestionSemaforosTest() {
    }
    
    @BeforeAll
    public static void antesTodo(){
        System.out.println("Comienza la Clase Test.");
    }
    
    @BeforeEach
    public void setUp(TestInfo info){
        System.out.println("Comienza la Prueba " + info.getDisplayName()+".");
        servicio = new CentroDenuncias();
        Ubicacion ubicacion = new Ubicacion("Jose gregorio", "Santos Ortiz", "Cerro Cruz", 2);
        semaforoDefecto = new Semaforo(1, ubicacion, "Activo", true, true); 
        
        Persona personaDenunc = new Persona("Juanjo", "jjsaez@ulp.edu.ar");
        String[] interseccion = {"Jose gregorio", "Santos Ortiz"};
        denuncia = new Denuncia(53, new Date(), personaDenunc, "luz roja no funciona", interseccion, "Media", semaforoDefecto);
        
        //equipo
        Miembro jose = new Miembro(1, "jose", "T1", true, false);
        Miembro jimenez = new Miembro(2, "jimenez", "T2", false, false);
        Miembro daniel = new Miembro(3, "daniel", "T3", false, false);
        Miembro thiago = new Miembro(4, "thiago", "T4", false, false);
        List<Miembro> miembros = new ArrayList<>();
        miembros.add(jose);
        miembros.add(jimenez);
        miembros.add(daniel);
        miembros.add(thiago);
        equipoCon  = new EquipoControl(418, miembros, "reparar semaforos", true);
        
    }
    
    
    @Test
    public void TresExactosSemaforosYLuz(){
        //mi atributo luces usa el arreglo int[], por lo que no es dinamico
        assertEquals(3, semaforoDefecto.getLuces().length);
        Luz luzPosicionCero = semaforoDefecto.getLuces()[0];
        assertSame(luzPosicionCero, semaforoDefecto.getLuces()[0]);
    }
    
    @Test
    @Timeout(value = 400, unit = TimeUnit.MILLISECONDS)
    public void duplicadosTimeout(){
        
        OrdenComposicion ordenCompo = new OrdenComposicion(501, new Date(), "debe reparar luz roja");
        OrdenComposicion ordenCompot2 = new OrdenComposicion(502, new Date(), "debe reparar luz roja");
        denuncia.setOrdenCompo(ordenCompo);
        
        assertThrows(OrdenYaAsignadaException.class, () -> {
            servicio.asignarOrden(denuncia, ordenCompot2);
        });
    }
    
    @Test
    public void finalizacionReparación(){
        //en este momento, equipo esta ocupado con el encargo de la luz Roja.
        OrdenComposicion ordenCompo = new OrdenComposicion(501, new Date(), "debe reparar luz roja");
        ordenCompo.setEquipoEncargado(equipoCon);
        denuncia.setOrdenCompo(ordenCompo);
        
        
        //Ahora, como el trabajo esta hecho, el Responsable setea estado del equipo, y le da fecha de hoy a fecha Reparacion Exitosa
        boolean booleano = ordenCompo.actualizarOrden();
        
        if(booleano == true) System.out.println("Funciono. (En finalizacionReparacion)");
        if(booleano == false) System.out.println("el equipo es nulo, no existe. (En finalizacionReparacion)");
        
        //comprobamos si el metodo actualizarOrden() funciono correctamente. si es asi, no deberia salir el mensaje.
        assertEquals("El equipo no esta Libre.", false, equipoCon.isOcupado());
        
        for (Miembro miembros : ordenCompo.getEquipoEncargado().getMiembros()) {
            assertTrue("el miembro " + miembros.getNombre() + " deberia estar libre, pero no lo esta.", miembros.isLibre());
        }
    }
    
    @ParameterizedTest
    @ValueSource(strings = { "Alta", "Media","Baja" })
    public void prioridadValida(String prioridadReparacion){
        
    }
    
    @AfterEach
    public void tearDown(TestInfo info){
        System.out.println("Termino la prueba " + info.getDisplayName()+".");
    }
    
    @AfterAll
    public static void despuesTodo(){
        System.out.println("Termino la Clase Test.");
    }
    
}
