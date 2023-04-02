package src.Entidades;

import src.Excepciones.*;

public abstract class Cuenta {
    protected String numeroCuenta;
    private int idUsuario;
    protected double saldo;
    //protected String tipo;
    protected String propietario;
    protected int numRetiros=0;
    protected int numDepositos=0;
    protected int id;

    public Cuenta(int id, String numeroCuenta, double saldo, String propietario, int numRetiros, int numDepositos, int idusuario) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.propietario = propietario;
        this.numRetiros = numRetiros;
        this.numDepositos = numDepositos;
        this.id = id;
        this.idUsuario=idusuario;
    }

    public int getId() {
        return id;
    }

    public Cuenta(String numeroCuenta, double saldo, String propietario, int numRetiros, int numDepositos, int idUsuario) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.propietario = propietario;
        this.numRetiros = numRetiros;
        this.numDepositos = numDepositos;
        this.idUsuario=idUsuario;
    }

    public Cuenta(String numeroCuenta, int idUsuario, double saldo, int id) {
        this.numeroCuenta = numeroCuenta;
        this.idUsuario = idUsuario;
        this.saldo = saldo;
        this.id = id;
    }

    public int getNumRetiros() {
        return numRetiros;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getNumDepositos() {
        return numDepositos;
    }

    public int getRetiros() {
        return numRetiros;
    }

    public Cuenta(String numeroCuenta, double saldo, String propietario) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.propietario = propietario;
    }

    public Cuenta() {

    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cuenta(String numeroCuenta, int idUsuario, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.idUsuario = idUsuario;
        this.saldo = saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public  boolean depositar(double cantidad) throws ValorNegativoException {
        if (cantidad<0) throw new ValorNegativoException("El valor a depositar no puede ser negativo");
        saldo =saldo+cantidad;
        return true;
    };
    public String getTipo(){
        return this.getClass().getSimpleName();
    }
    public  boolean retirar(double cantidad) throws ValorInvalidoException, LimiteRetirosException {
        if (cantidad<0) throw new ValorNegativoException("El saldo no puede ser negativo");
        if (saldo<cantidad) throw new SaldoInsuficienteException("No tiene saldo suficiente para este retiro");
        saldo=saldo-cantidad;
        return true;
    };
    public abstract boolean transferir(Cuenta destino, double cantidad) throws CuentaInvalidaException, ValorInvalidoException, LimiteTransferenciasException;

    @Override
    public String toString() {
        return "Cuenta{" +
                "numeroCuenta='" + numeroCuenta + '\'' +
                ", saldo=" + saldo +
                ", propietario='" + propietario + '\'' +
                ", numRetiros=" + numRetiros +
                ", numDepositos=" + numDepositos +
                ", id=" + id +
                '}';
    }
}
