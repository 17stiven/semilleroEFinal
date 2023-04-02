package src.Repositorio;




import src.Entidades.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository implements Repositorio {
    private String cadenaConexion;

    public UsuarioRepository() {
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

           
                Usuario usuario = (Usuario) objeto;
                String sentenciaSql = "INSERT INTO usuarios (nombre, apellido, cedula ) " +
                        " VALUES('" + usuario.getNombre() + "', '" + usuario.getApellido()
                        + "', '"  + usuario.getCedula() + "' "  +  ");";
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
    public void eliminar(String cedula) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSql = "DELETE FROM usuarios WHERE cedula = '" + cedula + "';";
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

                Usuario usuario = (Usuario) objeto;
                String sentenciaSql = "UPDATE usuarios SET nombre = " + usuario.getNombre() + ", apellido = '"
                        + usuario.getApellido() + "' "+ " WHERE cedula = '" + usuario.getCedula() + "';";
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
    public Usuario buscar(String numeroUsuario) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSQL = "SELECT * FROM usuario WHERE cedula = ?";
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
            sentencia.setString(1, numeroUsuario);
            ResultSet resultadoConsulta = sentencia.executeQuery();
            if (resultadoConsulta != null && resultadoConsulta.next()) {
                int id = resultadoConsulta.getInt("id");

                String nombre = resultadoConsulta.getString("nombre");

                String apellido = resultadoConsulta.getString("apellido");
                String cedula = resultadoConsulta.getString("cedula");





                return new Usuario(nombre,apellido,cedula);
            }

        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        }
        return null;
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> usuarioes = new ArrayList<>();

        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSql = "SELECT * FROM usuarios";
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql);
            ResultSet resultadoConsulta = sentencia.executeQuery();

            if (resultadoConsulta != null) {
                while (resultadoConsulta.next()) {
                     Usuario usuario = null;
                    int id = resultadoConsulta.getInt("id");

                    String nombre = resultadoConsulta.getString("nombre");

                    String apellido = resultadoConsulta.getString("apellido");
                    String cedula = resultadoConsulta.getString("cedula");

                 usuario=new Usuario(id,nombre,apellido,cedula);
                 usuarioes.add(usuario);


                }
                return usuarioes;
            }
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        }
        return null;

    }

    @Override
    public void actualizarId(Object objeto, String id) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {

            if (objeto.getClass().getSimpleName().equals("Usuario")){
                Usuario   usuario = (Usuario) objeto;
                String sentenciaSql = "UPDATE usuarios SET nombre = " + usuario.getNombre() + ", apellido = '"
                        + usuario.getApellido() + "', "+ "SET cedula = '"+usuario.getCedula()+  "' WHERE id = " + id
                        + ";";
                Statement sentencia = conexion.createStatement();
                sentencia.execute(sentenciaSql);
            }

        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }
}
