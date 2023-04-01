package src.Repositorio;

import src.Entidades.Transaccion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaccionRepository implements  Repositorio{
    private String cadenaConexion;

    public TransaccionRepository() {
        try {
            DriverManager.registerDriver(new org.sqlite.JDBC());
            cadenaConexion = "jdbc:sqlite:banco.db";

        } catch (SQLException e) {
            System.err.println("Error de conexión con la base de datos: " + e);
        }

    }



    @Override
    public void guardar(Object objeto) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {


            Transaccion transaccion = (Transaccion) objeto;
            String sentenciaSql = "INSERT INTO transacciones (fecha, hora, tipo_transaccion, monto, id_cuenta, tipo_cuenta_destino ) " +
                    " VALUES('" + transaccion.getFecha() + "', '" + transaccion.getHora()
                    + "', "  + transaccion.getTipoTransaccion() + "', "+ transaccion.getMonto()+", "+transaccion.getIdCuenta()+", '"+transaccion.getTipoCuentaDestino()  +  "');";
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
    public void eliminar(String numeroTransaccion) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSql = "DELETE FROM transacciones WHERE id = " + numeroTransaccion + ";";
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

            Transaccion transaccion = (Transaccion) objeto;
            String sentenciaSql = "UPDATE transacciones SET fecha = " + transaccion.getFecha() + ", hora = '"
                    + transaccion.getHora() +", monto="+transaccion.getMonto()+ ", tipo_transaccion = '"+transaccion.getTipoTransaccion()+"', id_cuenta="+ transaccion.getIdCuenta()+", tipo_cuenta_destino= '"+transaccion.getTipoCuentaDestino()+"' WHERE id = " + transaccion.getId()+ ";";
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
    public Transaccion buscar(String numeroTransaccion) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSQL = "SELECT * FROM transaccion WHERE id = ?";
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
            sentencia.setInt(1, new Integer(numeroTransaccion));
            ResultSet resultadoConsulta = sentencia.executeQuery();
            if (resultadoConsulta != null && resultadoConsulta.next()) {
                int id = resultadoConsulta.getInt("id");

                String fecha = resultadoConsulta.getString("fecha");

                String hora = resultadoConsulta.getString("hora");
                String tipo_transaccion = resultadoConsulta.getString("tipo_transaccion");
                float monto = resultadoConsulta.getFloat("monto");
                int id_cuenta = resultadoConsulta.getInt("id_cuenta");
                String tipo_cuenta_destino = resultadoConsulta.getString("tipo_cuenta_destino");








                return new Transaccion(id,fecha,hora,tipo_transaccion,monto,id_cuenta,tipo_cuenta_destino);
            }

        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        }
        return null;
    }

    @Override
    public List<Transaccion> listar() {
        List<Transaccion> transacciones = new ArrayList<>();

        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSql = "SELECT * FROM transacciones";
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql);
            ResultSet resultadoConsulta = sentencia.executeQuery();

            if (resultadoConsulta != null) {
                while (resultadoConsulta.next()) {
                    Transaccion transaccion = null;
                    int id = resultadoConsulta.getInt("id");

                    String fecha = resultadoConsulta.getString("fecha");

                    String hora = resultadoConsulta.getString("hora");
                    String tipo_transaccion = resultadoConsulta.getString("tipo_transaccion");
                    float monto = resultadoConsulta.getFloat("monto");
                    int id_cuenta = resultadoConsulta.getInt("id_cuenta");
                    String tipo_cuenta_destino = resultadoConsulta.getString("tipo_cuenta_destino");

                    transaccion= new Transaccion(id,fecha,hora,tipo_transaccion,monto,id_cuenta,tipo_cuenta_destino);

                    transacciones.add(transaccion);


                }
                return transacciones;
            }
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        }
        return null;

    }

    @Override
    public void actualizarId(Object objeto, String id) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {

            if (objeto.getClass().getSimpleName().equals("Transaccion")) {
                Transaccion transaccion = (Transaccion) objeto;
                String sentenciaSql = "UPDATE transacciones SET fecha = " + transaccion.getFecha() + ", hora = '"
                        + transaccion.getHora() + ", monto=" + transaccion.getMonto() + ", tipo_transaccion = '" + transaccion.getTipoTransaccion() + "', id_cuenta=" + transaccion.getIdCuenta() + ", tipo_cuenta_destino= '" + transaccion.getTipoCuentaDestino() + "' WHERE id = " + id + ";";

                Statement sentencia = conexion.createStatement();
                sentencia.execute(sentenciaSql);
            }

        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }}

