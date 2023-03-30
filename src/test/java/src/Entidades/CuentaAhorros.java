package src.Entidades;

import src.CuentaCorriente;
import src.Entidades.Cuenta;

public class CuentaAhorros extends Cuenta {
    private double tasaInteres;
private int numDepositos=0;
private int numRetiros=0;
    public CuentaAhorros(String numeroCuenta, double saldo, String propietario, double tasaInteres) {
        super(numeroCuenta, saldo, propietario);
        this.tasaInteres = tasaInteres;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    @Override
    public boolean depositar(double monto) {
        boolean resultado = super.depositar(monto);
        numDepositos++;
        if (resultado && (numDepositos < 2)) {
            double bonificacion = monto * 0.005;
            saldo += bonificacion;
        }
        return resultado;
    }

    @Override
    public boolean retirar(double monto) {
        if (numRetiros >= 3) {
            monto = monto * 1.01; // Se agrega un 1% al monto a retirar si se han hecho más de 3 retiros
        }
        boolean seRetiro = super.retirar(monto);
        if (seRetiro) {
            numRetiros++;
        }
        return seRetiro;
    }


    public boolean transferir(Cuenta cuentaDestino, double monto) {
        double valorCobro = 0;
        boolean seTransfirio = false;
        if (this.getSaldo() >= monto) {
            if (cuentaDestino instanceof CuentaCorriente) {
                valorCobro = monto * 0.015; // Se cobra un 1.5% al transferir a una cuenta corriente
            }
            this.retirar(monto);
            cuentaDestino.depositar(monto - valorCobro);
            seTransfirio = true;
        }
        return seTransfirio;
    }
}


