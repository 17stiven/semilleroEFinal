package src.Repositorio;


import src.Entidades.Cuenta;
import src.Entidades.CuentaAhorros;
import src.Entidades.CuentaCorriente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CuentaRepository implements Repositorio {

    private String cadenaConexion;

    public CuentaRepository() {
        try {
            DriverManager.registerDriver(new org.sqlite.JDBC());
            cadenaConexion = "jdbc:sqlite:banco.db";
           // crearTabla();
        } catch (SQLException e) {
            System.err.println("Error de conexión con la base de datos: " + e);
        }

    }



    @Override
    public void guardar(Object objeto) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {


                Cuenta cuenta = (Cuenta) objeto;
                String sentenciaSql = "INSERT INTO cuentas (tipo_cuenta, numero_cuenta, saldo, id_usuario ) " +
                        " VALUES('" + cuenta.getTipo() + "', '" + cuenta.getNumeroCuenta()
                        + "', " + cuenta.getSaldo() + ", " + cuenta.getIdUsuario() + ");";
                Statement sentencia = conexion.createStatement();
                System.out.println(sentenciaSql);
                sentencia.execute(sentenciaSql);

                System.out.println("Ejecutado exitosamente");



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

                Cuenta cuenta = (Cuenta) objeto;
            String sentenciaSql = "UPDATE cuentas SET saldo = " + cuenta.getSaldo() + ", tipo_cuenta = '"
                    + cuenta.getTipo() + "', saldo = " + cuenta.getSaldo() + ",id_usuario = "
                    + cuenta.getIdUsuario() + " WHERE numero_cuenta = '" + cuenta.getNumeroCuenta() + "';";
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
    public Cuenta buscar(String numeroCuenta) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSQL = "SELECT * FROM cuentas WHERE numero_cuenta = ?";
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
            sentencia.setString(1, numeroCuenta);
            ResultSet resultadoConsulta = sentencia.executeQuery();
            if (resultadoConsulta != null && resultadoConsulta.next()) {
                String tipoCuenta = resultadoConsulta.getString("tipo_cuenta");
                double saldo = resultadoConsulta.getDouble("saldo");
                String numCuenta = resultadoConsulta.getString("numero_cuenta");
                int id = resultadoConsulta.getInt("id");

                int idUsuario = resultadoConsulta.getInt("id_usuario");



                if (tipoCuenta.equals("CuentaCorriente")) {
                    return new CuentaCorriente(numeroCuenta,idUsuario,saldo,id);
                } else {
                    return new CuentaAhorros(numeroCuenta,idUsuario,saldo,id);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        }
        return null;
    }

    @Override
    public List<Cuenta> listar() {
        List<Cuenta> cuentas = new ArrayList<>();

        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSql = "SELECT * FROM cuentas";
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql);
            ResultSet resultadoConsulta = sentencia.executeQuery();

            if (resultadoConsulta != null) {
                while (resultadoConsulta.next()) {
                   // Cuenta cuenta = null;
                    int id = resultadoConsulta.getInt("id");
                    String numero = resultadoConsulta.getString("numero_cuenta");
                    String tipo = resultadoConsulta.getString("tipo_cuenta");
                    float saldo = resultadoConsulta.getInt("saldo");
                    int idusuario = resultadoConsulta.getInt("id_usuario");


                    if (tipo.equals("CuentaAhorros")){
    CuentaAhorros cuenta=new CuentaAhorros(numero,idusuario,saldo,id );
    cuentas.add(cuenta);
}else {
    CuentaCorriente cuenta=new CuentaCorriente(numero,idusuario,saldo,id );
cuentas.add(cuenta);
}
                   // cuenta = new Persona(id, nombre, apellido, edad, identificacion, celular);
                    //cuentas.add(cuenta);

                }
                return cuentas;
            }
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        }
        return null;

    }

    @Override
    public void actualizarId(Object objeto, String id) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {


             Cuenta   cuenta = (Cuenta) objeto;
                String sentenciaSql = "UPDATE cuenta SET numero_cuenta = '" + cuenta.getNumeroCuenta() + "', tipo_cuenta = '"
                        + cuenta.getTipo() + "', saldo = " + cuenta.getSaldo() + ", id_usuario = "
                        + cuenta.getIdUsuario()+   " WHERE id = " + id
                        + ";";
                Statement sentencia = conexion.createStatement();
                sentencia.execute(sentenciaSql);


        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }
}
