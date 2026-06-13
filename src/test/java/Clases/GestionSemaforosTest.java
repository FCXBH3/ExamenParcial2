package Clases;


import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class GestionSemaforosTest {
    private CentroDenuncias servicio; // Tu clase Gestor o Servicio
    private Semaforo semaforoDefecto;
    
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
    }
    
    
    @Test
    public void TresExactosSemaforosYLuz(){
        //mi atributo luces usa el arreglo int[], por lo que no es dinamico
        assertEquals(3, semaforoDefecto.getLuces().length);
        Luz luzPosicionCero = semaforoDefecto.getLuces()[0];
        assertSame(luzPosicionCero, semaforoDefecto.getLuces()[0]);
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
