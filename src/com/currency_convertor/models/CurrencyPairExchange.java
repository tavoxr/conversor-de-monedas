package com.currency_convertor.models;

public class CurrencyPairExchange {

    private String base_code;
    private String target_code;
    private Double convertion_rate;
    private Double convertion_result;


    public  CurrencyPairExchange(CurrencyPairDto currencyPairDto){

        this.base_code = currencyPairDto.base_code();
        this.target_code = currencyPairDto.target_code();
        this.convertion_rate = Double.valueOf(currencyPairDto.conversion_rate());
        this.convertion_result = Double.valueOf(currencyPairDto.conversion_result());

    }

    @Override
    public String toString() {
        return "CurrencyPairExchange{" +
                "base_code='" + base_code + '\'' +
                ", target_code='" + target_code + '\'' +
                ", convertion_rate=" + convertion_rate +
                ", convertion_result=" + convertion_result +
                '}';
    }

    public String mostrarDetalleCambiodeMoneda(){
        return String.format("""
                ==========================================
                                Resultado
                ==========================================
                Moneda Base: %s
                Moneda a Convertir: %s
                Cambio Actual: %s 1.00  = %s %.2f
                
                Resultado Conversion: %s %.2f
                ==========================================
                ==========================================
                  
                """,this.base_code,this.target_code,this.base_code,this.target_code, this.convertion_rate,this.target_code,this.convertion_result);
    }
}
