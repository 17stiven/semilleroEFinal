package src.Servicios;

import src.Entidades.Transaccion;
import src.Repositorio.Repositorio;
import src.Repositorio.TransaccionRepository;

import java.util.List;
import java.util.Map;

public class TransaccionService implements Service {
    private Repositorio repositorioTransaccion;

    public TransaccionService() {
        this.repositorioTransaccion = new TransaccionRepository();
    }

    @Override
    public void guardar(Map<String, Object> datos) {
        String fecha = (String) datos.get("fecha");
        String hora = (String) datos.get("hora");
        String tipoTransaccion = (String) datos.get("tipo_transaccion");
        double monto = (double) datos.get("monto");
        int idCuenta = (int) datos.get("id_cuenta");
        String tipoCuentaDestino = (String) datos.get("tipo_cuenta_destino");
        Transaccion transaccion = new Transaccion(fecha, hora, tipoTransaccion, monto, idCuenta, tipoCuentaDestino);
        repositorioTransaccion.guardar(transaccion);
    }

    @Override
    public List<Object> listar() {
        return (List<Object>) repositorioTransaccion.listar();
    }

    @Override
    public Object buscar(String id) {
        return repositorioTransaccion.buscar(id);
    }

    @Override
    public void actualizar(Map<String, Object> datos) {
        int id = (int) datos.get("id");
        String fecha = (String) datos.get("fecha");
        String hora = (String) datos.get("hora");
        String tipoTransaccion = (String) datos.get("tipo_transaccion");
        double monto = (double) datos.get("monto");
        int idCuenta = (int) datos.get("id_cuenta");
        String tipoCuentaDestino = (String) datos.get("tipo_cuenta_destino");
        Transaccion transaccion = new Transaccion(id, fecha, hora, tipoTransaccion, monto, idCuenta, tipoCuentaDestino);
        repositorioTransaccion.actualizar(transaccion);
    }

    @Override
    public void actualizarId(Map<String, Object> datos, int id) {
        String fecha = (String) datos.get("fecha");
        String hora = (String) datos.get("hora");
        String tipoTransaccion = (String) datos.get("tipoTransaccion");
        double monto = (double) datos.get("monto");
        int idCuenta = (int) datos.get("idCuenta");
        String tipoCuentaDestino = (String) datos.get("tipoCuentaDestino");

        Transaccion transaccion = new Transaccion(id, fecha, hora, tipoTransaccion, monto, idCuenta, tipoCuentaDestino);
        repositorioTransaccion.actualizar(transaccion);
    }

    @Override
    public void eliminar(String id) {
        repositorioTransaccion.eliminar(id);
    }
}
