package io.sc0.REST.API.Demo.utils.logging;


import io.sc0.REST.API.Demo.service.LoggingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CustomDispatcherServlet extends DispatcherServlet {

    private final LoggingService loggingService;

    public CustomDispatcherServlet(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Wrap the request and response to enable logging
        if (!(request instanceof ContentCachingRequestWrapper)) {
            request = new ContentCachingRequestWrapper(request);
        }
        if (!(response instanceof ContentCachingResponseWrapper)) {
            response = new ContentCachingResponseWrapper(response);
        }

        String reference = generateMdmUniqueReference();

        try {
            // Log the incoming request
            loggingService.logBefore(
                    request.getRequestURI(),
                    getRequestHeaders(request),
                    getRequestBody(request),
                    request,
                    reference
            );

            // Proceed with the dispatch
            super.doDispatch(request, response);
        } finally {
            // Log the outgoing response
            loggingService.logAfter(
                    request.getRequestURI(),
                    getRequestHeaders(request),
                    getRequestBody(request),
                    getResponseBody(response),
                    response.getStatus(),
                    request,
                    response,
                    reference
            );

            // Ensure the response body is written back to the client
            updateResponse(response);
        }
    }

    private String getRequestHeaders(HttpServletRequest request) {
        StringBuilder headers = new StringBuilder();
        request.getHeaderNames().asIterator().forEachRemaining(headerName ->
                headers.append(headerName).append(": ").append(request.getHeader(headerName)).append("; ")
        );
        return headers.toString();
    }

    private String getRequestBody(HttpServletRequest request) {
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                try {
                    return new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException ex) {
                    return "[Unknown]";
                }
            }
        }
        return "";
    }

    private String getResponseBody(HttpServletResponse response) {
        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                try {
                    return new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException ex) {
                    return "[Unknown]";
                }
            }
        }
        return "";
    }

    private void updateResponse(HttpServletResponse response) throws Exception {
        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (wrapper != null) {
            wrapper.copyBodyToResponse();
        }
    }

    public static String generateMdmUniqueReference() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.substring(0, 3);
        DateFormat timeStampFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timeStamp = timeStampFormat.format(new Date());
        return timeStamp + uuid;
    }
}
