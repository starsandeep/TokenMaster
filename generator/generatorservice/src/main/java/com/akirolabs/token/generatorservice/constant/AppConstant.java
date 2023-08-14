package com.akirolabs.token.generatorservice.constant;

/**
 * @author: Sandeep Kumar Nagaraj
 * @since: 12-08-2023
 */
public interface AppConstant {
    String EMPTY_DATA_ERROR_MESSAGE = "Require at least 1 digit to generate the token";
    String INVALID_DATA_ERROR_MESSAGE = "Invalid Request! Selected digit should range from 0 to 9";

    String GENERIC_LOG_METHOD_MESSAGE = "Method Name={},  message={}";
}
