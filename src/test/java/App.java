import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import src.Controlador.CuentaController;
import src.Entidades.Cuenta;
import src.CuentaCorriente;

import java.util.Scanner;

//import static sun.net.www.protocol.http.AuthCacheValue.Type.Server;


public class App
{
    public static  void main(String[] args )
    {
        Server server = new Server(8080);
        server.setHandler(new DefaultHandler());

        ServletContextHandler context = new ServletContextHandler();

        context.setContextPath("/");
        context.addServlet(String.valueOf(CuentaController.class), "/cuenta/*");

        server.setHandler(context);

        try{
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }




        Scanner scanner = new Scanner(System.in);
        Cuenta cuenta = new CuentaCorriente();

        int opcion = 0;

        while (opcion != 4) {
            System.out.println("---- MENÚ DE CUENTA ----");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Depositar");
            System.out.println("3. Retirar");
            System.out.println("4. Salir");

            System.out.print("Ingrese la opción deseada: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Saldo actual: $" + cuenta.getSaldo());
                    break;
                case 2:
                    System.out.print("Ingrese la cantidad a depositar: $");
                    double cantidadDeposito = scanner.nextDouble();
                    cuenta.depositar(cantidadDeposito);
                    System.out.println("Depósito exitoso. Saldo actual: $" + cuenta.getSaldo());
                    break;
                case 3:
                    System.out.print("Ingrese la cantidad a retirar: $");
                    double cantidadRetiro = scanner.nextDouble();

                    cuenta.retirar(cantidadRetiro);
                    System.out.println("Retiro exitoso. Saldo actual: $" + cuenta.getSaldo());

                    break;
                case 4:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }
        }

        scanner.close();
    }
}


