package src.Servicios;



import java.util.List;
import java.util.Map;


public interface Service {
    void guardar(Map<String, Object> datos);
    List<Object> listar();
    Object buscar(String id) throws Exception;
    void eliminar(String id);
    void actualizar(Map<String, Object> datos);
    void actualizarId(Map<String, Object> datos, int id);
}

