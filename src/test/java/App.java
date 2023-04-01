import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import src.Controlador.CuentaController;
import src.CuentaService;
import src.Entidades.Cuenta;

import src.Entidades.CuentaAhorros;
import src.Entidades.CuentaCorriente;
import src.Excepciones.CuentaInvalidaException;
import src.Excepciones.LimiteRetirosException;
import src.Excepciones.ValorInvalidoException;
import src.Excepciones.ValorNegativoException;
import src.Repositorio.CuentaRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//import static sun.net.www.protocol.http.AuthCacheValue.Type.Server;


public class App
{
    public static  void main(String[] args )  {
        CuentaRepository BD=new CuentaRepository();
        CuentaAhorros cuentaA=new CuentaAhorros("1234",1000,"brayan" );
        CuentaAhorros cuentaA4=new CuentaAhorros("20",8000,"diego" );
        CuentaAhorros cuentaA5=new CuentaAhorros("21",10000,"luis" );

        CuentaAhorros cuentaA2=new CuentaAhorros("1235",5000,"santiago" );
        CuentaCorriente cuentaA3=new CuentaCorriente("12",5000,"santiago" ,4,3,2);
        CuentaCorriente cuentaA6=new CuentaCorriente("15",7500,"vanesa" );

/*BD.guardar(cuentaA);
BD.guardar(cuentaA3);
BD.guardar(cuentaA4);
BD.guardar(cuentaA5);
BD.guardar(cuentaA6);
BD.actualizar(cuentaA2);*/




//
//
//
        Scanner scanner = new Scanner(System.in);
        //Cuenta cuenta = new CuentaCorriente();
        System.out.println("BUSCAR CUENTA");
        System.out.println("Ingrese el número de cuenta a buscar \n");
        String cd=scanner.nextLine();

        int opcion = 0;
        Cuenta cuentaEncontrada =BD.buscar(cd);
        if (cuentaEncontrada!=null) {


            while (opcion != 8) {
                System.out.println(" MENÚ DE LA CUENTA ENCONTRADA ");
                System.out.println("1. Consultar saldo");
                System.out.println("2. Depositar");
                System.out.println("3. Retirar");
                System.out.println("4. Ver detalles de cuenta");
                System.out.println("5. Listar todas las cuentas");
                System.out.println("8. Salir");

                System.out.print("Ingrese la opción deseada: ");
                opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Saldo actual: $" + cuentaEncontrada.getSaldo());
                    System.out.println("tipo cuenta "+cuentaEncontrada.getTipo());
                    break;
                case 2:
                    System.out.print("Ingrese la cantidad a depositar: $");
                    double cantidadDeposito = scanner.nextDouble();
                    try {
                        cuentaEncontrada.depositar(cantidadDeposito);
                        BD.actualizar(cuentaEncontrada);
                    } catch (ValorNegativoException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Depósito exitoso. Saldo actual: $" + cuentaEncontrada.getSaldo());
                    break;
                case 3:
                    System.out.print("Ingrese la cantidad a retirar: $");
                    double cantidadRetiro = scanner.nextDouble();
                    try {
                        cuentaEncontrada.retirar(cantidadRetiro);
                        BD.actualizar(cuentaEncontrada);
                    } catch (ValorInvalidoException e) {
                        throw new RuntimeException(e);
                    } catch (LimiteRetirosException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        cuentaEncontrada.retirar(cantidadRetiro);
                    } catch (ValorInvalidoException e) {
                        throw new RuntimeException(e);
                    } catch (LimiteRetirosException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Retiro exitoso. Saldo actual: $" + cuentaEncontrada.getSaldo());

                    break;
                case 4:
                    System.out.println("DETALLES");
                    System.out.println(cuentaEncontrada);

                    break;
                case 5:
                    System.out.println("LISTADO DE CUENTAS");
                    BD.listar().forEach(c->{
                        System.out.println("Número cuenta: "+c.getNumeroCuenta()+"\n" +
                                "Tipo Cuenta: "+c.getTipo());
                    });

                    break;
                case 8:
                    System.out.println("Terminado");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }
            }
        }else {
            try {
                throw new CuentaInvalidaException("No se encontró la cuenta con número: "+cd);
            } catch (CuentaInvalidaException e) {
                System.out.println(e.getMessage());
                main(args);


            }
        }
//        scanner.close();
    }
}


