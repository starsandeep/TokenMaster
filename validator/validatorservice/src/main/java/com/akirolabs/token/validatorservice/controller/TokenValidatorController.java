package com.akirolabs.token.validatorservice.controller;

import com.akirolabs.token.validatorservice.model.GenericResponse;
import com.akirolabs.token.validatorservice.service.TokenValidatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static com.akirolabs.token.validatorservice.constant.AppConstants.GENERIC_LOG_METHOD_MESSAGE;

/**
 * @author: Sandeep Kumar Nagaraj
 * @since: 13-08-2023
 */
@RestController
@Slf4j
@RequestMapping("api/token/validate")
@CrossOrigin
public class TokenValidatorController {

    @Autowired
    private TokenValidatorService tokenValidatorService;

    @GetMapping("/{token}")
    public Mono<GenericResponse> validateToken(@PathVariable String token) {
        log.info(GENERIC_LOG_METHOD_MESSAGE, "validateToken", "Input value token ::" + token);
        return tokenValidatorService.verifyToken(token).map(response -> GenericResponse.builder()
                .response(response).build());
    }

}
