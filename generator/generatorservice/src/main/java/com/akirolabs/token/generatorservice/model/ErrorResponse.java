package com.akirolabs.token.generatorservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * @author: Sandeep Kumar Nagaraj
 * @since: 12-08-2023
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {
  private String httpMethod;
  private String requestUri;
  private int statusCode;
  private String statusText;
  private String correlationIdentifier;
  private String errorTimestamp;
  private String errorMessage;
  private List<String> detailedErrors;
}
