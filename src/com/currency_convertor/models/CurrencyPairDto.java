package com.currency_convertor.models;

public record CurrencyPairDto(String base_code, String target_code, String conversion_rate, String conversion_result) {
}
