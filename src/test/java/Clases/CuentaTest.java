package Clases;

import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class CuentaTest {
    
    public CuentaTest() {
    }
    
    @BeforeClass
    public static void antesClase(){
        System.out.println("muestra: Fecha de transacción y la fechaLocal → 2020, Month.NOVEMBER, 13");
    }
    
    @AfterClass
    public static void DespuesClase(){
        System.out.println("FIN de la transacción ");
    }
    
    @Test (expected = Exception.class)
    public void testRetirarFallo(){
        
    }
    
}
