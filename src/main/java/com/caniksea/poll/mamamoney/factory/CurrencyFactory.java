package com.caniksea.poll.mamamoney.factory;

import com.caniksea.poll.mamamoney.model.Currency;

import java.math.BigDecimal;

public class CurrencyFactory {

    public static Currency build(String id, String code, String codeDescription, String countryName, BigDecimal rate) {
        return new Currency.Builder().id(id)
                .code(code).codeDescription(codeDescription)
                .countryName(countryName).rate(rate).build();
    }
}
