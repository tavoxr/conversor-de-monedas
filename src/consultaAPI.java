import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class consultaAPI {
    public static String obtenerDatosAPI(String url,String base_currency, String target_currency, Double amount){

        String URI_STRING = url + base_currency + "/" + target_currency + "/" + amount ;
        HttpClient cliente = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URI_STRING))
                .build();


        try {
            HttpResponse<String> response = cliente.send(request,HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() != 200 || response.body().contains("error") || response.body().contains("Not Found") ){

                if(response.body().contains("404 Not Found")){
                    return "Codigo de moneda no valido";
                }else {
                    JSONObject jsonObj = new JSONObject(response.body());
                    System.out.println("Error " + jsonObj.get("error-type"));

                    return "Error codigo de moneda no soportado";
                }

            }

           return response.body();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }


    public  static JSONObject obtenerCodigosMonedas(String url) {

        String URI_STRING = url;
        HttpClient cliente = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URI_STRING))
                .build();


        try {
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            //List<> codigos = new ArrayList<>();

            JSONObject codesJson = new JSONObject(response.body());
            //codigos = codesJson.get("supported_codes");
            System.out.println( codesJson.get("supported_codes"));
            return codesJson;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }



}
