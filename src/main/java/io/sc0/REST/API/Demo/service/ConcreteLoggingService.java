package io.sc0.REST.API.Demo.service;

import io.sc0.REST.API.Demo.entity.MdmApiLog;
import io.sc0.REST.API.Demo.repository.ApiLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

@Slf4j
@Service
public class ConcreteLoggingService implements LoggingService {

    @Autowired
    private ApiLogRepository apiLogRepository;

    @Override
    public void logBefore(String requestUrl, String requestHeaders, String requestBody, HttpServletRequest request, String reference) {
        MdmApiLog log = MdmApiLog.builder()
                .inputTimestamp(LocalDateTime.now())
                .reference(reference)
                .recordType("I")
                .requestHeaders(truncate(requestHeaders, 1000))
                .requestBody(truncate(requestBody, 4000))
                .requestUrl(requestUrl)
                .clientDetails(request.getRemoteAddr())
                .localDetails(getLocalAddress())
                .build();
        apiLogRepository.save(log);
    }

    @Override
    public void logAfter(String requestUrl, String requestHeaders, String requestBody, String responseBody, int responseCode, HttpServletRequest request, HttpServletResponse response, String reference) {
        MdmApiLog log = MdmApiLog.builder()
                .inputTimestamp(LocalDateTime.now())
                .outputTimestamp(LocalDateTime.now())
                .reference(reference)
                .recordType("O")
                .requestHeaders(truncate(requestHeaders, 1000))
                .requestBody(truncate(requestBody, 4000))
                .requestUrl(requestUrl)
                .responseCode(responseCode)
                .responseBody(responseBody)
                .clientDetails(request.getRemoteAddr())
                .localDetails(getLocalAddress())
                .build();
        apiLogRepository.save(log);
    }

    private String truncate(String value, int maxLength) {
        return value != null && value.length() > maxLength ? value.substring(0, maxLength) : value;
    }

    private String getLocalAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "Unknown";
        }
    }

    private LocalDateTime parseDateTime(String dateTime) {
        try {
            return dateTime != null ? LocalDateTime.parse(dateTime) : null;
        } catch (Exception e) {
            return null;
        }
    }
}
