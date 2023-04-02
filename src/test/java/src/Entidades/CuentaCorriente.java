package src.Entidades;

import src.Excepciones.*;

public class CuentaCorriente extends Cuenta{
    private int numTransferenciasAhorro=0;

    public CuentaCorriente(String numeroCuenta, double saldo, String propietario) {
        super(numeroCuenta, saldo, propietario);
        this.numTransferenciasAhorro = numTransferenciasAhorro;
    }

    public CuentaCorriente(int id, String numeroCuenta, double saldo, String propietario, int numRetiros, int numDepositos, int numTransferenciasAhorro, int idUusario) {
        super(id, numeroCuenta, saldo, propietario, numRetiros, numDepositos, idUusario);
        this.numTransferenciasAhorro = numTransferenciasAhorro;
    }

    public CuentaCorriente(String numeroCuenta, int idUsuario, double saldo) {
        super(numeroCuenta, idUsuario, saldo);
    }

    public CuentaCorriente(String numeroCuenta, int idUsuario, double saldo, int id) {
        super(numeroCuenta, idUsuario, saldo, id);
    }

    public CuentaCorriente(String numeroCuenta, double saldo, String propietario, int numRetiros, int numDepositos, int numTransferenciasAhorro, int idUsuario) {
        super(numeroCuenta, saldo, propietario, numRetiros, numDepositos, idUsuario);
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

    @Override
    public String toString() {
        return "CuentaCorriente: " +
                "numero Transferencias Ahorro=" + numTransferenciasAhorro +
                "Tipo de cuenta=" + getTipo() +
                ", numeroCuenta='" + numeroCuenta + '\'' +
                ", saldo=" + saldo +
                ", propietario='" + propietario + '\'' +
                ", numRetiros=" + numRetiros +
                ", numDepositos=" + numDepositos +
                ", id=" + id;
    }
}
