package src.Repositorio;


import src.Entidades.Cuenta;
import src.Entidades.CuentaAhorros;
import src.CuentaCorriente;

import java.sql.*;
import java.util.List;


public class CuentaRepository implements Repositorio {

    private String cadenaConexion;

    public CuentaRepository() {
        try {
            DriverManager.registerDriver(new org.sqlite.JDBC());
            cadenaConexion = "jdbc:sqlite:cuentas.db";
            crearTabla();
        } catch (SQLException e) {
            System.err.println("Error de conexión con la base de datos: " + e);
        }

    }

    private void crearTabla() {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {

            String sql = "CREATE TABLE IF NOT EXISTS cuentas (\n"
                    + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + " tipo TEXT NOT NULL,\n"
                    + " numero TEXT NOT NULL,\n"
                    + " saldo REAL NOT NULL,\n"
                    + " propietario TEXT NOT NULL,\n"
                    + " FOREIGN KEY (propietario) REFERENCES personas(identificacion)\n"
                    + ");";

            Statement sentencia = conexion.createStatement();
            sentencia.execute(sql);

        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }

    @Override
    public void guardar(Object objeto) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            Cuenta cuenta = (Cuenta) objeto;
            String sentenciaSql = "INSERT INTO cuentas (tipo, numero, saldo, propietario) " +
                    " VALUES('" + cuenta.getTipo() + "', '" + cuenta.getNumeroCuenta()
                    + "', " + cuenta.getSaldo() + ", '" + cuenta.getPropietario() + "');";
            Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSql);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }

    }

    @Override
    public void eliminar(String numeroCuenta) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSql = "DELETE FROM cuentas WHERE numero = '" + numeroCuenta + "';";
            Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSql);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Object objeto) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            Cuenta cuenta = (Cuenta) objeto;
            String sentenciaSql = "UPDATE cuentas SET saldo = " + cuenta.getSaldo() +
                    " WHERE numero = '" + cuenta.getNumeroCuenta() + "';";
            Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSql);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    @Override
    public Cuenta buscar(String numeroCuenta) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSQL = "SELECT * FROM cuentas WHERE numero_cuenta = ?";
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
            sentencia.setString(1, numeroCuenta);
            ResultSet resultadoConsulta = sentencia.executeQuery();
            if (resultadoConsulta != null && resultadoConsulta.next()) {
                String tipoCuenta = resultadoConsulta.getString("tipo_cuenta");
                double saldo = resultadoConsulta.getDouble("saldo");
                String propietario = resultadoConsulta.getString("propietario");
                String numeroResultado = resultadoConsulta.getString("numero_cuenta");
                double sobregiro = resultadoConsulta.getDouble("numero_cuenta");
                double interes = resultadoConsulta.getDouble("numero_cuenta");


                if (tipoCuenta.equals("Corriente")) {
                    return new CuentaCorriente(numeroResultado, saldo, propietario, sobregiro);
                } else {
                    return new CuentaAhorros(numeroResultado, saldo, propietario, interes);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        }
        return null;
    }

    @Override
    public List<?> listar() {
        return null;
    }
}
