package Clases;

import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

public class CuentaTest {
    static Cuenta miCuenta;
    static Cuenta c3;
    
    public CuentaTest() {
    }
    
    @BeforeAll
    public static void antesClase(){
        System.out.println("Fecha de transacción → " + LocalDate.now());
    }
    
    @AfterAll
    public static void DespuesClase(){
        System.out.println("FIN de la transacción ");
    }
    
    @BeforeEach
    public void setUp(TestInfo info){
        System.out.println("Nombre Metodo: " + info.getDisplayName());
        miCuenta = new Cuenta(1,1000.0);
        c3 = new Cuenta();
    }
    
    @Test
    public void testRetirarFallo(){
        assertThrows(Exception.class, () -> {
            miCuenta.retirarDinero(61.0); 
        });
    }
    
    @AfterEach
    public void despuesMetodo(){
        System.out.println("Saldo restante: " + miCuenta.saldo);
    }
    
}
