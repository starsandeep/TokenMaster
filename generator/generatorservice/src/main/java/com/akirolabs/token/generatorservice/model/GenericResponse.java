package com.akirolabs.token.generatorservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Sandeep Kumar Nagaraj
 * @since: 12-08-2023
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse {
    private Object response;
}
