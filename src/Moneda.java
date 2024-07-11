import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Moneda {

    public double obtenerTasaConversion(String jsonResponse, String divisa){
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

        JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");

        return conversionRates.get(divisa).getAsDouble();
    }

}