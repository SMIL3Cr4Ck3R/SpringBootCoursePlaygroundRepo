//package io.sc0.REST.API.Demo.service;
//
//import io.sc0.REST.API.Demo.entity.ApiLog;
//import io.sc0.REST.API.Demo.repository.ApiLogRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//public class LoggingServiceImpl {
//
//    @Autowired
//    private ApiLogRepository apiLogRepository;
//
//    public void logApiCall(String httpMethod, String requestUrl, String requestHeaders,
//                           String requestBody, Integer responseCode, String responseBody,
//                           String clientIp, String invokedMethodName, String className,
//                           LocalDateTime inputTimestamp, LocalDateTime outputTimestamp,
//                           Long duration, String isError, String exceptionType) {
//        ApiLog apiLog = ApiLog.builder()
//                .httpMethod(httpMethod)
//                .requestUrl(requestUrl)
//                .requestHeaders(requestHeaders)
//                .requestBody(requestBody)
//                .responseCode(responseCode)
//                .responseBody(responseBody)
//                .clientIp(clientIp)
//                .invokedMethodName(invokedMethodName)
//                .className(className)
//                .inputTimestamp(inputTimestamp)
//                .outputTimestamp(outputTimestamp)
//                .duration(duration)
//                .isError(isError)
//                .exceptionType(exceptionType)
//                .build();
//
//        apiLogRepository.save(apiLog);
//    }
//}
//
