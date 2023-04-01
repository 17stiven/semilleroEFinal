package src;

import src.Entidades.Cuenta;
import src.Entidades.CuentaAhorros;
import src.Entidades.CuentaCorriente;
import src.Repositorio.CuentaRepository;
import src.Repositorio.Repositorio;

import java.util.List;
import java.util.Map;



public class CuentaService {
    private Repositorio repositorioCuenta;

    public CuentaService() {
        repositorioCuenta = new CuentaRepository();
    }

    public void guardarCuenta(Map datos) {
        String tipo = (String) datos.get("tipo");
        String numero = (String) datos.get("numero");
        float saldo = (float) datos.get("saldo");
        String propietario = (String) datos.get("propietario");
        int numeroRetiros = (int) datos.get("numeroRetiros");
        int numeroDepositos = (int) datos.get("numeroDepositos");
        int transferenciasAhorros = (int) datos.get("transferenciasAhorro");

if (tipo.equals("CuentaAhorros")){
    CuentaAhorros cuenta = new CuentaAhorros(numero,saldo,propietario,numeroRetiros,numeroDepositos);
    repositorioCuenta.guardar(cuenta);
}else {
    Cuenta cuenta = new CuentaCorriente(numero,saldo, propietario,numeroRetiros,numeroDepositos,transferenciasAhorros);
    repositorioCuenta.guardar(cuenta);
}

    }

    public List<Cuenta> listarPersonas() {
        return (List<Cuenta>) repositorioCuenta.listar();
    }

    public Cuenta buscarCuenta(String numero) throws Exception {
        Object persona = repositorioCuenta.buscar(numero);
        if (persona == null) {
            throw new Exception("No se encontro la persona");
        }
        return (Cuenta) persona;
    }

    public void eliminarCuenta(String numero) {
        repositorioCuenta.eliminar(numero);
    }

    public void actualizarCuenta(Map datos) {
        String tipo = (String) datos.get("tipo");
        String numero = (String) datos.get("numero");
        float saldo = (float) datos.get("saldo");
        String propietario = (String) datos.get("propietario");
        int numeroRetiros = (int) datos.get("numeroRetiros");
        int numeroDepositos = (int) datos.get("numeroDepositos");
        int transferenciasAhorros = (int) datos.get("transferenciasAhorro");
//falta

        if (tipo.equals("CuentaAhorros")){
            CuentaAhorros cuenta = new CuentaAhorros(numero,saldo,propietario,numeroRetiros,numeroDepositos);
            repositorioCuenta.actualizar(cuenta);
        }else {
            Cuenta cuenta = new CuentaCorriente(numero,saldo, propietario,numeroRetiros,numeroDepositos,transferenciasAhorros);
            repositorioCuenta.actualizar(cuenta);
        }
       // Persona newPerson = new Persona(nombre, apellido, edad, identificacion, celular);
       // repositorioPersona.actualizar(newPerson);
    }

    public void actualizarCuentaId(Map datos, String id) {
        String tipo = (String) datos.get("tipo");
        String numero = (String) datos.get("numero");
        float saldo = (float) datos.get("saldo");
        String propietario = (String) datos.get("propietario");
        int numeroRetiros = (int) datos.get("numeroRetiros");
        int numeroDepositos = (int) datos.get("numeroDepositos");
        int transferenciasAhorros = (int) datos.get("transferenciasAhorro");
        if (tipo.equals("CuentaAhorros")){
            CuentaAhorros cuenta = new CuentaAhorros(numero,saldo,propietario,numeroRetiros,numeroDepositos);
            repositorioCuenta.actualizarId(cuenta, id);
        }else {
            Cuenta cuenta = new CuentaCorriente(numero,saldo, propietario,numeroRetiros,numeroDepositos,transferenciasAhorros);
            repositorioCuenta.actualizarId(cuenta, id);
        }
      //  Persona newPerson = new Persona(nombre, apellido, edad, identificacion, celular);
      //  repositorioPersona.actualizarId(newPerson, id);
    }
}