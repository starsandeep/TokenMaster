package com.akirolabs.token.validatorservice.utils;

/**
 * @author: Sandeep Kumar Nagaraj
 * @since: 13-08-2023
 */
public interface TokenValidatorUtil {
    static int doubleAndSumDigits(int digit) {
        int finalDigit = digit * 2;
        return (finalDigit > 9 ? digit - 9 : finalDigit);
    }
}
