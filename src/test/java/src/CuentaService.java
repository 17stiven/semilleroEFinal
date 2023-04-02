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
        String tipo = (String) datos.get("tipo_cuenta");
        String numero = (String) datos.get("numero_cuenta");
        float saldo = (float) datos.get("saldo");
        int usuario = (int) datos.get("id_usuario");


if (tipo.equals("CuentaAhorros")){
    CuentaAhorros cuenta = new CuentaAhorros(numero,usuario,saldo);
    repositorioCuenta.guardar(cuenta);
}else {
    Cuenta cuenta = new CuentaCorriente(numero,usuario,saldo);
    repositorioCuenta.guardar(cuenta);
}

    }

    public List<Cuenta> listarPersonas() {
        return (List<Cuenta>) repositorioCuenta.listar();
    }

    public Cuenta buscarCuenta(String numero) throws Exception {
        Object cuenta = repositorioCuenta.buscar(numero);
        if (cuenta == null) {
            throw new Exception("No se encontro la cuenta");
        }
        return (Cuenta) cuenta;
    }

    public void eliminarCuenta(String numero) {
        repositorioCuenta.eliminar(numero);
    }

    public void actualizarCuenta(Map datos) {
        String tipo = (String) datos.get("tipo_cuenta");
        String numero = (String) datos.get("numero_cuenta");
        float saldo = (float) datos.get("saldo");
        int usuario = (int) datos.get("id_usuario");


        if (tipo.equals("CuentaAhorros")){
            CuentaAhorros cuenta = new CuentaAhorros(numero,usuario,saldo);
            repositorioCuenta.actualizar(cuenta);
        }else {
            Cuenta cuenta = new CuentaCorriente(numero,usuario,saldo);
            repositorioCuenta.actualizar(cuenta);
        }
       // Persona newPerson = new Persona(nombre, apellido, edad, identificacion, celular);
       // repositorioPersona.actualizar(newPerson);
    }

    public void actualizarCuentaId(Map datos, String id) {
        String tipo = (String) datos.get("tipo_cuenta");
        String numero = (String) datos.get("numero_cuenta");
        float saldo = (float) datos.get("saldo");

        int idusuario = (int) datos.get("id_usuario");

        if (tipo.equals("CuentaAhorros")){
            CuentaAhorros cuenta = new CuentaAhorros(numero,idusuario,saldo );
            repositorioCuenta.actualizarId(cuenta, id);
        }else {
            Cuenta cuenta = new CuentaCorriente(numero,idusuario,saldo);
            repositorioCuenta.actualizarId(cuenta, id);
        }
      //  Persona newPerson = new Persona(nombre, apellido, edad, identificacion, celular);
      //  repositorioPersona.actualizarId(newPerson, id);
    }
}