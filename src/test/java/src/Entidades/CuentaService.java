package src.Entidades;

import src.Entidades.Cuenta;
import src.Repositorio.CuentaRepository;
import src.Repositorio.Repositorio;

import java.util.List;
import java.util.Map;

public class CuentaService {
    private Repositorio repositorioCuenta;

    public CuentaService() {
        repositorioCuenta = new CuentaRepository();
    }

    public void guardarCuenta(Cuenta newCuenta) {
        repositorioCuenta.guardar(newCuenta);
    }

    public List<Cuenta> listarCuentas() {
        return (List<Cuenta>) repositorioCuenta.listar();
    }

    public Cuenta buscarCuenta(String identificador) throws Exception {
        Object cuenta = repositorioCuenta.buscar(identificador);
        if(cuenta == null) {
            throw new Exception("No se encontró la cuenta");
        }
        return (Cuenta) cuenta;
    }

    public void eliminarCuenta(String identificador) {
        repositorioCuenta.eliminar(identificador);
    }

    public void actualizarCuenta(Map<String, Object> cuentaMap) {
        repositorioCuenta.actualizar(cuentaMap);
    }
}
