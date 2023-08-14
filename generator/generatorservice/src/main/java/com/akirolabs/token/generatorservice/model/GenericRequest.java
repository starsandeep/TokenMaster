package com.akirolabs.token.generatorservice.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author: Sandeep Kumar Nagaraj
 * @since: 12-08-2023
 */
@Data
@ToString
public class GenericRequest<T> {
    private List<T> data;
}
