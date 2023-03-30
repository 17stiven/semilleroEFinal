package src.Entidades;

public abstract class Cuenta {
    protected String numeroCuenta;
    protected double saldo;
    protected String propietario;

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

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public  boolean depositar(double cantidad){
        saldo =saldo+cantidad;
        return true;
    };
    public String getTipo(){
        return this.getClass().getTypeName();
    }
    public  boolean retirar(double cantidad){
        saldo=saldo-cantidad;
        return true;
    };
    public abstract boolean transferir(Cuenta destino, double cantidad);
}
