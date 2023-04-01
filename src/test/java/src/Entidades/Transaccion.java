package src.Entidades;

public class Transaccion {
    private int id;
    private String fecha;
    private String hora;
    private String tipoTransaccion;
    private double monto;
    private int idCuenta;
    private String tipoCuentaDestino;

    public Transaccion(int id, String fecha, String hora, String tipoTransaccion, double monto, int idCuenta, String tipoCuentaDestino) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.tipoTransaccion = tipoTransaccion;
        this.monto = monto;
        this.idCuenta = idCuenta;
        this.tipoCuentaDestino = tipoCuentaDestino;
    }

    public Transaccion(String fecha, String hora, String tipoTransaccion, double monto, int idCuenta, String tipoCuentaDestino) {
        this.fecha = fecha;
        this.hora = hora;
        this.tipoTransaccion = tipoTransaccion;
        this.monto = monto;
        this.idCuenta = idCuenta;
        this.tipoCuentaDestino = tipoCuentaDestino;
    }

    public int getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public double getMonto() {
        return monto;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public String getTipoCuentaDestino() {
        return tipoCuentaDestino;
    }

}

