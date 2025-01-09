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
public class MdmApiLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "INPUT_TIMESTAMP", nullable = false)
    private LocalDateTime inputTimestamp;

    @Column(name = "OUTPUT_TIMESTAMP")
    private LocalDateTime outputTimestamp;

    @Column(name = "REFERENCE", nullable = false, length = 50)
    private String reference;

    @Column(name = "RECORD_TYPE", nullable = false, length = 1)
    private String recordType; // 'I' for incoming, 'O' for outgoing

    @Column(name = "CHANNEL_ID", length = 50)
    private String channelId;

    @Column(name = "CHANNEL_REFERENCE", length = 50)
    private String channelReference;

    @Column(name = "CHANNEL_COUNTRY", length = 10)
    private String channelCountry;

    @Column(name = "CHANNEL_LANGUAGE", length = 10)
    private String channelLanguage;

    @Column(name = "CHANNEL_TIMESTAMP")
    private LocalDateTime channelTimestamp;

    @Column(name = "REQUEST_HEADERS", length = 1000)
    private String requestHeaders;

    @Column(name = "REQUEST_BODY", length = 4000)
    private String requestBody;

    @Column(name = "REQUEST_URL", length = 1000)
    private String requestUrl;

    @Column(name = "RESPONSE_CODE", precision = 3)
    private Integer responseCode;

    @Lob
    @Column(name = "RESPONSE_BODY")
    private String responseBody;

    @Column(name = "CLIENT_DETAILS", length = 4000)
    private String clientDetails;

    @Column(name = "LOCAL_DETAILS", length = 1000)
    private String localDetails;
}
