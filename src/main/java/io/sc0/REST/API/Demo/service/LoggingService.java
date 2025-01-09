package io.sc0.REST.API.Demo.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface LoggingService {
    void logBefore(String requestUrl, String requestHeaders, String requestBody, HttpServletRequest request, String reference);
    void logAfter(String requestUrl, String requestHeaders, String requestBody, String responseBody, int responseCode, HttpServletRequest request, HttpServletResponse response, String reference);
}

