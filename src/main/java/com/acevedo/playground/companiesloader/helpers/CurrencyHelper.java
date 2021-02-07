package com.acevedo.playground.companiesloader.helpers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class CurrencyHelper {
    public static Long convertAmountToUSD(Long amount, String currency) {
        // TODO replace with real impl
        return amount != null ? amount : 0L;
    }
}
