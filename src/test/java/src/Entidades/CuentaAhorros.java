package src.Entidades;

import src.CuentaCorriente;
import src.Entidades.Cuenta;
import src.Excepciones.LimiteRetirosException;
import src.Excepciones.SaldoInsuficienteException;
import src.Excepciones.ValorInvalidoException;
import src.Excepciones.ValorNegativoException;

public class CuentaAhorros extends Cuenta {

//private int numDepositos=0;
//private int numRetiros=0;
    public CuentaAhorros(String numeroCuenta, double saldo, String propietario) {
        super(numeroCuenta, saldo, propietario);

    }

    public CuentaAhorros(String numeroCuenta, double saldo, String propietario, int numRetiros, int numDepositos) {
        super(numeroCuenta, saldo, propietario, numRetiros, numDepositos);

    }





    @Override
    public boolean depositar(double monto) throws ValorNegativoException {
        boolean resultado = super.depositar(monto);
        numDepositos++;
        if (resultado && (numDepositos <= 2)) {
            double valorAdicional = monto * 0.05;
            saldo += valorAdicional;
            System.out.println("Se ha adicionado un valor a su saldo del 0.5% de "+valorAdicional);
        }
        return resultado;
    }

    @Override
    public boolean retirar(double monto) throws LimiteRetirosException, ValorInvalidoException {
        if (monto<0)throw new ValorNegativoException("El monto no puede ser negativo");
        if (saldo<monto) throw new SaldoInsuficienteException("No tiene saldo suficiente para este retiro");
        if (numRetiros > 3) {
            double valorRetiro=monto*0.01;
          //  monto = monto * 1.01; // Se agrega un 1% al monto a retirar si se han hecho más de 3 retiros
            System.out.println("Valor a retirar: "+monto);
            System.out.println("Costo del retiro: "+valorRetiro);
            monto=monto+valorRetiro;
            System.out.println("Total: "+monto);
        }
        System.out.println("Valor a retirar: "+monto);
        System.out.println("Costo del retiro: 0");
        boolean seRetiro = super.retirar(monto);
        if (seRetiro) {
            numRetiros++;
        }
        return seRetiro;
    }


    public boolean transferir(Cuenta cuentaDestino, double monto) throws ValorNegativoException, SaldoInsuficienteException {

        double valorCobro = 0;
        if (this.getSaldo() >= monto) {
            if (cuentaDestino.getTipo().equals("CuentaCorriente")) {
                valorCobro = monto * 0.015; // 1.5% adicional
            }
            if (this.getSaldo()>=(monto+valorCobro)){
                saldo=saldo-monto-valorCobro;
                cuentaDestino.depositar(monto);
                return true;
            }
            throw new SaldoInsuficienteException("No tiene saldo suficiente para completar esta operación");



        }
        throw new SaldoInsuficienteException("No tiene saldo suficiente para esta operación");
    }
}


