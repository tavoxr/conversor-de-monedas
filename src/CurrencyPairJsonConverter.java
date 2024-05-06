import com.currency_convertor.models.CurrencyPairDto;
import com.currency_convertor.models.CurrencyPairExchange;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CurrencyPairJsonConverter implements  IJsonConverter {

    private Gson gson;
    private CurrencyPairDto currencyPairDto;
    private CurrencyPairExchange currencyPairExchange;

    public  CurrencyPairJsonConverter(){
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public  CurrencyPairExchange convertirJsonAObjeto(String json) {


            this.currencyPairDto = gson.fromJson(json, CurrencyPairDto.class);
            this.currencyPairExchange = new CurrencyPairExchange(this.currencyPairDto);
            return this.currencyPairExchange;



    }


    @Override
    public String convertirObjetoAJson() {
        return null;
    }
}
