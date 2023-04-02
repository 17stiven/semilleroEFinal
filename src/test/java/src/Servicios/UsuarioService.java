package src.Servicios;

import src.Entidades.Usuario;
import src.Repositorio.Repositorio;
import src.Repositorio.UsuarioRepository;

import java.util.List;
import java.util.Map;

public class UsuarioService implements Service{
    private Repositorio repositorioUsuario;

    public UsuarioService() {
        repositorioUsuario = new UsuarioRepository();
    }

    public void guardar(Map datos) {
        String nombre = (String) datos.get("nombre");
        String apellido = (String) datos.get("apellido");
        String cedula = (String) datos.get("cedula");

        Usuario usuario = new Usuario(nombre, apellido, cedula);
        repositorioUsuario.guardar(usuario);
    }

    public List<Object> listar() {
        return (List<Object>) repositorioUsuario.listar();
    }

    public Usuario buscar(String id) throws Exception {
        Object usuario = repositorioUsuario.buscar(id);
        if (usuario == null) {
            throw new Exception("No se encontró el usuario");
        }
        return (Usuario) usuario;
    }

    @Override
    public void eliminar(String id) {
        repositorioUsuario.eliminar(id);
    }

    public void eliminarUsuario(String id) {
        repositorioUsuario.eliminar(id);
    }

    @Override
    public void actualizar(Map datos) {
        int id = (int) datos.get("id_usuario");
        String nombre = (String) datos.get("nombre");
        String apellido = (String) datos.get("apellido");
        String cedula = (String) datos.get("cedula");

        Usuario usuario = new Usuario(id, nombre, apellido, cedula);
        repositorioUsuario.actualizar(usuario);
    }

    @Override
    public void actualizarId(Map datos, int id) {
        String nombre = (String) datos.get("nombre");
        String apellido = (String) datos.get("apellido");
        String cedula = (String) datos.get("cedula");

        Usuario usuario = new Usuario(id, nombre, apellido, cedula);
        repositorioUsuario.actualizar(usuario);
    }


}

