package Clases;

public class Cuenta {
    int nroCuenta;
    double saldo = 2000;
    public double retirarDinero(double monto) throws Exception{
        if (monto > saldo){
            throw new Exception("Ojo! No hay saldo Suficiente.");
        }else
            this.saldo-=monto;
        return saldo;
    }
    
    public void depositarDinero(Cuenta c, double monto){
        c.saldo += monto;
    }
    
    public void transferirDinero(Cuenta c2, double monto) throws Exception{
        this.retirarDinero(monto);
        depositarDinero(c2, monto);
    }

    public Cuenta(int nroCuenta, double saldo) {
        this.nroCuenta = nroCuenta;
        this.saldo = saldo;
    }

    public Cuenta() {
    }
    
    public boolean cuentasDiferentes(Cuenta c2){
        return this.equals(c2);
    }
    public void OperacionTildada(int mili){
        try{
            Thread.sleep(mili);
            System.out.println("el tiempo operacion es Coherente.");
        }catch(InterruptedException ex){
            System.out.println("Tiempo de espera excedido....!! " + mili + "milisegundos.");
        }
    }
}
