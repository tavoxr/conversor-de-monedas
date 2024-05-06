import com.currency_convertor.models.CurrencyPairDto;
import com.currency_convertor.models.CurrencyPairExchange;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jdk.swing.interop.SwingInterOpUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Your API Key: 059a41228ae5116586d6e539
//Example Request: https://v6.exchangerate-api.com/v6/059a41228ae5116586d6e539/latest/USD
public class Main {
    public static void main(String[] args) {

        boolean ejecutarPrograma = true;
        Scanner sc = new Scanner(System.in);
        String base_currency_code;
        String target_currency_code;
        Double amount = 0.0;
        String api_response = "";
        int opcionMenu=0;

        while(opcionMenu != 3) {

            mostrarMenu();
            opcionMenu = Integer.parseInt(obtenerDatoDeUsuario(""));

            switch (opcionMenu){
                case 1:{ //Convertir Moneda
                        //Obtener Datos de Usuario
                            base_currency_code = obtenerDatoDeUsuario("Por favor, ingrese el codigo del tipo de moneda que desea convertir. Ejemplo: USD");
                            target_currency_code = obtenerDatoDeUsuario("Por favor seleccione el codigo la  moneda  a la que le gustaría realizar la conversión. Ejemplo: GTQ");
                            amount = Double.parseDouble(obtenerDatoDeUsuario("Por favor ingresa la cantidad a convertir. Ejemplo: 100.00"));

                         // Obtener json desde API
                            api_response = consultaAPI.obtenerDatosAPI("https://v6.exchangerate-api.com/v6/059a41228ae5116586d6e539/pair/",
                                    base_currency_code, target_currency_code, amount);

                            if(api_response.equalsIgnoreCase("Codigo de moneda no valido")) {
                                System.out.println("Error codigo de moneda no valido");
                            }else if(api_response.equalsIgnoreCase("Error codigo de moneda no soportado")) {

                                System.out.println("Error codigo de moneda no soportado");
                            }else{

                         //Iplementacion servicio convertidor de json
                                IJsonConverter jsonSerializer = new CurrencyPairJsonConverter();

                         //convertir json a objeto de tipo CurrencyPairExchange
                                CurrencyPairExchange currencyPairExchange = jsonSerializer.convertirJsonAObjeto(api_response);
                                System.out.println(currencyPairExchange);
                                System.out.println(currencyPairExchange.mostrarDetalleCambiodeMoneda());
                            }
                            break;
                    }
                case 2:{
                                JSONObject jsonCurrencyCodes;
                               jsonCurrencyCodes = consultaAPI.obtenerCodigosMonedas("https://v6.exchangerate-api.com/v6/059a41228ae5116586d6e539/codes");
                                //Convertir Objeto Json en JSONArray extrayendo la lista supported_codes
                               JSONArray codigosJsonArray  = jsonCurrencyCodes.getJSONArray("supported_codes");

                               //ArrayList<Object> codigos = new ArrayList<Object>();
                                System.out.println("Lista de codigos de monedas:");
                                System.out.println();
                                System.out.println("[\"CODIGO\",\"NOMBRE MONEDA\"]");
                                System.out.println();
                               codigosJsonArray.forEach(System.out::println);


                        //String buscarPorCodigoPorInicial = obtenerDatoDeUsuario("Ingresa una letra para buscar codigo por inicial");







                    break;
                }
                case 3: {
                    System.out.println("Gracias por utilizar nuestro programa");
                    break;
                }
                default:{
                    System.out.println("Opcion invalida, intenta de nuevo");
                    break;
                }



            }




        }


    }


    public static void  mostrarMenu(){

        System.out.println("""
                ******************************************************
                        Conversor de Monedas
                ******************************************************
                 Bienvenido elige el numero de la opcion del menu:
                  1 - Convertir monedas
                  2 - Ver codigos de monedas disponibles
                  3 - Salir del programa
                ______________________________________________________ 
                """);
    }

    public static String obtenerDatoDeUsuario(String mensajePantalla ){
        Scanner sc = new Scanner(System.in);

        if(!mensajePantalla.equalsIgnoreCase("")) {
            System.out.println(mensajePantalla);
            return sc.nextLine();
        }else{
            return sc.nextLine();
        }
    }



}