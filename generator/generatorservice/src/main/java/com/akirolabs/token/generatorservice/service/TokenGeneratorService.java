package com.akirolabs.token.generatorservice.service;

import com.akirolabs.token.generatorservice.Exception.BadRequestException;
import com.akirolabs.token.generatorservice.model.GenericRequest;
import com.akirolabs.token.generatorservice.utils.TokenGeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;


import static com.akirolabs.token.generatorservice.constant.AppConstant.*;

/**
 * @author: Sandeep Kumar Nagaraj
 * @since: 12-08-2023
 */
@Service
@Slf4j
public class TokenGeneratorService {

    /**
     * This method generates the token from the user passed digits
     *
     * Below are the steps performed
     * 1. Perform Validation for empty and Invalid data
     * 2. Generate the token of length 16 digits
     *
     * @param genericRequest
     * @return new token
     */
    public Mono<String> generateToken(GenericRequest<Integer> genericRequest) {

        log.info(GENERIC_LOG_METHOD_MESSAGE, "generateToken", "Request::" + genericRequest);
        List<Integer> digits = genericRequest.getData();
        performValidation(genericRequest.getData());

        log.info(GENERIC_LOG_METHOD_MESSAGE, "generateToken", "Passed Validation");

        StringBuilder token = TokenGeneratorUtil.
                createCustomSizeToken(digits, digits.size(), 16);

        log.info(GENERIC_LOG_METHOD_MESSAGE, "generateToken", "Generated token::" + token);

        return Mono.just(token.toString());
    }

    private static void performValidation(List<Integer> digits) {
        if(CollectionUtils.isEmpty(digits)) {
            log.info(GENERIC_LOG_METHOD_MESSAGE, "performValidation", "Digits are Empty");
            throw new BadRequestException(EMPTY_DATA_ERROR_MESSAGE);
        } else {
            Optional<Integer> invalidDigit = digits.stream().
                    filter(digit -> digit > 9).findFirst();
            invalidDigit.ifPresent(i -> {
                log.info(GENERIC_LOG_METHOD_MESSAGE, "performValidation", "Input contains digits > 9");
                throw new BadRequestException(INVALID_DATA_ERROR_MESSAGE);});
        }
    }
}
