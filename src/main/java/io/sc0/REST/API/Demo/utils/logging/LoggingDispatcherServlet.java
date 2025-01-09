//package io.sc0.REST.API.Demo.utils.logging;
//
//import io.sc0.REST.API.Demo.service.LoggingServiceImpl;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.web.servlet.DispatcherServlet;
//import org.springframework.web.servlet.HandlerExecutionChain;
//import org.springframework.web.util.ContentCachingRequestWrapper;
//import org.springframework.web.util.ContentCachingResponseWrapper;
//import org.springframework.web.util.WebUtils;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.time.LocalDateTime;
//
//public class LoggingDispatcherServlet extends DispatcherServlet {
//
//    private final LoggingServiceImpl loggingServiceImpl;
//
//    public LoggingDispatcherServlet(LoggingServiceImpl loggingServiceImpl) {
//        this.loggingServiceImpl = loggingServiceImpl;
//    }
//
//    @Override
//    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        if (!(request instanceof ContentCachingRequestWrapper)) {
//            request = new ContentCachingRequestWrapper(request);
//        }
//        if (!(response instanceof ContentCachingResponseWrapper)) {
//            response = new ContentCachingResponseWrapper(response);
//        }
//
//        HandlerExecutionChain handler = getHandler(request);
//
//        LocalDateTime inputTimestamp = LocalDateTime.now();
//
//        try {
//            super.doDispatch(request, response);
//        } finally {
//            LocalDateTime outputTimestamp = LocalDateTime.now();
//            long duration = java.time.Duration.between(inputTimestamp, outputTimestamp).toMillis();
//
//            logRequestAndResponse(request, response, handler, inputTimestamp, outputTimestamp, duration);
//            updateResponse(response);
//        }
//    }
//
//    private void logRequestAndResponse(HttpServletRequest request, HttpServletResponse response,
//                                       HandlerExecutionChain handler, LocalDateTime inputTimestamp,
//                                       LocalDateTime outputTimestamp, long duration) {
//        try {
//            // Extract request details
//            String httpMethod = request.getMethod();
//            String requestUrl = request.getRequestURI();
//            String requestHeaders = getRequestHeaders(request);
//            String requestBody = getRequestBody(request);
//
//            // Extract response details
//            int responseCode = response.getStatus();
//            String responseBody = getResponseBody(response);
//
//            // Handler details
//            String invokedMethodName = handler != null ? handler.toString() : "Unknown Handler";
//
//            // Client details
//            String clientIp = request.getRemoteAddr();
//
//            // Determine if the response is an error
//            String isError = responseCode >= 400 ? "Y" : "N";
//            String exceptionType = isError.equals("Y") ? "Error Response" : null;
//
//            // Log the data
//            loggingServiceImpl.logApiCall(
//                    httpMethod, requestUrl, requestHeaders, requestBody,
//                    responseCode, responseBody, clientIp,
//                    invokedMethodName, "LoggingDispatcherServlet",
//                    inputTimestamp, outputTimestamp, duration,
//                    isError, exceptionType
//            );
//        } catch (Exception e) {
//            System.err.println("Failed to log request/response: " + e.getMessage());
//        }
//    }
//
//    private String getRequestHeaders(HttpServletRequest request) {
//        StringBuilder headers = new StringBuilder();
//        request.getHeaderNames().asIterator().forEachRemaining(headerName ->
//                headers.append(headerName).append(": ").append(request.getHeader(headerName)).append("\n"));
//        return headers.toString();
//    }
//
//    private String getRequestBody(HttpServletRequest request) {
//        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
//        if (wrapper != null) {
//            byte[] buf = wrapper.getContentAsByteArray();
//            if (buf.length > 0) {
//                try {
//                    return new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
//                } catch (UnsupportedEncodingException e) {
//                    return "[Unsupported Encoding]";
//                }
//            }
//        }
//        return "[Empty Body]";
//    }
//
//    private String getResponseBody(HttpServletResponse response) {
//        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
//        if (wrapper != null) {
//            byte[] buf = wrapper.getContentAsByteArray();
//            if (buf.length > 0) {
//                try {
//                    return new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
//                } catch (UnsupportedEncodingException e) {
//                    return "[Unsupported Encoding]";
//                }
//            }
//        }
//        return "[Empty Body]";
//    }
//
//    private void updateResponse(HttpServletResponse response) throws IOException {
//        ContentCachingResponseWrapper responseWrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
//        if (responseWrapper != null) {
//            responseWrapper.copyBodyToResponse();
//        }
//    }
//}
