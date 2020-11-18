package com.caniksea.poll.mamamoney.factory;

import com.caniksea.poll.mamamoney.model.Currency;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CurrencyFactoryTest {

    @Test
    public void build() {
        Currency currency = CurrencyFactory.build("1", "KES", "Kenya Shillings", "Kenya", new BigDecimal(0.065));
        System.out.println(currency);
        assertNotNull(currency);
    }
}