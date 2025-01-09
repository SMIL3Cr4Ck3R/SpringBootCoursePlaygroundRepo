package io.sc0.REST.API.Demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "MDM_API_LOG")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "http_method", nullable = false)
    private String httpMethod;

    @Column(name = "request_url", nullable = false)
    private String requestUrl;

    @Column(name = "request_headers", columnDefinition = "CLOB")
    private String requestHeaders;

    @Column(name = "request_body", columnDefinition = "CLOB")
    private String requestBody;

    @Column(name = "response_code")
    private Integer responseCode;

    @Column(name = "response_body", columnDefinition = "CLOB")
    private String responseBody;

    @Column(name = "client_ip", nullable = false)
    private String clientIp;

    @Column(name = "invoked_method_name")
    private String invokedMethodName;

    @Column(name = "class_name")
    private String className;

    @Column(name = "input_timestamp", nullable = false)
    private LocalDateTime inputTimestamp;

    @Column(name = "output_timestamp")
    private LocalDateTime outputTimestamp;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "is_error", length = 1)
    private String isError; // Use 'Y' for true and 'N' for false

    @Column(name = "exception_type")
    private String exceptionType;
}