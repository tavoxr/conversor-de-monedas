import com.currency_convertor.models.CurrencyPairExchange;

import java.lang.reflect.Type;
import java.util.Objects;
import java.util.Optional;

public interface IJsonConverter {

    public <TipoClase>  TipoClase convertirJsonAObjeto(String json);

    public String convertirObjetoAJson();


}
