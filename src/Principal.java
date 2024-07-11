//Autor: Jovany Rios Estrada
import java.util.Scanner;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse;


public class Principal {

    private static final String llave = "e1722cd3a12669c987a273c8";
    private static final String url = "https://v6.exchangerate-api.com/v6/" + llave + "/latest/";


    public static void main(String[] args) {
        int opcion = 0;
        System.out.println("***************Conversor de Monedas**************\n");

        String menu = """
                1-> Dólar --> Peso Mexicano
                2-> Peso Mexicano --> Dólar
                3-> Dólar --> Peso argentino
                4-> Peso argentino --> Dólar
                5-> Dólar --> Real brasileño
                6-> Real brasileño --> Dólar  
                7-> Salir 
                """;

        Scanner teclado = new Scanner(System.in);
        while (opcion != 7){
            //hacemos la impresion del menú
            System.out.println(menu);

            System.out.print("\nSeleccione una opcion: ");
            opcion = teclado.nextInt();
            //si e s7 cerramos el while
            if (opcion == 7){
                System.out.println("Adiosssss......");
                break;
            }

            System.out.println("Ingrese el valor a convertir: ");
            double valor = teclado.nextDouble();

            try{
                switch (opcion){
                    case 1://Dólar --> Peso Mexicano
                        System.out.println(convertirMoneda("USD", "MXN", valor));
                        break;
                    case 2://Peso Mexicano --> Dólar
                        System.out.println(convertirMoneda("MXN", "USD", valor));
                        break;
                    case 3://Dólar --> Peso argentino
                        System.out.println(convertirMoneda("USD", "ARS", valor));
                        break;
                    case 4://Peso argentino --> Dólar
                        System.out.println(convertirMoneda("ARS", "USD", valor));
                        break;
                    case 5://Dólar --> Real brasileño
                        System.out.println(convertirMoneda("USD", "BRL", valor));
                        break;
                    case 6://Real brasileño --> Dólar
                        System.out.println(convertirMoneda("BRL", "USD", valor));
                        break;
                    default:
                        System.out.println("Opcion invalida");
                }
            } catch (IOException | InterruptedException e){
                System.out.println("Error al realizar la conversion: " + e.getMessage());
            }
        }
    }

    public static String convertirMoneda (String moneda1, String moneda2, double valor) throws IOException, InterruptedException {
        String mensaje="";
        String url2 = url + moneda1;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url2))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Moneda moneda = new Moneda();
        double tasa = moneda.obtenerTasaConversion(response.body(), moneda2);

        double resultado = valor * tasa;

        return mensaje = "El valor" + " " + valor + " " + "(" + moneda1 + ")" + " " + "equivale al valor final de -->" + " " + resultado + " " + "(" + moneda2 + ")\n";
    }

}