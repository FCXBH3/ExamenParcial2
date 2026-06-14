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
        //mi atributo luces usa el arreglo int[], por lo que no es dinamico, por ende, no tendria mas de 3 luces
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
        Ubicacion ubicaciont2 = new Ubicacion("Leandro N. Alem", "Centenario", "Eva", 5);
        Semaforo semaforo = new Semaforo(115, ubicaciont2, "Activo", true, true); 
        Persona persona = new Persona();
        String[] interseccion = {"Leandro N. Alem", "Centenario"};
        denuncia = new Denuncia(404, new Date(), persona, "las luces no se ven", interseccion, prioridadReparacion, semaforo);
        
        assertTrue("La prioridad no es valida.", denuncia.esPrioridadValida());
    }
    
    @Test
    public void tresDenunciasDiferentes(){
        Ubicacion ubicacion = new Ubicacion();
        Semaforo semaforoLocal = new Semaforo(404, ubicacion, "tipoA", false, true);
        servicio.getSemaforos().add(semaforoLocal);
        
        Persona p1 = new Persona("fcxbh3", "danttemiranda@gmail.com");
        String[] interseccion1 = {"Calle X", "Calle Y"};
        servicio.generarDenuncia(7, new Date(), p1,"problema1", interseccion1,"Baja",404);
        servicio.generarDenuncia(7, new Date(), p1,"problema1", interseccion1,"Baja",404);
        
        Persona p2 = new Persona("daniel", "danimiranda.dh@gmail.com");
        String[] interseccion2 = {"Calle X", "Calle Y"};
        servicio.generarDenuncia(8, new Date(), p2,"problema2", interseccion2,"Baja",404);
        
        Persona p3 = new Persona("jose", "josefvb21@gmail.com");
        String[] interseccion3 = {"Calle X", "Calle Y"};
        servicio.generarDenuncia(9, new Date(), p3,"problema3", interseccion3,"Baja",404);
        servicio.generarDenuncia(9, new Date(), p3,"problema3", interseccion3,"Baja",404);//esta no se añadira a la lista, ya que esta denuncia ya existe

//        El codigo de abajo causaria que la prueba falle
//        Persona p4 = new Persona("jose44", "josefvb241@gmail.com");
//        String[] interseccion4 = {"Calle X", "Calle Y"};
//        servicio.generarDenuncia(91, new Date(), p4,"problema4", interseccion4,"Baja",404);
        assertEquals(3, servicio.vecesDenunciadoSemaforo(404));

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
