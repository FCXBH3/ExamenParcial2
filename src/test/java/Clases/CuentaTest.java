package Clases;

import java.time.LocalDate;
import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.*;

public class CuentaTest {
    Cuenta miCuenta;
    Cuenta c3;
    
    public CuentaTest() {
    }
    
    @BeforeAll
    public static void antesClase(){
        System.out.println("Fecha de transacción → " + LocalDate.now());
    }
    
    @BeforeEach
    public void setUp(TestInfo info){
        System.out.println("Nombre Metodo: " + info.getDisplayName());
        miCuenta = new Cuenta();
        c3 = new Cuenta();
    }
    
    @Test
    public void testRetirarFallo(){
        assertThrows(Exception.class, () -> {
            miCuenta.retirarDinero(9000.0); 
        });
    }
    
    @Test
    public void testRetirarExito() throws Exception{
        miCuenta.retirarDinero(1000.0);
    }
    
    @Test
    public void testDepositarDinero(){
        assertNotNull("Es nulo", miCuenta);
        miCuenta.depositarDinero(miCuenta, 200);
        assertEquals("no son iguales, o tienen una diferencia de 0.2 o menos", 2200.0, miCuenta.saldo, 0.2);
    }
    
    @Test
    public void testTransferirCuentaNoNula() throws Exception{
        Cuenta c2 = new Cuenta(1, 5000);
        assertNull(null);
        miCuenta.transferirDinero(c2, 600);
    }
    
    @Test
    public void testCuentasDiferentes(){
        miCuenta.cuentasDiferentes(c3);
        assertNotSame("SON la MISMA cuenta!! origen y destino",miCuenta, miCuenta);
    }
    
    @Test
    public void testCuentasIguales(){
        assertSame("Las cuentas son diferentes---",miCuenta, c3);
        
    }
    
    @Test
    @Timeout(value = 60, unit = TimeUnit.MILLISECONDS)
    public void testOperacionTildada1() throws InterruptedException {
        miCuenta.OperacionTildada(30);
    }
    
    @Test
    @Timeout(value = 60, unit = TimeUnit.MILLISECONDS)
    public void testOperacionTildada2(){
        miCuenta.OperacionTildada(100);
    }
    
    @AfterEach
    public void despuesMetodo(){
        System.out.println("Saldo restante: " + miCuenta.saldo);
    }
    
    @AfterAll
    public static void DespuesClase(){
        System.out.println("FIN de la transacción ");
    }
    
}
