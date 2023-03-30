package src;

import src.Entidades.Cuenta;

public class CuentaCorriente extends Cuenta {
    private double sobregiro;
    private int retirosRealizados;

    public CuentaCorriente(String numeroCuenta, double saldo, String propietario, double sobregiro) {
        super(numeroCuenta, saldo, propietario);
        this.sobregiro = sobregiro;
    }

    public CuentaCorriente() {
        super();

    }

    public double getSobregiro() {
        return sobregiro;
    }

    public void setSobregiro(double sobregiro) {
        this.sobregiro = sobregiro;
    }



    @Override
    public boolean retirar(double cantidad) {
        if (this.retirosRealizados >= 5) {
            System.out.println("No puede realizar más de 5 retiros");
            return false;
        }
        if (cantidad <= (this.saldo + this.sobregiro)) {

            this.retirosRealizados++;
           return super.retirar(cantidad);
        } else {
            System.out.println("Saldo insuficiente");
            return false;
        }
    }

    @Override
    public boolean transferir(Cuenta destino, double cantidad) {
        if (cantidad <= (this.saldo + this.sobregiro)) {
            this.saldo -= cantidad;
           return destino.depositar(cantidad);
        } else {
            System.out.println("Saldo insuficiente");
            return false;
        }
    }
}

