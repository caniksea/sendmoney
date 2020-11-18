package com.caniksea.poll.mamamoney.factory;

import com.caniksea.poll.mamamoney.model.UssdMenu;
import org.hibernate.internal.util.StringHelper;

public class UssdMenuFactory {

    public static UssdMenu build(String menuNumber, String menuDescription) throws Exception {
        if (StringHelper.isEmptyOrWhiteSpace(menuNumber))
            throw new Exception("Provide menu number!");
        if (StringHelper.isEmptyOrWhiteSpace(menuDescription))
            throw new Exception("Provide menu description!");
        return new UssdMenu.Builder()
                .menuNumber(menuNumber).menuDescription(menuDescription)
                .build();
    }
}
