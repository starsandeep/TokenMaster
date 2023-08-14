package com.akirolabs.token.generatorservice.controller;

import com.akirolabs.token.generatorservice.model.GenericRequest;
import com.akirolabs.token.generatorservice.model.GenericResponse;
import com.akirolabs.token.generatorservice.service.TokenGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static com.akirolabs.token.generatorservice.constant.AppConstant.GENERIC_LOG_METHOD_MESSAGE;

/**
 * @author: Sandeep Kumar Nagaraj
 * @since: 12-08-2023
 */

@RestController
@Slf4j
@RequestMapping("api/token/generate")
@CrossOrigin
public class TokenGeneratorController {

    @Autowired
    private TokenGeneratorService tokenGeneratorService;

    @PostMapping
    public Mono<GenericResponse> createToken(@Validated
                                        @RequestBody GenericRequest<Integer> genericRequest) {

        log.info(GENERIC_LOG_METHOD_MESSAGE, "createToken", "Request::" + genericRequest);

        return tokenGeneratorService.generateToken(genericRequest)
                                .map(token -> GenericResponse.builder()
                                        .response(token).build());
    }

}
