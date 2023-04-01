package src.Entidades;

import src.Excepciones.*;

public class CuentaCorriente extends Cuenta{
    private int numTransferenciasAhorro=0;

    public CuentaCorriente(String numeroCuenta, double saldo, String propietario, int numTransferenciasAhorro) {
        super(numeroCuenta, saldo, propietario);
        this.numTransferenciasAhorro = numTransferenciasAhorro;
    }

    public CuentaCorriente(String numeroCuenta, double saldo, String propietario, int numRetiros, int numDepositos, int numTransferenciasAhorro) {
        super(numeroCuenta, saldo, propietario, numRetiros, numDepositos);
        this.numTransferenciasAhorro = numTransferenciasAhorro;
    }

    public int getNumTransferenciasAhorro() {
        return numTransferenciasAhorro;
    }

    @Override
    public boolean retirar(double cantidad) throws ValorInvalidoException, LimiteRetirosException {
        if (numRetiros>5){
            throw new LimiteRetirosException("No puede realizar más de 5 retiros");
        }
        return super.retirar(cantidad);
    }

    @Override
    public boolean transferir(Cuenta destino, double cantidad) throws ValorInvalidoException, LimiteTransferenciasException {
        double valorTransferencia=cantidad*0.02;
        if (saldo>=(cantidad+valorTransferencia)){
            if (destino.getTipo().equals("CuentaAhorros") && numTransferenciasAhorro<=2){
                destino.depositar(cantidad);
                numTransferenciasAhorro++;
                saldo=saldo-cantidad-valorTransferencia;
                return true;
            }else if (numTransferenciasAhorro>2){
                throw new LimiteTransferenciasException("No puede realizar más de 2 transferencias a cuentas de ahorro");
            }else {
                destino.depositar(cantidad);
                saldo=saldo-cantidad-valorTransferencia;
return true;
            }
        }else{
            throw new SaldoInsuficienteException("No tiene saldo suficiente para esta operación");
        }

       // return false;
    }
}
