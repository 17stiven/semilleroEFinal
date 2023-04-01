package src.Repositorio;


import src.Entidades.Cuenta;
import src.Entidades.CuentaAhorros;
import src.Entidades.CuentaCorriente;

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

            String sql = "CREATE TABLE IF NOT EXISTS cuenta (\n"
                    + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + " tipo TEXT NOT NULL,\n"
                    + " numero TEXT NOT NULL UNIQUE,\n"
                    + " saldo REAL NOT NULL,\n"
                    + " propietario TEXT NOT NULL,\n"
                    + " numeroRetiros INTEGER NOT NULL,\n"
                    + " numeroDepositos INTEGER,\n"
                    + " transferenciasAhorro INTEGER\n"
                    + ");";

            Statement sentencia = conexion.createStatement();
            sentencia.execute(sql);
            System.out.println("tabla creada");

        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }

    @Override
    public void guardar(Object objeto) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {

            if (objeto.getClass().getSimpleName().equals("CuentaAhorros")) {
                CuentaAhorros cuenta = (CuentaAhorros) objeto;
                String sentenciaSql = "INSERT INTO cuenta (tipo, numero, saldo, propietario, numeroRetiros, numeroDepositos, transferenciasAhorro ) " +
                        " VALUES('" + cuenta.getTipo() + "', '" + cuenta.getNumeroCuenta()
                        + "', " + cuenta.getSaldo() + ", '" + cuenta.getPropietario() + "', " + cuenta.getRetiros() + ", " + cuenta.getNumDepositos() + ", " + 0 + ");";
                Statement sentencia = conexion.createStatement();
                System.out.println(sentenciaSql);
                sentencia.execute(sentenciaSql);

                System.out.println("Ejecutado exitosamente");
            }else {
                CuentaCorriente cuenta = (CuentaCorriente) objeto;
                String sentenciaSql = "INSERT INTO cuenta (tipo, numero, saldo, propietario, numeroRetiros, numeroDepositos, transferenciasAhorro ) " +
                        " VALUES('" + cuenta.getTipo() + "', '" + cuenta.getNumeroCuenta()
                        + "', " + cuenta.getSaldo() + ", '" + cuenta.getPropietario() + "', " + cuenta.getRetiros() + ", " + cuenta.getNumDepositos() + ", " + 0 + ");";
                Statement sentencia = conexion.createStatement();
                System.out.println(sentenciaSql);
                sentencia.execute(sentenciaSql);

                System.out.println("Ejecutado exitosamente");
            }
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }

    }

    @Override
    public void eliminar(String numeroCuenta) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSql = "DELETE FROM cuenta WHERE numero = '" + numeroCuenta + "';";
            Statement sentencia = conexion.createStatement();
            System.out.println(sentenciaSql);
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
          //  if (objeto.getClass().getSimpleName().equals("CuentaAhorros")){
                Cuenta cuenta = (Cuenta) objeto;
                String sentenciaSql = "UPDATE cuenta SET saldo = " + cuenta.getSaldo() +
                        " WHERE numero = '" + cuenta.getNumeroCuenta() + "';";
                Statement sentencia = conexion.createStatement();
                System.out.println(sentenciaSql);
                sentencia.execute(sentenciaSql);
           // }

        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    @Override
    public Cuenta buscar(String numeroCuenta) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSQL = "SELECT * FROM cuenta WHERE numero = ?";
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
            sentencia.setString(1, numeroCuenta);
            ResultSet resultadoConsulta = sentencia.executeQuery();
            if (resultadoConsulta != null && resultadoConsulta.next()) {
                String tipoCuenta = resultadoConsulta.getString("tipo");
                double saldo = resultadoConsulta.getDouble("saldo");
                String propietario = resultadoConsulta.getString("propietario");
                String numCuenta = resultadoConsulta.getString("numero");
                int numeroRetiros = resultadoConsulta.getInt("numeroRetiros");
                int numeroDepositos = resultadoConsulta.getInt("numeroDepositos");
                int transferenciasAhorro = resultadoConsulta.getInt("transferenciasAhorro");



                if (tipoCuenta.equals("CuentaCorriente")) {
                    return new CuentaCorriente(numCuenta,saldo,propietario, numeroRetiros,numeroDepositos, transferenciasAhorro);
                } else {
                    return new CuentaAhorros(numeroCuenta,saldo,propietario,numeroRetiros,numeroDepositos);
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
