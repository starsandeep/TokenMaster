package com.akirolabs.token.validatorservice.service;

import com.akirolabs.token.validatorservice.utils.TokenValidatorUtil;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.akirolabs.token.validatorservice.constant.AppConstants.GENERIC_LOG_METHOD_MESSAGE;

/**
 * @author: Sandeep Kumar Nagaraj
 * @since: 13-08-2023
 */
@Service
@Slf4j
public class TokenValidatorService {

    /**
     * VerifyToken() uses Luhn algorithm to determine whether a given token is valid or not
     *
     * Below are the steps followed
     * 1. Cleanse the token if there are any "-" characters
     * 2. Iterate the token from right to left
     * 3. Add the digits to the sum variable
     * 4. For Every 2nd digit,  double the digit and sum the digits to single digit and add it to sun variable
     * 5. Perform Mod on final sum.
     *      -If result is 0 then it is a "Valid Token
     *      - else return as "Invalid token"
     *
     * @param token
     * @return response as "Valid Token" or "Invalid Token"
     */
    public Mono<String> verifyToken(String token) {
        log.info(GENERIC_LOG_METHOD_MESSAGE, "verifyToken", "Token Value::" + token);

        int sum=0;
        if( StringUtils.isNotBlank(token)) {
            token = token.replaceAll("-","");
            int tokenLength = token.length() - 1;

            for (int i = tokenLength; i >= 0; i--) {
                int digit = Integer.parseInt(Character.toString(token.charAt(i)));
                if (tokenLength % 2 == 0) {
                    digit = TokenValidatorUtil.doubleAndSumDigits(digit);
                }
                sum += digit;
            }
        }
        log.info(GENERIC_LOG_METHOD_MESSAGE, "verifyToken", "Final sum value::" + sum);
        return Mono.just(sum % 10 == 0 ? "Valid Token" : "Invalid Token");
    }
}
